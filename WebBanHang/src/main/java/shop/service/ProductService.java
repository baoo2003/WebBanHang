package shop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.request.CreateProductDto;
import shop.dto.request.UpdateProductDto;
import shop.entity.Brand;
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
	
	public List<Product> getRelatedProducts(Integer categoryId) {
		Session session = factory.openSession();
		String hql="FROM Product p WHERE p.category.id = :categoryId";	
		Query query=session.createQuery(hql);
		query.setParameter("categoryId", categoryId);
		return query.list();
	}
	
	@Transactional
	public void addProduct(CreateProductDto createProduct, Brand brand, Category category, String imagePath) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Product product = new Product();
		product.setName(createProduct.getName());
		product.setBrand(brand);
		product.setCategory(category);
		product.setDescribe(createProduct.getDescription());
		product.setOrigin(createProduct.getOrigin());
		product.setImage(imagePath);
		product.setUnit(createProduct.getUnit());
		product.setQuantity(createProduct.getQuantity());
		product.setPrice(createProduct.getPrice());
		product.setDiscount(createProduct.getDiscount());
		
		try {
			session.save(product);
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
	public void updateProduct(UpdateProductDto updateProduct, Brand brand, Category category, String newImage) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Product product = (Product) session.get(Product.class, updateProduct.getId());
			product.setName(updateProduct.getName());
			product.setBrand(brand);
			product.setCategory(category);
			product.setDescribe(updateProduct.getDescription());
			product.setOrigin(updateProduct.getOrigin());
			if (newImage != null) {
				product.setImage(newImage);
			}
			product.setUnit(updateProduct.getUnit());
			product.setQuantity(updateProduct.getQuantity());
			product.setPrice(updateProduct.getPrice());
			product.setDiscount(updateProduct.getDiscount());
			
			session.update(product);
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
	public void deleteProduct(Integer productId) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Product product = (Product) session.get(Product.class, productId);
			session.delete(product);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	
	public List<Product> get5Product(){
		Session session = factory.openSession();
        try {
            String hql = "FROM Product p ORDER BY p.quantity DESC";
            Query query = session.createQuery(hql);
            query.setMaxResults(5); 

            List<Product> products = query.list();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }
}
