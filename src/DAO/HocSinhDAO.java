package DAO;

import static DAO.DatabaseConnection.*;
import Entity.HocSinh;
import java.sql.*;
import java.util.ArrayList;

public class HocSinhDAO {

    public HocSinhDAO() {
    }

    public ArrayList<HocSinh> getAll() {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<HocSinh> listAll = new ArrayList<>();
        if (con != null) {
            try {
                st = con.createStatement();
                rs = st.executeQuery("select * from tbl_hocsinh");
                while (rs.next()) {
                    String maHocSinh = rs.getString(1) + rs.getString(2);
                    HocSinh hs = new HocSinh(maHocSinh, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                    listAll.add(hs);
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

    public Boolean checkDelete(HocSinh kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_bangdiem, tbl_hocsinh where tbl_hocsinh.maHocSinh='" + kh.getMaHocSinh() + "' and tbl_bangdiem.maHocSinh ='" + kh.getMaHocSinh() + "'");
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

    public int insert(HocSinh kh) {
        int k = 0;
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("insert into tbl_hocsinh (tenHocSinh,ngaySinh,gioiTinh,noiSinh,danToc,maLop) "
                        + "values ('" + kh.getTenHocSinh() + "','" + kh.getNgaySinh() + "','" + kh.getGioiTinh() + "','" + kh.getNoiSinh() + "','" + kh.getDanToc() + "','" + kh.getMaLop() + "')");
                k = pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
        return k;
    }

    public int update(String maHocSinh, HocSinh kh) {
        int k = 0;
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("update tbl_hocsinh set tenHocSinh='" + kh.getTenHocSinh() + "', ngaySinh='" + kh.getNgaySinh() + "', gioiTinh='" + kh.getGioiTinh() + "', noiSinh='" + kh.getNoiSinh() + "', danToc='" + kh.getDanToc() + "', maLop='" + kh.getMaLop() + "' where maHocSinh='" + maHocSinh + "'");
                k = pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
        return k;
    }

    public int delete(String maHocSinh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        int k = 0;
        try {
            pr = con.prepareStatement("delete from tbl_hocsinh where maHocSinh='" + maHocSinh + "'");
            k = pr.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeConnec(con);
            closeStatement(pr);
        }
        return k;
    }

    public ArrayList<HocSinh> findMaLop(String txt) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<HocSinh> listAll = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from tbl_hocsinh where maLop='" + txt + "'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maHocSinh = rs.getString(1) + rs.getString(2);
                HocSinh hs = new HocSinh(maHocSinh, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                listAll.add(hs);
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

    public ArrayList<HocSinh> find(String txt, String maLop) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<HocSinh> listAll = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from tbl_hocsinh"
                    + " where maHocSinh like'%" + txt + "%' and maLop='" + maLop + "' "
                    + " or tenHocSinh like'%" + txt + "%' and maLop='" + maLop + "' ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maHocSinh = rs.getString(1) + rs.getString(2);
                HocSinh hs = new HocSinh(maHocSinh, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                listAll.add(hs);
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
