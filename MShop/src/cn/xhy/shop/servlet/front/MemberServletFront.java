package cn.xhy.shop.servlet.front;


import cn.xhy.shop.factory.ServiceFrontFactory;
import cn.xhy.shop.vo.Member;
import cn.xhy.util.BasePath;
import cn.xhy.util.CookieUtil;
import cn.xhy.util.MD5Code;
import cn.xhy.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet( name = "MemberServletFront" ,urlPatterns = "/pages/front/MemberServletFront/*")
public class MemberServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = null;
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1) ;
        switch (status) {
            case "register":
                path = register(req);
                break;
            case "active":
                path = active(req);
                break;
            case "login":
                path = login(req,resp);
                break;
            case "logout":
                path = logout(req,resp);
                break;
            default:
                path = "/pages/errors.jsp";
                break;
        }
        req.getRequestDispatcher(path).forward(req,resp);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse reps) throws ServletException, IOException {
        doGet(req,reps);
    }
    private String register(HttpServletRequest req) {
        String mid = req.getParameter("mid");
        String password = req.getParameter("password");
        String msg = null;
        String url = null;
        if(ValidateUtil.ValidateEmpty(mid) && ValidateUtil.ValidateEmpty(password)){
            Member vo = new Member();
            vo.setMid(mid);
            vo.setMpassword(new MD5Code().getMD5ofStr(password));
            vo.setMregdate(new Date());
            vo.setMphoto("nophoto.jpg");
            vo.setMstatus(2);
            vo.setMcode(UUID.randomUUID().toString());
            try {
                if(ServiceFrontFactory.getIMemberServiceFrontInstance().register(vo)){
                    msg = "用户注册成功，请进行账户激活" ;
                    url = "/index.jsp" ;
                    System.out.println("【发生激活邮件】" + BasePath.getBasePath(req) + "/pages/front/MemberServletFront/active?mid=" + mid +"&mcode=" + vo.getMcode());
                }else{
                    msg = "用户注册失败，请填写新的注册信息" ;
                    url = "/pages/front/member_register.jsp" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            msg = "输入的用户信息不正确，请重新注册!" ;
            url = "/pages/front/member_register.jsp" ;
        }
        req.setAttribute("msg",msg);
        req.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    private String active(HttpServletRequest request){
        String msg = null;
        String url = null;
        String mid = request.getParameter("mid");
        String mcode = request.getParameter("mcode");
        if(ValidateUtil.ValidateEmpty(mid) && ValidateUtil.ValidateEmpty(mcode)){
            Member vo = new Member();
            vo.setMid(mid);
            vo.setMcode(mcode);
            try {
                if(ServiceFrontFactory.getIMemberServiceFrontInstance().active(vo)){
                    msg = "用户激活成功，请登录！";
                    url = "/pages/front/member_login.jsp";
                }else{
                    msg = "用户激活失败，请与管理员联系！";
                    url = "/index.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            msg = "激活信息错误，请重新激活！";
            url = "/index.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    private String login(HttpServletRequest request,HttpServletResponse response){
        String msg = null;
        String url = null;
        String mid = request.getParameter("mid");
        String mpassword = request.getParameter("password");
        String code = request.getParameter("code");
        String rand = (String)request.getSession().getAttribute("rand");
        if(ValidateUtil.ValidateEmpty(mid) && ValidateUtil.ValidateEmpty(mpassword) && ValidateUtil.ValidateEmpty(code)){
            Member vo = new Member();
            vo.setMid(mid);
            vo.setMpassword(new MD5Code().getMD5ofStr(mpassword));
            try {
                if(ValidateUtil.ValidateSame(code,rand)) {
                    if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)) {
                        msg = "登录成功！";
                        url = "/index.jsp";
                        request.getSession().setAttribute("mid", mid);
                        request.getSession().setAttribute("mphoto", vo.getMphoto());
                        if(request.getParameter("reme") != null){ //选择了记住密码复选框
                            int expiry = Integer.parseInt(request.getParameter("reme"));
                            CookieUtil.save(response,request,"mid",vo.getMid(),expiry);
                            CookieUtil.save(response,request,"password",vo.getMpassword(),expiry);
                        }
                    } else {
                        msg = "用户名或密码错误,请重新登录";
                        url = "/pages/front/member/member_login.jsp";
                    }
                }else {
                        msg = "验证码错误，请重新登录";
                        url = "/pages/front/member/member_login.jsp";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            msg = "登录信息有误,请输入正确登录信息!";
            url = "/pages/front/member/member_login.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    private String logout(HttpServletRequest request,HttpServletResponse response){
        String msg = "注销成功!";
        String url = "/index.jsp";
        request.getSession().removeAttribute("mid");
        CookieUtil.clean(request,response);
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
}
