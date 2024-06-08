package shop.service;

import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;

import javax.transaction.Transactional;

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
	
	@Autowired
	private CustomerService customerService;

	
	@Transactional
	public void createOrder(OrderDto orderDto, Integer customerId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Object[]> cartDetails = cartService.getCartAndProductDetailsByCustomer(customerId);
			Customer customer = customerService.getCustomerById(customerId);
			Order order = new Order();
			
			Date now = new Date();

			order.setFullname(orderDto.getFirstName() + " " + orderDto.getLastName());
			;
			order.setPhoneNumber(orderDto.getPhoneNumber());
			order.setAddress(orderDto.getAddress());
			order.setDeliveryNote(orderDto.getNote());
			order.setCustomer(customer);
			order.setOrderTime(now);
			now.setDate(7);
			order.setDeliveryTime(now);
			order.setDeliveryNote(orderDto.getNote());
			order.setStatus("PLACED");
			
			session.save(order);
			

			for (Object[] detail : cartDetails) {
				Integer productId = (Integer) detail[1];
				Integer quantity = (Integer) detail[2];
				Float price = (Float) detail[4];
				
				OrderDetailId orderDetailId = new OrderDetailId(order.getId(), productId);
				OrderDetail orderDetail = new OrderDetail();
				
				Product product = new Product();
				
				product.setId(productId);
				
				orderDetail.setId(orderDetailId);
				orderDetail.setOrder(order);
				orderDetail.setPrice(price);
				orderDetail.setProduct(product);
				orderDetail.setQuantity(quantity);
				session.save(orderDetail);
	        }
			
			transaction.commit();

			cartService.deleteCartByCustomerId(customerId);
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
