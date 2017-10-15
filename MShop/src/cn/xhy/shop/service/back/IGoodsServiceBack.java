package cn.xhy.shop.service.back;

import cn.xhy.shop.vo.Goods;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IGoodsServiceBack {
    boolean insert(Goods vo) throws Exception;
    boolean update(Goods vo) throws Exception;
    boolean delete(Set<Integer> ids) throws Exception;
    Map<String,Object> list(int currentPage, int pageSize, String column, String keyWord) throws Exception;
    Map<String,Object> listStatus(int status, int currentPage, int pageSize, String column, String keyWord) throws Exception;
    boolean updateUp(Set<Integer> ids) throws Exception ;
    boolean updateDown(Set<Integer> ids) throws Exception ;
    boolean updateDelete(Set<Integer> ids) throws Exception ;
    Map<String,Object> updatePre(Integer gid) throws Exception;
}
