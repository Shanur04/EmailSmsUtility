package gov.cdac.config;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		// save message in session
		if (exception.getMessage().equals("Bad credentials")) {
			session.setAttribute("message", "Invalid Credentials");
		} else if (exception.getMessage().equals("Invalid Email/Password!!!")
				|| exception.getMessage().equals("EmailId cannot be blank.")) {
			session.setAttribute("message", exception.getMessage());
		} else {
			session.setAttribute("message", "Something went wrong during sign in");
		}
		redirectStrategy.sendRedirect(request, response, "/emailSmsLogin");
	}

}
