package shop.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	@Autowired
	SessionFactory factory;
	
	public Integer login(String username, String password) {
		Session session = factory.openSession();
		String hql = "SELECT c.id FROM Customer c JOIN c.account a WHERE a.username = :username AND a.password = :password";
		Query query = session.createQuery(hql);
	    query
	    	.setParameter("username", username)
	    	.setParameter("password", password);
	    
	    Integer customerId = (Integer) query.uniqueResult();
		return customerId;
	}
}
