package cn.xhy.shop.service.back.impl;

import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.factory.DAOFactory;
import cn.xhy.shop.service.back.IItemServiceBack;
import cn.xhy.shop.vo.Item;

import java.util.List;
import java.util.Set;

public class ItemServiceBackImpl implements IItemServiceBack {
    DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(Item vo) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doCreate(vo);
        }finally {
            dbc.close();
        }
    }

    @Override
    public boolean update(Item vo) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doUpdate(vo);
        }finally {
            dbc.close();
        }
    }

    @Override
    public boolean delete(Set<Integer> ids) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
        }finally {
            dbc.close();
        }
    }

    @Override
    public List<Item> list() throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll();
        }finally {
            dbc.close();
        }
    }
}
