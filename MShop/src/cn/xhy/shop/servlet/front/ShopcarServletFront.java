package cn.xhy.shop.servlet.front;

import cn.xhy.shop.factory.ServiceFrontFactory;
import cn.xhy.shop.util.ShopcarCookieUtil;
import cn.xhy.shop.vo.Goods;
import cn.xhy.shop.vo.Member;
import cn.xhy.shop.vo.Shopcar;
import cn.xhy.util.BasePath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "ShopcarServletFront",urlPatterns = "/pages/front/shopcar/ShopcarServletFront/*")
public class ShopcarServletFront extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = null;
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1) ;
        switch (status) {
            case "insert" :
                path = this.insert(request,response);
                break;
            case "list" :
                path = this.list(request);
                break;
            case "update" :
                path = this.update(request,response);
                break;
            case "delete" :
                path = this.delete(request,response);
                break;
            default:
                path = "/pages/errors.jsp";
                break;
        }
        if(path != null){
            request.getRequestDispatcher(path).forward(request,response);
        }
    }
    private String delete(HttpServletRequest request,HttpServletResponse response){
        String mid =(String) request.getSession().getAttribute("mid");
        String msg = null;
        String url = null;
        String result[] = request.getParameter("ids").split("_");
        Set<Integer> set = new HashSet<>();
            for (int x = 0 ;x < result.length ; x++){
                set.add(Integer.parseInt(result[x]));
            }
        try {
            if(ServiceFrontFactory.getIShopcarFrontServiceInstance().delete(mid,set)){
                msg = "商品删除成功!";
            }else {
                msg = "商品删除失败!";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url","/pages/front/shopcar/ShopcarServletFront/list");
        return "/pages/forward.jsp";
    }
    private String update(HttpServletRequest request,HttpServletResponse response){
        String mid =(String) request.getSession().getAttribute("mid");
        Set<Shopcar> vos = new HashSet<>();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()){
            String temp = enu.nextElement();
            Integer gid = Integer.parseInt(temp);
            Integer count = Integer.parseInt(request.getParameter(temp));
            Shopcar vo = new Shopcar();
            Member member = new Member();
            member.setMid(mid);
            vo.setMember(member);
            Goods goods = new Goods();
            goods.setGid(gid);
            vo.setGoods(goods);
            vo.setAmount(count);
            vos.add(vo);
        }
        try {
            if(ServiceFrontFactory.getIShopcarFrontServiceInstance().update(mid,vos)){
                request.setAttribute("msg","购物车修改成功");
            }else{
                request.setAttribute("msg","购物车修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("url","/pages/front/shopcar/ShopcarServletFront/list");
        return "/pages/forward.jsp" ;
    }
    private String list(HttpServletRequest request){
        String mid =(String) request.getSession().getAttribute("mid");
        Map<String,Object> map = null;
        try {
            map = ServiceFrontFactory.getIShopcarFrontServiceInstance().list(mid);
            request.setAttribute("allGoods",map.get("allGoods"));
            request.setAttribute("allCars",map.get("allCars"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/front/shopcar/shopcar_list.jsp";
    }
    private String insert(HttpServletRequest request,HttpServletResponse response){
       String referer = request.getHeader("referer");
       if(referer != null){
           Shopcar vo = new Shopcar();
           String mid =(String) request.getSession().getAttribute("mid");
           Member member = new Member();
           member.setMid(mid);
           vo.setMember(member);
           Integer gid =Integer.parseInt(request.getParameter("gid"));
           Goods goods = new Goods();
           goods.setGid(gid);
           vo.setGoods(goods);
           try {
               if(ServiceFrontFactory.getIShopcarFrontServiceInstance().addCar(vo)){
                   request.setAttribute("msg","购物车添加成功");
               }else{
                   request.setAttribute("msg","购物车添加失败");
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           request.setAttribute("url","/pages/front/goods/GoodsServletFront" + referer.substring(referer.lastIndexOf("/")));
           return "/pages/forward.jsp";
       }
       return "/pages/errors.jsp";
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
