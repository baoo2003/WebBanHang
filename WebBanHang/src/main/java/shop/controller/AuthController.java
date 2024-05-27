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

import shop.dto.request.LoginDto;
import shop.dto.request.RegisterDto;
import shop.entity.Customer;
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
	public String login(ModelMap model) {
		model.addAttribute("login", new LoginDto());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
		@Validated @ModelAttribute("login") LoginDto loginDto,
		HttpSession session,
		BindingResult errors
	) {
		if (errors.hasErrors()) {
			return "login";
		}
		
		Integer customerId = authService.login(loginDto);
		
		if (customerId == null) {
			return "login";
		}
		session.setAttribute("customerId", customerId);
		return "redirect:/home.htm";
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
		
		String accountId = authService.register(registerDto);
		
		if (accountId == null) {
			return "register";
		}
		
		Customer customer = new Customer();
		customer.setFirstName(registerDto.getFirstName());
		customer.setLastName(registerDto.getLastName());
		customer.setGender(registerDto.getGender());
		customer.setAddress(registerDto.getAddress());
		customer.setPhoneNumber(registerDto.getPhoneNumber());
		customer.setEmail(registerDto.getEmail());
		
		customerService.addCustomer(accountId, customer);
		
		return "redirect:/login.htm";
	}
}
