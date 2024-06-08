package shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityAdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler
	) throws Exception {
		HttpSession session = request.getSession();
		
		Integer staffId = (Integer) session.getAttribute("staffId");
		
		if (staffId == null) {
			session.setAttribute("loginMessage", "Please log in to continue");
			response.sendRedirect(request.getContextPath() + "/admin-login.htm");
			return false;
		}
		return true;
	}
}
