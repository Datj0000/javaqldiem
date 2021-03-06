package Controller;

import View.*;
import Entity.*;
import DAO.*;
import Model.*;
import java.awt.event.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.RowFilter;
import javax.swing.event.*;
import javax.swing.table.TableRowSorter;

public class ControllerNamHoc {

    private final NamHocDAO DAO;
    private final JNamHoc jNamHoc;
    private ModelNamHoc modelNamHoc;

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
            String txtSearch = jNamHoc.txtSearch.getText().trim().toLowerCase();
            System.out.println(txtSearch);
            if (!DAO.getAll().isEmpty()) {
                modelNamHoc = (ModelNamHoc) jNamHoc.jTable.getModel();
                TableRowSorter<ModelNamHoc> trs = new TableRowSorter<>(modelNamHoc);
                jNamHoc.jTable.setRowSorter(trs);
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
                    Menu.showMessage("Th??m th??nh c??ng!");
                } else {
                    Menu.showMessage("T??n n??m h???c ???? t???n t???i!");
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
                    Menu.showMessage("C???p nh???t th??nh c??ng!");
                } else {
                    Menu.showMessage("T??n n??m h???c ???? t???n t???i!");
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
                    Menu.showMessage("X??a th??nh c??ng!");
                } else {
                    Menu.showMessage("N??m h???c n??y ??ang c?? h???c k???!");
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
                Menu.showMessage("Vui l??ng ??i???n th??ng tin n??m h???c");
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
