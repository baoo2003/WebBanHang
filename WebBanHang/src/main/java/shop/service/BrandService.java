package shop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
