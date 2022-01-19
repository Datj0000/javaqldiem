package View;

import View.Component.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class JBangDiem extends JPanel {

    private ButtonCustom btnAdd, btnDelete, btnImport;
    public JTextField txtSearch, txtMaBangDiem;
    public ComboboxCustom cboKhoi, cboLop, cboHocKy, cboHocSinh, cboNamHoc;
    public JTable jTable;

    public JBangDiem() {
        this.setLayout(new BorderLayout(0, 25));
        this.setBackground(Color.white);
        FormInput();
        Table();
    }

    public void FormInput() {
        JPanel panel = new JPanel();
        JPanel detailsPane = new JPanel(new GridBagLayout());
        detailsPane.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 15, 0, 15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        //Input
        JLabel lblTenKhoi = new JLabel();
        JLabel lblTenLop = new JLabel();
        JLabel lblHocKy = new JLabel();
        JLabel lblNamHoc = new JLabel();
        JLabel lblHocSinh = new JLabel();
        JLabel lblMaBangDiem = new JLabel();
        cboKhoi = new ComboboxCustom();
        cboLop = new ComboboxCustom();
        cboHocKy = new ComboboxCustom();
        cboHocSinh = new ComboboxCustom();
        cboNamHoc = new ComboboxCustom();
        txtMaBangDiem = new JTextField(15);
        txtMaBangDiem.setEditable(false);
        JPanel jPane = new JPanel();
        Menu.customTextField(jPane, lblMaBangDiem, txtMaBangDiem, "Mã bảng điểm: ");
        detailsPane.add(jPane, gbc);
        gbc.gridy++;
        JPanel jPane7 = new JPanel();
        Menu.customCombobox(jPane7, lblNamHoc, cboNamHoc, "Năm học: ");
        detailsPane.add(jPane7, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        JPanel jPanel2 = new JPanel();
        Menu.customCombobox(jPanel2, lblHocKy, cboHocKy, "Học Kỳ: ");
        detailsPane.add(jPanel2, gbc);
        gbc.gridy++;
        JPanel jPanel = new JPanel();
        Menu.customCombobox(jPanel, lblTenKhoi, cboKhoi, "Tên khối: ");
        detailsPane.add(jPanel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        JPanel jPanel1 = new JPanel();
        Menu.customCombobox(jPanel1, lblTenLop, cboLop, "Tên lớp: ");
        detailsPane.add(jPanel1, gbc);
        gbc.gridy++;
        JPanel jPanel3 = new JPanel();
        Menu.customCombobox(jPanel3, lblHocSinh, cboHocSinh, "Học sinh: ");
        detailsPane.add(jPanel3, gbc);
        //Button
        JPanel buttonsPane = new JPanel(new GridBagLayout());
        buttonsPane.setBackground(Color.white);
        btnAdd = new ButtonCustom();
        btnDelete = new ButtonCustom();
        btnImport = new ButtonCustom();
        Menu.customButton(btnAdd, "Thêm");
        Menu.customButton(btnDelete, "Xoá");
        Menu.customButton(btnImport, "Nhập điểm");
        btnAdd.setPreferredSize(new Dimension(100, 35));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 5, 0);
        buttonsPane.add(btnAdd, gbc);
        gbc.gridy++;
        buttonsPane.add(btnImport, gbc);
        gbc.gridy++;
        buttonsPane.add(btnDelete, gbc);
        panel.setLayout(new BorderLayout());
        panel.add(detailsPane);
        panel.add(buttonsPane, BorderLayout.LINE_END);
        this.add(panel, BorderLayout.NORTH);
    }

    public void Table() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
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
        String ColumnName[] = {"STT", "Mã bảng điểm", "Mã học sinh", "Tên học sinh"};
        Object[][] data = {};
        jTable = new JTable(data, ColumnName);
        JScrollPane jScrollPane1 = new JScrollPane();
        Menu.customTable(jTable, jScrollPane1);
        panel.add(jScrollPane1, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
    }

    public void DataTable(ModelBangDiem model) {
        jTable.setModel(model);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(245);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(245);
    }

    public void DataCboNamHoc(CbModelNamHoc model) {
        cboNamHoc.setModel(model);
    }

    public void DataCboKhoi(CbModelKhoi model) {
        cboKhoi.setModel(model);
    }

    public void DataCboLop(CbModelLop model) {
        cboLop.setModel(model);
    }

    public void DataCboHocKy(CbModelHocKy model) {
        cboHocKy.setModel(model);
    }

    public void DataCboHocSinh(CbModelHocSinh model) {
        cboHocSinh.setModel(model);
    }

    public void addImportListener(ActionListener listener) {
        btnImport.addActionListener(listener);
    }

    public void addInsertListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addSearchListener(DocumentListener listener) {
        txtSearch.getDocument().addDocumentListener(listener);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        jTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void addComboNamHocListener(ActionListener listener) {
        cboNamHoc.addActionListener(listener);
    }

    public void addComboHocKyListener(ActionListener listener) {
        cboHocKy.addActionListener(listener);
    }

    public void addComboKhoiListener(ActionListener listener) {
        cboKhoi.addActionListener(listener);
    }

    public void addComboLopListener(ActionListener listener) {
        cboLop.addActionListener(listener);
    }
}
