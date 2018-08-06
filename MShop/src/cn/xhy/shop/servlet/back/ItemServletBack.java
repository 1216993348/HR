package cn.xhy.shop.servlet.back;

import cn.xhy.shop.factory.ServiceBackFactory;
import cn.xhy.shop.vo.Item;
import cn.xhy.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
@WebServlet(name = "ItemServletBack",urlPatterns = "/pages/back/admin/item/ItemServletBack/*")
public class ItemServletBack extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/pages/errors.jsp" ;
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1) ;
        switch (status){
            case "list":
                path = this.list(req) ;
                break;
            case "insert":
                path = this.insert(req) ;
                break;
            case "update":
                path = this.update(req) ;
                break;
            case "delete":
                path = this.delete(req) ;
                break;
        }
        req.getRequestDispatcher(path).forward(req ,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    private String list(HttpServletRequest request){
        try {
            request.setAttribute("allItems", ServiceBackFactory.getIItemServiceBackInstance().list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/item/item_list.jsp" ;
    }
    private String insert(HttpServletRequest request){
        String msg = null;
        String url = "/pages/back/admin/item/item_insert.jsp";
        String ititle = request.getParameter("ititle");
        if( ValidateUtil.ValidateEmpty(ititle)){
            Item vo = new Item();
            vo.setItitle(ititle);
            try {
                if(ServiceBackFactory.getIItemServiceBackInstance().insert(vo)){
                    msg = "添加商品类型成功!" ;
                }else{
                    msg = "添加商品类型失败!" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            msg = "信息错误,添加失败" ;
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }
    private String update(HttpServletRequest request){
        String msg = null;
        String url = "/pages/back/admin/item/ItemServletBack/list";
        String iid = request.getParameter("iid");
        String ititle = request.getParameter("ititle");
        if(ValidateUtil.ValidateRegex(iid,"\\d+") && ValidateUtil.ValidateEmpty(ititle)){
            Item vo = new Item();
            vo.setIid(Integer.parseInt(iid));
            vo.setItitle(ititle);
            try {
                if(ServiceBackFactory.getIItemServiceBackInstance().update(vo)){
                    msg = "修改商品类型成功!" ;
                }else{
                    msg = "修改商品类型失败!" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            msg = "信息错误,修改失败" ;
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    private String delete(HttpServletRequest request){
        String msg = null;
        String url = "/pages/back/admin/item/ItemServletBack/list";
        String data = request.getParameter("ids");

        if(ValidateUtil.ValidateEmpty(data)){
            String result[] = data.split("_");
            boolean flag = true;
            for( int x = 0 ; x < result.length ;x++){
                if(!ValidateUtil.ValidateRegex(result[x],"\\d+")){
                    flag = false;
                }
            }
            if(flag){
                Set<Integer> ids = new HashSet<Integer>() ;
                for( int x = 0 ; x < result.length ;x++){
                    ids.add(Integer.parseInt(result[x]));
                }
                try {
                    if(ServiceBackFactory.getIItemServiceBackInstance().delete(ids)){
                        msg = "商品类型删除成功" ;
                    }else {
                        msg = "商品类型删除失败" ;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                msg = "数据错误,请稍后重新操作!" ;
            }
        }else{
            msg = "请选择要删除的数据!" ;
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }
}
