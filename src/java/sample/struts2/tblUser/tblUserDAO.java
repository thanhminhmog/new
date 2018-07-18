/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2.tblUser;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sample.struts2.utils.DBUtils;

/**
 *
 * @author Saisam
 */
public class tblUserDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws SQLException, NamingException, ClassNotFoundException {
        //  móc dữ liệu từ db lên xử lí
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        //  1/  Tạo connection
        //  Nguyên tắc viết code: tất cả các biến đặt trên đầu, null, 
        //  làm xong và trong tất cả mọi trường hợp phát sinh lỗi phải đóng kết nối lại
        try {
            conn = DBUtils.makeConnection();
            //  bắt lỗi CNFE
            //  khi thao tác obj, để tránh lỗi null pointer thì phải kiểm tra null trc khi xử lí

            if (conn != null) {
                //  2/  Tạo câu lệnh truy vấn
                String sql = "SELECT * FROM tblUser WHERE username=? AND password=?";

                //  3/  Tạo prepare statement, thực hiện truy vấn và tìm tham số
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, password);
                
                System.out.println(username +password);
                //  4/  Thực thi câu lệnh và đón kết quả trả về
                rs = pstm.executeQuery();
                //  Trả về số dòng ảnh hướng
                //  Câu lệnh trên bảng chắc chắn 1 dòng do username là primary key
                //  1 dòng: if, nhiều dòng: while
                if (rs.next()) {
                    //  resultset là 1 obj bình thường chứa con trỏ trỏ tới 1 vùng nhớ: begin of file -> end of file                    
                    return true;
                }
            }
            //  Hoàn thành chức năng gọi db, quay lại servlet
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
                // bắt lỗi SQLE
            }
        }
        return false;
    }

    private List<tblUserDTO> listUsers;

    public List<tblUserDTO> getListUsers() {
        return listUsers;
    }

    public void searchLastName(String searchValue) throws SQLException, NamingException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        //  1/  Tạo connection
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                //  2/  Tạo câu lệnh truy vấn
                String sql = "SELECT * FROM tblUser WHERE lastname like ?";
                //  3/  Tạo prepare statement, thực hiện truy vấn và tìm tham số
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + searchValue + "%");
                //  4/  Thực thi câu lệnh
                rs = pstm.executeQuery();

                while (rs.next()) {
                    //  lấy toàn bộ thông tin ra
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    tblUserDTO dto = new tblUserDTO(username, password, lastname, role);

                    if (this.listUsers == null) {
                        //  list == null nghĩa là ko có thông tin cần tìm
                        //  list này là interface -> tạo arraylist
                        this.listUsers = new ArrayList<>();
                    }

                    this.listUsers.add(dto);
                }
            }   //  end if conn
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean deleteAccount(String username) throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;

        //  1/  Tạo connection
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                //  2/  Tạo câu lệnh truy vấn
                String sql = "Delete from tblUser Where username =?";
                //  3/  Tạo prepare statement, thực hiện truy vấn và tìm tham số
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);

                //  4/  Thực thi câu lệnh
                int row = pstm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
            //  end if conn
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
    public boolean updateAccount(String password, boolean role, String username) throws SQLException{
         Connection conn = null;
        PreparedStatement pstm = null;

        //  1/  Tạo connection
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                //  2/  Tạo câu lệnh truy vấn
                String sql = "Update tblUser "
                        +   "Set password = ?, isAdmin = ? "
                        +   "Where username = ?";
                //  3/  Tạo prepare statement, thực hiện truy vấn và tìm tham số
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, password);
                pstm.setBoolean(2, role);
                pstm.setString(3, username);                

                //  4/  Thực thi câu lệnh
                int row = pstm.executeUpdate();
                System.out.println(row);
                if (row > 0) {
                    return true;
                }
            }
            //  end if conn
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tblUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(tblUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(tblUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return false;
    }
    public boolean  creatAccount (String username, String password, String lastName, boolean role) throws ClassNotFoundException, SQLException, NamingException{
        //  Lưu 4, do tbl có bao nhiều field thì mình dùng bao nhiều field, field nào ko có thì dùng giá trị mặc định
        //  Có bao nhiều field dưới db thì map lên bấy nhiêu
        int row = 0;
        Connection conn = null;
        PreparedStatement pstm = null;

        //  1/  Tạo connection
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                //  2/  Tạo câu lệnh truy vấn
                String sql = "Insert into "
                        +   "tblUser(username, password, lastname, isAdmin) "
                        +   "Values(?, ?, ?, ?)";
                //  3/  Tạo prepare statement, thực hiện truy vấn và tìm tham số
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, password);
                pstm.setString(3, lastName);
                pstm.setBoolean(4, role);
                System.out.println(row + username + password + lastName + role);
                //  4/  Thực thi câu lệnh
                 row = pstm.executeUpdate();
                System.out.println(row + "a");
                if (row > 0) {
                    return true;
                }
            }
            //  end if conn
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
}
