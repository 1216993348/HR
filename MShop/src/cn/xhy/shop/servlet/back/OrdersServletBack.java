package cn.xhy.shop.servlet.back;

import cn.xhy.shop.exception.UnCompleteMemberInformationException;
import cn.xhy.shop.exception.UnEnoughAmountException;
import cn.xhy.shop.factory.ServiceBackFactory;
import cn.xhy.shop.factory.ServiceFrontFactory;
import cn.xhy.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "OrdersServletBack",urlPatterns = "/pages/back/admin/orders/OrdersServletBack/*")
public class OrdersServletBack extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = null;
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        switch (status) {
            case "list":
                path = this.list(request);
                break;
            case "show":
                path = this.show(request);
                break;
            default:
                path = "/pages/errors.jsp";
                break;
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
    private String show(HttpServletRequest request){
        Integer oid = Integer.parseInt(request.getParameter("oid"));
        try {
            request.setAttribute("orders",ServiceBackFactory.getIOrdersServiceBackInstance().show(oid));
            System.out.println(ServiceBackFactory.getIOrdersServiceBackInstance().show(oid));
            System.out.println(oid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/orders/orders_show.jsp";
    }
    private String list(HttpServletRequest request){
        int currentPage = 1 ;
        int pageSize = 10 ;
        String column = null ;
        String keyWord = null ;
        String columnData = "姓名:mname" ;
        try {
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        } catch (Exception e) {}
        try {
            pageSize = Integer.parseInt(request.getParameter("ps")) ;
        } catch (Exception e) {}
        column = request.getParameter("col") ;
        keyWord = request.getParameter("kw");
        if (column == null) {
            column = "mid" ;
        }
        if (keyWord == null) {
            keyWord = "" ;  // 表示查询全部
        }
        try {
            Map<String,Object> map= ServiceBackFactory.getIOrdersServiceBackInstance().list(currentPage,pageSize,column,keyWord);
            request.setAttribute("allOrders",map.get("allOrders"));
            request.setAttribute("allRecorders",map.get("ordersCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url", "/pages/back/admin/orders/OrdersServletBack/list");
        return "/pages/back/admin/orders/orders_list.jsp";
    }

}
