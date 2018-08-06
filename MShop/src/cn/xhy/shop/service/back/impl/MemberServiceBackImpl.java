package cn.xhy.shop.service.back.impl;

import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.factory.DAOFactory;
import cn.xhy.shop.service.back.IMemberServiceBack;
import cn.xhy.shop.vo.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Filter;

public class MemberServiceBackImpl implements IMemberServiceBack {
    DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public Map<String, Object> list(int currentPage, int pageSize, String column, String keyWord) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allMembers",DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage,pageSize,column,keyWord));
            map.put("memberCount",DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).getAllCount(column,keyWord));
            return map;
        }finally {
            dbc.close();
        }
    }

    @Override
    public Map<String, Object> listByStatus(int status, int currentPage, int pageSize, String column, String keyWord) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allMembers",DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findAllByStatus(status,currentPage,pageSize,column,keyWord));
            map.put("memberCount",DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).getAllCountByStatus(status,column,keyWord));
            return map;
        }finally {
            dbc.close();
        }
    }

    @Override
    public boolean updateActive(Set<String> ids) throws Exception {
        try{
            return DAOFactory.getIMemberDAOInstance(dbc.getConnection()).doUpdateStatus(ids,1);
        }finally {
            dbc.close();
        }
    }

    @Override
    public boolean updateLock(Set<String> ids) throws Exception {
        try{
            return DAOFactory.getIMemberDAOInstance(dbc.getConnection()).doUpdateStatus(ids,0);
        }finally {
            dbc.close();
        }
    }

    @Override
    public Member show(String mid) throws Exception {
        try{
            return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(mid);
        }finally {
            dbc.close();
        }
    }
}
