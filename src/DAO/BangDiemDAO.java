package DAO;

import static Connection.DatabaseConnection.*;
import Entity.BangDiem;
import java.sql.*;
import java.util.ArrayList;

public class BangDiemDAO {

    public BangDiemDAO() {
    }

    public BangDiem getInfo(String maBD) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement("select tbl_bangdiem.*, tbl_hocsinh.tenHocSinh as tenHocSinhHT, tbl_hocsinh.prefix, tbl_lop.tenLop as tenLopHT"
                        + " from tbl_bangdiem, tbl_hocsinh, tbl_lop"
                        + " where tbl_bangdiem.maBangDiem='" + maBD + "' and tbl_bangdiem.maLop=tbl_lop.maLop and tbl_bangdiem.maHocSinh=tbl_hocsinh.maHocSinh");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maBangDiem = rs.getString(1) + rs.getString(2);
                String maHocSinh = rs.getString(6) + rs.getString(5);
                BangDiem bangdiem = new BangDiem(maBangDiem, rs.getString(3), rs.getString(4), maHocSinh, rs.getString("tenHocSinhHT"), rs.getString("tenLopHT"));
                rs.close();
                stmt.close();
                return bangdiem;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnec(con);
        }
    }

    public Boolean checkTen(BangDiem kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_bangdiem "
                        + "where maHocKy='" + kh.getMaHocKy() + "' and maHocSinh='" + kh.getMaHocSinh() + "'");
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

    public void insert(BangDiem kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("insert into tbl_bangdiem (maHocKy,maLop,maHocSinh) values ('" + kh.getMaHocKy() + "','" + kh.getMaLop() + "','" + kh.getMaHocSinh() + "')");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void delete(String maBangDiem) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        try {
            pr = con.prepareStatement("delete from tbl_bangdiem where maBangDiem='" + maBangDiem + "'");
            pr.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeConnec(con);
            closeStatement(pr);
        }
    }

    public ArrayList<BangDiem> findMa(String maHocKy, String maLop) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<BangDiem> listAll = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select tbl_bangdiem.*, tbl_hocsinh.tenHocSinh as tenHocSinhHT, tbl_hocsinh.prefix as maHocSinhHT"
                    + " from tbl_bangdiem, tbl_hocsinh"
                    + " where tbl_bangdiem.maHocKy='" + maHocKy + "' and tbl_bangdiem.maLop='" + maLop + "' and tbl_bangdiem.maHocSinh=tbl_hocsinh.maHocSinh");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maBangDiem = rs.getString(1) + rs.getString(2);
                String maHocSinh = rs.getString("maHocSinhHT") + rs.getString(5);
                BangDiem bangdiem = new BangDiem(maBangDiem, rs.getString(3), rs.getString(4), maHocSinh, rs.getString("tenHocSinhHT"));
                listAll.add(bangdiem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnec(con);
        }
        return listAll;
    }
}
