package cn.xhy.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractDAOImpl {
    protected Connection conn;
    protected PreparedStatement prs;
    public AbstractDAOImpl(Connection conn){
        this.conn = conn;
    }

    /**
     * 实现数据的批量删除操作
     * @param table 要删除的数据表
     * @param column 删除表ID数据列
     * @param ids 要删除的数据ID集合
     * @return 删除成功返回true 否则返回false
     * @throws Exception SQL执行异常
     */
    public boolean removeHandle(String table,String column,Set<?> ids) throws Exception{
        if( ids.size() == 0){
            return false;
        }
        StringBuffer buf = new StringBuffer();
        buf.append("DELETE FROM ").append(table).append(" WHERE ").append(column).append(" IN(");
        Iterator iter = ids.iterator();
        while(iter.hasNext()){
            buf.append(iter.next()).append(",");
        }
        buf.delete(buf.length()-1,buf.length()).append(")");
        return this.conn.prepareStatement(buf.toString()).executeUpdate() == ids.size();
    }

    /**
     * 实现数据统计操作
     * @param table 要删除的数据表
     * @param column 模糊查询的数据列
     * @param keyWord 模糊查询的关键字
     * @return 返回模糊查询统计的数据量
     * @throws Exception SQL执行异常
     */
    public Integer countHandle(String table,String column,String keyWord) throws Exception{
        StringBuffer buf = new StringBuffer();
        buf.append("SELECT COUNT(*) FROM ").append(table).append(" WHERE ").append(column).append(" LIKE ?");
        this.prs = this.conn.prepareStatement(buf.toString());
        this.prs.setString(1,"%" + keyWord +"%");
        ResultSet rs = this.prs.executeQuery();
        if (rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
}
