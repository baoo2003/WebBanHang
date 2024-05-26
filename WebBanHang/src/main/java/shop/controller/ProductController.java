package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.entity.Category;
import shop.entity.Product;
import shop.service.ProductService;

@Controller
@RequestMapping()
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/product")
	public String index(ModelMap model) {
		List<Product> products = productService.getProducts(0, 9);
		model.addAttribute("products", products);
		List<Category> categories = productService.getCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("categoryActive", null);
		return "Product";
	}

    @RequestMapping("/product-{id}")
    public String index2(ModelMap model, @PathVariable("id") String id) {
    	if(id.equals("All")) return "redirect:/product.htm";
    	model.addAttribute("categoryActive", id);
    	List<Product> products = productService.getProducts(0, 9);
		model.addAttribute("products", products);
		List<Category> categories = productService.getCategories();
		model.addAttribute("categories", categories);
        return "Product";
    }
}
