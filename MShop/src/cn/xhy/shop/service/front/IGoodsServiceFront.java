package cn.xhy.shop.service.front;

import cn.xhy.shop.vo.Goods;

import java.util.Map;

public interface IGoodsServiceFront {
    /**
     * 实现商品信息的查询操作,需要调用如下DAO层方法 <br>
     *     <li>调用IItemDAO.findAll()方法查询所有商品分类</li>
     *     <li>调用IGoodsDAO.findAllSplit()方法分页查询商品信息</li>
     *     <li>调用IGoodsDAO.getAllCount()方法查询商品数量</li>
     * @param currentPage
     * @param pageSize
     * @param column
     * @param keyWord
     * @return  结果以map集合返回: <br>
     *     <li>key = "allItems" value = IItemDAO.findAll(),保存的类型是List<Item></list></li>
     *     <li>key = "allGoods" value = IGoodsDAO.findAllSplit(),保存的类型是List<Goods></li>
     *     <li>key = "goodsCount" value = IGoodsDAO.getAllCount(),保存的类型是Integer</li>
     * @throws Exception
     */
    Map<String,Object> list(int currentPage,int pageSize, String column, String keyWord) throws Exception;

    Map<String,Object> listByItem(int iid,int currentPage,int pageSize, String column, String keyWord) throws Exception;
    Goods show(int gid) throws Exception;
}
