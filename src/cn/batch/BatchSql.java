package cn.batch;

import cn.tools.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

public class BatchSql {
    @Test
    //1 使用Statement对象批量执行sql
    public void fun1() throws Exception{

        //1 获得连接
        Connection conn = JDBCUtils.getConnection();
        //2 获得Statement
        Statement st =	conn.createStatement();
        //3 添加多条sql语句到st中

        st.addBatch("create table t_stu ( id int primary key auto_increment , name varchar(20) )");
        st.addBatch("insert into t_stu values(null,'tom')");
        st.addBatch("insert into t_stu values(null,'jerry')");
        st.addBatch("insert into t_stu values(null,'jack')");
        st.addBatch("insert into t_stu values(null,'rose')");
        //4 执行sql
        int[]  results = st.executeBatch();
        System.out.println(Arrays.toString(results));
        //5关闭资源
        JDBCUtils.close(conn, st, null);
    }
    @Test
    //2 使用PrepareStatement对象批量执行sql
    public void fun2() throws Exception{

        //1 获得连接
        Connection conn = JDBCUtils.getConnection();
        //2 书写sql语句
        String sql = "insert into t_stu values(null,?)";
        //3 创建PrepareStatement
        PreparedStatement ps = conn.prepareStatement(sql);
        //4 循环.添加参数
        for(int i=0;i<100;i++){
            ps.setString(1, "用户"+i);
            ps.addBatch();
        }
        //5 批量执行
        int[]  results =ps.executeBatch();
        System.out.println(Arrays.toString(results));
        //5关闭资源
        JDBCUtils.close(conn, ps, null);
    }
}
