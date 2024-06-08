package shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.dto.request.OrderDto;
import shop.dto.request.ProfileDto;
import shop.service.CartService;
import shop.service.OrderService;
import shop.service.CustomerService;
import shop.entity.Customer;

@Controller
public class CheckoutController {
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/checkout")
	public String index(ModelMap model, HttpSession session) {
		Integer customerIdInt = (Integer) session.getAttribute("userId");
		
		if (customerIdInt == null) {
			return "redirect:/login.htm";
		}

		Customer customer= customerService.getCustomerById((Integer) session.getAttribute("userId"));	
		ProfileDto profileDto=new ProfileDto(customer);
		model.addAttribute("profileDto", profileDto);

		List<Object[]> cartDetails = cartService.getCartAndProductDetailsByCustomer(customerIdInt);

		if (cartDetails.size() == 0) {
			return "redirect:/home.htm";
		}

		List<Map<String, Object>> carts = new ArrayList<>();
		for (Object[] detail : cartDetails) {
			Map<String, Object> cartMap = new HashMap<>();
			cartMap.put("customer", detail[0]);
			cartMap.put("product", detail[1]);
			cartMap.put("quantity", detail[2]);
			cartMap.put("productName", detail[3]);
			cartMap.put("productPrice", detail[4]);
			cartMap.put("image", detail[5]);
			carts.add(cartMap);
		}

		model.addAttribute("carts", carts);
		return "Checkout";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String createOrder(ModelMap model, HttpSession session,
			BindingResult errors) {

		Integer customerId = (Integer) session.getAttribute("userId");
		try {
			OrderDto orderDto = new OrderDto();
			
			orderService.createOrder(orderDto,customerId);
			return "customer/order";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "cart";
		}

	}
}
