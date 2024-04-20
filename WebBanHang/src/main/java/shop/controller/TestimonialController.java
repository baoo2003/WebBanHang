package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testimonial")
public class TestimonialController {
	@RequestMapping()
	public String Testimonial() {
		return "Testimonial";
	}
}

