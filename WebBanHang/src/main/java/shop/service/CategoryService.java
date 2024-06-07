package shop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Category;

@Service
public class CategoryService {
	@Autowired
	SessionFactory factory;
	
	public List<Category> getCategories() {
		Session session = factory.openSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public Category findById(Integer categoryId) {
		Session session = factory.openSession();
		Category category = (Category) session.get(Category.class, categoryId);
		return category;
	}
}
