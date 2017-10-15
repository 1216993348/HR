package cn.xhy.shop.service.front.impl;

import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.exception.UnCompleteMemberInformationException;
import cn.xhy.shop.exception.UnEnoughAmountException;
import cn.xhy.shop.factory.DAOFactory;
import cn.xhy.shop.service.front.IOrdersServiceFront;
import cn.xhy.shop.vo.Details;
import cn.xhy.shop.vo.Goods;
import cn.xhy.shop.vo.Member;
import cn.xhy.shop.vo.Orders;

import java.sql.SQLException;
import java.util.*;

public class OrdersServiceFrontImpl implements IOrdersServiceFront {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean insert(String mid, Map<Integer, Integer> allCars) throws UnCompleteMemberInformationException, UnEnoughAmountException, Exception {
        boolean flag = false;
        this.dbc.getConnection().setAutoCommit(false);
        try {
            this.dbc.getConnection().setAutoCommit(false);
            // 1 、首先应判断出当前用户的信息是否完整，根据mid查询出用户的完整信息
            Member member = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(mid);
            // 2、如果用户信息不完整则不允许创建订单
            if (member.getMname() == null || member.getMphone() == null || member.getMaddress() == null) {
                throw new UnCompleteMemberInformationException("用户信息不完整！");
            }
            // 3、查询所有订单商品的信息,取得商品的名称、价格、库存量保存到Set<Detils>集合里
            List<Details> allDetails = new ArrayList<>();
            // 4、取得订单商品ID
            Set<Integer> gids = allCars.keySet();
            // 5、根据取得的商品ID,查询商品信息
            List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByShopcar(gids);
            Iterator<Goods> iterGoods = allGoods.iterator();
            // 6、在判断库存量操作的同时还需要计算出总的花费金额
            double pay = 0.0;
            // 7、迭代判断并保存数据
            while (iterGoods.hasNext()) {
                Goods goods = iterGoods.next();
                // 库存量 - 要准备购买的商品数量
                if (goods.getGamount() - allCars.get(goods.getGid()) < 0) {
                    throw new UnEnoughAmountException("商品没有足够的出售数量，无法创建订单！");
                }
                Details details = new Details();
                details.setGoods(goods);
                details.setGname(goods.getGtitle());
                details.setGprice(goods.getGprice());
                details.setOdnumber(allCars.get(goods.getGid()));
                allDetails.add(details);
                pay += allCars.get(goods.getGid()) * goods.getGprice();
                // 8、更新库存量
                DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateAmount(goods.getGid(),0 - allCars.get(goods.getGid()));
            }
            // 9、创建订单对象,并取得订单ID,后将此ID迭代保存到Details对象内
            Orders orders = new Orders();
            orders.setMember(member);
            orders.setMname(member.getMname());
            orders.setMphone(member.getMphone());
            orders.setMaddress(member.getMaddress());
            orders.setMpay(pay);
            flag = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).doCreate(orders);
            // 10 、如果订单创建成功，则将订单ID迭代放入Details对象里
            Integer oid = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findLastInsertId();
            if (flag) {
                Iterator<Details> iterDetails = allDetails.iterator();
                while (iterDetails.hasNext()) {
                    Details details = iterDetails.next();
                    orders.setOid(oid);
                    details.setOrders(orders);
                }
                flag = DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).doCreateBatch(allDetails);
                // 11、订单创建成功后应该删除购物车中的信息
                if (flag) {
                    DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMemberAndGoods(mid, allCars.keySet());
                    this.dbc.getConnection().commit();
                }else{
                    this.dbc.getConnection().rollback();
                }
            }
            return flag;
        } catch (SQLException e) {
            dbc.getConnection().rollback();
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public  Map<String,Object> list(String mid, int currentPage, int pageSize) throws Exception {
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("allOrders",DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findByMember(mid,currentPage,pageSize));
            map.put("ordersCount",DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).getCountByMember(mid));
            return map;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Orders show(String mid,Integer oid) throws Exception {
        try {
            Orders vo = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findById(mid,oid);
            if(vo != null){
                vo.setAllDetails(DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllByOrders(oid));
            }
            return vo;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean revoke(Integer oid) throws Exception {
        boolean flag = false;
        this.dbc.getConnection().setAutoCommit(false);
        try {
            // 1 、撤销订单应先查询出所有的订单详情信息,以便进行商品库存的更新
            List<Details> allDetails  = DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllByOrders(oid);
            Iterator<Details> iter = allDetails.iterator();
            while(iter.hasNext()){
                Details details =iter.next();
                Integer gid =details.getGoods().getGid();
                Integer number =details.getOdnumber();
                // 2、迭代更新商品数量
                if(DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateAmount(gid,number)){
                    flag = true;
                }else {
                    flag =false;
                    break;
                }
            }
            if(flag){
                //3、删除订单信息
                Set<Integer> set = new HashSet();
                set.add(oid);
                flag = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).doRemoveBatch(set);
            }
            if(flag){
                this.dbc.getConnection().commit();
            }else {
                this.dbc.getConnection().rollback();
            }
            return flag;
        }catch (Exception e){
            this.dbc.getConnection().rollback();
            throw e;
        }finally {
            this.dbc.getConnection().close();
        }
    }

}
