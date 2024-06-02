package shop.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shop.entity.CartId;
import shop.service.CartService;

@Controller
public class CartController {
	@Autowired
    private CartService cartService;
	
	@RequestMapping("/cart")
	public String index(ModelMap model, HttpSession session) {
		Integer customerIdInt = (Integer) session.getAttribute("userId");
        List<Object[]> cartDetails = cartService.getCartAndProductDetailsByCustomer(customerIdInt);
        List<Map<String, Object>> carts = new ArrayList<>();
        for (Object[] detail : cartDetails) {
            Map<String, Object> cartMap = new HashMap<>();
            cartMap.put("customer", detail[0]);
            cartMap.put("product", detail[1]);
            cartMap.put("quantity", detail[2]);
            cartMap.put("productName", detail[3]);
            cartMap.put("productPrice", detail[4]);
            cartMap.put("image",  detail[5]);
            carts.add(cartMap);
        }

        model.addAttribute("carts", carts);
        return "Cart";
    }

	@RequestMapping(value = "/deleteProductOfCart", method = RequestMethod.POST)
    public String deleteCartItem(
    		@RequestParam("customerId") Integer customerId,
            @RequestParam("productId") Integer productId) {
        CartId cartId = new CartId(customerId, productId);
        cartService.deleteCart(cartId);
        return "redirect:/cart.htm";  
    }

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public String addToCart(HttpSession session,
			@RequestParam("productId") Integer productId) {
		
		Integer customerIdInt = (Integer) session.getAttribute("userId");
		if (customerIdInt == null) {
            return "redirect:/login"; // Redirect to login if customer is not logged in
        }
		cartService.addToCart(customerIdInt, productId, 1);
		
		return "redirect:/cart.htm";
	}
	
	@RequestMapping(value = "/loadCart", method = RequestMethod.POST)
	public String loadCart(HttpSession session,
							@RequestParam("customerId") Integer customerId,
							@RequestParam("productId") Integer productId,
							@RequestParam("quantity") Integer quantity) {
		
		Integer customerIdInt = (Integer) session.getAttribute("userId");
		System.out.print(quantity);
		if (customerIdInt == null) {
            return "redirect:/login"; // Redirect to login if customer is not logged in
        }
		cartService.loadCart(customerIdInt, productId, quantity);
		
		return "redirect:/cart.htm";
	}
}
