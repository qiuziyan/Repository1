package com.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String user = (String) request.getSession().getAttribute("user");
		if(null == user) {
			response.sendRedirect(request.getContextPath()+"/error.jsp");
			return false;
		}
		return true;
	}
}
