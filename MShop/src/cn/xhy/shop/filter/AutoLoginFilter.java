package cn.xhy.shop.filter;

import cn.xhy.shop.factory.ServiceFrontFactory;
import cn.xhy.shop.vo.Member;
import cn.xhy.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebFilter( filterName = "AutoLoginFilter",urlPatterns = {"/index.jsp","/pages/front/*"})
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest ;
        HttpSession session = request.getSession();
        if(session.getAttribute("mid") == null){
            Map<String,String> map = CookieUtil.load(request);
            if(map.containsKey("mid") && map.containsKey("password")) {
                Member vo = new Member();
                vo.setMid(map.get("mid"));
                vo.setMpassword(map.get("password"));
                try {
                    if(ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)){
                        session.setAttribute("mid",vo.getMid());
                        session.setAttribute("mphoto",vo.getMphoto());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
