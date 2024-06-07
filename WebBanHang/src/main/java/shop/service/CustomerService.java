package shop.service;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Account;
import shop.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	SessionFactory factory;
	
	@Transactional
	public void addCustomer(String accountId, Customer customer) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Account account = (Account) session.get(Account.class, accountId);
			customer.setAccount(account);
			session.save(customer);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public Customer getCustomerById(Integer customerId) {
		Session session = factory.openSession();
		String hql = "FROM Customer c WHERE c.id=:customerId";
		Query query=session.createQuery(hql);
		query.setParameter("customerId", customerId);
		return (Customer) query.uniqueResult();
	}
}
