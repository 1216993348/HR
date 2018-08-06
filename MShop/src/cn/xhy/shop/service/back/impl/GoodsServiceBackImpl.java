package cn.xhy.shop.service.back.impl;
import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.factory.DAOFactory;
import cn.xhy.shop.service.back.IGoodsServiceBack;
import cn.xhy.shop.vo.Goods;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GoodsServiceBackImpl implements IGoodsServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(Goods vo) throws Exception {
        try{
            return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doCreate(vo);
        }finally {
            dbc.close();
        }
    }

    @Override
    public boolean update(Goods vo) throws Exception {
        try{
            return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdate(vo);
        }finally {
            dbc.close();
        }
    }

    @Override
    public boolean delete(Set<Integer> ids) throws Exception {
        try{
            return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
        }finally {
            dbc.close();
        }
    }

    @Override
    public Map<String,Object> list(int currentPage, int pageSize, String column, String keyWord) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allGoods",DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage,pageSize,column,keyWord));
            map.put("goodsCount",DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCount(column,keyWord));
            return map;
        }finally {
            dbc.close();
        }
    }

    @Override
    public Map<String, Object> listStatus(int status, int currentPage, int pageSize, String column, String keyWord) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allGoods",DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByStatus(status,currentPage,pageSize,column,keyWord));
            map.put("goodsCount",DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCountByStatus(status,column,keyWord));
            return map;
        }finally {
            dbc.close();
        }
    }

    @Override
    public boolean updateUp(Set<Integer> ids) throws Exception {
        try{
             return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids,1);
        }finally {
            dbc.close();
        }
    }

    @Override
    public boolean updateDown(Set<Integer> ids) throws Exception {
        try{
            return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids,0);
        }finally {
            dbc.close();
        }
    }

    @Override
    public boolean updateDelete(Set<Integer> ids) throws Exception {
        try{
            return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids,2);
        }finally {
            dbc.close();
        }
    }

    @Override
    public Map<String, Object> updatePre(Integer gid) throws Exception {
        try{
            Map<String ,Object> map = new HashMap<String,Object>();
            map.put("goods",DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findById(gid));
            map.put("allItems",DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
            return map;
        }finally {
            dbc.close();
        }
    }
}
