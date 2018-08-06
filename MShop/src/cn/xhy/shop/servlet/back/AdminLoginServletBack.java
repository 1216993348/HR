package cn.xhy.shop.servlet.back;

import cn.xhy.shop.factory.ServiceBackFactory;
import cn.xhy.shop.vo.Admin;
import cn.xhy.util.MD5Code;
import cn.xhy.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name="AdminLoginServletBack" ,urlPatterns = "/pages/back/AdminLoginServletBack/*")
public class AdminLoginServletBack extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        String path = null;
        switch (status){
            case "login" :
                path = this.login(req);
                break;
            case "logout" :
                path = this.logout(req);
                break;
            default:
                path = "/pages/errors.jsp" ;
        }
        req.getRequestDispatcher(path).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
    private String login(HttpServletRequest req){
        String msg = null;
        String url = null;
        String aid = req.getParameter("aid");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        if(ValidateUtil.ValidateEmpty(aid) &&
                ValidateUtil.ValidateEmpty(password) &&
                ValidateUtil.ValidateEmpty(code)){
            if(ValidateUtil.ValidateSame(code,(String)req.getSession().getAttribute("rand"))){
                Admin vo = new Admin();
                vo.setAid(aid);
                vo.setApassword(new MD5Code().getMD5ofStr(password));
                try {
                    if(ServiceBackFactory.getIAdminServiceBackInstance().login(vo)){
                        msg = "登陆成功";
                        url = "/pages/back/admin/index.jsp" ;
                        req.getSession().setAttribute("aid",aid);
                        req.getSession().setAttribute("lastdate",vo.getLastdate());
                    }else{
                        msg = "用户名或密码错误!";
                        url = "/pages/back/login.jsp" ;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                msg = "验证码不正确,请重新输入!";
                url = "/pages/back/login.jsp" ;
            }
        }else {
            msg = "您输入的信息不符合要求,请重新输入!";
            url = "/pages/back/login.jsp" ;
        }
        req.setAttribute("msg",msg);
        req.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }
    private String logout(HttpServletRequest request){
        request.getSession().invalidate();
        request.setAttribute("msg", "管理员注销成功!");
        request.setAttribute("url", "/pages/back/login.jsp");
        return "/pages/forward.jsp" ;
    }
}
