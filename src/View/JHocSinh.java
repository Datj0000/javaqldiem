package View;

import View.Component.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class JHocSinh extends JPanel {

    public ButtonCustom btnAdd, btnEdit, btnDelete, btnPrint;
    public JTextField txtMaHocSinh, txtTenHocSinh, txtSearch, txtNgaySinh, txtNoiSinh, txtDanToc;
    public ComboboxCustom cboKhoi, cboLop;
    public RadioButtonCustom rdNam, rdNu;
    public JTable jTable;
    public ButtonGroup bgGioiTinh;

    public JHocSinh() {
        this.setLayout(new BorderLayout(0, 25));
        this.setBackground(Color.white);
        FormInput();
        Table();
    }

    private void FormInput() {
        JPanel panel = new JPanel();
        JPanel detailsPane = new JPanel(new GridBagLayout());
        detailsPane.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 15, 0, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        //Input
        JLabel lblKhoi = new JLabel();
        JLabel lblLop = new JLabel();
        JLabel lblMaHocSinh = new JLabel();
        JLabel lblTenHocSinh = new JLabel();
        JLabel lblGioiTinh = new JLabel();
        JLabel lblNgaySinh = new JLabel();
        JLabel lblNoiSinh = new JLabel();
        JLabel lblDanToc = new JLabel();
        txtMaHocSinh = new JTextField(15);
        txtMaHocSinh.setEnabled(false);
        txtTenHocSinh = new JTextField(15);
        txtNgaySinh = new JTextField(15);
        txtNoiSinh = new JTextField(15);
        txtDanToc = new JTextField(15);
        rdNam = new RadioButtonCustom();
        rdNam.setText("Nam");
        rdNu = new RadioButtonCustom();
        rdNu.setText("Nữ");
        rdNam.setActionCommand("Nam");
        rdNu.setActionCommand("Nữ");
        cboKhoi = new ComboboxCustom();
        JPanel jPanel1 = new JPanel();
        Menu.customCombobox(jPanel1, lblKhoi, cboKhoi, "Khối: ");
        detailsPane.add(jPanel1, gbc);
        gbc.gridy++;
        cboLop = new ComboboxCustom();
        JPanel jPanel2 = new JPanel();
        Menu.customCombobox(jPanel2, lblLop, cboLop, "Lớp: ");
        detailsPane.add(jPanel2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        JPanel jPanel3 = new JPanel();
        Menu.customTextField(jPanel3, lblMaHocSinh, txtMaHocSinh, "Mã học sinh: ");
        detailsPane.add(jPanel3, gbc);
        gbc.gridy++;
        JPanel jPanel4 = new JPanel();
        Menu.customTextField(jPanel4, lblTenHocSinh, txtTenHocSinh, "Họ và tên: ");
        detailsPane.add(jPanel4, gbc);
        gbc.gridy++;
        JPanel jPanel5 = new JPanel();
        Menu.customRadio(jPanel5, lblGioiTinh, rdNam, rdNu, "Giới tính: ");
        bgGioiTinh = new ButtonGroup();
        bgGioiTinh.add(rdNam);
        bgGioiTinh.add(rdNu);
        detailsPane.add(jPanel5, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        JPanel jPanel6 = new JPanel();
        Menu.customTextField(jPanel6, lblNgaySinh, txtNgaySinh, "Ngày sinh: ");
        detailsPane.add(jPanel6, gbc);
        gbc.gridy++;
        JPanel jPanel7 = new JPanel();
        Menu.customTextField(jPanel7, lblNoiSinh, txtNoiSinh, "Nơi sinh: ");
        detailsPane.add(jPanel7, gbc);
        gbc.gridy++;
        JPanel jPanel8 = new JPanel();
        Menu.customTextField(jPanel8, lblDanToc, txtDanToc, "Dân tộc: ");
        detailsPane.add(jPanel8, gbc);
        //Button
        JPanel buttonsPane = new JPanel(new GridBagLayout());
        buttonsPane.setBackground(Color.white);
        btnAdd = new ButtonCustom();
        btnEdit = new ButtonCustom();
        btnDelete = new ButtonCustom();
        btnPrint = new ButtonCustom();
        Menu.customButton(btnAdd, "Thêm");
        Menu.customButton(btnEdit, "Sửa");
        Menu.customButton(btnDelete, "Xoá");
        Menu.customButton(btnPrint, "In");
        btnAdd.setPreferredSize(new Dimension(100, 35));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 5, 0);
        buttonsPane.add(btnAdd, gbc);
        gbc.gridy++;
        buttonsPane.add(btnEdit, gbc);
        gbc.gridy++;
        buttonsPane.add(btnDelete, gbc);
        gbc.gridy++;
        buttonsPane.add(btnPrint, gbc);
        panel.setLayout(new BorderLayout());
        panel.add(detailsPane);
        panel.add(buttonsPane, BorderLayout.LINE_END);
        this.add(panel, BorderLayout.NORTH);
    }

    private void Table() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(new Color(255, 255, 255));
        //Search
        JPanel buttonsPane = new JPanel(new FlowLayout());
        txtSearch = new JTextField(18);
        txtSearch.setFont(new Font("Segoe UI", 0, 14));
        txtSearch.setForeground(new Color(102, 102, 102));
        txtSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(77, 77, 77)));
        JLabel lblSearch = new JLabel("Tìm kiếm: ");
        lblSearch.setFont(new Font("Segoe UI", 0, 14));
        lblSearch.setForeground(new Color(102, 102, 102));
        buttonsPane.add(lblSearch);
        buttonsPane.add(txtSearch);
        buttonsPane.setBackground(Color.white);
        panel.add(buttonsPane, BorderLayout.NORTH);
        //Table
        String ColumnName[] = {"STT","Mã học sinh", "Họ và tên", "Giới tính", "Ngày sinh", "Nơi sinh", "Dân tộc"};
        Object[][] data = {};
        jTable = new JTable(data, ColumnName);
        JScrollPane jScrollPane1 = new JScrollPane();
        Menu.customTable(jTable, jScrollPane1);
        panel.add(jScrollPane1, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
    }

    public void DataTable(ModelHocSinh model) {
        jTable.setModel(model);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(170);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(90);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(118);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(100);
    }

    public void DataCboKhoi(CbModelKhoi model) {
        cboKhoi.setModel(model);
    }

    public void DataCboLop(CbModelLop model) {
        cboLop.setModel(model);
    }

    public void addInsertListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addEditListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    public void addPrintListener(ActionListener listener) {
        btnPrint.addActionListener(listener);
    }
    public void addSearchListener(DocumentListener listener) {
        txtSearch.getDocument().addDocumentListener(listener);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        jTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void addComboKhoiListener(ActionListener listener) {
        cboKhoi.addActionListener(listener);
    }
    public void addComboLopListener(ActionListener listener) {
        cboLop.addActionListener(listener);
    }
}
