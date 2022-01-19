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

public class ControllerKhoi {

    private final KhoiDAO DAO;
    private final JKhoi jKhoi;
    private ModelKhoi modelKhoi;

    public ControllerKhoi(JKhoi _jKhoi) {
        this.jKhoi = _jKhoi;
        DAO = new KhoiDAO();
    }

    public void ShowData() {
        if (!DAO.getAll().isEmpty()) {
            jKhoi.DataTable(new ModelKhoi(DAO.getAll()));
        }
        jKhoi.addListSelectionListener(new SelectionListener());
        jKhoi.addInsertListener(new InsertListener());
        jKhoi.addEditListener(new EditListener());
        jKhoi.addDeleteListener(new DeleteListener());
        jKhoi.addSearchListener(new SearchListener());
    }

    class SearchListener implements DocumentListener {

        void search() {
            String txtSearch = jKhoi.txtSearch.getText().trim().toLowerCase();
            if (!DAO.getAll().isEmpty()) {
                modelKhoi = (ModelKhoi) jKhoi.jTable.getModel();
                TableRowSorter<ModelKhoi> trs = new TableRowSorter<>(modelKhoi);
                jKhoi.jTable.setRowSorter(trs);
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
            int row = jKhoi.jTable.getSelectedRow();
            if (row >= 0) {
                jKhoi.txtMaKhoi.setText(jKhoi.jTable.getModel().getValueAt(row, 1).toString());
                jKhoi.txtTenKhoi.setText(jKhoi.jTable.getModel().getValueAt(row, 2).toString());
            }
        }
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Khoi khoi = getInfo();
            if (khoi != null) {
                if (!DAO.checkTen(khoi)) {
                    clearInfo();
                    DAO.insert(khoi);
                    jKhoi.DataTable(new ModelKhoi(DAO.getAll()));
                    Menu.showMessage("Thêm thành công!");
                } else {
                    Menu.showMessage("Tên khối đã tồn tại!");
                }
            }
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Khoi khoi = getInfo();
            if (khoi != null) {
                if (!DAO.checkTen(khoi)) {
                    clearInfo();
                    DAO.update(khoi.getMaKhoi(), khoi);
                    jKhoi.DataTable(new ModelKhoi(DAO.getAll()));
                    Menu.showMessage("Cập nhật thành công!");
                } else {
                    Menu.showMessage("Tên khối đã tồn tại!");
                }
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Khoi khoi = getInfo();
            if (khoi != null) {
                if (!DAO.checkDelete(khoi)) {
                    DAO.delete(khoi.getMaKhoi());
                    clearInfo();
                    if (DAO.getAll().isEmpty()) {
                        clearTable();
                    } else {
                        jKhoi.DataTable(new ModelKhoi(DAO.getAll()));
                    }
                    Menu.showMessage("Xóa thành công!");
                } else {
                    Menu.showMessage("Khối này đang có lớp!");
                }
            }
        }
    }

    public Khoi getInfo() {
        try {
            Khoi khoi = new Khoi();
            String maKhoi = jKhoi.txtMaKhoi.getText().trim();
            maKhoi = maKhoi.replaceAll("[^\\d.]", "");
            String tenKhoi = jKhoi.txtTenKhoi.getText().trim();
            if (!"".equals(tenKhoi)) {
                khoi.setMaKhoi(maKhoi);
                khoi.setTenKhoi(tenKhoi);
            } else {
                Menu.showMessage("Vui lòng điền thông tin khối");
                khoi = null;
            }
            return khoi;
        } catch (Exception e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }

    public void clearInfo() {
        jKhoi.txtMaKhoi.setText("");
        jKhoi.txtTenKhoi.setText("");
        jKhoi.txtSearch.setText("");
    }

    public void clearTable() {
        ArrayList<Khoi> listAll = new ArrayList<>();
        Khoi mh = new Khoi();
        listAll.add(mh);
        jKhoi.DataTable(new ModelKhoi(listAll));
    }
}
