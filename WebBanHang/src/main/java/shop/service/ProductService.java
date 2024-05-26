package shop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Category;
import shop.entity.Product;

@Service
public class ProductService {
	@Autowired
	SessionFactory factory;
	
	public List<Product> getProducts(int page, int limit) {
		Session session = factory.openSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		query.setFirstResult(page * limit);
		query.setMaxResults(limit);
		return query.list();
	}
	
	public List<Category> getCategories() {
		Session session = factory.openSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		return query.list();
	}
}
