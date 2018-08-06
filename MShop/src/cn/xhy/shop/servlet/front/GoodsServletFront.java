package cn.xhy.shop.servlet.front;


import cn.xhy.shop.factory.ServiceBackFactory;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet( name = "GoodsServletFront" ,urlPatterns = "/pages/front/goods/GoodsServletFront/*")
public class GoodsServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = null;
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1) ;
        switch (status) {
            case "list" :
                path = this.list(request);
                break;
            case "show" :
                path = this.show(request);
                break;
            default:
                path = "/pages/errors.jsp";
                break;
        }
        request.getRequestDispatcher(path).forward(request,response);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse reps) throws ServletException, IOException {
        doGet(req,reps);
    }
    private String show(HttpServletRequest request){
        String gid = request.getParameter("gid");
        if(ValidateUtil.ValidateRegex(gid,"\\d+")){
            try {
                request.setAttribute("goods",ServiceFrontFactory.getIGoodsServiceFrontInstance().show(Integer.parseInt(gid)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/pages/front/goods/goods_show.jsp";
        }
        return "/pages/errors.jsp" ;
    }
    private String list(HttpServletRequest request){
        String iid = request.getParameter("iid");
        int currentPage = 1 ;
        int pageSize = 10 ;
        String column = null ;
        String keyWord = null ;
        String columnData = "商品名:gtitle" ;
        try {
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        } catch (Exception e) {}
        try {
            pageSize = Integer.parseInt(request.getParameter("ps")) ;
        } catch (Exception e) {}
            column = request.getParameter("col") ;
        keyWord = request.getParameter("kw") ;
        if (column == null) {
            column = "gtitle" ;
        }
        if (keyWord == null) {
            keyWord = "" ;  // 表示查询全部
        }
        try {
                Map<String,Object> map = null;
                if("".equals(iid) || "0".equals(iid) || !ValidateUtil.ValidateRegex(iid,"\\d+")){
                    map = ServiceFrontFactory.getIGoodsServiceFrontInstance().list(currentPage,pageSize,column,keyWord);
                    request.setAttribute("iid",0);
                } else {
                    map = ServiceFrontFactory.getIGoodsServiceFrontInstance().listByItem(Integer.parseInt(iid),currentPage,pageSize,column,keyWord);
                    request.setAttribute("iid",iid);
                }
                request.setAttribute("allItems",map.get("allItems"));
                request.setAttribute("allGoods",map.get("allGoods"));
                request.setAttribute("allRecorders",map.get("goodsCount"));
            }
        catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("column", column);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("columnData", columnData);
        request.setAttribute("url", "/pages/front/goods/GoodsServletFront/list");
        request.setAttribute("paramName", "iid");
        request.setAttribute("paramValue", String.valueOf(iid));
        return "/pages/front/goods/goods_list.jsp" ;
    }

}
