package gov.cdac.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gov.cdac.config.JwtService;
import gov.cdac.icgPojo.SystemUserCredential;
import gov.cdac.models.JWTRequest;
import gov.cdac.models.JWTResponse;
import gov.cdac.services.LoginService;
import gov.cdac.utils.AESDecryption;
import gov.cdac.validator.InputValidator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * <p>Login page redirection </p>
 * @author shanurj
 */

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;

	private final Logger logger = LogManager.getLogger(LoginController.class);

	private static final String ERROR = "error";
	private static final String LOGIN = "login";
	private static final String LOGINREDIRECTION = "redirect:/emailSmsLogin";

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserDetailsService userDetailsService;
	/**
	 * <p>Redirect to Login page for candidates</p>
	 * @return ModelAndView
	 */
	@GetMapping(value = {"/"})
	public ModelAndView redirectToCandidateLogin(Model model) {
		if(!model.containsAttribute("error"))
		{
		return new ModelAndView(LOGINREDIRECTION);
		}
		ModelAndView mav= new ModelAndView(LOGINREDIRECTION);
		return mav;
	}

	/**
	 * <p>Redirect to Login page with failure handler for Admin use only</p>
	 * @param error
	 * @param request
	 * @return ModelAndView
	 */
	 // GET mapping for login page
    @GetMapping("/emailSmsLogin")
    public ModelAndView getLoginForAdmin(@RequestParam(value = "error", required = false) Boolean error,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("EmailSmsLoginPage");
        if (Boolean.TRUE.equals(error)) {
            mav.addObject("error", "Invalid Credentials!");
        }
        return mav;
    }

    // POST mapping for form login
    @PostMapping("/emailSmsLogin")
    public String authenticateUser(@RequestParam String username,
                                   @RequestParam String password,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            // Set the authentication in the context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwt = jwtService.generateJwtToken(authentication);

            System.out.println("authenticateUser : jwt : "+jwt);
            // Add JWT token to response header (Authorization)
            response.addHeader("Authorization", "Bearer " + jwt);

            // Add JWT token to cookie
            Cookie jwtCookie = new Cookie("jwt", jwt);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/"); // Make sure it's accessible across all URLs
            jwtCookie.setMaxAge(3600); // Set cookie expiration (1 hour, adjust as needed)
            response.addCookie(jwtCookie);

            // Redirect to the home page after successful login
            return "redirect:/emailOrSms";  // Redirect after JWT is set
        } catch (AuthenticationException e) {
            return "redirect:/emailSmsLogin?error=true";  // If login fails, return to login page
        }
    }

    @GetMapping("/emailOrSms")
    public ModelAndView emailHomePage(Authentication auth, HttpServletRequest request, HttpServletResponse response) {
        try {
            // Get JWT from cookie
            String jwt = null;
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("jwt".equals(cookie.getName())) {
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }

            // If no JWT in cookies, try the Authorization header
            if (jwt == null) {
                jwt = jwtService.getJwtFromRequestHeader(request);
            }

            System.out.println("jwt : "+jwt+"\njwtService.validateToken(jwt):"+jwtService.validateToken(jwt));
            // If JWT exists and is valid
            if (jwt != null && jwtService.validateToken(jwt)) {
                // Extract username from JWT
                String username = jwtService.extractUsername(jwt);

                // Load user details using username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Create an authentication token and set it to the SecurityContext
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Set authentication in SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Return the home page after successful authentication
                return new ModelAndView("MailOrSmsHome"); // Ensure this is the correct view name
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If JWT is invalid or not present, redirect to login page
        return new ModelAndView("redirect:/emailSmsLogin");
    }
	
	/**
	 * <p>Logout function
	 * @param request
	 * @param response
	 * @return String 
	 */
	@GetMapping(value = "/emailSmsLogout")
	public ModelAndView adminSignout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mav = new ModelAndView("redirect:/emailSmsLogin");
		if (auth != null){    
			SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
			logoutHandler.setClearAuthentication(true);
			logoutHandler.setInvalidateHttpSession(true);
			logoutHandler.logout(request, response, auth);
		}
		return mav;
	}
	
	/**
	 * <p>Logout function
	 * @param request
	 * @param response
	 * @return String 
	 */
	@GetMapping(value = "/emailRegister")
	public ModelAndView registerUser(Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mav = new ModelAndView("EmailRegistrationPage");
		if (!model.containsAttribute("sysUserObj"))
			mav.addObject("sysUserObj", new SystemUserCredential());
		return mav;
	}
	
	@PostMapping(value = "/registerNewAdmin")
	public ModelAndView registerNewSeatAllocator(@Valid @ModelAttribute SystemUserCredential sysUserObj, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,Authentication auth) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/emailRegister");
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.sysUserObj",
					bindingResult);
			redirectAttributes.addFlashAttribute("sysUserObj", sysUserObj);
			return mav;
		}
		
		String password = AESDecryption.decrypt(sysUserObj.getPassword()); 
		
		if(sysUserObj.getName().trim() == "" || sysUserObj.getName().trim() == null 
				|| sysUserObj.getEmail().trim() == "" || sysUserObj.getEmail().trim() == null
				|| password.trim() == "" || password.trim() == null) {
			redirectAttributes.addFlashAttribute("error", "Fields cannot be left empty");
			redirectAttributes.addFlashAttribute("sysUserObj", sysUserObj);
			return mav;
		}
			
		if(!InputValidator.isValidEmail2(sysUserObj.getEmail())) {
			redirectAttributes.addFlashAttribute("error", "Enter valid Email Id");
			redirectAttributes.addFlashAttribute("sysUserObj", sysUserObj);
			return mav;
		}
		
		if(!InputValidator.isValidName(sysUserObj.getName())) {
			redirectAttributes.addFlashAttribute("error", "Enter valid Name");
			redirectAttributes.addFlashAttribute("sysUserObj", sysUserObj);
			return mav;
		}
		
		if(!InputValidator.isValidField(sysUserObj.getName()) || !InputValidator.isValidField(sysUserObj.getEmail()) || !InputValidator.isValidField(password)) {
			redirectAttributes.addFlashAttribute("error", "Invalid Format");
			redirectAttributes.addFlashAttribute("sysUserObj", sysUserObj);
			return mav;
		}
		
		List<SystemUserCredential> sysUserList = loginService.findAllSysUsers();
		
		if(!sysUserList.isEmpty()) {
			for (SystemUserCredential systemUserCredential : sysUserList) {
				System.out.println(systemUserCredential.getEmail());
				System.out.println(sysUserObj.getEmail());
				if(systemUserCredential.getEmail().equalsIgnoreCase(sysUserObj.getEmail())) {
					redirectAttributes.addFlashAttribute("error", "Email Id should be unique");
					redirectAttributes.addFlashAttribute("success", null);
					redirectAttributes.addFlashAttribute("sysUserObj", new SystemUserCredential());
					mav.addObject("error", "Email Id should be unique");
					mav.addObject("sysUserObj", new SystemUserCredential());
					return mav;
				}
			}
		} else {
			mav.addObject("sysUserObj", new SystemUserCredential());
		}
		
		sysUserObj.setPassword(password);
				
		Boolean saveSysUserCred = loginService.saveSysUserCred(sysUserObj);
		if(saveSysUserCred) {
			redirectAttributes.addFlashAttribute("success", "Registration Complete");
			mav.addObject("success", "Registration Complete");
			mav.addObject("sysUserObj", new SystemUserCredential());
		} else {
			redirectAttributes.addFlashAttribute("error", "Some error occurred!, Please try again");
			mav.addObject("error", "Some error occurred!, Please try again");
			mav.addObject("sysUserObj", sysUserObj);
		}	
		
		return mav;
	}
	
}
