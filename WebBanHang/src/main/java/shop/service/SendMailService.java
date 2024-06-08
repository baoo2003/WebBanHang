package shop.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
	@Autowired
	JavaMailSender mailer;
	
	public Boolean sendMail(String to, String subject, String body ) {
		try {
			String from = "pqt758837@gmail.com";
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			
			String formattedBody = body.replace("\n", "<br>");
	        helper.setText(formattedBody, true);
			mailer.send(mail);
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi: " + e.getMessage());
			return false;
		}
	}
}
