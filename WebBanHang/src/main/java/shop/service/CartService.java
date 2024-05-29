package shop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Cart;
import shop.entity.CartId;
import shop.entity.Customer;
import shop.entity.Product;

@Service
public class CartService {
	@Autowired
    private SessionFactory sessionFactory;
	
	public List<Object[]> getCartAndProductDetailsByCustomer(Integer customerId) {
	    Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        String hql = "SELECT c.cartId.customerId, c.cartId.productId, c.quantity, p.name, p.price, p.image FROM Cart c JOIN c.product p WHERE c.cartId.customerId = :customerId";
	        Query query = session.createQuery(hql);
	        query.setParameter("customerId", customerId);
	        List<Object[]> results = query.list();
	        tx.commit();
	        
	        return results;
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        throw e;  
	    } finally {
	        session.close();
	    }
	}
	
	public void addToCart(Integer customerId, Integer productId, Integer quantity) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	       
         // Fetch the existing cart item
            CartId cartId = new CartId(customerId, productId);
            Cart existingCart = (Cart) session.get(Cart.class, cartId);

            if (existingCart != null) {
                // If the product is already in the cart, update the quantity
                existingCart.setQuantity(existingCart.getQuantity() + quantity);
                session.update(existingCart);
            } else {
                // If the product is not in the cart, add a new entry
                Customer customer = (Customer) session.get(Customer.class, customerId);
                Product product = (Product) session.get(Product.class, productId);

                if (customer == null || product == null) {
                    throw new RuntimeException("Customer or Product not found");
                }

                Cart cart = new Cart(cartId, quantity);
                cart.setCustomer(customer);
                cart.setProduct(product);
                session.save(cart);
            }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        throw e;  
	    } finally {
	        session.close();
	    }
	}
	
	public void deleteCart(CartId cartId) {
	    Session session = sessionFactory.openSession();
	   
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        Cart cart = (Cart) session.get(Cart.class, cartId);
	        if (cart != null) {
	            session.delete(cart);
	        }
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        throw e;
	    } finally {
	        session.close();
	    }
	}
	
	public void loadCart(Integer customerId, Integer productId, Integer quantity) {
	    Session session = sessionFactory.openSession();
	   
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        
	        CartId cartId = new CartId(customerId, productId);
            Cart existingCart = (Cart) session.get(Cart.class, cartId);
            existingCart.setQuantity(quantity);
            session.update(existingCart);
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        throw e;
	    } finally {
	        session.close();
	    }
	}
}
