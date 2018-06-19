package cn.hellojdbc;


import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Hello {
    @Test
    public void fun1() throws Exception{
        //1. jdbc注册驱动
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //2. 连接数据库
        /*
        ?useUnicode=true&characterEncoding=UTF-8，这个是解决中文乱码输入问题
        UTC是统一标准世界时间
         */
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day05?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "1234");
        //3. 操作数据库
        Statement statement=conn.createStatement();
        String sql="INSERT INTO `day05`.`t_user`\n" +
                "VALUES (NULL,\n" +
                "'Tom',\n" +
                "18);\n";
        statement.executeUpdate(sql);

        //4. 关闭连接
        statement.close();
        conn.close();
    }
}
