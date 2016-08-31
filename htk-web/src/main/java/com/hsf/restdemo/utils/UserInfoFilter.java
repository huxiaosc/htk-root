package com.hsf.restdemo.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.hsf.restdemo.SystemConstants;


/**
 * Filter class
 * 
 * @web.filter name="UserInfo" display-name="Name for UserInfo" description=
 *             "Description for UserInfo"
 * @web.filter-mapping url-pattern="/UserInfo"
 * @web.filter-init-param name="A parameter" value="A value"
 */
public class UserInfoFilter implements Filter {
	private FilterConfig config;

	private String excludeFilters = "/login.do,/login.jsp";

	private String CONFIG_LOCATION_DELIMITERS = ",; \t\n";

	private List<String> excludeFiltersList = new ArrayList<String>();

	public UserInfoFilter() {
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = ((HttpServletRequest) request);

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String servletPath = httpServletRequest.getServletPath().toLowerCase().trim();

		if (!excludeFiltersList.contains(servletPath)) {
			// 如果有令牌 则进行验证,验证通过直接调用后续fliter
			if (!isAcessTokenAttempt(httpServletRequest, httpServletResponse)) {
				httpServletResponse.setContentType("text/html;charset=UTF-8");
				PrintWriter out = httpServletResponse.getWriter();
				out.println("<script type='text/javascript'>alert('您的用户会话失效！');</script>");

				return;
			}

		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	/**
	 * 
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.config.getInitParameter("loginUri");
		String _excludeFilters = this.config.getInitParameter("excludeFilters");
		if (StringUtils.hasText(_excludeFilters)) {
			this.excludeFilters = _excludeFilters;
		}
		String[] excludeFiltersArray = StringUtils.tokenizeToStringArray(excludeFilters, CONFIG_LOCATION_DELIMITERS);

		for (int i = 0, j = excludeFiltersArray.length; i < j; i++) {
			excludeFiltersList.add(excludeFiltersArray[i]);
		}

	}

	/**
	 * 是否含有有效令牌
	 * 
	 * @Title: isAcessTokenAttempt
	 * @Description: TODO
	 * @param @param
	 *            request
	 * @param @param
	 *            response
	 * @param @return
	 * @return boolean
	 * @throws @author
	 *             shijiyu
	 * @date 2013-4-17 上午9:36:00
	 */
	protected boolean isAcessTokenAttempt(HttpServletRequest request, HttpServletResponse response) {
		String accessToken = getAccessTokenParameter(request);
		//取不到accessToken
		if(!StringUtils.hasText(accessToken))
			return false;
		
		String user = UserStorage.get(accessToken);
		Map<String, Object> params = JSONUtils.JsonToMap(user);
		Integer status = (Integer) params.get("status");
		// 错误
		if (status!=null && status.intValue()==401)
			return false;
		else {
			//验证用户成功，将用户JSON对象放入REQUEST->key->GLOBAL_USER,避免多次请求用户
			request.setAttribute(SystemConstants.GLOBAL_USER, user);
			return true;
		}

	}

	/**
	 * 获取请求中的登录令牌
	 * 
	 * @Title: getAccessTokenParameter
	 * @Description: TODO
	 * @param @param
	 *            request
	 * @param @return
	 * @return String
	 * @throws @author
	 *             shijiyu
	 * @date 2013-4-17 上午9:37:30
	 */
	public static String getAccessTokenParameter(HttpServletRequest httpRequest) {
		String hearder = httpRequest.getHeader(SystemConstants.ACCESS_TOKEN_HEADER);
		if (StringUtils.hasText(hearder))
			return hearder;
		Cookie cookie = CookieTool.getCookieByName(httpRequest, SystemConstants.ACCESS_TOKEN_HEADER);
		if (cookie != null) {
			hearder = cookie.getValue();
			if (StringUtils.hasText(hearder))
				return hearder;
		}
		return httpRequest.getParameter(SystemConstants.ACCESS_TOKEN_HEADER);
	}
	
	/**
	 * 
	 * @param httpRequest
	 * @return
	 */
	public static String getCurrentUser(HttpServletRequest httpRequest){
		return (String) httpRequest.getAttribute(SystemConstants.GLOBAL_USER);
	}

}
