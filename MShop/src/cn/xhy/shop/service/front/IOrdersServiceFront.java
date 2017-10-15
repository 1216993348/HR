package cn.xhy.shop.service.front;

import cn.xhy.shop.exception.UnCompleteMemberInformationException;
import cn.xhy.shop.exception.UnEnoughAmountException;
import cn.xhy.shop.vo.Details;
import cn.xhy.shop.vo.Orders;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOrdersServiceFront {
    /**
     * 实现创建订单操作
     * @param mid
     * @param allCars
     * @return
     * @throws UnCompleteMemberInformationException 如果用户个人信息不完整,则抛出此异常
     * @throws UnEnoughAmountException 如果商品库存量不完整，则抛出此异常
     * @throws SQLException SQL执行异常
     */
    boolean insert(String mid,Map<Integer,Integer> allCars)throws UnCompleteMemberInformationException,UnEnoughAmountException,Exception;
    Map<String,Object> list(String mid,int currentPage,int pageSize) throws Exception;
    Orders show(String mid,Integer oid) throws Exception;
    boolean revoke(Integer oid) throws Exception;
}
