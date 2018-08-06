package cn.xhy.shop.dao.impl;

import cn.xhy.shop.dao.IOrdersDAO;
import cn.xhy.shop.vo.Member;
import cn.xhy.shop.vo.Orders;
import cn.xhy.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class OrdersDAOImpl extends AbstractDAOImpl implements IOrdersDAO {
    public OrdersDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Orders vo) throws SQLException {
        String sql = "INSERT INTO orders(mid,mname,mphone,maddress,credate,mpay) VALUE(?,?,?,?,?,?)";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1, vo.getMember().getMid());
        super.prs.setString(2, vo.getMember().getMname());
        super.prs.setString(3, vo.getMember().getMphone());
        super.prs.setString(4, vo.getMember().getMaddress());
        super.prs.setTimestamp(5, new Timestamp(new Date().getTime()));
        super.prs.setDouble(6, vo.getMpay());
        return super.prs.executeUpdate() == 1;
    }

    @Override
    public Integer findLastInsertId() throws Exception {
        String sql = "SELECT LAST_INSERT_ID()";
        super.prs = super.conn.prepareStatement(sql);
        ResultSet rs = super.prs.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public List<Orders> findByMember(String mid, Integer currentPage, Integer pageSize) throws SQLException {
        List<Orders> all = new ArrayList<Orders>();
        String sql = "SELECT oid,mid,mname,mphone,maddress,credate,mpay FROM orders WHERE mid=? LIMIT ?,?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1, mid);
        super.prs.setInt(2, (currentPage - 1) * pageSize);
        super.prs.setInt(3, pageSize);
        ResultSet rs = super.prs.executeQuery();
        while (rs.next()) {
            Orders orders = new Orders();
            orders.setOid(rs.getInt(1));
            Member member = new Member();
            member.setMid(rs.getString(2));
            orders.setMember(member);
            orders.setMname(rs.getString(3));
            orders.setMphone(rs.getString(4));
            orders.setMaddress(rs.getString(5));
            orders.setCredate(rs.getTimestamp(6));
            orders.setMpay(rs.getDouble(7));
            all.add(orders);
        }
        return all;
    }

    @Override
    public Integer getCountByMember(String mid) throws Exception {
        String sql = "SELECT COUNT(*) FROM orders WHERE mid=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1, mid);
        ResultSet rs = super.prs.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public Orders findById(String mid, Integer gid) throws Exception {
        Orders orders = null;
        String sql = "SELECT oid,mid,mname,mphone,maddress,credate,mpay FROM orders WHERE oid=? AND mid=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setInt(1, gid);
        super.prs.setString(2,mid);
        ResultSet rs = super.prs.executeQuery();
        while (rs.next()) {
            orders = new Orders();
            orders.setOid(rs.getInt(1));
            Member member = new Member();
            member.setMid(rs.getString(2));
            orders.setMember(member);
            orders.setMname(rs.getString(3));
            orders.setMphone(rs.getString(4));
            orders.setMaddress(rs.getString(5));
            orders.setCredate(rs.getTimestamp(6));
            orders.setMpay(rs.getDouble(7));
        }
        return orders;
    }

    @Override
    public boolean doUpdate(Orders vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return super.removeHandle("orders","oid",ids);
    }

    @Override
    public Orders findById(Integer id) throws Exception {
        Orders orders = null;
        String sql = "SELECT oid,mid,mname,mphone,maddress,credate,mpay FROM orders WHERE oid=? ";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setInt(1, id);
        ResultSet rs = super.prs.executeQuery();
        while (rs.next()) {
            orders = new Orders();
            orders.setOid(rs.getInt(1));
            Member member = new Member();
            member.setMid(rs.getString(2));
            orders.setMember(member);
            orders.setMname(rs.getString(3));
            orders.setMphone(rs.getString(4));
            orders.setMaddress(rs.getString(5));
            orders.setCredate(rs.getTimestamp(6));
            orders.setMpay(rs.getDouble(7));
        }
        return orders;
    }

    @Override
    public List<Orders> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Orders> findAllSplit(Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        List<Orders> all = new ArrayList<Orders>();
        String sql = "SELECT oid,mid,mname,mphone,maddress,credate,mpay FROM orders WHERE " + column + " LIKE ? LIMIT ?,?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1, "%" + keyWord + "%");
        super.prs.setInt(2, (currentPage - 1) * pageSize);
        super.prs.setInt(3, pageSize);
        ResultSet rs = super.prs.executeQuery();
        while (rs.next()) {
            Orders orders = new Orders();
            orders.setOid(rs.getInt(1));
            Member member = new Member();
            member.setMid(rs.getString(2));
            orders.setMember(member);
            orders.setMname(rs.getString(3));
            orders.setMphone(rs.getString(4));
            orders.setMaddress(rs.getString(5));
            orders.setCredate(rs.getTimestamp(6));
            orders.setMpay(rs.getDouble(7));
            all.add(orders);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.countHandle("orders",column,keyWord);
    }
}
