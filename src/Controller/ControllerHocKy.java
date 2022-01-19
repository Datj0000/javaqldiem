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

public class ControllerHocKy {

    private final HocKyDAO hkDAO;
    private final NamHocDAO nhDAO;
    private final JHocKy jHocKy;
    private ModelHocKy modelHocKy;

    public ControllerHocKy(JHocKy _jHocKy) {
        this.jHocKy = _jHocKy;
        hkDAO = new HocKyDAO();
        nhDAO = new NamHocDAO();
    }

    public void ShowData() {
        jHocKy.DataCboNamHo(new CbModelNamHoc(nhDAO.getAll()));
        jHocKy.addListSelectionListener(new SelectionListener());
        jHocKy.addInsertListener(new InsertListener());
        jHocKy.addEditListener(new EditListener());
        jHocKy.addDeleteListener(new DeleteListener());
        jHocKy.addSearchListener(new SearchListener());
        jHocKy.addComboNamHocListener(new ComboBoxNamHocListener());
    }

    class ComboBoxNamHocListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NamHoc namHoc = (NamHoc) jHocKy.cboNamHoc.getSelectedItem();
            String maNamHoc = namHoc.getMaNamHoc().replaceAll("[^\\d.]", "");
            clearInfo();
            if (!hkDAO.findMaNamHoc(maNamHoc).isEmpty()) {
                jHocKy.DataTable(new ModelHocKy(hkDAO.findMaNamHoc(maNamHoc)));
            } else {
                clearTable();
            }
        }
    }

    class SearchListener implements DocumentListener {

        void search() {
            String txtSearch = jHocKy.txtSearch.getText().trim().toLowerCase();
            NamHoc namHoc = (NamHoc) jHocKy.cboNamHoc.getSelectedItem();
            String maNamHoc = "";
            if (namHoc != null) {
                maNamHoc = namHoc.getMaNamHoc().replaceAll("[^\\d.]", "");
            }
            if (!hkDAO.findMaNamHoc(maNamHoc).isEmpty()) {
                modelHocKy = (ModelHocKy) jHocKy.jTable.getModel();
                TableRowSorter<ModelHocKy> trs = new TableRowSorter<>(modelHocKy);
                jHocKy.jTable.setRowSorter(trs);
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
            int row = jHocKy.jTable.getSelectedRow();
            if (row >= 0) {
                jHocKy.txtMaHocKy.setText(jHocKy.jTable.getModel().getValueAt(row, 1).toString());
                jHocKy.txtTenHocKy.setText(jHocKy.jTable.getModel().getValueAt(row, 2).toString());
            }
        }
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            HocKy hocky = getInfo();
            if (hocky != null) {
                clearInfo();
                hkDAO.insert(hocky);
                jHocKy.DataTable(new ModelHocKy(hkDAO.findMaNamHoc(hocky.getMaNamHoc())));
                Menu.showMessage("Thêm thành công!");
            }
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            HocKy hocky = getInfo();
            if (hocky != null) {
                clearInfo();
                hkDAO.update(hocky.getMaHocKy(), hocky);
                jHocKy.DataTable(new ModelHocKy(hkDAO.findMaNamHoc(hocky.getMaNamHoc())));
                Menu.showMessage("Cập nhật thành công!");
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            HocKy hocky = getInfo();
            if (hocky != null) {
                if (!hkDAO.checkDelete(hocky)) {
                    hkDAO.delete(hocky.getMaHocKy());
                    clearInfo();
                    if (hkDAO.findMaNamHoc(hocky.getMaNamHoc()).isEmpty()) {
                        clearTable();
                    } else {
                        jHocKy.DataTable(new ModelHocKy(hkDAO.findMaNamHoc(hocky.getMaNamHoc())));
                    }
                    Menu.showMessage("Xóa thành công!");
                } else {
                    Menu.showMessage("Học kỳ này đang được nhập điểm!");
                }
            }
        }
    }

    public HocKy getInfo() {
        try {
            HocKy hocky = new HocKy();
            NamHoc namHoc = (NamHoc) jHocKy.cboNamHoc.getSelectedItem();
            String maNamHoc = namHoc.getMaNamHoc();
            maNamHoc = maNamHoc.replaceAll("[^\\d.]", "");
            String maHocKy = jHocKy.txtMaHocKy.getText().trim();
            maHocKy = maHocKy.replaceAll("[^\\d.]", "");
            String tenHocKy = jHocKy.txtTenHocKy.getText().trim();
            if (!"".equals(tenHocKy)) {
                hocky.setMaHocKy(maHocKy);
                hocky.setTenHocKy(tenHocKy);
                hocky.setMaNamHoc(maNamHoc);
            } else {
                Menu.showMessage("Vui lòng điền thông tin học kỳ");
                hocky = null;
            }
            return hocky;
        } catch (Exception e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }

    public void clearInfo() {
        jHocKy.txtMaHocKy.setText("");
        jHocKy.txtTenHocKy.setText("");
        jHocKy.txtSearch.setText("");
    }

    public void clearTable() {
        ArrayList<HocKy> listAll = new ArrayList<>();
        HocKy mh = new HocKy();
        listAll.add(mh);
        jHocKy.DataTable(new ModelHocKy(listAll));
    }
}
