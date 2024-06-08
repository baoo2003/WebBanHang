package shop.service;

import javax.transaction.Transactional;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.request.LoginDto;
import shop.dto.request.RegisterDto;
import shop.entity.Account;
import shop.entity.Customer;
import shop.entity.Role;

@Service
public class AuthService {
	@Autowired
	SessionFactory factory;
	
	public Integer loginForCustomer(LoginDto login) throws Exception {
		Session session = factory.openSession();
		String roleId = getRoleId(session, login.getUsername(), login.getPassword());
		
		if (!roleId.equalsIgnoreCase("KH")) {
			throw new Exception("You do not have permission to login!");
		}
		
		String hql = "SELECT c.id FROM Customer c JOIN c.account a WHERE a.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", login.getUsername());
		
		Integer customerId = (Integer) query.uniqueResult();
		return customerId;
	}
	
	public Integer loginForAdmin(LoginDto login) throws Exception {
		Session session = factory.openSession();
		String roleId = getRoleId(session, login.getUsername(), login.getPassword());
		
		if (!(roleId.equalsIgnoreCase("QL") || roleId.equalsIgnoreCase("NV"))) {
			throw new Exception("You do not have permission to login!");
		}
		
		String hql = "SELECT s.id FROM Staff s JOIN s.account a WHERE a.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", login.getUsername());
		
		Integer staffId = (Integer) query.uniqueResult();
		return staffId;
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
		} catch (NonUniqueObjectException e) {
			transaction.rollback();
			throw new Exception("Username already existed!");
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw new Exception("Error while register. Please try again!");
		} finally {
			session.close();
		}
	}
	
	private String getRoleId(Session session, String username, String password) throws Exception {
		String hql = "FROM Account WHERE username = :username AND password = :password";
		Query query = session.createQuery(hql);
	    query
	    	.setParameter("username", username)
	    	.setParameter("password", password);
	    
	    Account account = (Account) query.uniqueResult();
	    
	    if (account == null) {
	    	throw new Exception("Wrong username or password");
	    }
	    return account.getRole().getId();
	}
}
