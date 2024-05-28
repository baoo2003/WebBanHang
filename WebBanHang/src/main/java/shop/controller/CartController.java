package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.entity.Cart;
import shop.entity.CartId;
import shop.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
    private CartService cartService;
	
	@RequestMapping("")
	public String index(ModelMap model) {
		List<Cart> carts = cartService.getCartByCustomer(4);
		model.addAttribute("carts", carts);
		return "Cart";
    }

	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.POST)
	public String deleteCartItem(@PathVariable Integer customerId, @PathVariable Integer productId, ModelMap model) {
		 System.out.println("Deleting item: Customer ID " + customerId + " Product ID " + productId); // Log thông tin
	    CartId cartId = new CartId(customerId, productId);
	    cartService.deleteCart(cartId);
	    return "redirect:/cart";  
	}

}
