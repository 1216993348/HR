package cn.xhy.shop.factory;

import cn.xhy.shop.service.back.*;
import cn.xhy.shop.service.back.impl.*;

public class ServiceBackFactory {
    public static IAdminServiceBack getIAdminServiceBackInstance(){
        return new AdminServiceBackImpl();
    }
    public static IMemberServiceBack getIMemberServiceBackInstance(){return new MemberServiceBackImpl();}
    public static IItemServiceBack getIItemServiceBackInstance(){
        return new ItemServiceBackImpl();
    }
    public static IGoodsServiceBack getIGoodsServiceBackInstance(){
        return new GoodsServiceBackImpl();
    }
    public static IOrdersServiceBack getIOrdersServiceBackInstance(){return new OrderServiceBackImpl();}
}
