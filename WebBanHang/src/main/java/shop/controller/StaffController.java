package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.dto.request.ChangePasswordDto;
import shop.service.StaffService;

@Controller
public class StaffController {
	@Autowired
	StaffService staffService;
	
	@RequestMapping("/change-password")
	public String changePassword(ModelMap model) {
		model.addAttribute("changePassword", new ChangePasswordDto());
		return "admin/changePassword";
	}
	
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public String changePassword(
		ModelMap model,
		@RequestParam("id") Integer staffId,
		@ModelAttribute("changePassword") ChangePasswordDto changePassword,
		BindingResult errors
	) {
		if (changePassword.getOldPassword() == null || changePassword.getOldPassword().isBlank()) {
			errors.rejectValue("oldPassword", "changePassword", "This field is required");
		}
		if (changePassword.getNewPassword() == null || changePassword.getNewPassword().isBlank()) {
			errors.rejectValue("newPassword", "changePassword", "This field is required");
		}
		if (changePassword.getConfirmPassword() == null || changePassword.getConfirmPassword().isBlank()) {
			errors.rejectValue("confirmPassword", "changePassword", "This field is required");
		} else if (!changePassword.getConfirmPassword().equals(changePassword.getNewPassword())) {
			errors.rejectValue("confirmPassword", "changePassword", "Confirm password does not match with new password");
		}
		
		if (errors.hasErrors()) {
			return "admin/changePassword";
		}
		
		try {
			staffService.changePassword(staffId, changePassword);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "admin/changePassword";
		}
		return "redirect:/admin.htm";
	}
}
