package shop.service;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.request.LoginDto;
import shop.dto.request.RegisterDto;
import shop.dto.response.LoginResponse;
import shop.entity.Account;
import shop.entity.Customer;
import shop.entity.Role;

@Service
public class AuthService {
	@Autowired
	SessionFactory factory;
	
	public LoginResponse login(LoginDto loginDto) {
		Session session = factory.openSession();
		String hql = "FROM Account WHERE username = :username AND password = :password";
		Query query = session.createQuery(hql);
	    query
	    	.setParameter("username", loginDto.getUsername())
	    	.setParameter("password", loginDto.getPassword());
	    
	    LoginResponse loginResponse = new LoginResponse();
	    Account account = (Account) query.uniqueResult();
	    
	    if (account.getRole().getId().equalsIgnoreCase("KH")) {
	    	Integer customerId = loginForCustomer(session, loginDto.getUsername());
	    	loginResponse.setUserId(customerId);
	    } else {
	    	Integer staffId = loginForAdmin(session, loginDto.getUsername());
	    	loginResponse.setUserId(staffId);
	    }
    	loginResponse.setRoleId(account.getRole().getId());
	    
		return loginResponse;
	}
	
	@Transactional
	public void register(RegisterDto registerDto) throws Exception {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Account account = new Account();
		Customer customer = new Customer();
		
		try {
			Role role = (Role) session.get(Role.class, "KH");
			account.setUsername(registerDto.getUsername());
			account.setPassword(registerDto.getPassword());
			account.setRole(role);
			session.save(account);
			
			customer.setFirstName(registerDto.getFirstName());
			customer.setLastName(registerDto.getLastName());
			customer.setGender(registerDto.getGender());
			customer.setAddress(registerDto.getAddress());
			customer.setPhoneNumber(registerDto.getPhoneNumber());
			customer.setEmail(registerDto.getEmail());
			customer.setAccount(account);
			session.save(customer);
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	private Integer loginForCustomer(Session session, String username) {
		String hql = "SELECT c.id FROM Customer c JOIN c.account a WHERE a.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		
		Integer customerId = (Integer) query.uniqueResult();
		return customerId;
	}
	
	private Integer loginForAdmin(Session session, String username) {
		String hql = "SELECT s.id FROM Staff s JOIN s.account a WHERE a.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		
		Integer staffId = (Integer) query.uniqueResult();
		return staffId;
	}
}
