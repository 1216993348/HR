package cn.xhy.shop.service.back.impl;

import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.factory.DAOFactory;
import cn.xhy.shop.service.back.IAdminServiceBack;
import cn.xhy.shop.vo.Admin;

public class AdminServiceBackImpl implements IAdminServiceBack {
    DatabaseConnection dbc = new DatabaseConnection() ;
    public boolean login(Admin vo) throws Exception {
        try{
            if(DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(vo)){
                return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdateLastdate(vo.getAid());
            }
            return false;
        }finally {
            this.dbc.close();
        }
    }
}
