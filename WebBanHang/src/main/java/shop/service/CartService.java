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

@Service
public class CartService {
	@Autowired
    private SessionFactory sessionFactory;
	
	public List<Object[]> getCartAndProductDetailsByCustomer(Integer customerId) {
	    Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        String hql = "SELECT c.quantity, p.name, p.price, p.image FROM Cart c JOIN c.product p WHERE c.cartId.customerId = :customerId";
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
}
