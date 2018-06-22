package cn.security;

import cn.tools.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SecSql {
    @Test
    //演示使用PrepareStatement对象,解决sql注入问题
    public void fun2() throws Exception{
        //String name ="xxx' OR 1=1 -- ";
        String name="jack";
        String password ="1234";

        //1 获得连接
        Connection conn= JDBCUtils.getConnection();
        //2 拼装sql语句
        String sql = "SELECT * FROM t_user  WHERE NAME=? AND   password=?";
        //3 获得PrepareStatement
        PreparedStatement ps = conn.prepareStatement(sql);
        //4 设置参数到ps对象中
        ps.setString(1, name);
        ps.setString(2, password);
        //5 运送参数,执行sql并拿到结果
        ResultSet rs = 	ps.executeQuery();
        //5 根据结果判断是否登录成功
        if(rs.next()){
            System.out.println("登录成功!");
        }else{
            System.out.println("登录失败!");
        }
        //6关闭资源
        JDBCUtils.close(conn, ps, rs);
    }
}
