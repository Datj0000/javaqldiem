package Controller;

import View.*;
import Entity.*;
import DAO.*;
import Model.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.event.*;
import javax.swing.table.TableRowSorter;

public class ControllerLop {

    private final KhoiDAO kDAO;
    private final LopDAO lDAO;
    private final JLop jLop;
    private ModelLop modelLop;

    public ControllerLop(JLop _jLop) {
        this.jLop = _jLop;
        lDAO = new LopDAO();
        kDAO = new KhoiDAO();
    }

    public void ShowData() {
        jLop.DataCboKhoi(new CbModelKhoi(kDAO.getAll()));
        jLop.addListSelectionListener(new SelectionListener());
        jLop.addInsertListener(new InsertListener());
        jLop.addEditListener(new EditListener());
        jLop.addDeleteListener(new DeleteListener());
        jLop.addSearchListener(new SearchListener());
        jLop.addComboKhoiListener(new ComboBoxKhoiListener());
    }

    class ComboBoxKhoiListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Khoi khoi = (Khoi) jLop.cboKhoi.getSelectedItem();
            String maKhoi = khoi.getMaKhoi();
            maKhoi = maKhoi.replaceAll("[^\\d.]", "");
            clearInfo();
            if (!lDAO.findMaKhoi(maKhoi).isEmpty()) {
                jLop.DataTable(new ModelLop(lDAO.findMaKhoi(maKhoi)));
            } else {
                clearTable();
            }
        }
    }

    class SearchListener implements DocumentListener {

        void search() {
            String txtSearch = jLop.txtSearch.getText().trim().toLowerCase();
            Khoi khoi = (Khoi) jLop.cboKhoi.getSelectedItem();
            String maKhoi = "";
            if (khoi != null) {
                maKhoi = khoi.getMaKhoi().replaceAll("[^\\d.]", "");
            }
            if (!"".equals(maKhoi) && !lDAO.findMaKhoi(maKhoi).isEmpty()) {
                modelLop = (ModelLop) jLop.jTable.getModel();
                TableRowSorter<ModelLop> trs = new TableRowSorter<>(modelLop);
                jLop.jTable.setRowSorter(trs);
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
            int row = jLop.jTable.getSelectedRow();
            if (row >= 0) {
                jLop.txtMaLop.setText(jLop.jTable.getModel().getValueAt(row, 1).toString());
                jLop.txtTenLop.setText(jLop.jTable.getModel().getValueAt(row, 2).toString());
                jLop.txtGvcn.setText(jLop.jTable.getModel().getValueAt(row, 3).toString());
            }
        }
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Lop lop = getInfo();
            if (lop != null) {
                if (!lDAO.checkTen(lop)) {
                    clearInfo();
                    lDAO.insert(lop);
                    jLop.DataTable(new ModelLop(lDAO.findMaKhoi(lop.getMaKhoi())));
                    Menu.showMessage("Thêm thành công!");
                } else {
                    Menu.showMessage("Tên lớp đã tồn tại!");
                }
            }
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Lop lop = getInfo();
            if (lop != null) {
                if (!lDAO.checkTen(lop)) {
                    clearInfo();
                    lDAO.update(lop.getMaLop(), lop);
                    jLop.DataTable(new ModelLop(lDAO.findMaKhoi(lop.getMaKhoi())));
                    Menu.showMessage("Cập nhật thành công!");
                } else {
                    Menu.showMessage("Tên lớp đã tồn tại!");
                }
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Lop lop = getInfo();
            if (lop != null) {
                if (!lDAO.checkDelete(lop)) {
                    lDAO.delete(lop.getMaLop());
                    clearInfo();
                    if (lDAO.findMaKhoi(lop.getMaKhoi()).isEmpty()) {
                        clearTable();
                    } else {
                        jLop.DataTable(new ModelLop(lDAO.findMaKhoi(lop.getMaKhoi())));
                    }
                    Menu.showMessage("Xóa thành công!");
                } else {
                    Menu.showMessage("Lớp này đang có học sinh!");
                }
            }
        }
    }

    public Lop getInfo() {
        try {
            Lop lop = new Lop();
            Khoi khoi = (Khoi) jLop.cboKhoi.getSelectedItem();
            String maKhoi = khoi.getMaKhoi();
            maKhoi = maKhoi.replaceAll("[^\\d.]", "");
            String maLop = jLop.txtMaLop.getText().trim();
            maLop = maLop.replaceAll("[^\\d.]", "");
            String tenLop = jLop.txtTenLop.getText().trim();
            String tenGVCN = jLop.txtGvcn.getText().trim();
            if (!"".equals(tenLop) && !"".equals(tenGVCN) && !"".equals(maKhoi)) {
                lop.setMaKhoi(maKhoi);
                lop.setMaLop(maLop);
                lop.setTenLop(tenLop);
                lop.setTenGVCN(tenGVCN);
            } else {
                Menu.showMessage("Không được để trống thông tin lớp");
                lop = null;
            }
            return lop;
        } catch (Exception e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }

    public void clearInfo() {
        jLop.txtMaLop.setText("");
        jLop.txtTenLop.setText("");
        jLop.txtGvcn.setText("");
        jLop.txtSearch.setText("");
    }

    public void clearTable() {
        ArrayList<Lop> listAll = new ArrayList<>();
        Lop lop = new Lop();
        listAll.add(lop);
        jLop.DataTable(new ModelLop(listAll));
    }
}
