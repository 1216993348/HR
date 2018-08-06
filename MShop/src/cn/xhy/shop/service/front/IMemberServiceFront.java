package cn.xhy.shop.service.front;

import cn.xhy.shop.vo.Member;

public interface IMemberServiceFront {
    /**
     * 实现用户注册操作 ,此操作需要提供mid,mpassword,mcode,mregdate,mstatus; <br>
     *   此业务将调用如下DAO操作: <br>
     *       <li>调用IMemberDAO.findByID()方法判断用户是否存在</li>
     *       <li>调用IMemberDAO.doCreate()方法添加用户信息</li>
     * @param vo 包含有注册信息的VO类对象
     * @return 注册成功返回true,否则返回false
     * @throws Exception SQL 执行异常
     */
    public boolean register(Member vo) throws Exception;

    /**
     * 实现用户激活操作，此业务需要调用如下DAO操作:<br>
     *     <li>调用IMemberDAO.isCode()方法判断用户激活码是否正确</li>
     *     <li>调用IMemberDAO.doUpdateStatus()方法修改激活码</li>
     * @param vo 包含有激活信息的VO类对象
     * @return 如果激活成功返回true,否则返回false
     * @throws Exception SQL执行异常
     */
    public boolean active(Member vo) throws Exception;

    /**
     * 实现用户登录验证操作,
     * @param vo 登录后可以查询出用户的头像信息
     * 接收参数是Member对象,所以可以将头像信息直接保存在Member对象里
     * @return 如果登录成功返回true,否则返回false
     * @throws Exception SQL执行异常
     */
    public boolean login(Member vo) throws Exception;
    Member updatePre(String mid) throws Exception;
    boolean update(Member vo) throws Exception;

}
