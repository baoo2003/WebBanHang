package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	@RequestMapping()
	public String Checkout() {
		return "Checkout";
	}
}
