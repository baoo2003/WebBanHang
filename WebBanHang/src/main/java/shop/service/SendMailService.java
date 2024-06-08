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
	
	public void sendMail(String from, String to, String subject, String body) throws Exception {
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			
			String formattedBody = body.replace("\n", "<br>");
	        helper.setText(formattedBody, true);
			mailer.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
