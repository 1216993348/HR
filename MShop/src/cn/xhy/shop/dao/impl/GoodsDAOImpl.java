package cn.xhy.shop.dao.impl;

import cn.xhy.shop.dao.IGoodsDAO;
import cn.xhy.shop.vo.Admin;
import cn.xhy.shop.vo.Goods;
import cn.xhy.shop.vo.Item;
import cn.xhy.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GoodsDAOImpl extends AbstractDAOImpl implements IGoodsDAO {
    public GoodsDAOImpl(Connection conn) {
        super(conn);
    }
    @Override
    public boolean doCreate(Goods vo) throws Exception {
        String sql = "INSERT INTO goods(iid,aid,gtitle,gpubdate,gprice,gamount,gphoto,gbrow,gnote,gstatus) VALUES(?,?,?,?,?,?,?,?,?,?)";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setInt(1,vo.getItem().getIid());
        super.prs.setString(2,vo.getAdmin().getAid());
        super.prs.setString(3,vo.getGtitle());
        super.prs.setTimestamp(4,new Timestamp(vo.getGpubdate().getTime()));
        super.prs.setDouble(5,vo.getGprice());
        super.prs.setInt(6,vo.getGamount());
        super.prs.setString(7,vo.getGphoto());
        super.prs.setInt(8,vo.getGbrow());
        super.prs.setString(9,vo.getGnote());
        super.prs.setInt(10,vo.getGstatus());
        return super.prs.executeUpdate() == 1;
    }

    @Override
    public boolean doUpdate(Goods vo) throws Exception {
        String sql = "UPDATE goods SET iid=?,aid=?,gtitle=?,gprice=?,gamount=?,gphoto=?,gnote=?,gstatus=? WHERE gid=?" ;
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setInt(1,vo.getItem().getIid());
        super.prs.setString(2,vo.getAdmin().getAid());
        super.prs.setString(3,vo.getGtitle());
        super.prs.setDouble(4,vo.getGprice());
        super.prs.setInt(5,vo.getGamount());
        super.prs.setString(6,vo.getGphoto());
        super.prs.setString(7,vo.getGnote());
        super.prs.setInt(8,vo.getGstatus());
        super.prs.setInt(9,vo.getGid());
        return super.prs.executeUpdate() == 1;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return super.removeHandle("goods","gid",ids);
    }

    @Override
    public Goods findById(Integer id) throws Exception {
        Goods vo = null;
        String sql = "SELECT gid,iid,aid,gtitle,gpubdate,gprice,gamount,gphoto,gbrow,gnote,gstatus FROM goods WHERE gid=?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setInt(1,id);
        ResultSet rs = this.prs.executeQuery();
        if(rs.next()){
            vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item() ;
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin() ;
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setGtitle(rs.getString(4));
            vo.setGpubdate(rs.getTimestamp(5));
            vo.setGprice(rs.getDouble(6));
            vo.setGamount(rs.getInt(7));
            vo.setGphoto(rs.getString(8));
            vo.setGbrow(rs.getInt(9));
            vo.setGnote(rs.getString(10));
            vo.setGstatus(rs.getInt(11));
        }
            return vo;
    }

    @Override
    public List<Goods> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Goods> findAllSplit(Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        List<Goods> all = new ArrayList<Goods>();
        String sql = "SELECT gid,iid,aid,gtitle,gpubdate,gprice,gamount,gphoto,gbrow,gnote,gstatus FROM goods WHERE " + column + " LIKE ? AND gstatus<>2 LIMIT ?,?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setString(1,"%"+keyWord+"%");
        super.prs.setInt(2,(currentPage - 1) * pageSize);
        super.prs.setInt(3,pageSize);
        ResultSet rs = super.prs.executeQuery();
        while(rs.next()){
            Goods vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item() ;
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin() ;
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setGtitle(rs.getString(4));
            vo.setGpubdate(rs.getTimestamp(5));
            vo.setGprice(rs.getDouble(6));
            vo.setGamount(rs.getInt(7));
            vo.setGphoto(rs.getString(8));
            vo.setGbrow(rs.getInt(9));
            vo.setGnote(rs.getString(10));
            vo.setGstatus(rs.getInt(11));
            all.add(vo) ;
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM goods WHERE gstatus<>2 AND " + column +" LIKE ?" ;
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setString(1,"%"+keyWord+"%");
        ResultSet rs = super.prs.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public List<Goods> findAllByStatus(Integer status, Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        List<Goods> all = new ArrayList<Goods>();
        String sql = "SELECT gid,iid,aid,gtitle,gpubdate,gprice,gamount,gphoto,gbrow,gnote,gstatus FROM goods WHERE " + column + " LIKE ? AND gstatus=? LIMIT ?,?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setString(1,"%"+keyWord+"%");
        super.prs.setInt(2,status);
        super.prs.setInt(3,(currentPage - 1) * pageSize);
        super.prs.setInt(4,pageSize);
        ResultSet rs = super.prs.executeQuery();
        while(rs.next()){
            Goods vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item() ;
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin() ;
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setGtitle(rs.getString(4));
            vo.setGpubdate(rs.getTimestamp(5));
            vo.setGprice(rs.getDouble(6));
            vo.setGamount(rs.getInt(7));
            vo.setGphoto(rs.getString(8));
            vo.setGbrow(rs.getInt(9));
            vo.setGnote(rs.getString(10));
            vo.setGstatus(rs.getInt(11));
            all.add(vo) ;
        }
        return all;
    }

    @Override
    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM goods WHERE gstatus =? AND " + column +" LIKE ?" ;
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setInt(1,status);
        super.prs.setString(2,"%"+keyWord+"%");
        ResultSet rs = super.prs.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doUpdateStatus(Set<Integer> ids, Integer status) throws Exception {
        StringBuffer sql  = new StringBuffer("UPDATE goods SET gstatus=? WHERE gid IN(");
        Iterator<Integer> iter = ids.iterator();
        while(iter.hasNext()){
            sql.append(iter.next()).append(",");
        }
        sql.delete(sql.length()-1,sql.length()).append(")");
        super.prs = super.conn.prepareStatement(sql.toString()) ;
        super.prs.setInt(1,status);
        return super.prs.executeUpdate() == ids.size() ;
    }

    @Override
    public List<Goods> findAllByItem(Integer iid, Integer status, Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        List<Goods> all = new ArrayList<Goods>();
        String sql = "SELECT gid,iid,aid,gtitle,gpubdate,gprice,gamount,gphoto,gbrow,gnote,gstatus FROM goods WHERE iid=? AND gstatus=? AND " + column + " LIKE ?" + " LIMIT ?,?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setInt(1,iid);
        super.prs.setInt(2,status);
        super.prs.setString(3,"%"+keyWord+"%");
        super.prs.setInt(4,(currentPage - 1 ) * pageSize);
        super.prs.setInt(5,pageSize);
        ResultSet rs = super.prs.executeQuery();
        while(rs.next()){
            Goods vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item() ;
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin() ;
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setGtitle(rs.getString(4));
            vo.setGpubdate(rs.getTimestamp(5));
            vo.setGprice(rs.getDouble(6));
            vo.setGamount(rs.getInt(7));
            vo.setGphoto(rs.getString(8));
            vo.setGbrow(rs.getInt(9));
            vo.setGnote(rs.getString(10));
            vo.setGstatus(rs.getInt(11));
            all.add(vo) ;
        }
        return all;
    }

    @Override
    public Integer getAllCountByItem(Integer iid, Integer status, String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM goods WHERE gstatus =? AND " + column +" LIKE ?" + " AND iid=?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setInt(1,status);
        super.prs.setString(2,"%"+keyWord+"%");
        super.prs.setInt(3,iid);
        ResultSet rs = super.prs.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doUpdateBrow(Integer gid) throws Exception {
        String sql = "UPDATE goods SET gbrow=gbrow+1 WHERE gid=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setInt(1,gid);
        return super.prs.executeUpdate() == 1;
    }

    @Override
    public List<Goods> findAllByShopcar(Set<Integer> ids) throws Exception {
        List<Goods> all = new ArrayList<Goods>();
        StringBuffer sql = new StringBuffer("SELECT gid,iid,aid,gtitle,gpubdate,gprice,gamount,gphoto,gbrow,gnote,gstatus FROM goods WHERE gid IN(");
        Iterator iter = ids.iterator();
        while(iter.hasNext()){
            sql.append(iter.next()).append(",");
        }
        sql.delete(sql.length() - 1,sql.length()).append(")");
        super.prs = super.conn.prepareStatement(sql.toString()) ;
        ResultSet rs = super.prs.executeQuery();
        while(rs.next()){
            Goods vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item() ;
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin() ;
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setGtitle(rs.getString(4));
            vo.setGpubdate(rs.getTimestamp(5));
            vo.setGprice(rs.getDouble(6));
            vo.setGamount(rs.getInt(7));
            vo.setGphoto(rs.getString(8));
            vo.setGbrow(rs.getInt(9));
            vo.setGnote(rs.getString(10));
            vo.setGstatus(rs.getInt(11));
            all.add(vo) ;
        }
        return all;
    }

    @Override
    public boolean doUpdateAmount(Integer gid, Integer num) throws SQLException {
        String sql ="UPDATE goods SET gamount=gamount+" + num + " WHERE gid=?";
        super.prs =super.conn.prepareStatement(sql);
        super.prs.setInt(1,gid);
        return super.prs.executeUpdate() == 1;
    }
}
