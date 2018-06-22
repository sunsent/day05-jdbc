package cn.text;

import cn.tools.JDBCUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Text {
    @Test
    //演示向mysql中存放大文本数据
    //存储大文本必须使用PrepareStatement对象
    public void fun1() throws Exception{

        //1 获得连接
        Connection conn = JDBCUtils.getConnection();
        //2 书写sql
        String sql = "insert into mytext values(null,?)";
        //3 创建PrepareStatement
        PreparedStatement ps = conn.prepareStatement(sql);
        //4 设置参数
        //参数1:参数的索引
        //参数2:需要保存的文本的流
        //参数3:文件长度

        File f = new File("src/text.txt");

        FileReader reader = new FileReader(f);

        ps.setCharacterStream(1, reader, (int)f.length());

//        File f = new File("src/wg.PNG");
//
//        InputStream is = new FileInputStream(f);
//
//        ps.setBinaryStream(1, is, (int)f.length());

        //5 执行sql
        int result = ps.executeUpdate();
        System.out.println(result);
        //6关闭资源
        JDBCUtils.close(conn, ps, null);
    }
}
