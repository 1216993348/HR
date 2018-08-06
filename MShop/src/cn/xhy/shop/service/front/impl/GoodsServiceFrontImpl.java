package cn.xhy.shop.service.front.impl;

import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.factory.DAOFactory;
import cn.xhy.shop.service.front.IGoodsServiceFront;
import cn.xhy.shop.vo.Goods;

import java.util.HashMap;
import java.util.Map;

public class GoodsServiceFrontImpl implements IGoodsServiceFront {
    DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public Map<String, Object> list(int currentPage, int pageSize, String column, String keyWord) throws Exception {
        try{
            Map map = new HashMap<String,Object>();
            map.put("allItems",DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allGoods",DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByStatus(1,currentPage,pageSize,column,keyWord));
            map.put("goodsCount",DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCountByStatus(1,column,keyWord));
            return map;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listByItem(int iid, int currentPage, int pageSize, String column, String keyWord) throws Exception {
        try{
            Map map = new HashMap<String,Object>();
            map.put("allItems",DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allGoods",DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByItem(iid,1,currentPage,pageSize,column,keyWord));
            map.put("goodsCount",DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCountByItem(iid,1,column,keyWord));
            return map;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Goods show(int gid) throws Exception {
        try{
           Goods vo = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findById(gid);
           if(vo != null){
               vo.setItem(DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findById(gid));
               DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateBrow(gid);
           }
           return vo;
        }finally {
            this.dbc.close();
        }
    }
}
