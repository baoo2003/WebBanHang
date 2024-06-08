package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.dto.request.CreateBrandDto;
import shop.dto.request.UpdateBrandDto;
import shop.entity.Brand;
import shop.service.BrandService;

@Controller
@RequestMapping()
public class BrandController {
	@Autowired
	BrandService brandService;
	
	
	@RequestMapping("/manage-brand")
	public String index(ModelMap model) {
		model.addAttribute("brands",brandService.getBrands());
		return "admin/brand/index";
	}
	
	@RequestMapping("/manage-brand-create")
	public String createBrand(ModelMap model) {
		model.addAttribute("brand", new CreateBrandDto());
		return "admin/brand/create";
	}
	
	@RequestMapping(value = "/manage-brand-create", method = RequestMethod.POST)
	public String createBrand(ModelMap model, @ModelAttribute("brand") CreateBrandDto createBrand, BindingResult errors) {
		if (createBrand.getName() == null || createBrand.getName().isBlank()) {
			errors.rejectValue("name", "brand", "This field is required");
		}		
		
		if (errors.hasErrors()) {			
			return "admin/brand/create";
		}
		
		try {						
			brandService.createBrand(createBrand);
			return "redirect:/manage-brand.htm";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "admin/brand/create";
		}
	}
	
	@RequestMapping("/manage-brand-update")
	public String manageBrandUpdate(ModelMap model, @RequestParam("brandId") Integer brandId) {
		Brand brand = brandService.findById(brandId);
		UpdateBrandDto updateBrand = new UpdateBrandDto(brand.getId(), brand.getName());
		model.addAttribute("brand", updateBrand);
		return "admin/brand/update";
	}
	
	@RequestMapping(value = "/manage-brand-update", method = RequestMethod.POST)
	public String updateBrand(ModelMap model, @ModelAttribute("brand") UpdateBrandDto updateBrand, BindingResult errors) {
		if (updateBrand==null || updateBrand.getName().isBlank()) {
			updateBrand.setName(null);
			errors.rejectValue("name", "brand", "This field is required");
		}
		if (errors.hasErrors()) {			
			return "admin/brand/update";
		}
		
		try {						
			brandService.updateBrand(updateBrand);
			return "redirect:/manage-brand.htm";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());	
			return "admin/brand/update";
		}		
	}
	
	@RequestMapping(value = "/manage-brand-delete", method = RequestMethod.POST)
	public String deleteBrand(ModelMap model, @RequestParam("brandId") Integer brandId) {
		try {
			brandService.deleteBrand(brandId);
			return "redirect:/manage-brand.htm";
		} catch (Exception e) {			
			model.addAttribute("message", e.getMessage());
			model.addAttribute("brands",brandService.getBrands());
			return "admin/brand/index";
		}		
	}
}
