package View;

import View.Component.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class JBangDiemChiTiet extends JPanel {

    private ButtonCustom btnAdd, btnEdit, btnDelete, btnPrint;
    public JTextField txtSearch, txtMaBangDiem, txtTenHocSinh, txtMaHocSinh, txtTenHocKy, txtDiemMieng, txtDiemKT1, txtDiemKT2, txtDiemThi;
    public JTable jTable;
    public ComboboxCustom cboMonHoc;

    public JBangDiemChiTiet() {
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
        JLabel lblDiemMieng = new JLabel();
        JLabel lblDiem1 = new JLabel();
        JLabel lblDiem2 = new JLabel();
        JLabel lblDiemThi = new JLabel();
        JLabel lblTenHocSinh = new JLabel();
        JLabel lblHocKy = new JLabel();
        JLabel lblMaHocSinh = new JLabel();
        JLabel lblMaBangDiem = new JLabel();
        JLabel lblMonHoc = new JLabel();
        txtMaBangDiem = new JTextField(15);
        txtMaHocSinh = new JTextField(15);
        txtTenHocSinh = new JTextField(15);
        txtTenHocKy = new JTextField(15);
        txtMaBangDiem.setEditable(false);
        txtMaHocSinh.setEditable(false);
        txtTenHocSinh.setEditable(false);
        txtTenHocKy.setEditable(false);
        txtDiemMieng = new JTextField(15);
        txtDiemKT1 = new JTextField(15);
        txtDiemKT2 = new JTextField(15);
        txtDiemThi = new JTextField(15);
        cboMonHoc = new ComboboxCustom();
        JPanel jPane = new JPanel();
        Menu.customTextField(jPane, lblMaBangDiem, txtMaBangDiem, "M?? b???ng ??i???m: ");
        detailsPane.add(jPane, gbc);
        gbc.gridy++;
        JPanel jPanel = new JPanel();
        Menu.customTextField(jPanel, lblMaHocSinh, txtMaHocSinh, "M?? h???c sinh: ");
        detailsPane.add(jPanel, gbc);
        gbc.gridy++;
        JPanel jPanel1 = new JPanel();
        Menu.customTextField(jPanel1, lblTenHocSinh, txtTenHocSinh, "T??n h???c sinh: ");
        detailsPane.add(jPanel1, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        JPanel jPanel2 = new JPanel();
        Menu.customTextField(jPanel2, lblHocKy, txtTenHocKy, "H???c k???: ");
        detailsPane.add(jPanel2, gbc);
        gbc.gridy++;
        JPanel jPanel3 = new JPanel();
        Menu.customCombobox(jPanel3, lblMonHoc, cboMonHoc, "M??n h???c: ");
        detailsPane.add(jPanel3, gbc);
        gbc.gridy++;
        JPanel jPanel4 = new JPanel();
        Menu.customTextField(jPanel4, lblDiemMieng, txtDiemMieng, "??i???m mi???ng: ");
        detailsPane.add(jPanel4, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        JPanel jPanel5 = new JPanel();
        Menu.customTextField(jPanel5, lblDiem1, txtDiemKT1, "??i???m ki???m tra 1: ");
        detailsPane.add(jPanel5, gbc);
        gbc.gridy++;
        JPanel jPanel6 = new JPanel();
        Menu.customTextField(jPanel6, lblDiem2, txtDiemKT2, "??i???m ki???m tra 2: ");
        detailsPane.add(jPanel6, gbc);
        gbc.gridy++;
        JPanel jPanel7 = new JPanel();
        Menu.customTextField(jPanel7, lblDiemThi, txtDiemThi, "??i???m thi: ");
        detailsPane.add(jPanel7, gbc);
        //Button
        JPanel buttonsPane = new JPanel(new GridBagLayout());
        buttonsPane.setBackground(Color.white);
        btnAdd = new ButtonCustom();
        btnEdit = new ButtonCustom();
        btnDelete = new ButtonCustom();
        btnPrint = new ButtonCustom();
        Menu.customButton(btnAdd, "Th??m");
        Menu.customButton(btnEdit, "S???a");
        Menu.customButton(btnDelete, "Xo??");
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

    public void Table() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(new Color(255, 255, 255));
        //Search
        JPanel buttonsPane = new JPanel(new FlowLayout());
        txtSearch = new JTextField(18);
        txtSearch.setFont(new Font("Segoe UI", 0, 14));
        txtSearch.setForeground(new Color(102, 102, 102));
        txtSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(77, 77, 77)));
        JLabel lblSearch = new JLabel("T??m ki???m: ");
        lblSearch.setFont(new Font("Segoe UI", 0, 14));
        lblSearch.setForeground(new Color(102, 102, 102));
        buttonsPane.add(lblSearch);
        buttonsPane.add(txtSearch);
        buttonsPane.setBackground(Color.white);
        panel.add(buttonsPane, BorderLayout.NORTH);
        //Table
        String ColumnName[] = {"STT", "M?? b???ng ??i???m", "M??n h???c", "??i???m mi???ng", "??i???m ki???m tra 1", "??i???m ki???m tra 2", "??i???m thi", "??i???m trung b??nh"};
        Object[][] data = {};
        jTable = new JTable(data, ColumnName);
        JScrollPane jScrollPane1 = new JScrollPane();
        Menu.customTable(jTable, jScrollPane1);
        panel.add(jScrollPane1, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
    }

    public void setText(String maBangDiem, String maHocSinh, String tenHocSinh, String TenHocKy) {
        txtMaBangDiem.setText(maBangDiem);
        txtMaHocSinh.setText(maHocSinh);
        txtTenHocSinh.setText(tenHocSinh);
        txtTenHocKy.setText(TenHocKy);
    }

    public void DataTable(ModelBangDiemChiTiet model) {
        jTable.setModel(model);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(117);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(117);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(117);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(120);
    }

    public void DataCboMonHoc(CbModelMonHoc model) {
        cboMonHoc.setModel(model);
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
}
