package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.service.SendMailService;

@Controller
public class SendMailController {
	
	@Autowired
	private SendMailService sendMailService;
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(
			 @RequestParam("emailReceiver") String emailReceiver
			
			) {
		
			try {
				String subject = "Information products of Green Valley";
				String body = "";
				sendMailService.sendMail(emailReceiver, subject, body);
			}
			catch(Exception e) {
				
			}			
	

		return "redirect:/home.htm";
	}
}
