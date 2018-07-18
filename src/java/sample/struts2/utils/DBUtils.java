/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Saisam
 */
public class DBUtils implements Serializable {
//    vì nằm ở server
    public static Connection makeConnection() throws ClassNotFoundException, SQLException, NamingException{
//        //  load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        // Đôi khi 1 class kết nối nhiều db, ko biết db nào có lỗi nên ko bắt lỗi tại chổ -> dùng throw
//        
//        //  tạo url String để kết nối
//        String url ="jdbc:sqlserver://localhost:1433;databaseName=User";
//        //  Phải tự gõ chuỗi
//        
//        //  tạo connection
//        Connection conn = DriverManager.getConnection(url,"sa","486300");
//        //  Bắt lỗi SQLException
//        //  Hỗ trợ đọc netflix để convert cho mình
//        //  Tại sao gọi là kết nối tĩnh, vì tất cả giá trị (url, sa,... ) đc đưa vào trong code, sau đó build lại
//        //  -> muốn đổi địa chỉ mới, đổi db mới thì phải mở code ra sửa
//        //  => hoàn tất thử viện hỗ trợ kết nói, quay lại DAO
//        return null;
        //  context: là nơi hệ thống đang sử dụng để...
        Context context = new InitialContext();
            //  Context ở phía server
        Context tomcatCtx = (Context)context.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatCtx.lookup("DS007");
        Connection con = ds.getConnection();
        System.out.println(con == null);
        return con;
    }
}
