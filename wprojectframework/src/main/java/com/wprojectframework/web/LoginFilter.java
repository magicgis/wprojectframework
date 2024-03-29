package com.wprojectframework.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * <pre>
 * framework 用户过滤器
 * 主要用来过滤当前会话域是否存在用户对象，以
 * 判断用户是否登录
 * </pre>
 * @author  WuJ
 * @version v1.0
 * @date    2014年1月23日
 * @see     Filter
 * @since   JDK1.6
 */
public class LoginFilter implements Filter{
	
	/**
	 * session中登录对象key
	 */
	private static final String SESSION_KEY = "sessionKey";
	
	/**
	 * 登录路径key
	 */
	private static final String LOGIN_KEY = "login";
	
	/**
	 * 重定向值key
	 */
	private static final String REDIRECT_KEY = "redirectKey";
	
	/**
	 * 重定向值
	 */
	private static final String REDIRECT_VALUE = "redirectValue";
	
	/**
	 * 默认首页面
	 */
	private static final String INDEX = "index";
	
	private String sessionKey;
	private String login;
	private String redirectKey;
	private String redirectValue;
	private String index;

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		Object obj = req.getSession().getAttribute(sessionKey);
		String path = req.getContextPath();
		String reqUri = req.getRequestURI();
		
		if(StringUtils.isNotEmpty(login) && reqUri.endsWith(login)){
			chain.doFilter(req, resp);
			return;
		}
		if(StringUtils.isNotEmpty(redirectKey) && reqUri.endsWith(redirectKey)){
			resp.sendRedirect(path+redirectValue);
			return;
		}
		if(null == obj){
			resp.sendRedirect(path+index);
		}else{
			chain.doFilter(req, resp);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		sessionKey = filterConfig.getInitParameter(SESSION_KEY);
		login = filterConfig.getInitParameter(LOGIN_KEY);
		redirectKey = filterConfig.getInitParameter(REDIRECT_KEY);
		redirectValue = filterConfig.getInitParameter(REDIRECT_VALUE);
		index = filterConfig.getInitParameter(INDEX);
	}

}
