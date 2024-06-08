package shop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import jakarta.validation.ConstraintViolationException;
import shop.dto.request.CreateBrandDto;
import shop.dto.request.UpdateBrandDto;
import shop.entity.Brand;

@Service
public class BrandService {
	@Autowired
	SessionFactory factory;
	
	public List<Brand> getBrands() {
		Session session = factory.openSession();
		String hql = "FROM Brand";	
		Query query = session.createQuery(hql);
		List<Brand> brands = query.list();
		return brands;
	}
	
	public Brand findById(Integer brandId) {
		Session session = factory.openSession();
		Brand brand = (Brand) session.get(Brand.class, brandId);
		return brand;
	}
	
	@Transactional
	public void createBrand(CreateBrandDto createbrand) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Brand brand = new Brand();
		brand.setName(createbrand.getName());			
		
		try {
			session.save(brand);
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
	public void updateBrand(UpdateBrandDto updateBrand) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Brand brand = (Brand) session.get(Brand.class, updateBrand.getId());
			brand.setName(updateBrand.getName());			
			
			session.update(brand);
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
	public void deleteBrand(Integer brandId) throws Exception {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Brand brand = (Brand) session.get(Brand.class, brandId);
			session.delete(brand);
			transaction.commit();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw new Exception("Brand is being used in shop!");
		} finally {
			session.close();
		}
	}
}
