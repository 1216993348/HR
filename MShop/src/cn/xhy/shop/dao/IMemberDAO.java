package cn.xhy.shop.dao;

import cn.xhy.shop.vo.Member;

import java.util.List;
import java.util.Set;

public interface IMemberDAO extends IDAO<String,Member> {
    /**
     * 判断给定的mid与给定的code是否相同
     * @param mid 要激活的用户id
     * @param mcode 设置好的激活码
     * @return 如果用户id与激活码相匹配则可以返回true，否则返回false
     */
    public boolean isCode(String mid,String mcode)throws Exception;
    /**
     * 实现更新指定用户的状态操作
     * @param mid 用户id
     * @param mstatus 用户状态（0表示锁定、1表示激活、2表示等待激活）
     * @return
     * @throws Exception
     */
    public boolean doUpdateStatus(String mid,Integer mstatus) throws Exception;
    /**
     * 实现用户的登录检查操作，正常登录后可以查询出用户的照片信息，
     * 由于参数接收的是Member对象，所以可以直接将照片信息保存在Member对象
     * @param vo 包含了mid与password的VO类对象
     * @return 登录成功返回true，否则返回false
     * @throws Exception
     */
    public boolean isLogin(Member vo) throws Exception;

    /**
     * 根据用户的状态来进行数据的列表操作
     * @param status
     * @param currentPage
     * @param pageSize
     * @param column
     * @param keyWord
     * @return
     */
    public List<Member> findAllByStatus(Integer status, Integer currentPage, Integer pageSize, String column, String keyWord) throws Exception;

    /**
     * 根据用户的状态统计所有数据量
     * @param status
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Integer getAllCountByStatus(Integer status,String column,String keyWord) throws Exception;

    /**
     * 批量更新用户状态
     * @param ids 包含多个用户id的Set集合
     * @param status 更新后的状态
     * @return 更新成功返回true,失败返回false;
     * @throws Exception
     */
    public boolean doUpdateStatus(Set<String> ids, Integer status) throws Exception;
    boolean doUpdateMember(Member vo) throws Exception;
}
