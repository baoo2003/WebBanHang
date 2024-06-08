package shop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.request.CreateCategoryDto;
import shop.dto.request.UpdateCategoryDto;
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
	
	@Transactional
	public void createCategory(CreateCategoryDto createCategory) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Category category = new Category();
		category.setName(createCategory.getName());			
		
		try {
			session.save(category);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	@Transactional
	public void updateCategory(UpdateCategoryDto updateCategory) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Category category = (Category) session.get(Category.class, updateCategory.getId());
			category.setName(updateCategory.getName());			
			
			session.update(category);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	@Transactional
	public void deleteCategory(Integer categoryId) throws Exception {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Category category = (Category) session.get(Category.class, categoryId);
			session.delete(category);
			transaction.commit();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw new Exception("Category is being used in shop!");
		} finally {
			session.close();
		}
	}
}
