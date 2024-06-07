package shop.service;

import javax.transaction.Transactional;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.request.ProfileDto;
import shop.entity.Account;
import shop.entity.Customer;
import shop.entity.Role;

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
	
	public Account getAccountByUsername(String username) {
		Session session = factory.openSession();
		String hql = "FROM Account a WHERE a.username=:username";
		Query query=session.createQuery(hql);
		query.setParameter("username", username);
		return (Account) query.uniqueResult();
	}
	
	@Transactional
	public void updateProfile(ProfileDto profileDto, Integer customerId) throws Exception {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = (Customer) session.get(Customer.class, customerId);		
		Account account = (Account) session.get(Account.class, customer.getAccount().getUsername());
		
		try {					
			account.setPassword(profileDto.getPassword());			
			session.update(account);
			
			customer.setFirstName(profileDto.getFirstName());
			customer.setLastName(profileDto.getLastName());
			customer.setGender(profileDto.getGender());
			customer.setAddress(profileDto.getAddress());
			customer.setPhoneNumber(profileDto.getPhoneNumber());
			customer.setEmail(profileDto.getEmail());			
			session.update(customer);
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw new Exception("Error while update. Please try again!");
		} finally {
			session.close();
		}
	}
}
