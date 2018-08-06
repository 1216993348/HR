package cn.xhy.shop.service.back.impl;

import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.factory.DAOFactory;
import cn.xhy.shop.service.back.IOrdersServiceBack;
import cn.xhy.shop.vo.Orders;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceBackImpl implements IOrdersServiceBack {
    DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Map<String, Object> list(Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("allOrders", DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, pageSize, column, keyWord));
            map.put("ordersCount", DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
            return map;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Orders show(Integer oid) throws Exception {
        try {
            Orders orders = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findById(oid);
            if(orders != null){
                orders.setAllDetails(DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllByOrders(oid));
            }
            return orders;
        } finally {
            this.dbc.close();
        }
    }
}
