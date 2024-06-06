package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/admin")
public class DashboardController {
	@RequestMapping
	public String index() {
		return "admin/dashboard";
	}
}
