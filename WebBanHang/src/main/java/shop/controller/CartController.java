package shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.entity.Cart;
import shop.entity.CartId;
import shop.service.CartService;

@Controller
@RequestMapping
public class CartController {
	@Autowired
    private CartService cartService;
	
	@RequestMapping("/cart")
	public String index(ModelMap model) {
        // Lấy thông tin sản phẩm và giỏ hàng của khách hàng có ID là 4
        List<Object[]> cartDetails = cartService.getCartAndProductDetailsByCustomer(4);
        List<Map<String, Object>> carts = new ArrayList<>();

        for (Object[] detail : cartDetails) {
            Map<String, Object> cartMap = new HashMap<>();
            cartMap.put("quantity", detail[0]);
            cartMap.put("productName", detail[1]);
            cartMap.put("productPrice", detail[2]);
            cartMap.put("image",  detail[3]);
            carts.add(cartMap);
        }

        model.addAttribute("carts", carts);
        return "Cart";
    }
	
	@RequestMapping(value = "/cart/delete/1", method = RequestMethod.POST)
    public String deleteCartItem(@RequestParam("customerId") Integer customerId, 
                                 @PathVariable("productId") Integer productId) {
		CartId cartId = new CartId(customerId, productId);
        cartService.deleteCart(cartId);
        return "redirect:/Cart.htm";  
    }

}
