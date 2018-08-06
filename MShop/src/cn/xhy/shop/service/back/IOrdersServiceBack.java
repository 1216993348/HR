package cn.xhy.shop.service.back;

import cn.xhy.shop.vo.Orders;

import java.util.HashMap;
import java.util.Map;

public interface IOrdersServiceBack {
    Map<String,Object> list(Integer currentPage,Integer pageSize,String column,String keyWord) throws Exception;
    Orders show(Integer oid) throws Exception;
}
