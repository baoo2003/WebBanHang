package shop.service;

import java.util.List;
import java.util.Date;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.request.OrderDto;
import shop.entity.Cart;
import shop.entity.CartId;
import shop.entity.Customer;
import shop.entity.Order;
import shop.entity.OrderDetail;
import shop.entity.OrderDetailId;
import shop.entity.Product;
import shop.service.CartService;;

@Service
public class OrderService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CartService cartService;

	@Transactional
	public void createOrder(OrderDto orderDto, Integer customerId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Object[]> cartDetails = cartService.getCartAndProductDetailsByCustomer(customerId);
			Integer timestamp = (int) (new Date().getTime() / 1000);
			Order order = new Order();
			order.setId(timestamp);
//			order.setAddress()
			
			
			session.save(order);

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
