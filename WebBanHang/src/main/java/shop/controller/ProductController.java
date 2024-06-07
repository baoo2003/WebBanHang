package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.dto.request.FilterProductDto;
import shop.entity.Category;
import shop.entity.Product;
import shop.service.ProductService;

@Controller
@RequestMapping()
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/home")
	public String index(ModelMap model, @RequestParam(value="categoryId", required=false) Integer categoryId, 
						@RequestParam(value="pageActive",defaultValue="1", required=false) Long pageNumber, 
						@RequestParam(value="startPage", defaultValue="1", required=false) Long startPage, 					
						@RequestParam(value="filterByPrice", defaultValue="0", required=false) Integer filterId, 
						@RequestParam(value="keyWord", defaultValue="", required=false) String keyWord) {
		Long endPage;
		Long numRecord=productService.countRecord(categoryId, filterId, keyWord);
		if(pageNumber<startPage) pageNumber=startPage;
		List<Product> products = productService.getProducts(pageNumber, 9, categoryId, filterId, keyWord);
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
		if (keyWord != null) {
			model.addAttribute("keyWord", keyWord);
		}		
		return "Product";
	}
	
	@RequestMapping("/manage-product")
	public String manageProduct(
		ModelMap model,
		@RequestParam(value = "page", defaultValue = "1") Long page,
		@ModelAttribute("filter") FilterProductDto filter
	) {
		final int limit = 8;
		
		Long numOfProducts = productService.countRecord(filter.getCategoryId(), filter.getPriceGroupId(), filter.getKeyword());
		int numOfPages = (int) Math.ceil((double) numOfProducts / limit);
		model.addAttribute("numOfPages", numOfPages);
		
		model.addAttribute("filter", new FilterProductDto());
		
		List<Product> products = productService.getProducts(page, limit, filter.getCategoryId(), filter.getPriceGroupId(), filter.getKeyword());
		model.addAttribute("products", products);
		return "admin/product/index";
	}
	
	@RequestMapping(value = "/manage-product", method = RequestMethod.POST)
	public String mangeProduct(
		ModelMap model,
		@RequestParam(value = "page", defaultValue = "1") Long page,
		@ModelAttribute("filter") FilterProductDto filter
	) {
		final int limit = 8;
		
		List<Product> products = productService.getProducts(page, limit, filter.getCategoryId(), filter.getPriceGroupId(), filter.getKeyword());
		model.addAttribute("products", products);
		
		return "admin/product/index";
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return productService.getCategories();
	}
}
