package cn.xhy.shop.dao.impl;

import cn.xhy.shop.dao.IDetailsDAO;
import cn.xhy.shop.vo.Details;
import cn.xhy.shop.vo.Goods;
import cn.xhy.shop.vo.Orders;
import cn.xhy.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DetailsDAOImpl extends AbstractDAOImpl implements IDetailsDAO{
    public DetailsDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Details vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Details vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Details findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Details> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Details> findAllSplit(Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public boolean doCreateBatch(List<Details> vos) throws SQLException {
        String sql = "INSERT INTO details(gid,oid,gname,gprice,odnumber) VALUE(?,?,?,?,?)";
        super.prs = super.conn.prepareStatement(sql);
        Iterator<Details> iter = vos.iterator();
        while(iter.hasNext()){
            Details vo = iter.next();
            super.prs.setInt(1,vo.getGoods().getGid());
            super.prs.setInt(2,vo.getOrders().getOid());
            super.prs.setString(3,vo.getGname());
            super.prs.setDouble(4,vo.getGprice());
            super.prs.setInt(5,vo.getOdnumber());
            super.prs.addBatch();
        }
        int result[] = super.prs.executeBatch();
        for( int x = 0 ;x < result.length ; x++){
            if(result[x] == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Details> findAllByOrders(Integer oid) throws SQLException {
        List<Details> all = new ArrayList<Details>();
        String sql = "SELECT odid,gid,oid,gname,gprice,odnumber FROM details WHERE oid=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setInt(1,oid);
        ResultSet rs = super.prs.executeQuery();
        while(rs.next()){
            Details details = new Details();
            details.setOdid(rs.getInt(1));
            Goods goods = new Goods();
            goods.setGid(rs.getInt(2));
            details.setGoods(goods);
            Orders orders =new Orders();
            orders.setOid(rs.getInt(3));
            details.setOrders(orders);
            details.setGname(rs.getString(4));
            details.setGprice(rs.getDouble(5));
            details.setOdnumber(rs.getInt(6));
            all.add(details);
        }
        return all;
    }
}
