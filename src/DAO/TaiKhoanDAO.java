package DAO;

import static Connection.DatabaseConnection.*;
import Entity.*;
import java.sql.*;

public class TaiKhoanDAO {

    public TaiKhoanDAO() {
    }

    public Boolean checkLogin(TaiKhoan kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_taikhoan where taiKhoan='" + kh.getTaiKhoan() + "' and matKhau ='" + kh.getMatKhau() + "'");
                rs = stmt.executeQuery();
                if (rs.next()) {
                    k = true;
                }
            } catch (SQLException e) {
            } finally {
                closeResultSet(rs);
                closeStatement(stmt);
                closeConnec(con);
            }
        }
        return k;
    }

    public Boolean checkPass(TaiKhoan kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_taikhoan "
                        + "where taiKhoan='" + kh.getTaiKhoan() + "' and matKhau ='" + kh.getMatKhau() + "'");
                rs = stmt.executeQuery();
                if (rs.next()) {
                    k = true;
                }
            } catch (SQLException e) {
            } finally {
                closeResultSet(rs);
                closeStatement(stmt);
                closeConnec(con);
            }
        }
        return k;
    }

    public void changePass(TaiKhoan kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("update tbl_taikhoan set matKhau='" + kh.getMatKhau()+ "' where taiKhoan='" + kh.getTaiKhoan()+ "'");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }
}
