package cn.xhy.shop.service.front.impl;

import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.factory.DAOFactory;
import cn.xhy.shop.service.front.IMemberServiceFront;
import cn.xhy.shop.vo.Member;

public class MemberServiceFrontImpl implements IMemberServiceFront {
    DatabaseConnection dbc = new DatabaseConnection();
    public boolean register(Member vo) throws Exception {
        try {
            if(DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(vo.getMid())== null){
                return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
            return false;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean active(Member vo) throws Exception {
        try {
            if(DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).isCode(vo.getMid(),vo.getMcode())){
                return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateStatus(vo.getMid(),1);
            }
            return false;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean login(Member vo) throws Exception {
        try {
            return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).isLogin(vo);
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Member updatePre(String mid) throws Exception {
        try {
            return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(mid);
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Member vo) throws Exception {
        try {
            return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateMember(vo);
        }finally {
            this.dbc.close();
        }
    }
}
