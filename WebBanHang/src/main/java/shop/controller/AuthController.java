package shop.controller;

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
		@Valid @ModelAttribute("login") LoginDto loginDto,
		HttpSession session,
		BindingResult errors
	) {
		if (errors.hasErrors()) {
			return "login";
		}
		
		LoginResponse loginResponse = authService.login(loginDto);
		
		if (loginResponse.getUserId() == null) {
			return "login";
		}
		
		session.setAttribute("userId", loginResponse.getUserId());
		session.setAttribute("roleId", loginResponse.getRoleId());
		
		if (loginResponse.getRoleId().equalsIgnoreCase("KH")) {
			return "redirect:/product.htm";
		} else {
			return "redirect:/home.htm";
		}
	}
	
	@RequestMapping("/register")
	public String register(ModelMap model) {
		model.addAttribute("register", new RegisterDto());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
		@Validated @ModelAttribute("register") RegisterDto registerDto,
		HttpSession session,
		BindingResult errors
	) {
		if (errors.hasErrors()) {
			return "register";
		}
		
		try {
			authService.register(registerDto);
		} catch (Exception e) {
			return "register";
		}
		return "redirect:/login.htm";
	}
}
