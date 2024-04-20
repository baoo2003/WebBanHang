package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shop-detail")
public class ShopDetailController {
	@RequestMapping()
	public String ShopDetail() {
		return "Shop-detail";
	}
}
