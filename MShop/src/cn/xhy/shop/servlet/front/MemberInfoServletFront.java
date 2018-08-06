package cn.xhy.shop.servlet.front;


import cn.xhy.shop.factory.ServiceFrontFactory;
import cn.xhy.shop.vo.Member;
import cn.xhy.util.BasePath;
import cn.xhy.util.CookieUtil;
import cn.xhy.util.MD5Code;
import cn.xhy.util.validate.ValidateUtil;
import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet( name = "MemberInfoServletFront" ,urlPatterns = "/pages/front/member/MemberInfoServletFront/*")
public class MemberInfoServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = null;
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1) ;
        switch (status) {
            case "updatePre":
                path = updatePre(req);
                break;
            case "update":
                path = update(req,resp);
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
    private String updatePre(HttpServletRequest request){
        String mid = (String)request.getSession().getAttribute("mid");
        try {
            request.setAttribute("member",ServiceFrontFactory.getIMemberServiceFrontInstance().updatePre(mid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/front/member/member_update.jsp";
    }
    private String update(HttpServletRequest request,HttpServletResponse response){
        String mid = (String)request.getSession().getAttribute("mid");
        String msg = null;
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),request,response);
            smart.upload();
            String mname = smart.getRequest().getParameter("mname");
            String mphone = smart.getRequest().getParameter("mphone");
            String maddress = smart.getRequest().getParameter("maddress");
            String mphoto = smart.getRequest().getParameter("oldPhoto");
            if(ValidateUtil.ValidateEmpty(mname)
                    && ValidateUtil.ValidateEmpty(mphone)
                    && ValidateUtil.ValidateEmpty(maddress)
                    && ValidateUtil.ValidateEmpty(mphoto)){
                Member vo = new Member();
                vo.setMid(mid);
                vo.setMname(mname);
                vo.setMphone(mphone);
                vo.setMaddress(maddress);
                vo.setMphoto(mphoto);
                if(smart.getFiles().getFile(0).getSize() > 0){
                    if(smart.getFiles().getFile(0).getContentType().contains("image")){
                        vo.setMphoto(UUID.randomUUID().toString() + "." + smart.getFiles().getFile(0).getFileExt());
                    }
                }
                if(ServiceFrontFactory.getIMemberServiceFrontInstance().update(vo)){
                    msg = "个人信息更新成功！";
                    if(!mphoto.equals(vo.getMphoto())){
                        String filePath = super.getServletContext().getRealPath("/upload/member/") + vo.getMphoto();
                        System.out.println(filePath);
                        smart.getFiles().getFile(0).saveAs(filePath);
                        System.out.println(smart.getFiles().getFile(0).getSubTypeMIME());
                        request.getSession().setAttribute("mphoto", vo.getMphoto());
                        if(!"nophoto.jpg".equals(vo.getMphoto())){
                            System.out.println(new File(super.getServletContext().getRealPath("/upload/member/") + mphoto).delete());
                        }
                    }

                }else {
                    msg = "个人信息更新失败！";
                }
            }else {
                msg = "输入的信息错误，请重新输入!";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url","/pages/front/member/MemberInfoServletFront/updatePre");
        return "/pages/forward.jsp";
    }
}
