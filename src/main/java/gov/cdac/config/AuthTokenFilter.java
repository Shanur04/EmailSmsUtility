package gov.cdac.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import gov.cdac.services.UserDetailsFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtService jwtService;

  @Autowired
  private UserDetailsFactory userDetailsFactory;

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
      try {
          String authHeader = request.getHeader("Authorization");
          String token = null;
          String username = null;

          if (authHeader != null && authHeader.startsWith("Bearer ")) {
              // Extract the token from the Authorization header
              token = authHeader.substring(7);

              // Extract username from the token
              username = jwtService.extractUsername(token);
          }

          // Check if the username is already authenticated in the context
          if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
              // Load user details based on the username
              UserDetails userDetails = userDetailsFactory.getUserDetails(username);

              // If token is valid, authenticate the user
              if (jwtService.validateToken(token, userDetails)) {
                  // Create authentication token
                  UsernamePasswordAuthenticationToken authenticationToken = 
                          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                  
                  // Set authentication details for the current request
                  authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                  
                  // Set authentication in the SecurityContext
                  SecurityContextHolder.getContext().setAuthentication(authenticationToken);
              }
          }

          // Proceed with the filter chain
          filterChain.doFilter(request, response);

      } catch (Exception e) {
          logger.error("Authentication error: ", e);
      }
  }

}