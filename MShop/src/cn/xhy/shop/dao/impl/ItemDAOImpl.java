package cn.xhy.shop.dao.impl;

import cn.xhy.shop.dao.IItemDAO;
import cn.xhy.shop.vo.Item;
import cn.xhy.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ItemDAOImpl extends AbstractDAOImpl implements IItemDAO {
    public ItemDAOImpl(Connection conn) {
        super(conn);
    }


    @Override
    public boolean doCreate(Item vo) throws Exception {
        String sql = "INSERT INTO item(ititle) VALUES(?)";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setString(1,vo.getItitle());
        return super.prs.executeUpdate() == 1;
    }

    @Override
    public boolean doUpdate(Item vo) throws Exception {
        String sql = "UPDATE item SET ititle=? WHERE iid=?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setString(1,vo.getItitle());
        super.prs.setInt(2,vo.getIid());
        return super.prs.executeUpdate() == 1;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        StringBuffer sql = new StringBuffer("DELETE FROM item WHERE iid IN(") ;
        Iterator<Integer> iter = ids.iterator();
        while (iter.hasNext()){
            sql.append(iter.next()).append(",");
        }
        sql.delete(sql.length() - 1,sql.length()).append(")");
        super.prs = super.conn.prepareStatement(sql.toString()) ;
        return  super.prs.executeUpdate() == ids.size();
    }

    @Override
    public Item findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Item> findAll() throws Exception {
        List<Item> all= new ArrayList<Item>();
        String sql = "SELECT iid,ititle FROM item " ;
        super.prs = super.conn.prepareStatement(sql) ;
        ResultSet rs = super.prs.executeQuery();
        while(rs.next()){
            Item vo = new Item() ;
            vo.setIid(rs.getInt(1));
            vo.setItitle(rs.getString(2));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Item> findAllSplit(Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.countHandle("goods",column,keyWord) ;
    }
}
