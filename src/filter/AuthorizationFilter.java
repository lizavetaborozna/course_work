package filter;

import model.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();
        String user = (String) session.getAttribute("user");
        String role = (String) session.getAttribute("role");
        if (user == null) {
            httpServletResponse.sendRedirect("/");
        } else {
            String requestURI = httpServletRequest.getRequestURI();
            switch (role) {
                case UserRole.ADMIN:
                    if (requestURI.startsWith("/admin")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        httpServletResponse.sendRedirect("/admin");
                    }
                    break;
                case UserRole.USER:
                    if (requestURI.startsWith("/user")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        httpServletResponse.sendRedirect("/user");
                    }
                    break;
//                default:
//                    httpServletResponse.sendRedirect("/");
//                    break;
            }
        }
    }
    @Override
    public void destroy() {
    }
}