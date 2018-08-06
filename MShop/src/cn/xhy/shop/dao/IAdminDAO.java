package cn.xhy.shop.dao;

import cn.xhy.shop.dao.IDAO;
import cn.xhy.shop.vo.Admin;

public interface IAdminDAO extends IDAO<String,Admin> {
    /**
     * 实现管理员的登陆验证, 在登陆完成后将上一次的登陆时间取出
     * 利用VO类对象参数直接将上次登陆时间保存到此对象即可
     * @param vo 包含有aid,apassword数据
     * @return 如果验证成功返回true,否则返回false
     * @throws Exception
     */
    public boolean findLogin(Admin vo) throws Exception;

    /**
     * 用于更新管理员最后一次登陆的时间更新
     * @param mid 管理员ID
     * @return 如果更新成功返回true,否则返回false
     * @throws Exception
     */
    public boolean doUpdateLastdate(String mid) throws Exception;
}
