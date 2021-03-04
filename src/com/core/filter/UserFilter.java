package com.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import com.core.util.MD5Util;
import com.core.util.SignHelper;
import com.core.util.Utils;

public class UserFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ((request.getRequestURI().indexOf("assets") >= 0 || request.getRequestURI().indexOf("login") >= 0)
				&& request.getRequestURI().indexOf("/api/") < 0) {
			chain.doFilter(request, response);
			return;
		} else if (request.getRequestURI().indexOf("/api/") >= 0 && request.getRequestURI().indexOf("login") < 0) {
			String token = request.getHeader("token");
			String code = request.getParameter("code");
			if (null == token || "".equals(token) || null == code || "".equals(code)) {
				System.out.println("1111111111111");
				signError(request, response);
				return;
			} else {
				try {
					String SALT = MD5Util.encrypt(code + token);
					String sign = SignHelper.getSignature(request.getParameterMap(), token, SALT);
					if (sign.equals(request.getParameter("sign"))) {
						chain.doFilter(request, response);
					} else {
						System.out.println("2222222222222222222");
						signError(request, response);
						return;
					}
				} catch (IOException e) {
					System.out.println("3333333333333333");
					signError(request, response);
					return;
				}
			}

		} else if (request.getRequestURI().indexOf("/api/") >= 0 && request.getRequestURI().indexOf("login") >= 0) {
			chain.doFilter(request, response);
		} else {
			Object obj = session.getAttribute("currentUser");
			if (null == obj) {
				if (request.getHeader("x-requested-with") != null
						&& "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
					response.setHeader("sessionstatus", "timeout");
					response.setStatus(403);
					response.addHeader("loginPath", request.getContextPath() + "/login");
					return;
				}
				response.sendRedirect(request.getContextPath() + "/login");
			} else {
				// String referer=request.getHeader("referer");
				chain.doFilter(request, response);
			}
		}

	}

	private void signError(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getMethod().equals("GET")) {
			response.setStatus(403);
			response.getOutputStream().write("[]".getBytes());
		} else {
			response.setStatus(403);
			response.getOutputStream().write(Utils.ajaxReturn(false).getBytes());
		}
	}
}
