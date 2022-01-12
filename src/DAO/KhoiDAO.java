package DAO;

import static DAO.DatabaseConnection.*;
import Entity.Khoi;
import java.sql.*;
import java.util.ArrayList;

public class KhoiDAO {

    public KhoiDAO() {
    }

    public ArrayList<Khoi> getAll() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Khoi> listAll = new ArrayList<>();
        if (con != null) {
            try {
                st = con.createStatement();
                rs = st.executeQuery("select * from tbl_khoi");
                while (rs.next()) {
                    String maKhoi = rs.getString(1) + rs.getString(2);
                    Khoi khoi = new Khoi(maKhoi, rs.getString(3));
                    listAll.add(khoi);
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

    public Boolean checkTen(Khoi kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_khoi where tenKhoi='" + kh.getTenKhoi() + "'");
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

    public Boolean checkDelete(Khoi kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_khoi, tbl_lop where tbl_lop.maKhoi='" + kh.getMaKhoi() + "' and tbl_khoi.maKhoi ='" + kh.getMaKhoi() + "'");
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

    public void insert(Khoi kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("insert into tbl_khoi (tenKhoi) values ('" + kh.getTenKhoi() + "')");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void update(String maKhoi, Khoi kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("update tbl_khoi set tenKhoi='" + kh.getTenKhoi() + "' where maKhoi='" + maKhoi + "'");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void delete(String maKhoi) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        try {
            pr = con.prepareStatement("delete from tbl_khoi where maKhoi='" + maKhoi + "'");
            pr.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeConnec(con);
            closeStatement(pr);
        }
    }

    public ArrayList<Khoi> find(String txt) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Khoi> listAll = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from tbl_khoi where maKhoi like N'%" + txt + "%' or tenKhoi like N'%" + txt + "%'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maKhoi = rs.getString(1) + rs.getString(2);
                Khoi khoi = new Khoi(maKhoi, rs.getString(3));
                listAll.add(khoi);
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
