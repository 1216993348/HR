package cn.xhy.shop.factory;

import cn.xhy.shop.service.front.IGoodsServiceFront;
import cn.xhy.shop.service.front.IMemberServiceFront;
import cn.xhy.shop.service.front.IOrdersServiceFront;
import cn.xhy.shop.service.front.IShopcarServiceFront;
import cn.xhy.shop.service.front.impl.GoodsServiceFrontImpl;
import cn.xhy.shop.service.front.impl.MemberServiceFrontImpl;
import cn.xhy.shop.service.front.impl.OrdersServiceFrontImpl;
import cn.xhy.shop.service.front.impl.ShopcarServiceFrontImpl;

public class ServiceFrontFactory {
    public static IMemberServiceFront getIMemberServiceFrontInstance(){
        return new MemberServiceFrontImpl();
    }
    public static IGoodsServiceFront getIGoodsServiceFrontInstance(){
        return new GoodsServiceFrontImpl();
    }
    public static IShopcarServiceFront getIShopcarFrontServiceInstance(){
        return new ShopcarServiceFrontImpl();
    }
    public static IOrdersServiceFront getIOrdersServiceFrontInstance(){return new OrdersServiceFrontImpl();}
}
