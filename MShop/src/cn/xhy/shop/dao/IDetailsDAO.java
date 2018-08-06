package cn.xhy.shop.dao;

import cn.xhy.shop.vo.Details;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IDetailsDAO extends IDAO<Integer,Details>{
    boolean doCreateBatch(List<Details> vos) throws SQLException;
    List<Details> findAllByOrders(Integer oid) throws SQLException;
}
