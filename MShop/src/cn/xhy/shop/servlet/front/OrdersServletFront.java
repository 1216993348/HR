package cn.xhy.shop.servlet.front;

import cn.xhy.shop.exception.UnCompleteMemberInformationException;
import cn.xhy.shop.exception.UnEnoughAmountException;
import cn.xhy.shop.factory.ServiceFrontFactory;
import cn.xhy.shop.vo.Goods;
import cn.xhy.shop.vo.Member;
import cn.xhy.shop.vo.Shopcar;
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
@WebServlet(name = "OrdersServletFront",urlPatterns = "/pages/front/orders/OrdersServletFront/*")
public class OrdersServletFront extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = null;
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        switch (status) {
            case "insert":
                path = this.insert(request);
                break;
            case "list":
                path = this.list(request);
                break;
            case "show":
                path = this.show(request);
                break;
            case "revoke":
                path = this.revoke(request);
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
    private String revoke(HttpServletRequest request){
        String msg = null;
        if(ValidateUtil.ValidateRegex(request.getParameter("oid"),"\\d+")){
            Integer oid = Integer.parseInt(request.getParameter("oid"));
            try {
                if(ServiceFrontFactory.getIOrdersServiceFrontInstance().revoke(oid)){
                    msg = "订单撤销成功";
                }else {
                    msg = "订单撤销失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url","/pages/front/orders/OrdersServletFront/list");
        return "/pages/forward.jsp";
    }
    private String list(HttpServletRequest request){
        String mid = (String)request.getSession().getAttribute("mid");
        int currentPage = 1 ;
        int pageSize = 10 ;
        try {
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        } catch (Exception e) {}
        try {
            pageSize = Integer.parseInt(request.getParameter("ps")) ;
        } catch (Exception e) {}
        try {
            if(mid != null){
                Map<String,Object> map = ServiceFrontFactory.getIOrdersServiceFrontInstance().list(mid,currentPage,pageSize);
                request.setAttribute("allOrders",map.get("allOrders"));
                request.setAttribute("allRecorders",map.get("ordersCount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("url", "/pages/front/orders/OrdersServletFront/list");
        return "/pages/front/orders/orders_list.jsp";
    }
    private String show(HttpServletRequest request){
        String mid =(String)request.getSession().getAttribute("mid");
        Integer oid = Integer.parseInt(request.getParameter("oid"));
        try {
            request.setAttribute("orders",ServiceFrontFactory.getIOrdersServiceFrontInstance().show(mid,oid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/front/orders/orders_show.jsp";
    }
    private String insert(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String mid = (String) request.getSession().getAttribute("mid");
        Map<Integer, Integer> allCars = new HashMap<>();
        Enumeration<String> enu = request.getParameterNames();
        boolean flag = false;
        while (enu.hasMoreElements()) {
            String temp = enu.nextElement();
            if( temp.startsWith("orders-")){
                String gid = temp.substring(temp.lastIndexOf("-") + 1);
                String count = request.getParameter(temp);
                if(ValidateUtil.ValidateRegex(gid,"\\d+")
                        && ValidateUtil.ValidateRegex(count,"\\d+")){
                    allCars.put(Integer.parseInt(gid), Integer.parseInt(count));
                    flag = true ;
                }else {
                    msg = "数据错误,创建订单失败！";
                    flag = false ;
                    break;
                }
            }

        }
        if(flag){
            try {
                if(ServiceFrontFactory.getIOrdersServiceFrontInstance().insert(mid, allCars)){
                    msg = "创建订单成功，请及时支付！";
                    url = "/index.jsp";
                }else {
                    msg = "创建订单失败!";
                    url = "/index.jsp";
                }
            }catch (UnCompleteMemberInformationException e){
                msg = "用户信息不完整,不能创建订单！";
                url = "/pages/front/member/MemberInfoServletFront/updatePre";
            } catch (UnEnoughAmountException e) {
                msg = "商品库存不足,请修改商品数量!";
                url = "/pages/front/shopcar/ShopcarServletFront/list";
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
}
