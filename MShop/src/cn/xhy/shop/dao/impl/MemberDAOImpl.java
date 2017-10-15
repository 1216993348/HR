package cn.xhy.shop.dao.impl;

import cn.xhy.shop.dao.IMemberDAO;
import cn.xhy.shop.vo.Member;
import cn.xhy.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MemberDAOImpl extends AbstractDAOImpl implements IMemberDAO {
    public MemberDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Member vo) throws Exception {
        String sql = "INSERT INTO member(mid,mpassword,mcode,mstatus,mregdate,mphoto) VALUES(?,?,?,?,?,?)" ;
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1,vo.getMid());
        super.prs.setString(2,vo.getMpassword());
        super.prs.setString(3,vo.getMcode());
        super.prs.setInt(4,vo.getMstatus());
        super.prs.setTimestamp(5,new Timestamp(vo.getMregdate().getTime()));
        super.prs.setString(6,vo.getMphoto());
        return this.prs.executeUpdate() == 1;
    }

    @Override
    public boolean doUpdate(Member vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        return false;
    }

    @Override
    public Member findById(String id) throws Exception {
        Member vo = null;
        String sql = "SELECT mid,mpassword,mname,mphone,maddress,mstatus,mcode,mregdate,mphoto FROM member WHERE mid=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1,id);
        ResultSet rs = super.prs.executeQuery();
        if(rs.next()){
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setMpassword(rs.getString(2));
            vo.setMname(rs.getString(3));
            vo.setMphone(rs.getString(4));
            vo.setMaddress(rs.getString(5));
            vo.setMstatus(rs.getInt(6));
            vo.setMcode(rs.getString(7));
            vo.setMregdate(rs.getTimestamp(8));
            vo.setMphoto(rs.getString(9));
        }
        return vo;
    }

    @Override
    public List<Member> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Member> findAllSplit(Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        List<Member> all = new ArrayList<Member>();
        String sql = "SELECT mid,mpassword,mname,mphone,maddress,mstatus,mcode,mregdate,mphoto FROM member WHERE " + column + " LIKE ? LIMIT ?,?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setString(1,"%" + keyWord + "%");
        super.prs.setInt(2,(currentPage - 1) * pageSize);
        super.prs.setInt(3,pageSize);
        ResultSet rs = super.prs.executeQuery();
        while (rs.next()){
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setMpassword(rs.getString(2));
            vo.setMname(rs.getString(3));
            vo.setMphone(rs.getString(4));
            vo.setMaddress(rs.getString(5));
            vo.setMstatus(rs.getInt(6));
            vo.setMcode(rs.getString(7));
            vo.setMregdate(rs.getTimestamp(8));
            vo.setMphoto(rs.getString(9));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.countHandle("member",column,keyWord);
    }

    @Override
    public boolean isCode(String mid, String code) throws Exception {
        String sql = "SELECT COUNT(*) FROM member WHERE mid=? AND mcode=? AND mstatus=2";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1,mid);
        super.prs.setString(2,code);
        ResultSet rs = super.prs.executeQuery();
        if(rs.next()){
            return rs.getInt(1) == 1;
        }
        if(rs   !=   null){
                rs.close();
        }
        if(super.prs!=   null){
                super.prs.close();
        }
        return false;
    }

    @Override
    public boolean doUpdateStatus(String mid, Integer mstatus) throws Exception {
        String sql = "UPDATE member SET mstatus=? WHERE mid=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setInt(1,mstatus);
        super.prs.setString(2,mid);
        return super.prs.executeUpdate() == 1;
    }

    @Override
    public boolean isLogin(Member vo) throws Exception {
        boolean flag = false;
        String sql= "SELECT mphoto FROM member WHERE mid=? AND mpassword=? AND mstatus=1";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1,vo.getMid());
        super.prs.setString(2,vo.getMpassword());
        ResultSet rs = super.prs.executeQuery();
        if(rs.next()){
            flag = true;
            vo.setMphoto(rs.getString(1));
        }
        return flag;
    }

    @Override
    public List<Member> findAllByStatus(Integer status, Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        List<Member> all = new ArrayList<Member>();
        String sql = "SELECT mid,mpassword,mname,mphone,maddress,mstatus,mcode,mregdate,mphoto FROM member WHERE " + column + " LIKE ? AND mstatus=? LIMIT ?,?";
        super.prs = super.conn.prepareStatement(sql) ;
        super.prs.setString(1,"%"+keyWord+"%");
        super.prs.setInt(2,status);
        super.prs.setInt(3,(currentPage - 1) * pageSize);
        super.prs.setInt(4,pageSize);
        ResultSet rs = super.prs.executeQuery();
        while (rs.next()){
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setMpassword(rs.getString(2));
            vo.setMname(rs.getString(3));
            vo.setMphone(rs.getString(4));
            vo.setMaddress(rs.getString(5));
            vo.setMstatus(rs.getInt(6));
            vo.setMcode(rs.getString(7));
            vo.setMregdate(rs.getTimestamp(8));
            vo.setMphoto(rs.getString(9));
            all.add(vo);
        }
        return all;
    }
    @Override
    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM member WHERE mstatus=? AND " + column + " LIKE ?" ;
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setInt(1,status);
        super.prs.setString(2,"%" +keyWord+"%");
        ResultSet rs = super.prs.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doUpdateStatus(Set<String> ids, Integer status) throws Exception {
        if(ids.size() == 0){
            return false;
        }
        StringBuffer buf = new StringBuffer("UPDATE member SET mstatus=? WHERE mid IN(");
        Iterator<String> iter = ids.iterator();
        while(iter.hasNext()){
            buf.append("'").append(iter.next()).append("',");
        }
        buf.delete(buf.length() - 1, buf.length()).append(")");
        super.prs = super.conn.prepareStatement(buf.toString());
        super.prs.setInt(1,status);
        return super.prs.executeUpdate() == ids.size();
    }

    @Override
    public boolean doUpdateMember(Member vo) throws Exception {
        String sql = "UPDATE member SET mname=?,mphone=?,maddress=?,mphoto=? WHERE mid=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1,vo.getMname());
        super.prs.setString(2,vo.getMphone());
        super.prs.setString(3,vo.getMaddress());
        super.prs.setString(4,vo.getMphoto());
        super.prs.setString(5,vo.getMid());
        return super.prs.executeUpdate() == 1;
    }

    public boolean doUpdateStatus1(Set<String> ids, Integer status) throws Exception {
        if (ids.size() == 0) {
            return false ;
        }
        String sql = "UPDATE member SET mstatus=? WHERE mid=?" ;
        super.prs = super.conn.prepareStatement(sql) ;
        Iterator<String> iter = ids.iterator() ;
        while (iter.hasNext()) {
            super.prs.setInt(1,status);
            super.prs.setString(2,iter.next());
            super.prs.addBatch(); // 增加到批处理操作
        }
        int result [] = super.prs.executeBatch() ;    // 执行批处理
        for (int x = 0 ; x < result.length ; x ++) {
            if (result[x] == 0) {   // 没有数据被更新
                return false ;
            }
        }
        return true;
    }
}
