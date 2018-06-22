package cn.hellojdbc;


import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hello {
    @Test
    public void insert() throws Exception{
        //1. jdbc注册驱动, 不推荐.因为驱动实现类中 的静态代码以及调用过
        //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //推荐的注册驱动方法
        Class.forName("com.mysql.cj.jdbc.Driver");//加载类,驱动实现类中的静态块代码会执行DriverManager.registerDriver(new Driver())
        //2. 连接数据库
        /*
        ?useUnicode=true&characterEncoding=UTF-8，这个是解决中文乱码输入问题
        UTC是统一标准世界时间
         */
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day05?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                "root", "1234");
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
    @Test
    public void query() throws Exception{
        //1. jdbc注册驱动,不推荐.因为驱动实现类中 的静态代码以及调用过
        //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());//驱动实现类中的静态块代码也会执行DriverManager.registerDriver(new Driver()),所以总共执行了两遍
        //推荐的注册驱动方法
        Class.forName("com.mysql.cj.jdbc.Driver");//加载类,驱动实现类中的静态块代码会执行DriverManager.registerDriver(new Driver())
        //2. 连接数据库
        /*
        ?useUnicode=true&characterEncoding=UTF-8，这个是解决中文乱码输入问题
        UTC是统一标准世界时间
         */
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day05?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "1234");
        //3. 操作数据库
        Statement statement=conn.createStatement();
        String sql="select * from `day05`.`t_user`;";
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()){
            int id= resultSet.getInt("id");
            String name=resultSet.getString("name");
            int age=resultSet.getInt("age");
            System.out.println(id+name+age);
        }
        //4. 关闭连接
        statement.close();
        conn.close();
    }
}
