package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shop.dto.request.ContactDto;
import shop.service.SendMailService;
import shop.utils.Const;

@Controller
public class ContactController {
	@Autowired
	SendMailService mailService;
	
	@RequestMapping("/contact")
	public String contact(ModelMap model) {
		model.addAttribute("contact", new ContactDto());
		return "Contact";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String contact(
		ModelMap model,
		@ModelAttribute("contact") ContactDto contact,
		BindingResult errors,
		RedirectAttributes redirect
	) {
		if (contact.getName() == null || contact.getName().isBlank()) {
			errors.rejectValue("name", "contact", "This field is required");
		}
		if (contact.getEmail() == null || contact.getEmail().isBlank()) {
			errors.rejectValue("email", "contact", "This field is required");
		} else if (!contact.getEmail().contains("@")) {
			errors.rejectValue("email", "contact", "Invalid email");
		}
		if (contact.getMessage() == null || contact.getMessage().isBlank()) {
			errors.rejectValue("message", "contact", "This field is required");
		}
		
		if (errors.hasErrors()) {
			return "Contact";
		}
		
		String subject = "Green Valley: Message from " + contact.getName();
		
		try {
			mailService.sendMail(contact.getEmail(), Const.EMAIL_ADMIN, subject, contact.getMessage());
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "Contact";
		}
		redirect.addFlashAttribute("message", "Send successfully!");
		return "redirect:/contact.htm";
	}
}
