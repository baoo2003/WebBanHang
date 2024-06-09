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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		Integer customerIdInt = (Integer) session.getAttribute("customerId");

		if (customerIdInt == null) {
			return "redirect:/login.htm";
		}

		Customer customer = customerService.getCustomerById((Integer) session.getAttribute("customerId"));
		ProfileDto profileDto = new ProfileDto(customer);
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

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String createOrder(
			HttpSession session, 
			ModelMap model, 
			RedirectAttributes redirectAttributes,
			@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("address") String address,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("shipping") String shipping,
			@RequestParam("payment") String payment,
			@RequestParam("note") String note
			) {

		Integer customerId = (Integer) session.getAttribute("customerId");
		try {

			OrderDto orderDto = new OrderDto();
			orderDto.setFirstName(firstName);
			orderDto.setLastName(lastName);
			orderDto.setEmail(email);
			orderDto.setAddress(address);
			orderDto.setPhoneNumber(phoneNumber);
			orderDto.setShipping(shipping);
			orderDto.setPayment(payment);
			orderDto.setNote(note);

			orderService.createOrder(orderDto, customerId);
			return "redirect:/customer-profile.htm";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/cart.htm";
		}

	}
}
