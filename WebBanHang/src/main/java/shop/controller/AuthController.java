package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import shop.dto.request.LoginDto;
import shop.dto.request.RegisterDto;
import shop.dto.response.LoginResponse;
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
		@Valid @ModelAttribute("login") LoginDto loginDto,
		HttpSession session,
		BindingResult errors
	) {
		boolean isValid = true;
		if (loginDto.getUsername().isBlank()) {
			errors.rejectValue("username", "login", "This field is required");
			isValid = false;
		}
		if (loginDto.getPassword().isBlank()) {
			errors.rejectValue("password", "login", "This field is required");
			isValid = false;
		}
		
		if (!isValid) {
			return "login";
		}
		
		LoginResponse loginResponse = new LoginResponse();
		try {
			loginResponse = authService.login(loginDto);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "login";
		}
		
		if (loginResponse.getUserId() == null) {
			return "login";
		}
		
		session.setAttribute("userId", loginResponse.getUserId());
		session.setAttribute("roleId", loginResponse.getRoleId());
		
		if (loginResponse.getRoleId().equalsIgnoreCase("KH")) {
			return "redirect:/home.htm";
		} else {
			// TODO: redirect to admin page
			return "redirect:/404.htm";
		}
	}
	
	@RequestMapping("/register")
	public String register(ModelMap model) {
		model.addAttribute("register", new RegisterDto());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
		ModelMap model,
		@Validated @ModelAttribute("register") RegisterDto registerDto,
		HttpSession session,
		BindingResult errors
	) {
		boolean isValid = true;
		if (registerDto.getUsername().isBlank()) {
			errors.rejectValue("username", "login", "This field is required");
			isValid = false;
		}
		if (registerDto.getFirstName().isBlank()) {
			errors.rejectValue("username", "login", "This field is required");
			isValid = false;
		}
		if (registerDto.getLastName().isBlank()) {
			errors.rejectValue("username", "login", "This field is required");
			isValid = false;
		}
		if (registerDto.getPhoneNumber().isBlank()) {
			errors.rejectValue("username", "login", "This field is required");
			isValid = false;
		}
		if (registerDto.getPassword().isBlank()) {
			errors.rejectValue("password", "login", "This field is required");
			isValid = false;
		}
		
		if (!isValid) {
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
		session.removeAttribute("userId");
		session.removeAttribute("roleId");

		return "redirect:/home.htm";
	}
}
