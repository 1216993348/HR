package cn.xhy.shop.dao;

import cn.xhy.shop.vo.Orders;

import java.sql.SQLException;
import java.util.List;

public interface IOrdersDAO extends IDAO<Integer,Orders>{
    boolean doCreate(Orders vo) throws SQLException;
    Integer findLastInsertId() throws Exception;
    List<Orders> findByMember(String mid,Integer currentPage,Integer pageSize) throws SQLException;
    Integer getCountByMember(String mid) throws Exception;
    Orders findById(String mid,Integer gid) throws Exception;
}
