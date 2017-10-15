package cn.xhy.shop.service.front.impl;

import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.factory.DAOFactory;
import cn.xhy.shop.service.front.IShopcarServiceFront;
import cn.xhy.shop.vo.Goods;
import cn.xhy.shop.vo.Shopcar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShopcarServiceFrontImpl implements IShopcarServiceFront {
    DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public Map<String, Object> list(String mid) throws Exception {
        try {
            Map<String, Object> map = new HashMap<>();
            Map<Integer,Integer> allCars = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findAllByMember(mid);
            List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByShopcar(allCars.keySet());
            map.put("allCars",allCars);
            map.put("allGoods",allGoods);
            return map;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean addCar(Shopcar vo) throws Exception {
        try {
            if(DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).isByMemberAndGoods(vo.getMember().getMid(),vo.getGoods().getGid())){
                //如果购物车商品信息存在,则更新商品数量
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doUpdateAmount(vo.getMember().getMid(),vo.getGoods().getGid());
            }else{
                //如果购物车商品信息不存在,则添加
                vo.setAmount(1);
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean delete(String mid, Set<Integer> gids) throws Exception {
        try {
            return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMemberAndGoods(mid,gids);
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(String mid, Set<Shopcar> vos) throws Exception {
        try {
            if(DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMember(mid)){
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doCreateBatch(vos);
            }
            return false;
        }finally {
            this.dbc.close();
        }
    }
}
