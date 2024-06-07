package shop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.dto.request.CreateProductDto;
import shop.dto.request.FilterProductDto;
import shop.entity.Brand;
import shop.entity.Category;
import shop.entity.Product;
import shop.service.BrandService;
import shop.service.CategoryService;
import shop.service.ImageService;
import shop.service.ProductService;

@Controller
@RequestMapping()
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	ImageService imageService;
	
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
		List<Category> categories = categoryService.getCategories();
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
		@RequestParam(value = "page", defaultValue = "1") Long page
	) {
		FilterProductDto filter = new FilterProductDto();
		
		final int limit = 8;
		
		Long numOfProducts = productService.countRecord(filter.getCategoryId(), filter.getPriceGroupId(), filter.getKeyword());
		int numOfPages = (int) Math.ceil((double) numOfProducts / limit);
		model.addAttribute("numOfPages", numOfPages);
		
		model.addAttribute("filter", filter);
		
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
		return categoryService.getCategories();
	}
	
	@ModelAttribute("brands")
	public List<Brand> getBrands() {
		List<Brand> brands = brandService.getBrands()
				.stream()
				.sorted((brand1, brand2) -> {
					if ("No brand".equals(brand1.getName())) {
	                    return -1;
	                } else if ("No brand".equals(brand2.getName())) {
	                    return 1;
	                } else {
	                    return 0;
	                }
				})
				.collect(Collectors.toList());
		return brands;
	}
	
	@RequestMapping("/create-product")
	public String createProduct(ModelMap model) {
		model.addAttribute("product", new CreateProductDto());
		return "admin/product/create";
	}
	
	@RequestMapping(value = "/create-product", method = RequestMethod.POST)
	public String createProduct(
		ModelMap model,
		@ModelAttribute("product") CreateProductDto createProduct,
		BindingResult errors
	) {
		if (createProduct.getName() == null || createProduct.getName().isBlank()) {
			errors.rejectValue("name", "createProduct", "This field is required");
		}
		if (createProduct.getDescription() == null || createProduct.getDescription().isBlank()) {
			errors.rejectValue("description", "createProduct", "This field is required");
		}
		if (createProduct.getOrigin() == null || createProduct.getOrigin().isBlank()) {
			errors.rejectValue("origin", "createProduct", "This field is required");
		}
		if (createProduct.getImage() == null) {
			errors.rejectValue("image", "createProduct", "This field is required");
		}
		if (createProduct.getUnit() == null || createProduct.getUnit().isBlank()) {
			errors.rejectValue("unit", "createProduct", "This field is required");
		}
		if (createProduct.getQuantity() == null || createProduct.getQuantity().toString().isBlank()) {
			errors.rejectValue("quantity", "createProduct", "This field is required");
		} else if (createProduct.getQuantity() == 0) {
			errors.rejectValue("quantity", "createProduct", "This field must be greater than 0");
		}
		if (createProduct.getPrice() == null || createProduct.getPrice().toString().isBlank()) {
			errors.rejectValue("price", "createProduct", "This field is required");
		} else if (createProduct.getPrice() == 0.0) {
			errors.rejectValue("price", "createProduct", "This field must be greater than 0.0");
		}
		if (createProduct.getDiscount() == null || createProduct.getDiscount().toString().isBlank()) {
			errors.rejectValue("discount", "createProduct", "This field is required");
		}
		
		if (errors.hasErrors()) {
			return "admin/product/create";
		}
		
		try {
			Integer brandId = createProduct.getBrandId();
			Brand brand;
			brand = brandId != null
					? brandService.findById(createProduct.getBrandId())
					: null;
			
			Category category = categoryService.findById(createProduct.getCategoryId());

			String imagePath = imageService.upload(createProduct.getImage());
			
			productService.addProduct(createProduct, brand, category, imagePath);
			return "redirect:/manage-product.htm?page=1";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "admin/product/create";
		}
	}
}
