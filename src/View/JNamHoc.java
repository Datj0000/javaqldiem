package View;

import View.Component.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class JNamHoc extends JPanel {

    public ButtonCustom btnAdd, btnEdit, btnDelete;
    public JTextField txtMaNamHoc, txtTenNamHoc, txtSearch;
    public JTable jTable;

    public JNamHoc() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        FormInput();
        TableView();
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
        JLabel lblMaNamHoc = new JLabel();
        JLabel lblTenNamHoc = new JLabel();
        txtMaNamHoc = new JTextField(18);
        txtMaNamHoc.setEnabled(false);
        txtTenNamHoc = new JTextField(18);
        JPanel jPanel = new JPanel();
        Menu.customTextField(jPanel, lblMaNamHoc, txtMaNamHoc, "Mã năm học: ");
        detailsPane.add(jPanel, gbc);
        gbc.gridy++;
        JPanel jPanel2 = new JPanel();
        Menu.customTextField(jPanel2, lblTenNamHoc, txtTenNamHoc, "Tên năm học: ");
        detailsPane.add(jPanel2, gbc);
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

    public void TableView() {
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
        String ColumnName[] = {"STT", "Mã năm học", "Tên năm học"};
        Object[][] data = {};
        jTable = new JTable(data, ColumnName);
        JScrollPane jScrollPane1 = new JScrollPane();
        Menu.customTable(jTable, jScrollPane1);
        panel.add(jScrollPane1, BorderLayout.CENTER);
        this.add(panel, BorderLayout.EAST);
    }

    public void DataTable(ModelNamHoc model) {
        jTable.setModel(model);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(205);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(205);
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
}
