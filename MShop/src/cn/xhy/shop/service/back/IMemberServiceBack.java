package cn.xhy.shop.service.back;

import cn.xhy.shop.vo.Member;

import java.util.Map;
import java.util.Set;

public interface IMemberServiceBack {
    /**
     * 实现用户分页列表查询操作，此业务方法需要调用如下两个DAO方法<br>
     *     <li>调用IMemberDAO.findAllSplit()方法进行列表查询</li>
     *     <li>调用IMemberDAO.getAllCount()方法，统计全部数据量</li>
     * @param currentPage
     * @param pageSize
     * @param column
     * @param keyWord
     * @return 由于要返回的数据有List与Integer两种类型，所以使用Map返回，包含如下内容：<br>
     *     <li>key = allMembers、value = findAllSplit()</li>
     *     <li>key = memberCount、value = getAllCount()</li>
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage,int pageSize,String column,String keyWord) throws Exception;

    /**
     *实现根据用户状态进行分页列表查询操作，此业务方法需要调用如下两个DAO方法<br>
     *     <li>调用IMemberDAO.findAllByStatus()方法进行列表查询</li>
     *     <li>调用IMemberDAO.getAllCountByStatus()方法，统计全部数据量</li>
     * @param status
     * @param currentPage
     * @param pageSize
     * @param column
     * @param keyWord
     * @return 由于要返回的数据有List与Integer两种类型，所以使用Map返回，包含如下内容：<br>
     *     <li>key = allMembers、value = findAllByStatus()</li>
     *     <li>key = memberCount、value = getAllCountByStatus()</li>
     * @throws Exception
     */
    public Map<String,Object> listByStatus(int status,int currentPage,int pageSize,String column,String keyWord) throws Exception;

    /**
     * 批量激活用户状态
     * @param ids 包含用户id的Set集合
     * @return 激活成功返回true,否则返回false
     * @throws Exception
     */
    public boolean updateActive(Set<String> ids) throws Exception;
    /**
     * 批量锁定用户状态
     * @param ids 包含用户ID的Set集合
     * @return 锁定成功返回true,否则返回false
     * @throws Exception
     */
    public boolean updateLock(Set<String> ids) throws Exception;

    /**
     * 查询单个用户的详细信息
     * @param mid 用户ID
     * @return 返回Member类对象
     * @throws Exception
     */
    public Member show(String mid) throws Exception ;
}
