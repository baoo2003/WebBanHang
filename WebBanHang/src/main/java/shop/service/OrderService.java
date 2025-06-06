package shop.service;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.request.OrderDto;
import shop.dto.request.UpdateOrderDto;
import shop.dto.request.UpdateProductDto;
import shop.entity.Brand;
import shop.entity.Cart;
import shop.entity.CartId;
import shop.entity.Category;
import shop.entity.Customer;
import shop.entity.Order;
import shop.entity.OrderDetail;
import shop.entity.OrderDetailId;
import shop.entity.Product;
import shop.entity.Staff;
import shop.service.CartService;;

@Service
public class OrderService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CartService cartService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private NotificationService notificationService;

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
			notificationService.setNotification(customerId,
					"You have just placed a new order. Check order status here.", "/customer-order.htm");
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public List<Map<String, Object>> getOrders(Optional<Integer> page, Optional<Integer> limit,
			Optional<Integer> customerId) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT o.id, o.customer, o.fullname, o.phoneNumber, o.address, o.deliveryTime, o.orderTime, o.status, o.cancelReason, o.deliveryNote FROM Order o ORDER BY o.id DESC";
		if (customerId.isPresent()) {
			hql = "SELECT o.id, o.customer, o.fullname, o.phoneNumber, o.address, o.deliveryTime, o.orderTime, o.status, o.cancelReason, o.deliveryNote FROM Order o WHERE o.customer.id = :customerId ORDER BY o.id DESC";
		}
		Query query = session.createQuery(hql);
		if (customerId.isPresent()) {
			query.setParameter("customerId", customerId.get());
		}
		if (page.isPresent() && limit.isPresent()) {

			query.setFirstResult((int) ((page.get() - 1) * limit.get()));
			query.setMaxResults(limit.get());
		}
		List<Object[]> results = query.list();
		List<Map<String, Object>> orders = new ArrayList<>();
		for (Object[] detail : results) {
			Map<String, Object> cartMap = new HashMap<>();

			String productQueryHql = "FROM OrderDetail c WHERE c.order.id = :orderId";
			Query productQuery = session.createQuery(productQueryHql);

			productQuery.setParameter("orderId", (Integer) detail[0]);

			List<OrderDetail> orderDetails = productQuery.list();

			List<Product> productList = new ArrayList<>();
			
			Double totalPrice = 0.0;

			for (OrderDetail orderDetail : orderDetails) {
				Product product = productService.findById(orderDetail.getProduct().getId());
				totalPrice += orderDetail.getPrice() * orderDetail.getQuantity();
				orderDetail.setProduct(product);
			}

			cartMap.put("id", detail[0]);
			cartMap.put("orderDetails", orderDetails);
			cartMap.put("fullname", detail[2]);
			cartMap.put("phoneNumber", detail[3]);
			cartMap.put("address", detail[4]);
			cartMap.put("deliveryTime", detail[5]);
			cartMap.put("orderTime", detail[6]);
			cartMap.put("status", detail[7]);
			cartMap.put("cancelReason", detail[8]);
			cartMap.put("deliveryNote", detail[9]);
			cartMap.put("totalPrice", totalPrice);
			orders.add(cartMap);
		}
		return orders;
	}
	
	@Transactional
	public void updateOrderCustomer(UpdateOrderDto orderDto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			Order order = (Order) session.get(Order.class, orderDto.getId());			
			order.setStatus(orderDto.getStatus());			
			session.update(order);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Transactional
	public void updateOrder(UpdateOrderDto orderDto, Integer staffId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			Order order = (Order) session.get(Order.class, orderDto.getId());
			Staff staff = (Staff) session.get(Staff.class, staffId);
			order.setStatus(orderDto.getStatus());
			order.setStaff(staff);
			session.update(order);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public List<Order> getAllOrders(String status) {
		Session session = sessionFactory.openSession();
		String hql = "FROM Order o";
		if (status != null) {
			hql += " WHERE status = :status";
		}
		Query query = session.createQuery(hql);
		if (status != null) {
			query.setParameter("status", status);
		}
		return query.list();
	}
	
	public Order findById(Integer id) {
		Session session = sessionFactory.openSession();
		String hql = "FROM Order WHERE id = :id";
		Query query = session.createQuery(hql);	
		query.setParameter("id", id);
		return (Order) query.uniqueResult();
	}
	
	public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {
		Session session = sessionFactory.openSession();
		String hql = "FROM OrderDetail WHERE order.id = :orderId";
		Query query = session.createQuery(hql);
		query.setParameter("orderId", orderId);
		List<OrderDetail> orderDetails = query.list();
		
		return orderDetails;
	}
}
