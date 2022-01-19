package DAO;

import static Connection.DatabaseConnection.*;
import Entity.HocKy;
import java.sql.*;
import java.util.ArrayList;

public class HocKyDAO {

    public HocKyDAO() {
    }

    public HocKy getInfo(String maHK) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement("select tbl_hocky.*, tbl_namhoc.tenNamHoc"
                        + " from tbl_hocky, tbl_namhoc"
                        + " where tbl_hocky.maHocKy='" + maHK + "' and tbl_hocky.maNamHoc=tbl_namhoc.maNamHoc");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maHocKy = rs.getString(1) + rs.getString(2);
                HocKy hk = new HocKy(maHocKy, rs.getString(3), rs.getString(4), rs.getString(5));
                rs.close();
                stmt.close();
                return hk;
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

    public ArrayList<HocKy> getAll() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<HocKy> listAll = new ArrayList<>();
        if (con != null) {
            try {
                st = con.createStatement();
                rs = st.executeQuery("select * from tbl_hocky");
                while (rs.next()) {
                    String maHocKy = rs.getString(1) + rs.getString(2);
                    HocKy hk = new HocKy(maHocKy, rs.getString(3), rs.getString(4));
                    listAll.add(hk);
                }
            } catch (SQLException e) {
            } finally {
                closeResultSet(rs);
                closeStatement(st);
                closeConnec(con);
            }
        }
        return listAll;
    }

    public Boolean checkDelete(HocKy kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_bangdiem, tbl_hocky where tbl_hocky.maHocKy='" + kh.getMaHocKy() + "' and tbl_bangdiem.maHocKy ='" + kh.getMaHocKy() + "'");
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

    public void insert(HocKy kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("insert into tbl_hocky (tenHocKy,maNamHoc) values ('" + kh.getTenHocKy() + "','" + kh.getMaNamHoc() + "')");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void update(String maHocKy, HocKy kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("update tbl_hocky set tenHocKy='" + kh.getTenHocKy() + "' where maHocKy='" + maHocKy + "'");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void delete(String maHocKy) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        try {
            pr = con.prepareStatement("delete from tbl_hocky where maHocKy='" + maHocKy + "'");
            pr.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeConnec(con);
            closeStatement(pr);
        }
    }

    public ArrayList<HocKy> findMaNamHoc(String txt) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<HocKy> listAll = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from tbl_hocky where tbl_hocky.maNamHoc='" + txt + "'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maHocKy = rs.getString(1) + rs.getString(2);
                HocKy hk = new HocKy(maHocKy, rs.getString(3), rs.getString(4));
                listAll.add(hk);
            }
        } catch (SQLException e) {
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnec(con);
        }
        return listAll;
    }
}
