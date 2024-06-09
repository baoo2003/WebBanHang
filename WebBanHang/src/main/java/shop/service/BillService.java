package shop.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Bill;
import shop.entity.Order;
import shop.entity.OrderDetail;
import shop.entity.Staff;

@Service
public class BillService {
	@Autowired
	SessionFactory factory;
	
	public Double getTotalPrice(Integer orderId) {
		Session session1 = factory.openSession();
		String hql = "FROM OrderDetail od WHERE od.order.id=:orderId";	
		Query query = session1.createQuery(hql);
		query.setParameter("orderId", orderId);
		List<OrderDetail> orderDetails = query.list();
		Double total=0.0;
		for(OrderDetail orderDetail:orderDetails) {
			total+=orderDetail.getPrice()*orderDetail.getQuantity();
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
	    String formattedTotal = decimalFormat.format(total);
	    return Double.parseDouble(formattedTotal);
	}
	
	@Transactional
	public void createBill(Integer staffId, Integer orderId) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Order order= (Order) session.get(Order.class, orderId);
			Staff staff= (Staff) session.get(Staff.class, staffId);
			Bill bill = new Bill();
			bill.setOrder(order);			
			bill.setCreateTime(new Date());
			bill.setStaff(staff);			
			bill.setTotalPrice(getTotalPrice(orderId));
			session.save(bill);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
