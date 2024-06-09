package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.dto.request.CreateCategoryDto;
import shop.dto.request.UpdateCategoryDto;
import shop.entity.Category;
import shop.service.CategoryService;

@Controller
@RequestMapping()
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/manage-category")
	public String index(ModelMap model, @RequestParam(value="filter", defaultValue="", required=false) String name) {
		if(name.isBlank()||name==null) {
			model.addAttribute("categories",categoryService.getCategories());
		}
		else {
			model.addAttribute("categories",categoryService.getCategoriesByName(name));
			model.addAttribute("filter", name);
		}
		return "admin/category/index";
	}
	
	@RequestMapping("/manage-category-create")
	public String createCategory(ModelMap model) {
		model.addAttribute("category", new CreateCategoryDto());
		return "admin/category/create";
	}
	
	@RequestMapping(value = "/manage-category-create", method = RequestMethod.POST)
	public String createCategory(ModelMap model, @ModelAttribute("category") CreateCategoryDto createCategory, BindingResult errors) {
		if (createCategory.getName() == null || createCategory.getName().isBlank()) {
			errors.rejectValue("name", "category", "This field is required");
		}		
		
		if (errors.hasErrors()) {			
			return "admin/category/create";
		}
		
		try {						
			categoryService.createCategory(createCategory);
			return "redirect:/manage-category.htm";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "admin/category/create";
		}
	}
	
	@RequestMapping("/manage-category-update")
	public String updateCategory(ModelMap model, @RequestParam("categoryId") Integer categoryId) {
		Category category = categoryService.findById(categoryId);
		UpdateCategoryDto updateCategory = new UpdateCategoryDto(category.getId(), category.getName());
		model.addAttribute("category", updateCategory);
		return "admin/category/update";
	}
	
	@RequestMapping(value = "/manage-category-update", method = RequestMethod.POST)
	public String updateCategory(ModelMap model, @ModelAttribute("category") UpdateCategoryDto updateCategory, BindingResult errors) {
		if (updateCategory==null || updateCategory.getName().isBlank()) {
			updateCategory.setName(null);
			errors.rejectValue("name", "category", "This field is required");
		}
		if (errors.hasErrors()) {			
			return "admin/category/update";
		}
		
		try {						
			categoryService.updateCategory(updateCategory);
			return "redirect:/manage-category.htm";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());	
			return "admin/category/update";
		}		
	}
	
	@RequestMapping(value = "/manage-category-delete", method = RequestMethod.POST)
	public String deleteCategory(ModelMap model, @RequestParam("categoryId") Integer categoryId) {
		try {
			categoryService.deleteCategory(categoryId);
			return "redirect:/manage-category.htm";
		} catch (Exception e) {			
			model.addAttribute("message", e.getMessage());
			model.addAttribute("categories",categoryService.getCategories());
			return "admin/category/index";
		}		
	}
}
