package cn.xhy.shop.servlet.back;

import cn.xhy.shop.factory.ServiceBackFactory;
import cn.xhy.shop.vo.Admin;
import cn.xhy.shop.vo.Goods;
import cn.xhy.shop.vo.Item;
import cn.xhy.util.validate.ValidateUtil;
import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet(name="GoodsServletBack",urlPatterns = "/pages/back/admin/goods/GoodsServletBack/*")
public class GoodsServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp" ;
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1) ;
        if("insertPre".equals(status)){
            path = insertPre(request) ;
        }if("insert".equals(status)){
            path = insert(request,response) ;
        }else if("update".equals(status)){
            path =this.update(request,response) ;
        }else if("delete".equals(status)){
            path =this.delete(request) ;
        }else if("list".equals(status)){
            path =this.list(request) ;
        }else if("listStatus".equals(status)){
            path =this.listStatus(request) ;
        }else if("updateStatus".equals(status)){
            path =this.updateStatus(request) ;
        }else if("updatePre".equals(status)){
            path =this.updatePre(request) ;
        }

        request.getRequestDispatcher(path).forward(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    private String delete(HttpServletRequest request){
        String msg = null ;
        String url = null ;
        String referer = request.getHeader("referer");
        if(ValidateUtil.ValidateEmpty(referer)){
            String result = request.getParameter("ids");
            if(ValidateUtil.ValidateEmpty(result)){
                Set<Integer> ids = new HashSet<Integer>();
                Set<String> photos = new HashSet<String>();
                String data[] = result.split("_");
                for (int x = 0 ;x < data.length ; x++){
                    String temp[] = data[x].split(":");
                    if(ValidateUtil.ValidateRegex(temp[0],"\\d+")){
                        ids.add(Integer.parseInt(temp[0]));
                    }
                    if(!"nophoto.jpg".equals(temp[1])){
                        photos.add(temp[1]);
                    }
                }
                try {
                    if(ServiceBackFactory.getIGoodsServiceBackInstance().delete(ids)){
                        msg = "删除成功!";
                        Iterator iter = photos.iterator();
                        while(iter.hasNext()){
                            String filePath = super.getServletContext().getRealPath("/upload/goods/") + iter.next();
                            new File(filePath).delete();
                        }
                    }else{
                        msg = "删除失败" ;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            url = "/pages/back/admin/goods/GoodsServletBack" + referer.substring(referer.lastIndexOf("/"));
            request.setAttribute("msg",msg);
            request.setAttribute("url",url);
            return "/pages/forward.jsp";
        }
        return "/pages/errors.jsp";
    }
    private String listStatus(HttpServletRequest request){
        int status = 0 ;
        int currentPage = 1 ;
        int pageSize = 10 ;
        String column = null ;
        String keyWord = null ;
        String columnData = "商品名:gtitle|上架管理员:aid" ;
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
            column = "gid" ;
        }
        if (keyWord == null) {
            keyWord = "" ;  // 表示查询全部
        }
        try {
            Map<String,Object> map = ServiceBackFactory.getIGoodsServiceBackInstance().listStatus(status,currentPage,pageSize,column,keyWord) ;
            request.setAttribute("allGoods",map.get("allGoods"));
            request.setAttribute("allRecorders",map.get("goodsCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","pages/back/admin/goods/GoodsServletBack/listStatus");
        request.setAttribute("paramName","status");
        request.setAttribute("paramValue",String.valueOf(status));
        return "/pages/back/admin/goods/goods_list.jsp" ;
    }
    private String insertPre(HttpServletRequest request){
        try {
            request.setAttribute("allItems",ServiceBackFactory.getIItemServiceBackInstance().list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/goods/goods_insert.jsp" ;
    }
    private String list(HttpServletRequest request){
        int currentPage = 1 ;
        int pageSize = 10 ;
        String column = null ;
        String keyWord = null ;
        String columnData = "商品名:gtitle|上架管理员:aid" ;
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
            Map<String,Object> map = ServiceBackFactory.getIGoodsServiceBackInstance().list(currentPage,pageSize,column,keyWord) ;
            request.setAttribute("allGoods",map.get("allGoods"));
            request.setAttribute("allRecorders",map.get("goodsCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","pages/back/admin/goods/GoodsServletBack/list");
        return "/pages/back/admin/goods/goods_list.jsp" ;
    }
    private String insert(HttpServletRequest request,HttpServletResponse response){
        String msg = null;
        String url = "/pages/back/admin/goods/GoodsServletBack/insertPre";
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),request,response);
            smart.upload();
            String iid = smart.getRequest().getParameter("iid") ;
            String gtitle = smart.getRequest().getParameter("gtitle") ;
            String gprice = smart.getRequest().getParameter("gprice") ;
            String gamount = smart.getRequest().getParameter("gamount") ;
            String gstatus = smart.getRequest().getParameter("gstatus");
            if(ValidateUtil.ValidateRegex(iid,"\\d+")
                    && ValidateUtil.ValidateEmpty(gtitle)
                    && ValidateUtil.ValidateRegex(gprice,"\\d+(\\.\\d{1,2})?")
                    && ValidateUtil.ValidateRegex(gamount,"\\d+")
                    && ValidateUtil.ValidateRegex(gstatus,"\\d")){
                Goods vo = new Goods() ;
                vo.setGtitle(gtitle);
                vo.setGprice(Double.parseDouble(gprice));
                vo.setGamount(Integer.parseInt(gamount));
                vo.setGpubdate(new Date());
                vo.setGbrow(0);
                vo.setGstatus(Integer.parseInt(gstatus));
                if(smart.getRequest().getParameter("gnote") != null){
                    vo.setGnote(smart.getRequest().getParameter("gnote"));
                }
                Item item = new Item();
                item.setIid(Integer.parseInt(iid));
                vo.setItem(item);
                Admin admin = new Admin();
                admin.setAid((String)request.getSession().getAttribute("aid"));
                vo.setAdmin(admin);
                if(smart.getFiles().getFile(0).getSize() > 0){
                    if(smart.getFiles().getFile(0).getContentType().contains("image")){
                        vo.setGphoto(UUID.randomUUID().toString() + "." + smart.getFiles().getFile(0).getFileExt());
                    }else{
                        vo.setGphoto("nophoto.jpg");
                    }
                }else{
                    vo.setGphoto("nophoto.jpg");
                }
                if(ServiceBackFactory.getIGoodsServiceBackInstance().insert(vo)){
                    msg = "商品信息增加成功!";
                    if(!"nophoto.jpg".equals(vo.getGphoto())){
                        String filePath = super.getServletContext().getRealPath("/upload/goods/") + vo.getGphoto();
                        smart.getFiles().getFile(0).saveAs(filePath);
                    }
                }else{
                    msg = "商品信息增加失败!";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }
    private String updateStatus(HttpServletRequest request){
        String msg = null;
        String url = "/pages/errors.jsp";
        String referer = request.getHeader("referer");
        String type = request.getParameter("type");
        String data = request.getParameter("ids");
        if(ValidateUtil.ValidateEmpty(data)){
            String result[] = data.split("_");
            Set<Integer> ids = new HashSet<Integer>();
            for(int x = 0 ; x < result.length ;x ++){
                String temp[] = result[x].split(":");
                ids.add(Integer.parseInt(temp[0]));
            }
            try {
                if("up".equals(type)){
                    if(ServiceBackFactory.getIGoodsServiceBackInstance().updateUp(ids)){
                        msg = "上架成功!" ;
                    }else{
                        msg = "上架失败" ;
                    }
                }
                if("down".equals(type)){
                    if(ServiceBackFactory.getIGoodsServiceBackInstance().updateDown(ids)){
                        msg = "下架成功!" ;
                    }else{
                        msg = "下架失败" ;
                    }
                }
                if("delete".equals(type)){
                    if(ServiceBackFactory.getIGoodsServiceBackInstance().updateDelete(ids)){
                        msg = "成功移动到回收站" ;
                    }else{
                        msg = "操作失败" ;
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(referer != null){
            url = "/pages/back/admin/goods/GoodsServletBack" + referer.substring(referer.lastIndexOf("/"));
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }
    private String updatePre(HttpServletRequest request){
        String gid = request.getParameter("gid");
        String referer = request.getHeader("referer") ;
        if(ValidateUtil.ValidateRegex(gid,"\\d+") && ValidateUtil.ValidateEmpty(referer)){
            try {
                Map<String ,Object> map = ServiceBackFactory.getIGoodsServiceBackInstance().updatePre(Integer.parseInt(gid));
                request.setAttribute("goods",map.get("goods"));
                request.setAttribute("allItems",map.get("allItems"));
                request.setAttribute("back",referer.substring(referer.lastIndexOf("/")));
                return "/pages/back/admin/goods/goods_update.jsp";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "/pages/errors.jsp" ;
    }
    private String update(HttpServletRequest request,HttpServletResponse response){
        String msg = null;
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),request,response);
            smart.upload();
            String gid = smart.getRequest().getParameter("gid") ;
            String iid = smart.getRequest().getParameter("iid") ;
            String gtitle = smart.getRequest().getParameter("gtitle") ;
            String gprice = smart.getRequest().getParameter("gprice") ;
            String gamount = smart.getRequest().getParameter("gamount") ;
            String gstatus = smart.getRequest().getParameter("gstatus");
            if(ValidateUtil.ValidateRegex(gid,"\\d+")
                    &&ValidateUtil.ValidateRegex(iid,"\\d+")
                    && ValidateUtil.ValidateEmpty(gtitle)
                    && ValidateUtil.ValidateRegex(gprice,"\\d+(\\.\\d{1,2})?")
                    && ValidateUtil.ValidateRegex(gamount,"\\d+")
                    && ValidateUtil.ValidateRegex(gstatus,"\\d")){
                Goods vo = new Goods() ;
                vo.setGid(Integer.parseInt(gid));
                vo.setGtitle(gtitle);
                vo.setGprice(Double.parseDouble(gprice));
                vo.setGamount(Integer.parseInt(gamount));
                vo.setGstatus(Integer.parseInt(gstatus));
                if(smart.getRequest().getParameter("gnote") != null){
                    vo.setGnote(smart.getRequest().getParameter("gnote"));
                }
                Item item = new Item();
                item.setIid(Integer.parseInt(iid));
                vo.setItem(item);
                Admin admin = new Admin();
                admin.setAid((String)request.getSession().getAttribute("aid"));
                vo.setAdmin(admin);
                String oldPhoto = smart.getRequest().getParameter("oldPhoto") ;
                if(smart.getFiles().getFile(0).getSize() > 0){
                    if(smart.getFiles().getFile(0).getContentType().contains("image")){
                        vo.setGphoto(UUID.randomUUID().toString() + "." + smart.getFiles().getFile(0).getFileExt());
                    }else{
                        vo.setGphoto(oldPhoto);
                    }
                }else{
                    vo.setGphoto(oldPhoto);
                }
                if(ServiceBackFactory.getIGoodsServiceBackInstance().update(vo)){
                    msg = "商品信息修改成功!";
                    if(!vo.getGphoto().equals(oldPhoto)){ //判断是否上传新图片
                        String filePath = super.getServletContext().getRealPath("/upload/goods/") + vo.getGphoto();
                        smart.getFiles().getFile(0).saveAs(filePath);
                        if(!"nophoto.jpg".equals(oldPhoto)) {//如果上传新图片判断是否可以删除
                            new File(super.getServletContext().getRealPath("/upload/goods/") + oldPhoto).delete();
                        }
                    }
                }else{
                    msg = "商品信息修改失败!";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url","/pages/back/admin/goods/GoodsServletBack"+smart.getRequest().getParameter("back"));
        return "/pages/forward.jsp" ;
    }
}
