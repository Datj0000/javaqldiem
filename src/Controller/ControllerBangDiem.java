package Controller;

import View.*;
import Entity.*;
import DAO.*;
import Model.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.*;

public class ControllerBangDiem {

    private final BangDiemDAO bdDAO;
    private final NamHocDAO nhDAO;
    private final KhoiDAO kDAO;
    private final LopDAO lDAO;
    private final HocSinhDAO hsDAO;
    private final HocKyDAO hkDAO;
    private final JBangDiem jBangDiem;
    private final Menu menu;

    public ControllerBangDiem(JBangDiem _jBangDiem, Menu _menu) {
        this.jBangDiem = _jBangDiem;
        menu = _menu;
        bdDAO = new BangDiemDAO();
        kDAO = new KhoiDAO();
        lDAO = new LopDAO();
        hsDAO = new HocSinhDAO();
        hkDAO = new HocKyDAO();
        nhDAO = new NamHocDAO();
    }

    public void ShowData() {
        jBangDiem.DataCboKhoi(new CbModelKhoi(kDAO.getAll()));
        jBangDiem.DataCboNamHoc(new CbModelNamHoc(nhDAO.getAll()));
        jBangDiem.addListSelectionListener(new SelectionListener());
        jBangDiem.addInsertListener(new InsertListener());
        jBangDiem.addDeleteListener(new DeleteListener());
        jBangDiem.addSearchListener(new SearchListener());
        jBangDiem.addImportListener(new ImportListener());
        jBangDiem.addComboKhoiListener(new ComboBoxKhoiListener());
        jBangDiem.addComboLopListener(new ComboBoxLopListener());
        jBangDiem.addComboNamHocListener(new ComboBoxNamHocListener());
        jBangDiem.addComboHocKyListener(new ComboBoxHocKyListener());
    }

    class ComboBoxNamHocListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NamHoc namhoc = (NamHoc) jBangDiem.cboNamHoc.getSelectedItem();
            String maNamHoc = namhoc.getMaNamHoc().replaceAll("[^\\d.]", "");
            jBangDiem.DataCboHocKy(new CbModelHocKy(hkDAO.findMaNamHoc(maNamHoc)));
            clearInfo();
            clearTable();
        }
    }

    class ComboBoxKhoiListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Khoi khoi = (Khoi) jBangDiem.cboKhoi.getSelectedItem();
            String maKhoi = khoi.getMaKhoi().replaceAll("[^\\d.]", "");
            jBangDiem.DataCboLop(new CbModelLop(lDAO.findMaKhoi(maKhoi)));
            Lop lop = (Lop) jBangDiem.cboLop.getSelectedItem();
            String maLop = lop.getMaLop().replaceAll("[^\\d.]", "");
            jBangDiem.DataCboHocSinh(new CbModelHocSinh(hsDAO.findMaLop(maLop)));
            clearInfo();
            clearTable();
        }
    }

    class ComboBoxLopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Lop lop = (Lop) jBangDiem.cboLop.getSelectedItem();
            String maLop = lop.getMaLop().replaceAll("[^\\d.]", "");
            jBangDiem.DataCboHocSinh(new CbModelHocSinh(hsDAO.findMaLop(maLop)));
            if (jBangDiem.cboHocKy.getItemCount() > 0) {
                HocKy hk = (HocKy) jBangDiem.cboHocKy.getSelectedItem();
                String maHocKy = hk.getMaHocKy().replaceAll("[^\\d.]", "");
                if (!bdDAO.findMa(maHocKy, maLop).isEmpty()) {
                    jBangDiem.DataTable(new ModelBangDiem(bdDAO.findMa(maHocKy, maLop)));
                } else {
                    clearTable();
                }
            }
        }
    }

    class ComboBoxHocKyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jBangDiem.cboLop.getItemCount() > 0) {
                HocKy hk = (HocKy) jBangDiem.cboHocKy.getSelectedItem();
                String maHocKy = hk.getMaHocKy().replaceAll("[^\\d.]", "");
                Lop lop = (Lop) jBangDiem.cboLop.getSelectedItem();
                String maLop = lop.getMaLop().replaceAll("[^\\d.]", "");
                if (!bdDAO.findMa(maHocKy, maLop).isEmpty()) {
                    jBangDiem.DataTable(new ModelBangDiem(bdDAO.findMa(maHocKy, maLop)));
                } else {
                    clearTable();
                }
            }
        }
    }

    class ImportListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BangDiem bangdiem = getInfo();
            HocKy hk = (HocKy) jBangDiem.cboHocKy.getSelectedItem();
            String tenHocKy = hk.getTenHocKy();
            HocSinh hs = (HocSinh) jBangDiem.cboHocSinh.getSelectedItem();
            String tenHocSinh = hs.getTenHocSinh();
            if (!"".equals(bangdiem.getMaBangDiem())) {
                JBangDiemChiTiet panelbdct = new JBangDiemChiTiet();
                panelbdct.setText("BD" + bangdiem.getMaBangDiem(), "HS" + bangdiem.getMaHocSinh(), tenHocSinh, tenHocKy);
                ControllerBangDiemChiTiet controller = new ControllerBangDiemChiTiet(panelbdct);
                controller.ShowData();
                menu.openPanel(panelbdct);
            } else {
                Menu.showMessage("Vui lòng chọn học sinh cần nhập điểm!");
            }
        }
    }

    class SearchListener implements DocumentListener {

        void search() {
            String txtSearch = jBangDiem.txtSearch.getText().trim();
            String maLop = "";
            String maHocKy = "";
            if (jBangDiem.cboLop.getItemCount() > 0) {
                Lop lop = (Lop) jBangDiem.cboLop.getSelectedItem();
                maLop = lop.getMaLop();
                maLop = maLop.replaceAll("[^\\d.]", "");
            }
            if (jBangDiem.cboHocKy.getItemCount() > 0) {
                HocKy hocky = (HocKy) jBangDiem.cboLop.getSelectedItem();
                maHocKy = hocky.getMaHocKy();
                maHocKy = maHocKy.replaceAll("[^\\d.]", "");
            }
            if (!"".equals(txtSearch) && !"".equals(maLop) && !"".equals(maHocKy)) {
                if (!bdDAO.find(txtSearch, maHocKy, maLop).isEmpty()) {
                    jBangDiem.DataTable(new ModelBangDiem(bdDAO.find(txtSearch, maHocKy, maLop)));
                } else {
                    clearTable();
                }
            } else {
                if (!bdDAO.findMa(maHocKy, maLop).isEmpty()) {
                    jBangDiem.DataTable(new ModelBangDiem(bdDAO.findMa(maHocKy, maLop)));
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
            int row = jBangDiem.jTable.getSelectedRow();
            if (row >= 0) {
                jBangDiem.txtMaBangDiem.setText(jBangDiem.jTable.getModel().getValueAt(row, 1).toString());
                String maHocSinh = jBangDiem.jTable.getValueAt(row, 3).toString();
                for (int i = 0; i < jBangDiem.cboHocSinh.getItemCount(); i++) {
                    if (jBangDiem.cboHocSinh.getItemAt(i).toString().equalsIgnoreCase(maHocSinh)) {
                        jBangDiem.cboHocSinh.setSelectedIndex(i);
                    }
                }
            }
        }
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BangDiem bangdiem = getInfo();
            if (bangdiem != null) {
                if (!bdDAO.checkTen(bangdiem)) {
                    clearInfo();
                    bdDAO.insert(bangdiem);
                    jBangDiem.DataTable(new ModelBangDiem(bdDAO.findMa(bangdiem.getMaHocKy(), bangdiem.getMaLop())));
                    Menu.showMessage("Thêm thành công!");
                } else {
                    Menu.showMessage("Học kỳ này học sinh này đã có bảng điểm!");
                }
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BangDiem bangdiem = getInfo();
            if (bangdiem != null) {
                bdDAO.delete(bangdiem.getMaBangDiem());
                clearInfo();
                if (!bdDAO.findMa(bangdiem.getMaHocKy(), bangdiem.getMaLop()).isEmpty()) {
                    jBangDiem.DataTable(new ModelBangDiem(bdDAO.findMa(bangdiem.getMaHocKy(), bangdiem.getMaLop())));
                } else {
                    clearTable();
                }
                Menu.showMessage("Xóa thành công!");
            }
        }
    }

    public BangDiem getInfo() {
        try {
            BangDiem bangdiem = new BangDiem();
            String maBangDiem = jBangDiem.txtMaBangDiem.getText().trim();
            maBangDiem = maBangDiem.replaceAll("[^\\d.]", "");
            Lop lop = (Lop) jBangDiem.cboLop.getSelectedItem();
            String maLop = lop.getMaLop().replaceAll("[^\\d.]", "");
            String maHocKy = "";
            HocKy hk = (HocKy) jBangDiem.cboHocKy.getSelectedItem();
            if (hk != null) {
                maHocKy = hk.getMaHocKy().replaceAll("[^\\d.]", "");
            }
            String maHocSinh = "";
            HocSinh hs = (HocSinh) jBangDiem.cboHocSinh.getSelectedItem();
            if (hs != null) {
                maHocSinh = hs.getMaHocSinh().replaceAll("[^\\d.]", "");
            }
            if (!"".equals(maHocKy) && !"".equals(maHocSinh)) {
                bangdiem.setMaBangDiem(maBangDiem);
                bangdiem.setMaHocKy(maHocKy);
                bangdiem.setMaLop(maLop);
                bangdiem.setMaHocSinh(maHocSinh);
            } else {
                Menu.showMessage("Vui lòng điền thông tin bảng điểm");
                bangdiem = null;
            }
            return bangdiem;
        } catch (Exception e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }

    public void clearInfo() {
        jBangDiem.txtMaBangDiem.setText("");
        jBangDiem.txtSearch.setText("");
    }

    public void clearTable() {
        ArrayList<BangDiem> listAll = new ArrayList<>();
        BangDiem mh = new BangDiem();
        listAll.add(mh);
        jBangDiem.DataTable(new ModelBangDiem(listAll));
    }
}
