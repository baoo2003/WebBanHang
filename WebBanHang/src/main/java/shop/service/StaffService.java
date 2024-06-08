package shop.service;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.request.ChangePasswordDto;
import shop.entity.Staff;

@Service
public class StaffService {
	@Autowired
	SessionFactory factory;
	
	@Transactional
	public void changePassword(Integer staffId, ChangePasswordDto changePassword) throws Exception {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Staff staff = (Staff) session.get(Staff.class, staffId);
			String oldPassword = staff.getAccount().getPassword();
			
			if (!oldPassword.equals(changePassword.getOldPassword())) {
				throw new Exception("Old password is wrong");
			}
			
			staff.getAccount().setPassword(changePassword.getNewPassword());
			session.update(staff);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
