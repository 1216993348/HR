package cn.xhy.shop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(filterName = "AdminLoginFilter" ,urlPatterns = "/pages/back/admin/*")
public class AdminLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if(session.getAttribute("aid") !=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            request.setAttribute("msg","您还未登录，请登录!");
            request.setAttribute("url","/pages/back/login.jsp");
            request.getRequestDispatcher("/pages/forward.jsp").forward(request,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
