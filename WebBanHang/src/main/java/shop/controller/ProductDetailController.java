package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product-detail")
public class ProductDetailController {
	@RequestMapping()
	public String ShopDetail() {
		return "Product-detail";
	}
}
