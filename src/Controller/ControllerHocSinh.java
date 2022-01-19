package Controller;

import View.*;
import DAO.*;
import Entity.*;
import Model.*;
import View.Component.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.RowFilter;
import javax.swing.event.*;
import javax.swing.table.TableRowSorter;

public class ControllerHocSinh {

    private final LopDAO lDAO;
    private final KhoiDAO kDAO;
    private final HocSinhDAO hsDAO;
    private final JHocSinh jHocSinh;
    private ModelHocSinh modelHocSinh;

    public ControllerHocSinh(JHocSinh _jHocSinh) {
        this.jHocSinh = _jHocSinh;
        hsDAO = new HocSinhDAO();
        kDAO = new KhoiDAO();
        lDAO = new LopDAO();
    }

    public void ShowData() {
        jHocSinh.DataCboKhoi(new CbModelKhoi(kDAO.getAll()));
        jHocSinh.addListSelectionListener(new SelectionListener());
        jHocSinh.addInsertListener(new InsertListener());
        jHocSinh.addEditListener(new EditListener());
        jHocSinh.addDeleteListener(new DeleteListener());
        jHocSinh.addPrintListener(new PrintListener());
        jHocSinh.addSearchListener(new SearchListener());
        jHocSinh.addComboKhoiListener(new ComboBoxKhoiListener());
        jHocSinh.addComboLopListener(new ComboBoxLopListener());
    }

    class ComboBoxKhoiListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Khoi khoi = (Khoi) jHocSinh.cboKhoi.getSelectedItem();
            String maKhoi = khoi.getMaKhoi();
            maKhoi = maKhoi.replaceAll("[^\\d.]", "");
            jHocSinh.DataCboLop(new CbModelLop(lDAO.findMaKhoi(maKhoi)));
            clearInfo();
            clearTable();
        }
    }

    class ComboBoxLopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Lop lop = (Lop) jHocSinh.cboLop.getSelectedItem();
            String maLop = lop.getMaLop();
            maLop = maLop.replaceAll("[^\\d.]", "");
            clearInfo();
            if (!hsDAO.findMaLop(maLop).isEmpty()) {
                jHocSinh.DataTable(new ModelHocSinh(hsDAO.findMaLop(maLop)));
            } else {
                clearTable();
            }
        }
    }

    class SearchListener implements DocumentListener {

        void search() {
            String txtSearch = jHocSinh.txtSearch.getText().trim().toLowerCase();
            Lop lop = (Lop) jHocSinh.cboLop.getSelectedItem();
            String maLop = "";
            if (lop != null) {
                maLop = lop.getMaLop().replaceAll("[^\\d.]", "");
            }
            if (!"".equals(txtSearch) && !hsDAO.findMaLop(maLop).isEmpty()) {
                modelHocSinh = (ModelHocSinh) jHocSinh.jTable.getModel();
                TableRowSorter<ModelHocSinh> trs = new TableRowSorter<>(modelHocSinh);
                jHocSinh.jTable.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch));
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e
        ) {
            search();
        }

        @Override
        public void removeUpdate(DocumentEvent e
        ) {
            search();
        }

        @Override
        public void changedUpdate(DocumentEvent e
        ) {
            search();
        }
    }

    class SelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = jHocSinh.jTable.getSelectedRow();
            if (row >= 0) {
                jHocSinh.txtMaHocSinh.setText(jHocSinh.jTable.getModel().getValueAt(row, 1).toString());
                jHocSinh.txtTenHocSinh.setText(jHocSinh.jTable.getModel().getValueAt(row, 2).toString());
                String gioiTinh = jHocSinh.jTable.getModel().getValueAt(row, 3).toString();
                if (gioiTinh.equals("Nam")) {
                    jHocSinh.rdNam.setSelected(true);
                } else if (gioiTinh.equals("Nữ")) {
                    jHocSinh.rdNu.setSelected(true);
                }
                jHocSinh.txtNgaySinh.setText(jHocSinh.jTable.getModel().getValueAt(row, 4).toString());
                jHocSinh.txtNoiSinh.setText(jHocSinh.jTable.getModel().getValueAt(row, 5).toString());
                jHocSinh.txtDanToc.setText(jHocSinh.jTable.getModel().getValueAt(row, 6).toString());
            }
        }
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            HocSinh hocsinh = getInfo();
            if (hocsinh != null) {
                hsDAO.insert(hocsinh);
                jHocSinh.DataTable(new ModelHocSinh(hsDAO.findMaLop(hocsinh.getMaLop())));
                Menu.showMessage("Thêm thành công!");
            }
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            HocSinh hocsinh = getInfo();
            if (hocsinh != null) {
                hsDAO.update(hocsinh.getMaHocSinh(), hocsinh);
                jHocSinh.DataTable(new ModelHocSinh(hsDAO.findMaLop(hocsinh.getMaLop())));
                Menu.showMessage("Cập nhật thành công!");
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            HocSinh hocsinh = getInfo();
            if (hocsinh != null) {
                if (!hsDAO.checkDelete(hocsinh)) {
                    hsDAO.delete(hocsinh.getMaHocSinh());
                    Menu.showMessage("Xóa thành công!");
                    clearInfo();
                    if (hsDAO.findMaLop(hocsinh.getMaLop()).isEmpty()) {
                        clearTable();
                    } else {
                        jHocSinh.DataTable(new ModelHocSinh(hsDAO.findMaLop(hocsinh.getMaLop())));
                    }
                } else {
                    Menu.showMessage("Học sinh này đang được nhập điểm!");
                }
            }
        }
    }

    class PrintListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String maLop = "";
            Lop lop = (Lop) jHocSinh.cboLop.getSelectedItem();
            if (lop != null) {
                maLop = lop.getMaLop();
                maLop = maLop.replaceAll("[^\\d.]", "");
                XuLyFileExcel cc = new XuLyFileExcel();
                cc.xuatExcel(jHocSinh.jTable);
            } else {
                Menu.showMessage("Vui lòng chọn lớp để in");
            }
        }
    }

    public HocSinh getInfo() {
        try {
            HocSinh hocsinh = new HocSinh();
            String maHocSinh = jHocSinh.txtMaHocSinh.getText().replaceAll("[^\\d.]", "").trim();
            String tenHocSinh = jHocSinh.txtTenHocSinh.getText().trim();
            String ngaySinh = jHocSinh.txtNgaySinh.getText().trim();
            String noiSinh = jHocSinh.txtNoiSinh.getText().trim();
            String danToc = jHocSinh.txtDanToc.getText().trim();
            String gioiTinh = "";
            String maLop = "";
            Lop lop = (Lop) jHocSinh.cboLop.getSelectedItem();
            if (lop != null) {
                maLop = lop.getMaLop();
                maLop = maLop.replaceAll("[^\\d.]", "");
            }
            if (jHocSinh.rdNam.isSelected() != false || jHocSinh.rdNu.isSelected() != false) {
                gioiTinh = jHocSinh.bgGioiTinh.getSelection().getActionCommand();
            }
            if (!"".equals(maLop) && !"".equals(tenHocSinh) && !"".equals(ngaySinh) && !"".equals(noiSinh) && !"".equals(danToc)) {
                if (DateUtils.isValidDate(ngaySinh)) {
                    hocsinh.setMaLop(maLop);
                    hocsinh.setMaHocSinh(maHocSinh);
                    hocsinh.setTenHocSinh(tenHocSinh);
                    hocsinh.setGioiTinh(gioiTinh);
                    hocsinh.setNgaySinh(ngaySinh);
                    hocsinh.setNoiSinh(noiSinh);
                    hocsinh.setDanToc(danToc);
                } else {
                    Menu.showMessage("Sai định dạng ngày(dd/mm/yyyy)");
                    hocsinh = null;
                }
            } else {
                Menu.showMessage("Không được để trống thông tin học sinh");
                hocsinh = null;
            }
            return hocsinh;
        } catch (Exception e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }

    public void clearInfo() {
        jHocSinh.txtMaHocSinh.setText("");
        jHocSinh.txtTenHocSinh.setText("");
        jHocSinh.rdNam.setSelected(false);
        jHocSinh.rdNu.setSelected(false);
        jHocSinh.txtNgaySinh.setText("");
        jHocSinh.txtNoiSinh.setText("");
        jHocSinh.txtDanToc.setText("");
        jHocSinh.txtSearch.setText("");
    }

    public void clearTable() {
        ArrayList<HocSinh> listAll = new ArrayList<>();
        HocSinh hs = new HocSinh();
        listAll.add(hs);
        jHocSinh.DataTable(new ModelHocSinh(listAll));
    }
}
