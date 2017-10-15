package cn.xhy.shop.service.back;

import cn.xhy.shop.vo.Admin;

public interface IAdminServiceBack {
    /**
     * 实现管理员的登陆操作,此业务层需要调用如下两个DAO操作:<br>
     *      <li>调用IAdminDAO.findLogin()方法进行用户名和密码验证,如果正确返回最后一次登录日期</li>
     *      <li>调用IAdminDAO.doUpdateLastdate()方法,更新最后一次登陆日期</li>
     * @param vo 包含有aid,password
     * @return 如果登陆验证成功返回true,否则返回false
     * @throws Exception
     */
    public boolean login(Admin vo) throws Exception ;
}
