package cn.xhy.shop.service.front;

import cn.xhy.shop.vo.Shopcar;

import java.util.Map;
import java.util.Set;

public interface IShopcarServiceFront {
    /**
     * 实现购物车列表功能,调用如下DAO层方法:<br>
     *     <li>调用IShopcarDAO.findAllByMember()方法,查询用户所有购物车数据</li>
     *     <li>调用IGoodsDAO.findAllByShopcar()方法,查询购物车商品详细信息</li>
     * @param mid
     * @return 结果以Map集合返回:<br>
     *     <li>key = allCars、value = IShopcarDAO.findAllByMember(),类型为Map</li>
     *     <li>key = allGoods、value = IGoodsDAO.findAllByShopcar(),类型为List</li>
     * @throws Exception
     */
    Map<String,Object> list(String mid) throws Exception;

    /**
     * 实现添加商品到购物车功能,调用如下DAO层方法:<br>
     *     <li>调用IShopcarDAO.isByMemberAndGoods()方法判断购物车商品是否存在</li>
     *     <li>若商品存已在，则调用IShopcarDAO.doUpdateAmount()方法增加购物车商品数量</li>
     *     <li>若商品不存在，调用IShopcarDAO.doCreate()方法,添加购物车</li>
     * @param vo
     * @return
     * @throws Exception
     */
    boolean addCar(Shopcar vo) throws Exception;

    /**
     * 批量删除购物车数据
     * @param mid
     * @param gids
     * @return
     * @throws Exception
     */
    boolean delete(String mid,Set<Integer> gids) throws Exception;

    /**
     * 实现购物车更新操作,需要调用如下DAO层操作:<br>
     *     <li>调用IShopcarDAO.doRemoveByMember()方法,清空用户购物车</li>
     *     <li>调用IShopcarDAO.doCreateBatch(),批量添加数据到购物车</li>
     * @param mid
     * @param vos
     * @return
     * @throws Exception
     */
    boolean update(String mid,Set<Shopcar> vos) throws Exception;
}
