package shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.entity.Account;
import shop.service.AuthService;

@Controller
@RequestMapping
public class AuthController {
	@Autowired
	AuthService authService;
	
	@RequestMapping("/login")
	public String login(ModelMap model) {
		model.addAttribute("account", new Account());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("account") Account account, HttpSession session) {
		Integer customerId = authService.login(account.getUsername(), account.getPassword());
		
		if (customerId == null) {
			return "login";
		}
		session.setAttribute("customerId", customerId);
		return "redirect:/home.htm";
	}
}
