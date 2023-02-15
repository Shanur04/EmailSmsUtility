package gov.cdac.emailservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gov.cdac.emailservice.icg.pojo.SystemUserCredential;
import gov.cdac.emailservice.services.LoginService;
import gov.cdac.emailservice.utils.AESDecryption;
import gov.cdac.emailservice.validator.InputValidator;

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
	private static final String LOGINREDIRECTION = "redirect:/emailLogin";

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
	@GetMapping(value = {"/emailLogin"})
	public ModelAndView getLoginForAdmin(@RequestParam(value = "error", required = false) boolean error,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("EmailLoginPage");
		if (error) {
			mav.addObject(ERROR,"Invalid Credentials!");	
		}else {
			if (request.getSession().getAttribute("emailId") != null) {
				//mav.setViewName("redirect:/icg");
			}else {	
				//mav.setViewName("redirect:/icg");
			}
		}
		return mav;
	}

	/**
	 * <p>Logout function
	 * @param request
	 * @param response
	 * @return String 
	 */
	@GetMapping(value = "/emailLogout")
	public ModelAndView adminSignout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mav = new ModelAndView("redirect:/emailLogin");
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
