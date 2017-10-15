package cn.xhy.shop.dao;

import cn.xhy.shop.vo.Shopcar;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public interface IShopcarDAO extends IDAO<Integer,Shopcar> {
    /**
     * 判断指定用户购物车商品是否存在
     * @param mid
     * @param gid
     * @return
     * @throws Exception
     */
    boolean isByMemberAndGoods(String mid,Integer gid) throws SQLException;

    /**
     * 更新指定用户购物车商品数量加1
     * @param mid
     * @param gid
     * @return
     * @throws Exception
     */
    boolean doUpdateAmount(String mid,Integer gid)throws SQLException;

    /**
     * 批量删除指定用户购物车数据
     * @param mid
     * @param gids
     * @return
     * @throws Exception
     */
    boolean doRemoveByMemberAndGoods(String mid,Set<Integer> gids) throws SQLException;

    /**
     * 删除一个用户的所有购物车信息,用于更新购物车操作
     * @param mid
     * @return
     * @throws Exception
     */
    boolean doRemoveByMember(String mid) throws SQLException;

    /**
     * 批量增加用用户购物车数据
     * @param vos
     * @return
     * @throws Exception
     */
    boolean doCreateBatch(Set<Shopcar> vos) throws SQLException;

    /**
     * 查询一个用户的所有购物车信息
     * @param mid
     * @return
     * @throws Exception
     */
    Map<Integer,Integer> findAllByMember(String mid) throws SQLException;

}
