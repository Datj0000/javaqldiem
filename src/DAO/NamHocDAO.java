package DAO;

import static DAO.DatabaseConnection.*;
import Entity.NamHoc;
import java.sql.*;
import java.util.ArrayList;

public class NamHocDAO {

    public NamHocDAO() {
    }

    public ArrayList<NamHoc> getAll() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<NamHoc> listAll = new ArrayList<>();
        if (con != null) {
            try {
                st = con.createStatement();
                rs = st.executeQuery("select * from tbl_namhoc");
                while (rs.next()) {
                    String maNamHoc = rs.getString(1) + rs.getString(2);
                    NamHoc namhoc = new NamHoc(maNamHoc, rs.getString(3));
                    listAll.add(namhoc);
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

    public Boolean checkTen(NamHoc kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_namhoc where tenNamHoc='" + kh.getTenNamHoc() + "'");
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

    public Boolean checkDelete(NamHoc kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_namhoc, tbl_hocky where tbl_hocky.maNamHoc='" + kh.getMaNamHoc() + "' and tbl_namhoc.maNamHoc ='" + kh.getMaNamHoc() + "'");
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

    public void insert(NamHoc kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("insert into tbl_namhoc (tenNamHoc) values ('" + kh.getTenNamHoc() + "')");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void update(String maNamHoc, NamHoc kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("update tbl_namhoc set tenNamHoc='" + kh.getTenNamHoc() + "' where maNamHoc='" + maNamHoc + "'");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void delete(String maNamHoc) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        try {
            pr = con.prepareStatement("delete from tbl_namhoc where maNamHoc='" + maNamHoc + "'");
            pr.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeConnec(con);
            closeStatement(pr);
        }
    }

    public ArrayList<NamHoc> find(String txt) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<NamHoc> listAll = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from tbl_namhoc where maNamHoc like N'%" + txt + "%' or tenNamHoc like N'%" + txt + "%'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maNamHoc = rs.getString(1) + rs.getString(2);
                NamHoc namhoc = new NamHoc(maNamHoc, rs.getString(3));
                listAll.add(namhoc);
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
