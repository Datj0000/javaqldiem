package Controller;

import View.*;
import Entity.*;
import DAO.*;
import Model.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.RowFilter;
import javax.swing.event.*;
import javax.swing.table.TableRowSorter;

public class ControllerMonHoc {

    private MonHocDAO DAO;
    private JMonHoc jMonHoc;
    private ModelMonHoc modelMonHoc;

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
            String txtSearch = jMonHoc.txtSearch.getText().trim().toLowerCase();
            if (!DAO.getAll().isEmpty()) {
                modelMonHoc = (ModelMonHoc) jMonHoc.jTable.getModel();
                TableRowSorter<ModelMonHoc> trs = new TableRowSorter<>(modelMonHoc);
                jMonHoc.jTable.setRowSorter(trs);
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
                    Menu.showMessage("Th??m th??nh c??ng!");
                } else {
                    Menu.showMessage("T??n m??n h???c ???? t???n t???i!");
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
                    Menu.showMessage("C???p nh???t th??nh c??ng!");
                } else {
                    Menu.showMessage("T??n m??n h???c ???? t???n t???i!");
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
                Menu.showMessage("X??a th??nh c??ng!");
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
                Menu.showMessage("Vui l??ng ??i???n th??ng tin m??n h???c");
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
