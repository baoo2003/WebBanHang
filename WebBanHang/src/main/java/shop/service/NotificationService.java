package shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Account;
import shop.entity.Customer;
import shop.entity.Notification;

@Service
public class NotificationService {
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<Notification> getNotifications(Integer customerId){
		Session session = sessionFactory.openSession();
		List<Notification> notifications = new ArrayList<>();
		try {
			String hql;
			hql = "SELECT n.id, n.createTime, n.message, n.url, n.status "
					+ "FROM Notification n "
					+ "JOIN n.customer c "
					+ "WHERE c.id = :customerId "
					+ "ORDER BY n.createTime DESC";
			Query query = session.createQuery(hql);		
			query.setParameter("customerId", customerId);	
			query.setMaxResults(10);
			List<Object[]> results = query.list();
			Customer customer = (Customer) session.get(Customer.class, customerId);
			for (Object[] detail : results) {
				Notification notification = new Notification();
				notification.setId((Integer) detail[0]);
				notification.setCreateTime((Date) detail[1]);
				notification.setMessage((String) detail[2]);
				notification.setUrl((String) detail[3]);
				notification.setStatus( (Boolean) detail[4]);
				notification.setCustomer(customer);
				notifications.add(notification);
			}
			return notifications;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	@Transactional
	public Boolean setNotification(Integer customerId, String message, String url) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Customer customer = (Customer) session.get(Customer.class, customerId);
			Notification notification = new Notification();
			notification.setMessage(message);
			notification.setUrl(url);
			notification.setCustomer(customer);
			session.save(notification);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	@Transactional
	public Boolean updateNotifi(Notification notification, Integer customerId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Customer customer = (Customer) session.get(Customer.class, customerId);
			notification.setStatus(true);
			notification.setCustomer(customer);
			session.update(notification);
			transaction.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			if (transaction != null)
				transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}
}
