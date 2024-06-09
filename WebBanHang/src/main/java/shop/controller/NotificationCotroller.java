package shop.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.entity.Notification;
import shop.service.NotificationService;

@Controller
public class NotificationCotroller {
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(value = "/readAllNotifi")
	public String readAllNotifi(HttpSession session){
		Integer customerId = (Integer) session.getAttribute("customerId");
		List<Notification> notifications = notificationService.getNotifications(customerId);
		for(Notification notification : notifications) {
			notificationService.updateNotifi(notification, customerId);			
		}	
		return "redirect:/home.htm";
	} 
	
	@RequestMapping(value = "/updateNotifi",  method = RequestMethod.POST)
	public String updateNotifi(HttpSession session,
			@RequestParam(value="id") Integer id,
			@RequestParam(value="message") String message,
			@RequestParam(value="status") Boolean status,
			@RequestParam(value="createTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date createtime,
			@RequestParam(value="url") String url
			) {
		Integer customerIdInt = (Integer) session.getAttribute("customerId");
		Notification notification = new Notification(id, message, url, createtime, status);
		notificationService.updateNotifi(notification, customerIdInt);
		return "redirect:"+notification.getUrl();
	}
}
