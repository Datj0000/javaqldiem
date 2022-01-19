package View;

import View.Component.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class JLop extends JPanel {

    private ButtonCustom btnAdd, btnEdit, btnDelete;
    public JTextField txtMaLop, txtTenLop, txtGvcn, txtSearch;
    public ComboboxCustom cboKhoi;
    public JTable jTable;

    public JLop() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        FormInput();
        Table();
        this.setVisible(true);
    }

    public void FormInput() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel detailsPane = new JPanel(new GridBagLayout());
        detailsPane.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        //Input
        JLabel lbltenKhoi = new JLabel();
        JLabel lblMaLop = new JLabel();
        JLabel lblTenLop = new JLabel();
        JLabel lblGvcn = new JLabel();
        txtMaLop = new JTextField(18);
        txtMaLop.setEditable(false);
        txtTenLop = new JTextField(18);
        txtGvcn = new JTextField(18);
        cboKhoi = new ComboboxCustom();
        JPanel jPanel = new JPanel();
        Menu.customCombobox(jPanel, lbltenKhoi, cboKhoi, "Tên khối: ");
        cboKhoi.setPreferredSize(new Dimension(220, 25));
        detailsPane.add(jPanel, gbc);
        gbc.gridy++;
        JPanel jPanel1 = new JPanel();
        Menu.customTextField(jPanel1, lblMaLop, txtMaLop, "Mã lớp: ");
        detailsPane.add(jPanel1, gbc);
        gbc.gridy++;
        JPanel jPanel2 = new JPanel();
        Menu.customTextField(jPanel2, lblTenLop, txtTenLop, "Tên lớp: ");
        detailsPane.add(jPanel2, gbc);
        gbc.gridy++;
        JPanel jPanel3 = new JPanel();
        Menu.customTextField(jPanel3, lblGvcn, txtGvcn, "Chủ nghiệm: ");
        detailsPane.add(jPanel3, gbc);
        //Button
        JPanel buttonsPane = new JPanel(new FlowLayout());
        buttonsPane.setBackground(Color.white);
        btnAdd = new ButtonCustom();
        btnEdit = new ButtonCustom();
        btnDelete = new ButtonCustom();
        Menu.customButton(btnAdd, "Thêm");
        Menu.customButton(btnEdit, "Sửa");
        Menu.customButton(btnDelete, "Xoá");
        buttonsPane.add(btnAdd);
        buttonsPane.add(btnEdit);
        buttonsPane.add(btnDelete);
        panel.add(detailsPane, BorderLayout.CENTER);
        panel.add(buttonsPane, BorderLayout.SOUTH);
        this.add(panel, BorderLayout.WEST);
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
        String ColumnName[] = {"STT", "Mã lớp", "Tên lớp", "Giáo viên chủ nghiệm"};
        Object[][] data = {};
        jTable = new JTable(data, ColumnName);
        JScrollPane jScrollPane1 = new JScrollPane();
        Menu.customTable(jTable, jScrollPane1);
        panel.add(jScrollPane1, BorderLayout.CENTER);
        this.add(panel, BorderLayout.EAST);
    }

    public void DataTable(ModelLop model) {
        jTable.setModel(model);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(125);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(125);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(160);
    }

    public void DataCboKhoi(CbModelKhoi model) {
        cboKhoi.setModel(model);
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

    public void addSearchListener(DocumentListener listener) {
        txtSearch.getDocument().addDocumentListener(listener);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        jTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void addComboKhoiListener(ActionListener listener) {
        cboKhoi.addActionListener(listener);
    }
}
