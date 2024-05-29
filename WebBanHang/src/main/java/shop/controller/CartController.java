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

import shop.entity.CartId;
import shop.service.CartService;

@Controller
public class CartController {
	@Autowired
    private CartService cartService;
	
	@RequestMapping("/cart")
	public String index(ModelMap model, HttpSession session) {
		Integer customerIdInt = (Integer) session.getAttribute("customerId");
        List<Object[]> cartDetails = cartService.getCartAndProductDetailsByCustomer(customerIdInt);
        List<Map<String, Object>> carts = new ArrayList<>();
        System.out.print(customerIdInt);
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
		System.out.print(productId);
        CartId cartId = new CartId(customerId, productId);
        cartService.deleteCart(cartId);
        return "redirect:/cart.htm";  
    }

	

}
