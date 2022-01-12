package Controller;

import View.*;
import Entity.*;
import DAO.*;
import Model.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.*;

public class ControllerNamHoc {

    private final NamHocDAO DAO;
    private final JNamHoc jNamHoc;

    public ControllerNamHoc(JNamHoc _jNamHoc) {
        this.jNamHoc = _jNamHoc;
        DAO = new NamHocDAO();
    }

    public void ShowData() {
        if (!DAO.getAll().isEmpty()) {
            jNamHoc.DataTable(new ModelNamHoc(DAO.getAll()));
        }
        jNamHoc.addListSelectionListener(new SelectionListener());
        jNamHoc.addInsertListener(new InsertListener());
        jNamHoc.addEditListener(new EditListener());
        jNamHoc.addDeleteListener(new DeleteListener());
        jNamHoc.addSearchListener(new SearchListener());
    }

    class SearchListener implements DocumentListener {

        void search() {
            String txtSearch = jNamHoc.txtSearch.getText().trim();
            if (!"".equals(txtSearch)) {
                if (!DAO.find(txtSearch).isEmpty()) {
                    jNamHoc.DataTable(new ModelNamHoc(DAO.find(txtSearch)));
                } else {
                    clearTable();
                }
            } else {
                if (!DAO.getAll().isEmpty()) {
                    jNamHoc.DataTable(new ModelNamHoc(DAO.getAll()));
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
            int row = jNamHoc.jTable.getSelectedRow();
            if (row >= 0) {
                jNamHoc.txtMaNamHoc.setText(jNamHoc.jTable.getModel().getValueAt(row, 1).toString());
                jNamHoc.txtTenNamHoc.setText(jNamHoc.jTable.getModel().getValueAt(row, 2).toString());
            }
        }
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NamHoc namHoc = getInfo();
            if (namHoc != null) {
                if (!DAO.checkTen(namHoc)) {
                    clearInfo();
                    DAO.insert(namHoc);
                    jNamHoc.DataTable(new ModelNamHoc(DAO.getAll()));
                    Menu.showMessage("Thêm thành công!");
                } else {
                    Menu.showMessage("Tên năm học đã tồn tại!");
                }
            }
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NamHoc namHoc = getInfo();
            if (namHoc != null) {
                if (!DAO.checkTen(namHoc)) {
                    clearInfo();
                    DAO.update(namHoc.getMaNamHoc(), namHoc);
                    jNamHoc.DataTable(new ModelNamHoc(DAO.getAll()));
                    Menu.showMessage("Cập nhật thành công!");
                } else {
                    Menu.showMessage("Tên năm học đã tồn tại!");
                }
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NamHoc namHoc = getInfo();
            if (namHoc != null) {
                if (!DAO.checkDelete(namHoc)) {
                    DAO.delete(namHoc.getMaNamHoc());
                    clearInfo();
                    if (DAO.getAll().isEmpty()) {
                        clearTable();
                    } else {
                        jNamHoc.DataTable(new ModelNamHoc(DAO.getAll()));
                    }
                    Menu.showMessage("Xóa thành công!");
                } else {
                    Menu.showMessage("Năm học này đang có học kỳ!");
                }
            }
        }
    }

    public NamHoc getInfo() {
        try {
            NamHoc namHoc = new NamHoc();
            String maNamHoc = jNamHoc.txtMaNamHoc.getText().trim();
            maNamHoc = maNamHoc.replaceAll("[^\\d.]", "");
            String tenNamHoc = jNamHoc.txtTenNamHoc.getText().trim();
            if (!"".equals(tenNamHoc)) {
                namHoc.setMaNamHoc(maNamHoc);
                namHoc.setTenNamHoc(tenNamHoc);
            } else {
                Menu.showMessage("Vui lòng điền thông tin năm học");
                namHoc = null;
            }
            return namHoc;
        } catch (Exception e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }

    public void clearInfo() {
        jNamHoc.txtMaNamHoc.setText("");
        jNamHoc.txtTenNamHoc.setText("");
        jNamHoc.txtSearch.setText("");
    }

    public void clearTable() {
        ArrayList<NamHoc> listAll = new ArrayList<>();
        NamHoc mh = new NamHoc();
        listAll.add(mh);
        jNamHoc.DataTable(new ModelNamHoc(listAll));
    }
}
