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
	
	public List<Product> getProducts(Long page, int limit, Integer categoryId, Integer filterId, String keyWord) {
		Session session = factory.openSession();
		String hql;	
		Query query;
		if(keyWord == null || keyWord.isEmpty() || keyWord.isBlank()) {
			if(categoryId == null) {
				switch (filterId) {
					case 1: 
						hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))<=10";
						break;
					case 2:
						hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>10 and (p.price*(1-(p.discount/100.0)))<=50";
						break;
					case 3:
						hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>50 and (p.price*(1-(p.discount/100.0)))<=100";
						break;
					case 4:
						hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>100";
						break;
					case 5:
						hql = "FROM Product p WHERE p.discount>0";
						break;
					default:
						hql = "FROM Product";
				}
				query = session.createQuery(hql);
				query.setFirstResult((int) ((page-1) * limit));
				query.setMaxResults(limit);
			}
			else {
				switch (filterId) {
				case 1: 
					hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))<=10 and p.category.id = :categoryId";
					break;
				case 2:
					hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>10 and (p.price*(1-(p.discount/100.0)))<=50 and p.category.id = :categoryId";
					break;
				case 3:
					hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>50 and (p.price*(1-(p.discount/100.0)))<=100 and p.category.id = :categoryId";
					break;
				case 4:
					hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>100 and p.category.id = :categoryId";
					break;
				case 5:
					hql = "FROM Product p WHERE p.discount>0 and p.category.id = :categoryId";
					break;
				default:
					hql = "FROM Product p WHERE p.category.id = :categoryId";
				}
				query = session.createQuery(hql);
				query.setParameter("categoryId", categoryId);
				query.setFirstResult((int) ((page-1) * limit));
				query.setMaxResults(limit);
			}
		}
		else {
			if(categoryId == null) {
				switch (filterId) {
					case 1: 
						hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))<=10 and p.name LIKE CONCAT('%', :keyWord, '%')";
						break;
					case 2:
						hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>10 and (p.price*(1-(p.discount/100.0)))<=50 and p.name LIKE CONCAT('%', :keyWord, '%')";
						break;
					case 3:
						hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>50 and (p.price*(1-(p.discount/100.0)))<=100 and p.name LIKE CONCAT('%', :keyWord, '%')";
						break;
					case 4:
						hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>100 and p.name LIKE CONCAT('%', :keyWord, '%')";
						break;
					case 5:
						hql = "FROM Product p WHERE p.discount>0 and p.name LIKE CONCAT('%', :keyWord, '%')";
						break;
					default:
						hql = "FROM Product p WHERE p.name LIKE CONCAT('%', :keyWord, '%')";
				}
				query = session.createQuery(hql);
				query.setParameter("keyWord", keyWord);
				query.setFirstResult((int) ((page-1) * limit));
				query.setMaxResults(limit);
			}
			else {
				switch (filterId) {
				case 1: 
					hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))<=10 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 2:
					hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>10 and (p.price*(1-(p.discount/100.0)))<=50 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 3:
					hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>50 and (p.price*(1-(p.discount/100.0)))<=100 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 4:
					hql = "FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>100 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 5:
					hql = "FROM Product p WHERE p.discount>0 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				default:
					hql = "FROM Product p WHERE p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
				}
				query = session.createQuery(hql);
				query.setParameter("categoryId", categoryId);
				query.setParameter("keyWord", keyWord);
				query.setFirstResult((int) ((page-1) * limit));
				query.setMaxResults(limit);
			}
		}
		
		return query.list();
	}
	
	public List<Category> getCategories() {
		Session session = factory.openSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public Long countRecord(Integer categoryId, Integer filterId, String keyWord) {
		Session session = factory.openSession();
		String hql;
		Query query;
		if(keyWord == null || keyWord.isEmpty() || keyWord.isBlank()) {
			if(categoryId == null) {
				switch (filterId) {
				case 1: 
					hql = "SELECT COUNT(id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))<=10";
					break;
				case 2:
					hql = "SELECT COUNT(id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>10 and (p.price*(1-(p.discount/100.0)))<=50";
					break;
				case 3:
					hql = "SELECT COUNT(id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>50 and (p.price*(1-(p.discount/100.0)))<=100";
					break;
				case 4:
					hql = "SELECT COUNT(id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>100";
					break;
				case 5:
					hql = "SELECT COUNT(id) FROM Product p WHERE p.discount>0";
					break;
				default:
					hql = "SELECT COUNT(id) FROM Product";
				}
				query = session.createQuery(hql);
			}
			else {
				switch (filterId) {
				case 1: 
					hql = "SELECT COUNT(p.id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))<=10 and p.category.id = :categoryId";
					break;
				case 2:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>10 and (p.price*(1-(p.discount/100.0)))<=50 and p.category.id = :categoryId";
					break;
				case 3:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>50 and (p.price*(1-(p.discount/100.0)))<=100 and p.category.id = :categoryId";
					break;
				case 4:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>100 and p.category.id = :categoryId";
					break;
				case 5:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE p.discount>0 and p.category.id = :categoryId";
					break;
				default:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE p.category.id = :categoryId";
				}
				query = session.createQuery(hql);
				query.setParameter("categoryId", categoryId);
			}
		}
		else {
			if(categoryId == null) {
				switch (filterId) {
				case 1: 
					hql = "SELECT COUNT(id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))<=10 and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 2:
					hql = "SELECT COUNT(id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>10 and (p.price*(1-(p.discount/100.0)))<=50 and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 3:
					hql = "SELECT COUNT(id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>50 and (p.price*(1-(p.discount/100.0)))<=100 and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 4:
					hql = "SELECT COUNT(id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>100 and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 5:
					hql = "SELECT COUNT(id) FROM Product p WHERE p.discount>0 and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				default:
					hql = "SELECT COUNT(id) FROM Product p WHERE p.name LIKE CONCAT('%', :keyWord, '%')";
				}
				query = session.createQuery(hql);
				query.setParameter("keyWord", keyWord);
			}
			else {
				switch (filterId) {
				case 1: 
					hql = "SELECT COUNT(p.id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))<=10 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 2:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>10 and (p.price*(1-(p.discount/100.0)))<=50 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 3:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>50 and (p.price*(1-(p.discount/100.0)))<=100 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 4:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE (p.price*(1-(p.discount/100.0)))>100 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				case 5:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE p.discount>0 and p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
					break;
				default:
					hql = "SELECT COUNT(p.id) FROM Product p WHERE p.category.id = :categoryId and p.name LIKE CONCAT('%', :keyWord, '%')";
				}
				query = session.createQuery(hql);
				query.setParameter("categoryId", categoryId);
				query.setParameter("keyWord", keyWord);
			}
		}
		
		return (Long) query.uniqueResult();
	}
	
	public Product findById(Integer productId) {
		Session session = factory.openSession();
		String hql="FROM Product p WHERE p.id = :productId";	
		Query query=session.createQuery(hql);
		query.setParameter("productId", productId);
		return (Product) query.uniqueResult();
	}
}
