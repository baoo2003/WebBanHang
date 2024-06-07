package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/admin")
public class DashboardController {
	
	
	//a
	@RequestMapping
	public String index() {
		return "admin/dashboard";
	}
}
