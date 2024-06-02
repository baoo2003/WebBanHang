package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.entity.Product;
import shop.service.ProductService;

@Controller
@RequestMapping("/product-detail")
public class ProductDetailController {
	@Autowired
	ProductService productService;
	
	@RequestMapping()
	public String ShopDetail(ModelMap model, @RequestParam(value="productId", required=false) Integer productId) {
		Product product= productService.findById(productId);
		model.addAttribute("product",product);
		return "Product-detail";
	}
}
