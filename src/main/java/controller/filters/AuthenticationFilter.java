package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.StringUtils;

public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Cast the request and response to HttpServletRequest and HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Get the requested URI
		String uri = req.getRequestURI();

		// Immediately allows requests for CSS files and the index page to pass
		// through without further checks.
		// If you want other files to directly open, include them in this
		// condition
		if (uri.endsWith(".css") || uri.endsWith(StringUtils.URL_INDEX)) {
			// it delegates control to the next filter in the chain, or if this filter is
			// the last one in the chain, to the servlet that services the incoming request.
			chain.doFilter(request, response);
			return;
		}

		// Check if the requested URI indicates a login page (e.g., /login.jsp)
		boolean isLogin = uri.endsWith(StringUtils.URL_LOGIN);

		// Check if the requested URI indicates a login servlet (e.g., /login)
		boolean isLoginServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGIN);

		// Check if the requested URI indicates a logout servlet (e.g., /logout)
		boolean isLogoutServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGOUT);

		// Attempt to retrieve the current session associated with the request.
		// If 'false' is passed as an argument and no session exists, it returns null.
		HttpSession session = req.getSession(false);

		// Check if a session exists and if the 'USERNAME' attribute is set in the
		// session.
		// If both conditions are true, it indicates that the user is logged in.
		boolean isLoggedIn = session != null && session.getAttribute(StringUtils.USERNAME) != null;

		// If the user is not logged in and the requested URI does not indicate an
		// attempt to access the login page or login servlet,
		// redirect the user to the login page to authenticate.
		if (!isLoggedIn && !(isLogin || isLoginServlet)) {
			res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_LOGIN);
		}
		// If the user is logged in and the requested URI does not indicate an attempt
		// to access the login page or logout servlet,
		// redirect the user to the home page to prevent access to login-related pages.
		else if (isLoggedIn && !(!isLogin || isLogoutServlet)) {
			res.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);
		}
		// If none of the above conditions are met, allow the request to continue down
		// the filter chain.
		else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
	}
}
