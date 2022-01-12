package Controller;

import View.*;
import Entity.*;
import DAO.*;
import Model.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.*;

public class ControllerMonHoc {

    private MonHocDAO DAO;
    private JMonHoc jMonHoc;

    public ControllerMonHoc(JMonHoc _jMonHoc) {
        this.jMonHoc = _jMonHoc;
        DAO = new MonHocDAO();
    }

    public void ShowData() {
        if (!DAO.getAll().isEmpty()) {
            jMonHoc.DataTable(new ModelMonHoc(DAO.getAll()));
        }
        jMonHoc.addListSelectionListener(new SelectionListener());
        jMonHoc.addInsertListener(new InsertListener());
        jMonHoc.addEditListener(new EditListener());
        jMonHoc.addDeleteListener(new DeleteListener());
        jMonHoc.addSearchListener(new SearchListener());
    }

    class SearchListener implements DocumentListener {

        void search() {
            String txtSearch = jMonHoc.txtSearch.getText().trim();
            if (!"".equals(txtSearch)) {
                if (!DAO.find(txtSearch).isEmpty()) {
                    jMonHoc.DataTable(new ModelMonHoc(DAO.find(txtSearch)));
                } else {
                    clearTable();
                }
            } else {
                if (!DAO.getAll().isEmpty()) {
                    jMonHoc.DataTable(new ModelMonHoc(DAO.getAll()));
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
            int row = jMonHoc.jTable.getSelectedRow();
            if (row >= 0) {
                jMonHoc.txtMaMonHoc.setText(jMonHoc.jTable.getModel().getValueAt(row, 1).toString());
                jMonHoc.txtTenMonHoc.setText(jMonHoc.jTable.getModel().getValueAt(row, 2).toString());
            }
        }
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MonHoc monhoc = getInfo();
            if (monhoc != null) {
                if (!DAO.checkTen(monhoc)) {
                    clearInfo();
                    DAO.insert(monhoc);
                    jMonHoc.DataTable(new ModelMonHoc(DAO.getAll()));
                    Menu.showMessage("Thêm thành công!");
                } else {
                    Menu.showMessage("Tên môn học đã tồn tại!");
                }
            }
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MonHoc monhoc = getInfo();
            if (monhoc != null) {
                if (!DAO.checkTen(monhoc)) {
                    clearInfo();
                    DAO.update(monhoc.getMaMonHoc(), monhoc);
                    jMonHoc.DataTable(new ModelMonHoc(DAO.getAll()));
                    Menu.showMessage("Cập nhật thành công!");
                } else {
                    Menu.showMessage("Tên môn học đã tồn tại!");
                }
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MonHoc monhoc = getInfo();
            if (monhoc != null) {
                DAO.delete(monhoc.getMaMonHoc());
                clearInfo();
                if (DAO.getAll().isEmpty()) {
                    clearTable();
                } else {
                    jMonHoc.DataTable(new ModelMonHoc(DAO.getAll()));
                }
                Menu.showMessage("Xóa thành công!");
            }
        }
    }

    public MonHoc getInfo() {
        try {
            MonHoc monhoc = new MonHoc();
            String maMonHoc = jMonHoc.txtMaMonHoc.getText().trim();
            maMonHoc = maMonHoc.replaceAll("[^\\d.]", "");
            String tenMonHoc = jMonHoc.txtTenMonHoc.getText().trim();
            if (!"".equals(tenMonHoc)) {
                monhoc.setMaMonHoc(maMonHoc);
                monhoc.setTenMonHoc(tenMonHoc);
            } else {
                Menu.showMessage("Vui lòng điền thông tin môn học");
                monhoc = null;
            }
            return monhoc;
        } catch (Exception e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }

    public void clearInfo() {
        jMonHoc.txtMaMonHoc.setText("");
        jMonHoc.txtTenMonHoc.setText("");
        jMonHoc.txtSearch.setText("");
    }

    public void clearTable() {
        ArrayList<MonHoc> listAll = new ArrayList<>();
        MonHoc mh = new MonHoc();
        listAll.add(mh);
        jMonHoc.DataTable(new ModelMonHoc(listAll));
    }

}
