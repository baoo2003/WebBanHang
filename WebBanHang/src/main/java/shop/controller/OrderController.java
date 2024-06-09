package shop.controller;

import java.util.Optional;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/customer-order")
	public String index(ModelMap model, HttpSession session) {
		Integer customerIdInt = (Integer) session.getAttribute("customerId");		
		
		System.out.println(customerIdInt);
		List<Map<String, Object>> orders = orderService.getOrders(Optional.empty(), Optional.empty(),
				Optional.of(customerIdInt));

		model.addAttribute("orders", orders);
		return "Customer-order";
	}	
	
	@RequestMapping("/manage-order")
	public String index2(ModelMap model, HttpSession session) {
		List<Map<String, Object>> orders = orderService.getOrders(Optional.empty(), Optional.empty(),
				Optional.empty());

		model.addAttribute("orders", orders);
		return "admin/order/index";
	}		
}
