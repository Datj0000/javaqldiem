package Controller;

import View.*;
import Entity.*;
import DAO.*;
import Model.*;
import View.Component.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.event.*;
import javax.swing.table.TableRowSorter;

public class ControllerBangDiemChiTiet {

    private final BangDiemDAO bdDAO;
    private final HocKyDAO hkDAO;
    private final MonHocDAO mhDAO;
    private final LopDAO lDAO;
    private final BangDiemChiTietDAO bdctDAO;
    private final JBangDiemChiTiet jBangDiemChiTiet;
    private ModelBangDiemChiTiet modelBangDiemChiTiet;

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
            String txtSearch = jBangDiemChiTiet.txtSearch.getText().trim().toLowerCase();
            String maBangDiem = jBangDiemChiTiet.txtMaBangDiem.getText().replaceAll("[^\\d.]", "");
            if (!bdctDAO.getAll(maBangDiem).isEmpty()) {
                modelBangDiemChiTiet = (ModelBangDiemChiTiet) jBangDiemChiTiet.jTable.getModel();
                TableRowSorter<ModelBangDiemChiTiet> trs = new TableRowSorter<>(modelBangDiemChiTiet);
                jBangDiemChiTiet.jTable.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch));
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
                    bdctDAO.update(bdct);
                    jBangDiemChiTiet.DataTable(new ModelBangDiemChiTiet(bdctDAO.getAll(bdct.getMaBangDiem())));
                    Menu.showMessage("Cập nhật thành công!");
                } else {
                    Menu.showMessage("Không tìm thấy môn học để sửa!");
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

    static boolean checkFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean checkDiem(float diem) {
        if (0 < diem && diem < 10) {
            return true;
        } else {
            return false;
        }
    }

    public BangDiemChiTiet getInfo() {
        try {
            BangDiemChiTiet dbct = new BangDiemChiTiet();
            MonHoc monHoc = (MonHoc) jBangDiemChiTiet.cboMonHoc.getSelectedItem();
            String maMonHoc = monHoc.getMaMonHoc().replaceAll("[^\\d.]", "");
            String maBangDiem = jBangDiemChiTiet.txtMaBangDiem.getText().trim().replaceAll("[^\\d.]", "");
            if (!"".equals(maMonHoc)) {
                String diemMieng = jBangDiemChiTiet.txtDiemMieng.getText().trim();
                String diemKT1 = jBangDiemChiTiet.txtDiemKT1.getText().trim();
                String diemKT2 = jBangDiemChiTiet.txtDiemKT2.getText().trim();
                String diemThi = jBangDiemChiTiet.txtDiemThi.getText().trim();
                if (checkFloat(diemMieng) && checkFloat(diemKT1) && checkFloat(diemKT2) && checkFloat(diemThi)) {
                    float DiemMieng = Float.parseFloat(jBangDiemChiTiet.txtDiemMieng.getText().trim());
                    float DiemKT1 = Float.parseFloat(jBangDiemChiTiet.txtDiemKT1.getText().trim());
                    float DiemKT2 = Float.parseFloat(jBangDiemChiTiet.txtDiemKT2.getText().trim());
                    float DiemThi = Float.parseFloat(jBangDiemChiTiet.txtDiemThi.getText().trim());
                    if (checkDiem(DiemMieng) && checkDiem(DiemKT1) && checkDiem(DiemKT2) && checkDiem(DiemThi)) {
                        dbct.setMaBangDiem(maBangDiem);
                        dbct.setMaMon(maMonHoc);
                        dbct.setDiemMieng(DiemMieng);
                        dbct.setDiem1(DiemKT1);
                        dbct.setDiem2(DiemKT2);
                        dbct.setDiemThi(DiemThi);
                    } else {
                        Menu.showMessage("Vui lòng nhập lại điểm");
                        dbct = null;
                    }
                } else {
                    Menu.showMessage("Vui lòng nhập lại điểm");
                    dbct = null;
                }
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
