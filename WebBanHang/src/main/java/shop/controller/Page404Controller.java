package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/404")
public class Page404Controller {
	@RequestMapping()
	public String Page404() {
		return "404";
	}
}
