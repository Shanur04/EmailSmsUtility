package gov.cdac.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtService {

	// Generate a secure, strong key for HS512 signing (512-bit / 64 bytes)
	private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Automatically generates a
																						// 512-bit key

	@Value("${security.jwt.expiration-time}")
	private String expTime;

	// Extract expiration date from the JWT
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	// Extract a specific claim from the JWT token
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// Extract all claims from the JWT token
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey) // Use the generated secret key
				.build().parseClaimsJws(token).getBody();
	}

	// Check if the token is expired
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	// Extract username from the JWT token
	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey) // Use the same secret key for validation
				.build().parseClaimsJws(token) // Parse the JWT and automatically validate it
				.getBody().getSubject();
	}

	// Validate the token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// Generate JWT token based on the authentication object
	public String generateJwtToken(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		// Create JWT claims
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", username);
		claims.put("iat", System.currentTimeMillis());
		claims.put("exp", System.currentTimeMillis() + Long.parseLong(expTime)); // Convert expTime to long
		claims.put("roles", roles);

		return Jwts.builder().setClaims(claims).signWith(secretKey) // Use the securely generated secret key for signing
				.compact();
	}

	// Create token with custom claims and expiration
	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token expires in 30 minutes
				.signWith(secretKey, SignatureAlgorithm.HS512) // Use the secret key with HS512 algorithm
				.compact();
	}

	// Get the signing key for token validation
	private Key getSignKey() {
		// No need to decode the SecretKey; it is already in the correct format
		return secretKey;
	}

	public String getJwtFromRequestHeader(HttpServletRequest request) {
		// Get the "Authorization" header
		String header = request.getHeader("Authorization");

		// Check if the header exists and starts with "Bearer "
		if (header != null && header.startsWith("Bearer ")) {
			// Return the JWT token by removing the "Bearer " prefix
			return header.substring(7); // Skip the first 7 characters ("Bearer ")
		}
		return null; // Return null if the header doesn't exist or isn't in the expected format
	}

	public Boolean validateToken(String token) {
		try {
			System.out.println("hiiiiii");
			// Extract username from the token
			String username = extractUsername(token);
			System.out.println("username : " + username);
			// Check if the token is expired
			if (isTokenExpired(token)) {
				return false; // Token is expired
			}

			// Token is valid if we didn't hit the expiration check and username matches
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false; // If any error occurs (like parsing error), return false
		}
	}
}
