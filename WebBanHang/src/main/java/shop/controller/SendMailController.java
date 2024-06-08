package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.entity.Product;
import shop.service.ProductService;
import shop.service.SendMailService;

@Controller
public class SendMailController {
	
	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(
			 @RequestParam("emailReceiver") String emailReceiver
			) {
			
			
			try {
				String subject = "Information products of Green Valley";
				String body = "Top 5 best selling products of Green Valley \n";
				List<Product> products = productService.get5Product();
				int i = 1;
				for(Product p : products) {
					
					body = body + i + ". " + p.getName() + " - Price: $" + p.getPrice() + "\n";
					i++;
				} 
				sendMailService.sendMail(emailReceiver, subject, body);
				
			}
			catch(Exception e) {
				e.printStackTrace();
	            throw e;
			}			
	

		return "redirect:/home.htm";
	}
}
