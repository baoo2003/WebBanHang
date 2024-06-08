package shop.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
	public String addToCart(HttpSession session, ModelMap model, RedirectAttributes redirectAttributes,
			@RequestParam("productId") Integer productId) {
		try {
		Integer customerIdInt = (Integer) session.getAttribute("userId");
		if (customerIdInt == null) {
			redirectAttributes.addFlashAttribute("message", "Please login to continue!");
            return "redirect:/login.htm"; 
        }
		cartService.addToCart(customerIdInt, productId, 1);
		return "redirect:/home.htm";
		} catch (Exception e){
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/home.htm" ;
		}
	}
	
	@RequestMapping(value = "/loadCart", method = RequestMethod.POST)
	public String loadCart(HttpSession session, RedirectAttributes redirectAttributes,
							@RequestParam("customerId") Integer customerId,
							@RequestParam("productId") Integer productId,
							@RequestParam("quantity") Integer quantity) {
		try {
		Integer customerIdInt = (Integer) session.getAttribute("userId");
		if (customerIdInt == null) {
            return "redirect:/login"; // Redirect to login if customer is not logged in
        }
			cartService.loadCart(customerIdInt, productId, quantity);
		}catch (Exception e){
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/cart.htm";
	}
}
