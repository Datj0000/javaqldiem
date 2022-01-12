package Controller;

import View.*;
import Entity.*;
import DAO.*;
import Model.*;
import View.Component.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.*;

public class ControllerBangDiemChiTiet {

    private final BangDiemDAO bdDAO;
    private final HocKyDAO hkDAO;
    private final MonHocDAO mhDAO;
    private final LopDAO lDAO;
    private final BangDiemChiTietDAO bdctDAO;
    private final JBangDiemChiTiet jBangDiemChiTiet;

    public ControllerBangDiemChiTiet(JBangDiemChiTiet _jBangDiemChiTiet) {
        this.jBangDiemChiTiet = _jBangDiemChiTiet;
        bdctDAO = new BangDiemChiTietDAO();
        bdDAO = new BangDiemDAO();
        mhDAO = new MonHocDAO();
        hkDAO = new HocKyDAO();
        lDAO = new LopDAO();
    }

    public void ShowData() {
        jBangDiemChiTiet.DataCboMonHoc(new CbModelMonHoc(mhDAO.getAll()));
        jBangDiemChiTiet.addListSelectionListener(new SelectionListener());
        jBangDiemChiTiet.addInsertListener(new InsertListener());
        jBangDiemChiTiet.addEditListener(new EditListener());
        jBangDiemChiTiet.addDeleteListener(new DeleteListener());
        jBangDiemChiTiet.addSearchListener(new SearchListener());
        jBangDiemChiTiet.addPrintListener(new PrintListener());
        String maBangDiem = jBangDiemChiTiet.txtMaBangDiem.getText().replaceAll("[^\\d.]", "");
        if (!bdctDAO.getAll(maBangDiem).isEmpty()) {
            jBangDiemChiTiet.DataTable(new ModelBangDiemChiTiet(bdctDAO.getAll(maBangDiem)));
        }
    }

    class SearchListener implements DocumentListener {

        void search() {
            String txtSearch = jBangDiemChiTiet.txtSearch.getText().trim();
            String maBangDiem = jBangDiemChiTiet.txtMaBangDiem.getText().replaceAll("[^\\d.]", "");
            if (!"".equals(txtSearch) && !"".equals(maBangDiem)) {
                if (!bdctDAO.find(txtSearch, maBangDiem).isEmpty()) {
                    jBangDiemChiTiet.DataTable(new ModelBangDiemChiTiet(bdctDAO.find(txtSearch, maBangDiem)));
                } else {
                    clearTable();
                }
            } else {
                if (!bdctDAO.getAll(maBangDiem).isEmpty()) {
                    jBangDiemChiTiet.DataTable(new ModelBangDiemChiTiet(bdctDAO.getAll(maBangDiem)));
                }
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            search();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            search();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            search();
        }
    }

    class SelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = jBangDiemChiTiet.jTable.getSelectedRow();
            if (row >= 0) {
                String tenMonHoc = jBangDiemChiTiet.jTable.getValueAt(row, 1).toString();
                for (int i = 0; i < jBangDiemChiTiet.cboMonHoc.getItemCount(); i++) {
                    if (jBangDiemChiTiet.cboMonHoc.getItemAt(i).toString().equalsIgnoreCase(tenMonHoc)) {
                        jBangDiemChiTiet.cboMonHoc.setSelectedIndex(i);
                    }
                }
                jBangDiemChiTiet.txtDiemMieng.setText(jBangDiemChiTiet.jTable.getModel().getValueAt(row, 2).toString());
                jBangDiemChiTiet.txtDiemKT1.setText(jBangDiemChiTiet.jTable.getModel().getValueAt(row, 3).toString());
                jBangDiemChiTiet.txtDiemKT2.setText(jBangDiemChiTiet.jTable.getModel().getValueAt(row, 4).toString());
                jBangDiemChiTiet.txtDiemThi.setText(jBangDiemChiTiet.jTable.getModel().getValueAt(row, 5).toString());
            }
        }
    }

    class PrintListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            XuLyFileExcel excel = new XuLyFileExcel();
            String maBangDiem = jBangDiemChiTiet.txtMaBangDiem.getText().replaceAll("[^\\d.]", "");
            BangDiem infoBangDiem = bdDAO.getInfo(maBangDiem);
            HocKy infoHocKy = hkDAO.getInfo(infoBangDiem.getMaHocKy());
            Lop infoLop = lDAO.getInfo(infoBangDiem.getMaLop());
            String tenKhoi = infoLop.getTenKhoi();
            String tenLop = infoBangDiem.getTenLop();
            String tenHocSinh = infoBangDiem.getTenHocSinh();
            String tenNamHoc = infoHocKy.getTenNamHoc();
            String tenHocKy = infoHocKy.getTenHocKy();
            excel.xuatExcel2(jBangDiemChiTiet.jTable, tenKhoi, tenLop, tenHocSinh, tenNamHoc, tenHocKy);
        }
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BangDiemChiTiet bdct = getInfo();
            if (bdct != null) {
                if (!bdctDAO.checkTen(bdct)) {
                    clearInfo();
                    bdctDAO.insert(bdct);
                    jBangDiemChiTiet.DataTable(new ModelBangDiemChiTiet(bdctDAO.getAll(bdct.getMaBangDiem())));
                    Menu.showMessage("Thêm thành công!");
                } else {
                    Menu.showMessage("Học kì này học sinh đã có điểm môn này rồi!");
                }
            }
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BangDiemChiTiet bdct = getInfo();
            if (bdct != null) {
                if (bdctDAO.checkTen(bdct)) {
                    clearInfo();
                    bdctDAO.update(bdct.getMaBangDiem(), bdct.getMaMon(), bdct);
                    jBangDiemChiTiet.DataTable(new ModelBangDiemChiTiet(bdctDAO.getAll(bdct.getMaBangDiem())));
                    Menu.showMessage("Cập nhật thành công!");
                } else {
                    Menu.showMessage("Học kì này học sinh đã có điểm môn này rồi!");
                }
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BangDiemChiTiet bdct = getInfo();
            if (bdct != null) {
                bdctDAO.delete(bdct.getMaBangDiem(), bdct.getMaMon());
                clearInfo();
                if (bdctDAO.getAll(bdct.getMaBangDiem()).isEmpty()) {
                    clearTable();
                } else {
                    jBangDiemChiTiet.DataTable(new ModelBangDiemChiTiet(bdctDAO.getAll(bdct.getMaBangDiem())));
                }
                Menu.showMessage("Xóa thành công!");
            }
        }
    }

    public BangDiemChiTiet getInfo() {
        try {
            BangDiemChiTiet dbct = new BangDiemChiTiet();
            MonHoc khoi = (MonHoc) jBangDiemChiTiet.cboMonHoc.getSelectedItem();
            String maMonHoc = khoi.getMaMonHoc().replaceAll("[^\\d.]", "");
            String maBangDiem = jBangDiemChiTiet.txtMaBangDiem.getText().trim().replaceAll("[^\\d.]", "");
            float diemMieng = Float.parseFloat(jBangDiemChiTiet.txtDiemMieng.getText().trim());
            float diemKT1 = Float.parseFloat(jBangDiemChiTiet.txtDiemKT1.getText().trim());
            float diemKT2 = Float.parseFloat(jBangDiemChiTiet.txtDiemKT2.getText().trim());
            float diemThi = Float.parseFloat(jBangDiemChiTiet.txtDiemThi.getText().trim());
            if (!"".equals(maMonHoc)) {
                dbct.setMaBangDiem(maBangDiem);
                dbct.setMaMon(maMonHoc);
                dbct.setDiemMieng(diemMieng);
                dbct.setDiem1(diemKT1);
                dbct.setDiem2(diemKT2);
                dbct.setDiemThi(diemThi);
            } else {
                Menu.showMessage("Vui lòng chọn môn học");
                dbct = null;
            }
            return dbct;
        } catch (Exception e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }

    public void clearInfo() {
        jBangDiemChiTiet.cboMonHoc.setSelectedIndex(0);
        jBangDiemChiTiet.txtDiemMieng.setText("");
        jBangDiemChiTiet.txtDiemKT1.setText("");
        jBangDiemChiTiet.txtDiemKT2.setText("");
        jBangDiemChiTiet.txtDiemThi.setText("");
        jBangDiemChiTiet.txtSearch.setText("");
    }

    public void clearTable() {
        ArrayList<BangDiemChiTiet> listAll = new ArrayList<>();
        BangDiemChiTiet bdct = new BangDiemChiTiet();
        listAll.add(bdct);
        jBangDiemChiTiet.DataTable(new ModelBangDiemChiTiet(listAll));
    }
}
