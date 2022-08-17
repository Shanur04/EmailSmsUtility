package gov.cdac.emailservice.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import gov.cdac.emailservice.utils.AESDecryption;

public class CaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final List<String> URLS_OF_CAPTCHA = Arrays
	    .asList(new String[] { "/emailLogin"});

    public CaptchaAuthenticationFilter(String defaultFilterProcessesUrl) {
	super(defaultFilterProcessesUrl);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse res = (HttpServletResponse) response;

	try {
	    if (URLS_OF_CAPTCHA.contains(req.getServletPath()) && "POST".equalsIgnoreCase(req.getMethod())) {
		String expect = req.getSession().getAttribute("CAPTCHA_KEY").toString();
		req.getSession().removeAttribute("CAPTCHA_KEY");
		if (req.getParameter("Ctext") == null) {
		    unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("Invalid Captcha."));
		    return;
		}
		if (expect != null && !expect.equals(AESDecryption.decrypt(req.getParameter("Ctext")))) {
		    unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("Invalid Captcha."));
		    return;
		}
	    }

	} catch (NullPointerException ignore) {
	} catch (Exception ignore) {
	    ignore.printStackTrace();
	    unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("Invalid Captcha."));
	}
	chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest arg0, HttpServletResponse arg1)
	    throws IOException, ServletException {
	throw new BadCredentialsException("Invalid Captcha");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	    AuthenticationException failed) throws IOException, ServletException {
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	HttpSession session = request.getSession(false);

	// save message in session
	session.setAttribute("message", failed.getMessage());
	redirectStrategy.sendRedirect(request, response, request.getServletPath() + "?error=true");
    }

}
