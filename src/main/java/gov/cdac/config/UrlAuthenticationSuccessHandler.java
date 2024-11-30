package gov.cdac.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.filters.RemoteIpFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private final JwtService jwtService; // Assuming you have a service for generating JWTs

    public UrlAuthenticationSuccessHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // Generate JWT token
        String jwtToken = jwtService.generateJwtToken(authentication);

        // Add the JWT token to a cookie
        addJwtToCookie(response, jwtToken);

        // Determine the target URL after successful authentication
        String targetUrl = determineTargetUrl(authentication);

        // Redirect the user to the target URL
        response.sendRedirect(targetUrl);
    }

    // Method to add JWT token to the response cookies
    private void addJwtToCookie(HttpServletResponse response, String jwtToken) {
        Cookie jwtCookie = new Cookie("jwt", jwtToken);
        jwtCookie.setHttpOnly(true); // Make it inaccessible via JavaScript
        jwtCookie.setSecure(true); // Ensure the cookie is sent only over HTTPS
        jwtCookie.setPath("/"); // Available across all paths in your application
        jwtCookie.setMaxAge(3600); // Cookie expiration (1 hour)
        response.addCookie(jwtCookie);
    }

    // Determine the target URL for redirect after login
    protected String determineTargetUrl(final Authentication authentication) {

        return "/emailOrSms"; // Redirect to a default page if no preference found
    }

}