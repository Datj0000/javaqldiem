package DAO;

import static DAO.DatabaseConnection.*;
import Entity.Lop;
import java.sql.*;
import java.util.ArrayList;

public class LopDAO {

    public LopDAO() {
    }

    public Lop getInfo(String maL) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement("select tbl_lop.*,tbl_khoi.tenKhoi from tbl_lop,tbl_khoi where tbl_lop.maLop='" + maL + "' and tbl_lop.maKhoi=tbl_khoi.maKhoi");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maLop = rs.getString(1) + rs.getString(2);
                Lop lop = new Lop(maLop, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                rs.close();
                stmt.close();
                return lop;
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

    public ArrayList<Lop> getAll() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Lop> listAll = new ArrayList<>();
        if (con != null) {
            try {
                st = con.createStatement();
                rs = st.executeQuery("select * from tbl_lop");
                while (rs.next()) {
                    String maLop = rs.getString(1) + rs.getString(2);
                    Lop lop = new Lop(maLop, rs.getString(3), rs.getString(4), rs.getString(5));
                    listAll.add(lop);
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

    public Boolean checkTen(Lop kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_lop where tenLop='" + kh.getTenLop() + "'");
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

    public Boolean checkDelete(Lop kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_hocsinh, tbl_lop where tbl_lop.maLop='" + kh.getMaLop() + "' and tbl_hocsinh.maLop ='" + kh.getMaLop() + "'");
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

    public void insert(Lop kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("insert into tbl_lop (tenLop,tenGVCN,maKhoi) "
                        + "values ('" + kh.getTenLop() + "','" + kh.getTenGVCN() + "','" + kh.getMaKhoi() + "')");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void update(String maLop, Lop kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("update tbl_lop set maKhoi='" + kh.getMaKhoi() + "', tenLop='" + kh.getTenLop() + "',tenGVCN='" + kh.getTenGVCN() + "' where maLop='" + maLop + "'");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeConnec(con);
                closeStatement(pr);
            }
        }
    }

    public void delete(String maLop) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        try {
            pr = con.prepareStatement("delete from tbl_lop where maLop='" + maLop + "'");
            pr.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeConnec(con);
            closeStatement(pr);
        }
    }

    public ArrayList<Lop> find(String txt, String maKhoi) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Lop> listAll = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from tbl_lop"
                    + " where maLop like'%" + txt + "%' and maKhoi='" + maKhoi + "' "
                    + " or tenLop like'%" + txt + "%' and maKhoi='" + maKhoi + "' "
                    + " or tenGVCN like'%" + txt + "%' and maKhoi='" + maKhoi + "' ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maLop = rs.getString(1) + rs.getString(2);
                Lop lop = new Lop(maLop, rs.getString(3), rs.getString(4), rs.getString(5));
                listAll.add(lop);
            }
        } catch (SQLException e) {
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnec(con);
        }
        return listAll;
    }

    public ArrayList<Lop> findMaKhoi(String txt) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Lop> listAll = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from tbl_lop where maKhoi='" + txt + "'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maLop = rs.getString(1) + rs.getString(2);
                Lop lop = new Lop(maLop, rs.getString(3), rs.getString(4), rs.getString(5));
                listAll.add(lop);
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
