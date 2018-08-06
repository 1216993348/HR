package cn.xhy.shop.servlet.back;

import cn.xhy.shop.factory.ServiceBackFactory;
import cn.xhy.shop.factory.ServiceBackFactory;
import cn.xhy.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet(name="MemberServletBack",urlPatterns = "/pages/back/admin/member/MemberServletBack/*")
public class MemberServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp" ;
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1) ;
        if (status != null) {
            if ("list".equals(status)) {
                path = this.list(request);
            } else if ("listStatus".equals(status)) {
                path = this.listStatus(request) ;
            } else if ("updateStatus".equals(status)) {
                path = this.updateStatus(request) ;
            } else if ("show".equals(status)) {
                path = this.show(request) ;
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    private String show(HttpServletRequest request){
        String mid = request.getParameter("mid") ;

        if(ValidateUtil.ValidateEmpty(mid)){
            try {
                request.setAttribute("member",ServiceBackFactory.getIMemberServiceBackInstance().show(mid));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/pages/back/admin/member/member_show.jsp" ;
        }else {
            request.setAttribute("msg","提交数据错误！");
            request.setAttribute("url","/pages/back/admin/member/MemberServletBack" + request.getHeader("referer").substring(request.getHeader("referer").lastIndexOf("/")));
            return "/pages/forward.jsp" ;
        }

    }
    public String list(HttpServletRequest request) {
        int currentPage = 1 ;
        int pageSize = 10 ;
        String column = null ;
        String keyWord = null ;
        String columnData = "用户名:mid|真实姓名:mname|联系电话:mphone|地址:maddress" ;
        try {
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        } catch (Exception e) {}
        try {
            pageSize = Integer.parseInt(request.getParameter("ps")) ;
        } catch (Exception e) {}
        column = request.getParameter("col") ;
        keyWord = request.getParameter("kw") ;
        if (column == null) {
            column = "mid" ;
        }
        if (keyWord == null) {
            keyWord = "" ;  // 表示查询全部
        }
        try {
            Map<String,Object> map = ServiceBackFactory.getIMemberServiceBackInstance().list(currentPage,pageSize,column,keyWord) ;
            request.setAttribute("allMembers",map.get("allMembers"));
            request.setAttribute("allRecorders",map.get("memberCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","pages/back/admin/member/MemberServletBack/list");
        return "/pages/back/admin/member/member_list.jsp" ;
    }
    public String listStatus(HttpServletRequest request) {
        int status = 0 ;
        int currentPage = 1 ;
        int pageSize = 10 ;
        String column = null ;
        String keyWord = null ;
        String columnData = "用户名:mid|真实姓名:mname|联系电话:mphone|地址:maddress" ;
        try {
            status = Integer.parseInt(request.getParameter("status")) ;
        } catch (Exception e) {}
        try {
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        } catch (Exception e) {}
        try {
            pageSize = Integer.parseInt(request.getParameter("ps")) ;
        } catch (Exception e) {}
        column = request.getParameter("col") ;
        keyWord = request.getParameter("kw") ;
        if (column == null) {
            column = "mid" ;
        }
        if (keyWord == null) {
            keyWord = "" ;  // 表示查询全部
        }
        try {
            Map<String,Object> map = ServiceBackFactory.getIMemberServiceBackInstance().listByStatus(status,currentPage,pageSize,column,keyWord) ;
            request.setAttribute("allMembers",map.get("allMembers"));
            request.setAttribute("allRecorders",map.get("memberCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","pages/back/admin/member/MemberServletBack/listStatus");
        request.setAttribute("paramName","status");
        request.setAttribute("paramValue",String.valueOf(status));
        return "/pages/back/admin/member/member_list.jsp" ;
    }
    public String updateStatus(HttpServletRequest request) {
        String msg = null;
        String referer = request.getHeader("referer");
        String url = null;
        if(referer != null){
            url = "/pages/back/admin/member/MemberServletBack" + referer.substring(referer.lastIndexOf("/"));
        }
        String type = request.getParameter("type") ;
        String data = request.getParameter("ids");
        if(ValidateUtil.ValidateEmpty(type) && ValidateUtil.ValidateEmpty(data)){
            String id[] = data.split("_");
            Set<String> ids = new HashSet<String>();
            for(int x = 0 ;x < id.length ;x++ ){
                ids.add(id[x]);
            }
            try {
                if("active".equalsIgnoreCase(type)){
                    if (ServiceBackFactory.getIMemberServiceBackInstance().updateActive(ids)) {
                        msg = "用户激活成功!" ;
                    }else {
                        msg = "用户激活失败!" ;
                    }
                }else if("lock".equalsIgnoreCase(type)) {
                    if (ServiceBackFactory.getIMemberServiceBackInstance().updateLock(ids)) {
                        msg = "用户锁定成功!" ;
                    }else {
                        msg = "用户锁定失败!" ;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            msg = "请选择要操作的数据!" ;
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }
}
