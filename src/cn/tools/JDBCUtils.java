package cn.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String driverName;
    private static String url;
    private static String user;
    private static String password;
    static{
        //推荐的注册驱动方法
        try {
            //读取配置文件
            Properties properties=new Properties();
            InputStream inputStream=new FileInputStream("src/db.properties");
            properties.load(inputStream);
            inputStream.close();
            driverName=properties.getProperty("driverName");
            url=properties.getProperty("url");
            user=properties.getProperty("user");
            password=properties.getProperty("password");
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("创建数据库连接失败");
        }
        return conn;
    }
    //1.参数可能为空  2.由小到大关闭
    public static void close(Connection conn, Statement st, ResultSet rs){
        try {
            if(rs!=null){
                rs.close();;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(st!=null){
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(conn!=null){
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args){
        System.out.println(getConnection());
    }
}
