package DAO;

import static Connection.DatabaseConnection.*;
import Entity.MonHoc;
import java.sql.*;
import java.util.ArrayList;

public class MonHocDAO {

    public MonHocDAO() {
    }

    public ArrayList<MonHoc> getAll() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<MonHoc> listAll = new ArrayList<>();
        if (con != null) {
            try {
                st = con.createStatement();
                rs = st.executeQuery("select * from tbl_monhoc");
                while (rs.next()) {
                    String maMonHoc = rs.getString(1) + rs.getString(2);
                    MonHoc monHoc = new MonHoc(maMonHoc, rs.getString(3));
                    listAll.add(monHoc);
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

    public Boolean checkTen(MonHoc kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_monhoc where tenMonHoc='" + kh.getTenMonHoc() + "'");
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

    public void insert(MonHoc kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("insert into tbl_monhoc (tenMonHoc) values ('" + kh.getTenMonHoc() + "')");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void update(String maMonHoc, MonHoc kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("update tbl_monhoc set tenMonHoc='" + kh.getTenMonHoc() + "' where maMonHoc='" + maMonHoc + "'");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void delete(String maMonHoc) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        try {
            pr = con.prepareStatement("delete from tbl_monhoc where maMonHoc='" + maMonHoc + "'");
            pr.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeConnec(con);
            closeStatement(pr);
        }
    }
}
