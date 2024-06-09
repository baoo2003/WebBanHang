package shop.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import shop.dto.request.ProfileDto;
import shop.entity.Customer;
import shop.entity.Order;
import shop.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/customer-order")
	public String index(ModelMap model, HttpSession session) {
		Integer customerIdInt = (Integer) session.getAttribute("cusomterId");

		if (customerIdInt == null) {
			return "redirect:/login.htm";
		}

		List<Map<String, Object>> orders = orderService.getOrders(Optional.empty(), Optional.empty(),
				Optional.of(customerIdInt));

		model.addAttribute("orders", orders);
		return "Customer-order";
	}
}
