package DAO;

import static DAO.DatabaseConnection.*;
import Entity.BangDiemChiTiet;
import java.sql.*;
import java.util.*;

public class BangDiemChiTietDAO {

    public BangDiemChiTietDAO() {
    }

    public BangDiemChiTiet getInfo(String maBD) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement("select tbl_bangdiemchitiet.*, tbl_monhoc.tenMonHoc as tenMonHocHT "
                    + "from tbl_bangdiemchitiet, tbl_monhoc "
                    + "where tbl_bangdiemchitiet.maBangDiem='" + maBD + "' and tbl_bangdiemchitiet.maMonHoc=tbl_monhoc.maMonHoc "
                    + "order by tbl_bangdiemchitiet.maMonHoc asc");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                float diemTB = (float) (rs.getFloat(4) * 0.1 + rs.getFloat(5) * 0.15 + rs.getFloat(6) * 0.15 + rs.getFloat(7) * 0.6);
                BangDiemChiTiet bdct = new BangDiemChiTiet(rs.getString(2), rs.getString(3), rs.getString("tenMonHocHT"), rs.getFloat(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7), diemTB);
                rs.close();
                stmt.close();
                return bdct;
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
                    float diemTB = (float) (rs.getFloat(4) * 0.1 + rs.getFloat(5) * 0.15 + rs.getFloat(6) * 0.15 + rs.getFloat(7) * 0.6);
                    BangDiemChiTiet bdct = new BangDiemChiTiet(rs.getString(2), rs.getString(3), rs.getString("tenMonHocHT"), rs.getFloat(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7), diemTB);
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
                stmt = con.prepareStatement("select * from tbl_bangdiemchitiet "
                        + "where maBangDiem='" + kh.getMaBangDiem() + "' and maMonHoc='" + kh.getMaMon() + "'");
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

    public void update(String maBangDiem, String maMonHoc, BangDiemChiTiet kh) {
        Connection con = getConnection();
        PreparedStatement pr = null;
        if (con != null) {
            try {
                pr = con.prepareStatement("update tbl_bangdiemchitiet set diemMieng='" + kh.getDiemMieng() + "',diem1='" + kh.getDiem1() + "', diem2='" + kh.getDiem2() + "', diemThi='" + kh.getDiemThi() + "'"
                        + " where maBangDiemChiTiet='" + maBangDiem + "' and maMonHoc='" + maMonHoc + "' ");
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

    public ArrayList<BangDiemChiTiet> find(String txt, String maBangDiem) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<BangDiemChiTiet> listAll = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select tbl_bangdiemchitiet.*, tbl_monhoc.tenMonHoc as tenMonHocHT "
                    + "from tbl_bangdiemchitiet, tbl_monhoc "
                    + "where tbl_monhoc.tenMonHoc likeN'%" + txt + "%' and tbl_bangdiemchitiet.maBangDiem='" + maBangDiem + "' and tbl_bangdiemchitiet.maMonHoc=tbl_monhoc.maMonHoc "
                    + "order by tbl_bangdiemchitiet.maMonHoc asc");
            rs = stmt.executeQuery();
            while (rs.next()) {
                float diemTB = (float) (rs.getFloat(4) * 0.1 + rs.getFloat(5) * 0.15 + rs.getFloat(6) * 0.15 + rs.getFloat(7) * 0.6);
                BangDiemChiTiet bdct = new BangDiemChiTiet(rs.getString(2), rs.getString(3), rs.getString("tenMonHocHT"), rs.getFloat(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7), diemTB);
                listAll.add(bdct);
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
