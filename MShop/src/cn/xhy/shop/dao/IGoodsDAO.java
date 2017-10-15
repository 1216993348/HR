package cn.xhy.shop.dao;

import cn.xhy.shop.vo.Goods;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IGoodsDAO extends IDAO<Integer,Goods> {
    List<Goods> findAllByStatus(Integer status, Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception;
    Integer getAllCountByStatus(Integer status,String column,String keyWord) throws Exception;
    boolean doUpdateStatus(Set<Integer> ids, Integer status) throws Exception;
    List<Goods> findAllByItem(Integer iid,Integer status, Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception;
    Integer getAllCountByItem(Integer iid,Integer status, String column, String keyWord) throws Exception;
    boolean doUpdateBrow(Integer gid) throws Exception;
    List<Goods> findAllByShopcar(Set<Integer> ids) throws Exception;
    boolean doUpdateAmount(Integer gid,Integer num) throws SQLException;
}
