package cn.xhy.shop.dao.impl;

import cn.xhy.shop.dao.IShopcarDAO;
import cn.xhy.shop.vo.Shopcar;
import cn.xhy.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ShopcarDAOImpl extends AbstractDAOImpl implements IShopcarDAO {

    public ShopcarDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean isByMemberAndGoods(String mid, Integer gid) throws SQLException {
        String sql = "SELECT COUNT(*) FROM shopcar WHERE mid=? AND gid=?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setString(1,mid);
        super.prs.setInt(2,gid);
        ResultSet rs = super.prs.executeQuery();
        if(rs.next()){
            return rs.getInt(1) > 0;
        }
        return false;
    }

    @Override
    public boolean doUpdateAmount(String mid, Integer gid) throws SQLException {
        String sql = "UPDATE shopcar SET amount=amount+1 WHERE mid=? AND gid=?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setString(1,mid);
        super.prs.setInt(2,gid);
        return super.prs.executeUpdate() == 1;
    }

    @Override
    public boolean doRemoveByMemberAndGoods(String mid, Set<Integer> gids) throws SQLException {
        if( gids.size() == 0){
            return false;
        }
        StringBuffer sql = new StringBuffer("DELETE FROM shopcar WHERE mid=? AND gid IN(");
        Iterator<Integer> iter = gids.iterator();
        while(iter.hasNext()){
            sql.append(iter.next()).append(",");
        }
        sql.delete(sql.length() - 1 ,sql.length()).append(")");
        super.prs = super.conn.prepareStatement(sql.toString());
        super.prs.setString(1,mid);
        return super.prs.executeUpdate() == gids.size();
    }

    @Override
    public boolean doRemoveByMember(String mid) throws SQLException {
        String sql = "DELETE FROM shopcar WHERE mid=? ";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1,mid);
        return super.prs.executeUpdate() > 0;
    }

    @Override
    public boolean doCreateBatch(Set<Shopcar> vos) throws SQLException {
        String sql = "INSERT INTO shopcar(mid,gid,amount) VALUE(?,?,?)";
        super.prs = super.conn.prepareStatement(sql);
        Iterator<Shopcar> iter = vos.iterator();
        while (iter.hasNext()){
            Shopcar vo = iter.next();
            super.prs.setString(1,vo.getMember().getMid());
            super.prs.setInt(2,vo.getGoods().getGid());
            super.prs.setInt(3,vo.getAmount());
            super.prs.addBatch();
        }
        int result[] = super.prs.executeBatch();
        for(int x= 0 ;x < result.length ; x++){
            if(result[x] == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public Map<Integer, Integer> findAllByMember(String mid) throws SQLException {
        Map<Integer, Integer> map = new HashMap<>();
        String sql = "SELECT gid,amount FROM shopcar WHERE mid=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1,mid);
        ResultSet rs = super.prs.executeQuery();
        while (rs.next()){
            map.put(rs.getInt(1),rs.getInt(2));
        }
        return map;
    }

    @Override
    public boolean doCreate(Shopcar vo) throws Exception {
        String sql = "INSERT INTO shopcar(mid,gid,amount) VALUE(?,?,?)";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1,vo.getMember().getMid());
        super.prs.setInt(2,vo.getGoods().getGid());
        super.prs.setInt(3,vo.getAmount());
        return super.prs.executeUpdate() == 1;
    }

    @Override
    public boolean doUpdate(Shopcar vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Shopcar findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Shopcar> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Shopcar> findAllSplit(Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
