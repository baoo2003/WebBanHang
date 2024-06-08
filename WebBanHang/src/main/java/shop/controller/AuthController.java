package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.dto.request.LoginDto;
import shop.dto.request.RegisterDto;
import shop.service.AuthService;
import shop.service.CustomerService;

@Controller
@RequestMapping
public class AuthController {
	@Autowired
	AuthService authService;
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/login")
	public String login(ModelMap model, HttpSession session) {
		String loginMessage = (String) session.getAttribute("loginMessage");
		if (loginMessage != null) {
			model.addAttribute("message", loginMessage);
			session.removeAttribute("loginMessage");
		}
		
		model.addAttribute("login", new LoginDto());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
		ModelMap model,
		@ModelAttribute("login") LoginDto loginDto,
		HttpSession session,
		BindingResult errors
	) {		
		if (loginDto.getUsername().isBlank()) {
			loginDto.setUsername(null);
			errors.rejectValue("username", "login", "This field is required");			
		}
		if (loginDto.getPassword().isBlank()) {
			loginDto.setPassword(null);
			errors.rejectValue("password", "login", "This field is required");			
		}
		
		if (errors.hasErrors()) {
			return "login";
		}
		
		Integer customerId;
		try {
			customerId = authService.loginForCustomer(loginDto);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "login";
		}
		
		session.setAttribute("customerId", customerId);
		return "redirect:/home.htm";
	}
	
	@RequestMapping("/admin-login")
	public String adminLogin(ModelMap model) {
		model.addAttribute("login", new LoginDto());
		return "admin/login";
	}
	
	@RequestMapping(value = "/admin-login", method = RequestMethod.POST)
	public String adminLogin(
		ModelMap model,
		HttpSession session,
		@ModelAttribute("login") LoginDto loginDto,
		BindingResult errors
	) {
		if (loginDto.getUsername().isBlank()) {
			loginDto.setUsername(null);
			errors.rejectValue("username", "login", "This field is required");			
		}
		if (loginDto.getPassword().isBlank()) {
			loginDto.setPassword(null);
			errors.rejectValue("password", "login", "This field is required");			
		}
		if (errors.hasErrors()) {
			return "admin/login";
		}
		
		Integer staffId;
		try {
			staffId = authService.loginForAdmin(loginDto);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "admin/login";
		}
		
		session.setAttribute("staffId", staffId);
		return "redirect:/admin.htm";
	}
	
	@RequestMapping("/register")
	public String register(ModelMap model) {
		model.addAttribute("register", new RegisterDto());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
		ModelMap model,
		@ModelAttribute("register") RegisterDto registerDto,
		HttpSession session,
		BindingResult errors
	) {		
		if (registerDto.getUsername().isBlank()) {
			registerDto.setUsername(null);
			errors.rejectValue("username", "register", "This field is required");			
		}
		if (registerDto.getFirstName().isBlank()) {
			registerDto.setFirstName(null);
			errors.rejectValue("firstName", "register", "This field is required");			
		}
		if (registerDto.getLastName().isBlank()) {
			registerDto.setLastName(null);
			errors.rejectValue("lastName", "register", "This field is required");			
		}
		if (registerDto.getPhoneNumber().isBlank()) {
			registerDto.setPhoneNumber(null);
			errors.rejectValue("phoneNumber", "register", "This field is required");			
		}
		if (registerDto.getPassword().isBlank()) {
			registerDto.setPassword(null);
			errors.rejectValue("password", "register", "This field is required");			
		}
		if (registerDto.getAddress().isBlank()) {
			registerDto.setAddress(null);
			errors.rejectValue("address", "register", "This field is required");			
		}
		
		if (errors.hasErrors()) {
			model.addAttribute("message", "Please correct the following errors!");
			return "register";
		}
		
		if (registerDto.getEmail().isBlank()) {
			registerDto.setEmail(null);
		}
		
		try {
			authService.register(registerDto);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "register";
		}
		return "redirect:/login.htm";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session) {
		session.removeAttribute("customerId");
		
		return "redirect:/home.htm";
	}
	
	@RequestMapping(value = "/admin-logout", method = RequestMethod.POST)
	public String logoutForAdmin(HttpSession session) {
		session.removeAttribute("staffId");
		
		return "redirect:/admin-login.htm";
	}
}
