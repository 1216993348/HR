package cn.xhy.shop.dao.impl;

import cn.xhy.shop.dao.IAdminDAO;
import cn.xhy.shop.vo.Admin;
import cn.xhy.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class AdminDAOImpl extends AbstractDAOImpl implements IAdminDAO {
    public AdminDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Admin vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Admin vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        return false;
    }

    @Override
    public Admin findById(String id) throws Exception {
        return null;
    }

    @Override
    public List<Admin> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Admin> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
    @Override
    public boolean findLogin(Admin vo) throws Exception {
        String sql = "SELECT lastdate FROM admin WHERE aid=? AND apassword=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setString(1,vo.getAid());
        super.prs.setString(2,vo.getApassword());
        ResultSet rs = super.prs.executeQuery();
        if(rs.next()){
            vo.setLastdate(rs.getTimestamp(1));
            return true;
        }
        return false;
    }

    @Override
    public boolean doUpdateLastdate(String mid) throws Exception {
        String sql = "UPDATE admin SET lastdate=? WHERE aid=?";
        super.prs = super.conn.prepareStatement(sql);
        super.prs.setTimestamp(1,new Timestamp(new Date().getTime()));
        super.prs.setString(2,mid);
        return super.prs.executeUpdate() == 1;
    }
}
