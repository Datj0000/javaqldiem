package DAO;

import static Connection.DatabaseConnection.*;
import Entity.BangDiemChiTiet;
import java.sql.*;
import java.util.*;

public class BangDiemChiTietDAO {

    public BangDiemChiTietDAO() {
    }

    public ArrayList<BangDiemChiTiet> getAll(String maBangDiem) {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<BangDiemChiTiet> listAll = new ArrayList<>();
        if (con != null) {
            try {
                st = con.createStatement();
                rs = st.executeQuery("select tbl_bangdiemchitiet.*, tbl_monhoc.tenMonHoc as tenMonHocHT "
                        + "from tbl_bangdiemchitiet, tbl_monhoc "
                        + "where tbl_bangdiemchitiet.maBangDiem='" + maBangDiem + "' and tbl_bangdiemchitiet.maMonHoc=tbl_monhoc.maMonHoc "
                        + "order by tbl_bangdiemchitiet.maMonHoc asc");
                while (rs.next()) {
                    double diemTB = (double) (rs.getFloat(4) * 0.1 + rs.getFloat(5) * 0.15 + rs.getFloat(6) * 0.15 + rs.getFloat(7) * 0.6);
                    double roundOff = Math.round(diemTB * 100.0) / 100.0;
                    BangDiemChiTiet bdct = new BangDiemChiTiet(rs.getString(2), rs.getString(3), rs.getString("tenMonHocHT"), rs.getFloat(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7), roundOff);
                    listAll.add(bdct);
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

    public Boolean checkTen(BangDiemChiTiet kh) {
        Boolean k = false;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                stmt = con.prepareStatement("select * from tbl_bangdiemchitiet"
                        + " where maBangDiem='" + kh.getMaBangDiem() + "' and maMonHoc='" + kh.getMaMon() + "'");
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

    public void insert(BangDiemChiTiet kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("insert into tbl_bangdiemchitiet (maBangDiem,maMonHoc,diemMieng,diem1,diem2,diemThi) "
                        + "values ('" + kh.getMaBangDiem() + "','" + kh.getMaMon() + "','" + kh.getDiemMieng() + "','" + kh.getDiem1() + "','" + kh.getDiem2() + "','" + kh.getDiemThi() + "')");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void update(BangDiemChiTiet kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("update tbl_bangdiemchitiet set diemMieng='" + kh.getDiemMieng() + "',diem1='" + kh.getDiem1() + "', diem2='" + kh.getDiem2() + "', diemThi='" + kh.getDiemThi() + "'"
                        + " where maBangDiem='" + kh.getMaBangDiem() + "' and maMonHoc='" + kh.getMaMon() + "'");
                pr.executeUpdate();
            } catch (SQLException e) {
            } finally {
                closeStatement(pr);
                closeConnec(con);
            }
        }
    }

    public void delete(String maBangDiem, String maMonHoc) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        try {
            pr = con.prepareStatement("delete from tbl_bangdiemchitiet where maBangDiem='" + maBangDiem + "' and maMonHoc='" + maMonHoc + "'");
            pr.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeConnec(con);
            closeStatement(pr);
        }
    }
}
