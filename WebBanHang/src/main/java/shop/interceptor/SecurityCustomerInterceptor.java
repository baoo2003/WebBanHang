package shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityCustomerInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler
	) throws Exception {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("userId") == null) {
			session.setAttribute("loginMessage", "Please log in to continue");
			response.sendRedirect(request.getContextPath() + "/login.htm");
			return false;
		}
		return true;
	}
}
