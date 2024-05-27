package shop.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.request.LoginDto;
import shop.dto.request.RegisterDto;
import shop.entity.Account;
import shop.entity.Role;

@Service
public class AuthService {
	@Autowired
	SessionFactory factory;
	
	public Integer login(LoginDto loginDto) {
		Session session = factory.openSession();
		String hql = "SELECT c.id FROM Customer c JOIN c.account a WHERE a.username = :username AND a.password = :password";
		Query query = session.createQuery(hql);
	    query
	    	.setParameter("username", loginDto.getUsername())
	    	.setParameter("password", loginDto.getPassword());
	    
	    Integer customerId = (Integer) query.uniqueResult();
		return customerId;
	}
	
	@Transactional
	public String register(RegisterDto registerDto) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Account account = new Account();
		
		try {
			Role role = (Role) session.get(Role.class, "KH");
			account.setUsername(registerDto.getUsername());
			account.setPassword(registerDto.getPassword());
			account.setRole(role);
			session.save(account);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return account.getUsername();
	}
}
