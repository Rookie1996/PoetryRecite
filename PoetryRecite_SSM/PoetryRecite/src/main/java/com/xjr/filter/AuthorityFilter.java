package com.xjr.filter;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.xjr.dao.AdminMapper;
import com.xjr.model.SessionAdmin;
import com.xjr.model.Admin;
import com.xjr.service.IAdminService;
import com.xjr.service.impl.AdminServiceImpl;
import com.xjr.util.Constants;


public class AuthorityFilter implements Filter {
	private final static String[] static_ext = { "js", "css", "jpg", "png", "gif", "html", "ico", "vm", "swf" };
	private final static String action_ext = "action";
	private static String absolutePath = null;
	private static int count = 0;
	
	private Logger logger=LoggerFactory.getLogger(AuthorityFilter.class);
	
	private IAdminService adminService;
	
	private IAdminService getAdminService(){
		return adminService;
	}
	
	private final SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		ServletContext application = request.getSession().getServletContext();
		String req_uri = request.getRequestURI();
		HttpSession session = request.getSession();
		String type = req_uri.substring(req_uri.lastIndexOf('.') + 1);
		
		// 静态资源忽略
		if (ArrayUtils.contains(static_ext, type)) {
			chain.doFilter(req, resp);
			return;
		}
		
		if(absolutePath == null){
			absolutePath = getRealPath(request);
		}
		
		if(application.getAttribute(Constants.ABSOLUTEPATH) == null){
			application.setAttribute(Constants.ABSOLUTEPATH,absolutePath);
		}
		
		//SObject sessionUserObj = session.getAttribute(Constants.SESSION_USER_KEY);
		
		// 凡是有action后缀的都不过滤（注册，登录功能带action后缀）|| 意思第一次过滤显示登录界面，以后只要不关闭页面就不用过滤。
		if(!action_ext.equals(type)){
			
			if (AuthorityFilter.count == 0) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<script>");
				out.println("parent.location.href = ('" + request.getContextPath()
						+ "/adminController/showAdminLogin.action')");
				out.println("</script>");
				out.println("</html>");
				AuthorityFilter.count++;
				return;
			}

//			sessionUserObj = session.getAttribute(Constants.SESSION_USER_KEY);
//			if(null==sessionUserObj){
//				response.setContentType("text/html;charset=utf-8");
//				PrintWriter out = response.getWriter();  
//		        out.println("<html>");      
//		        out.println("<script>"); 
//		        out.println("alert('您的登录已过期，请重新登录')"); 
//		        out.println("parent.location.href = ('"+request.getContextPath()+"/adminController/showAdminLogin.action')");      
//		        out.println("</script>");      
//		        out.println("</html>");   
//				return;
//			}
		}
		chain.doFilter(request, resp);
		return;
	}

	
	private String getRealPath(HttpServletRequest request) {
		String port = request.getServerPort() == 80 ? "" : (":" + request.getServerPort());
		String realPath = "http://" + request.getServerName() + port + request.getContextPath();
		return realPath;
	}

	public Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	private Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	public void destroy() {
	}
	
	public void init(FilterConfig config) throws ServletException {
		
	}
}