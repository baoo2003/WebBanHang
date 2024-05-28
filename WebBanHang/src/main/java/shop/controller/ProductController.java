package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.entity.Category;
import shop.entity.Product;
import shop.service.ProductService;

@Controller
@RequestMapping()
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/product")
	public String index(ModelMap model, @RequestParam(value="categoryId", required=false) Integer categoryId, 
						@RequestParam(value="pageActive",defaultValue="1", required=false) Long pageNumber, 
						@RequestParam(value="startPage", defaultValue="1", required=false) Long startPage, 
						@RequestParam(value="endPage", required=false) Long endPage,
						@RequestParam(value="maxPage", required=false) Long maxPage, 
						@RequestParam(value="filterByPrice", defaultValue="0", required=false) Integer filterId) {
		Long numRecord=productService.countRecord(categoryId, filterId);
		if(pageNumber<startPage) pageNumber=startPage;
		List<Product> products = productService.getProducts(pageNumber, 9, categoryId, filterId);
		model.addAttribute("products", products);
		List<Category> categories = productService.getCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("categoryActive", categoryId);
		Long maxPage1=((numRecord)/9)+1;
		if (maxPage1<=startPage+5)endPage=maxPage1;
		else endPage=startPage+5;
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage1);
		model.addAttribute("pageActive", pageNumber);
		model.addAttribute("filterActive", filterId);
		return "Product";
	}

//    @RequestMapping("/product-{id}")
//    public String index2(ModelMap model, @PathVariable("id") String id) {
//    	if(id.equals("All")) return "Product";
//    	model.addAttribute("categoryActive", id);
//    	List<Product> products = productService.getProducts(0, 9);
//		model.addAttribute("products", products);
//		List<Category> categories = productService.getCategories();
//		model.addAttribute("categories", categories);
//		return "Product";
//    }
}
