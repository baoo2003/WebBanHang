package shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.dto.request.ChangePasswordDto;
import shop.dto.request.ProfileDto;
import shop.entity.Customer;
import shop.entity.Notification;
import shop.service.CustomerService;
import shop.service.NotificationService;

@Controller
@RequestMapping()
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private NotificationService notificationService;

	@RequestMapping("/customer-profile")
	public String index(ModelMap model, HttpSession session) {
		Customer customer=customerService.getCustomerById((Integer) session.getAttribute("customerId"));	
		ProfileDto profileDto=new ProfileDto(customer);
		model.addAttribute("profileDto", profileDto);
		Integer customerIdInt = (Integer) session.getAttribute("customerId");
		if(customerIdInt != null) {
			List<Notification>  notifications = notificationService.getNotifications(customerIdInt);
			model.addAttribute("notifications", notifications);
			
		}
		return "Profile";
	}

	@RequestMapping(value = "/customer-profile", method = RequestMethod.POST)
	public String update(ModelMap model, HttpSession session, @ModelAttribute("profileDto") ProfileDto profileDto,
			BindingResult errors) {

		if (profileDto.getFirstName().isBlank()) {
			profileDto.setFirstName(null);
			errors.rejectValue("firstName", "profileDto", "This field is required");
		}
		if (profileDto.getLastName().isBlank()) {
			profileDto.setLastName(null);
			errors.rejectValue("lastName", "profileDto", "This field is required");
		}
		if (profileDto.getPhoneNumber().isBlank()) {
			profileDto.setPhoneNumber(null);
			errors.rejectValue("phoneNumber", "profileDto", "This field is required");
		}
		if (profileDto.getAddress().isBlank()) {
			profileDto.setAddress(null);
			errors.rejectValue("address", "profileDto", "This field is required");
		}
		if (profileDto.getEmail().isBlank()) {
			profileDto.setEmail(null);
		}
		if (errors.hasErrors()) {
			model.addAttribute("message", "Please correct the following errors!");
			return "Profile";
		} else {
			try {
				customerService.updateProfile(profileDto,(Integer) session.getAttribute("customerId"));
				model.addAttribute("message", "Update successfully!");
			} catch (Exception e) {
				model.addAttribute("message", e.getMessage());
			}
		}
		return "Profile";
	}
	
	@RequestMapping("/customer-change-password")
	public String changePassword(ModelMap model) {
		model.addAttribute("password", new ChangePasswordDto());
		return "changePassword";
	}
	
	@RequestMapping(value = "/customer-change-password", method = RequestMethod.POST)
	public String changePassword(
		ModelMap model,
		@RequestParam("id") Integer customerId,
		@ModelAttribute("password") ChangePasswordDto changePassword,
		BindingResult errors
	) {
		if (changePassword.getOldPassword() == null || changePassword.getOldPassword().isBlank()) {
			errors.rejectValue("oldPassword", "password", "This field is required");
		}
		if (changePassword.getNewPassword() == null || changePassword.getNewPassword().isBlank()) {
			errors.rejectValue("newPassword", "password", "This field is required");
		}
		if (changePassword.getConfirmPassword() == null || changePassword.getConfirmPassword().isBlank()) {
			errors.rejectValue("confirmPassword", "password", "This field is required");
		} else if (!changePassword.getConfirmPassword().equals(changePassword.getNewPassword())) {
			errors.rejectValue("confirmPassword", "password", "Confirm password does not match with new password");
		}
		
		if (errors.hasErrors()) {
			return "changePassword";
		}
		
		try {
			customerService.changePassword(customerId, changePassword);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "changePassword";
		}
		return "redirect:/home.htm";
	}
}
