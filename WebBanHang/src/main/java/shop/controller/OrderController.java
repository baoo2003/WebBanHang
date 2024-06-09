package shop.controller;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shop.dto.request.UpdateOrderDto;
import shop.entity.Order;
import shop.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping("/customer-order")
	public String index(ModelMap model, HttpSession session) {
		Integer customerIdInt = (Integer) session.getAttribute("customerId");		
		
		System.out.println(customerIdInt);
		List<Map<String, Object>> orders = orderService.getOrders(Optional.empty(), Optional.empty(),
				Optional.of(customerIdInt));

		model.addAttribute("orders", orders);
		return "Customer-order";
	}
	
	@RequestMapping(value = "customer-order", method = RequestMethod.POST)
	public String updateOrder(HttpSession session, ModelMap model, RedirectAttributes redirectAttributes,
			@RequestParam("orderId") Integer orderId, @RequestParam("status") String status,
			@RequestParam("cancelReason") String cancelReason) {

		Integer customerId = (Integer) session.getAttribute("customerId");
		Session s = sessionFactory.openSession();
		try {
			UpdateOrderDto orderDto= new UpdateOrderDto(orderId, status);					

			orderService.updateOrder(orderDto);
			return "redirect:/customer-order.htm";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/customer-order.htm";
		}

	}
	
	@RequestMapping("/manage-order")
	public String index2(ModelMap model, HttpSession session) {
		List<String> orderStatus = Arrays.asList("Placed", "Confirmed", "Delivering", "Delivered");
		
		model.addAttribute("orders", orderService.getAllOrders());
		model.addAttribute("orderStatus", orderStatus);
		model.addAttribute("updateOrder", new UpdateOrderDto());
		return "admin/order/index";
	}
	
	@RequestMapping(value = "/update-status", method = RequestMethod.POST)
	public String updateStatus(
		ModelMap model,
		@ModelAttribute("updateOrder") UpdateOrderDto updateOrder
	) {
		orderService.updateOrder(updateOrder);
		
		return "redirect:/manage-order.htm";
	}
}
