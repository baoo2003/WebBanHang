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
		
		String roleId = (String) session.getAttribute("roleId");
		
		if (roleId == null || !(roleId.equalsIgnoreCase("QL") || roleId.equalsIgnoreCase("NV"))) {
			session.setAttribute("loginMessage", "Please log in to continue");
			response.sendRedirect(request.getContextPath() + "/admin-login.htm");
			return false;
		}
		return true;
	}
}
