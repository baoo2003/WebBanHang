package shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.dto.request.ProfileDto;
import shop.entity.Customer;
import shop.service.CustomerService;

@Controller
@RequestMapping()
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@RequestMapping("/customer-profile")
	public String index(ModelMap model, HttpSession session) {
		Customer customer = customerService.getCustomerById((Integer) session.getAttribute("userId"));
		ProfileDto profileDto = new ProfileDto(customer);
		model.addAttribute("profileDto", profileDto);
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
		if (profileDto.getPassword().isBlank()) {
			profileDto.setPassword(null);
			errors.rejectValue("password", "profileDto", "This field is required");
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
				customerService.updateProfile(profileDto, (Integer) session.getAttribute("userId"));
				model.addAttribute("message", "Update successfully!");
			} catch (Exception e) {
				model.addAttribute("message", e.getMessage());
			}
		}
		return "Profile";
	}

}
