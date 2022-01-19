package View;

import View.Component.ButtonCustom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.imageio.*;

public class JDangNhap extends JFrame {

    private JLabel lblTaikhoan, lblMatKhau;
    private ButtonCustom btnLogin, btnExit;
    public JTextField txtTaikhoan;
    public JPasswordField txtMatKhau;
    private JPanel jPanel, jPanel2;

    public JDangNhap() {
        this.setLayout(new BorderLayout());
        JPanel detailsPane = new JPanel(new GridBagLayout());
        detailsPane.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        lblTaikhoan = new JLabel();
        lblMatKhau = new JLabel();
        txtTaikhoan = new JTextField(18);
        txtMatKhau = new JPasswordField(18);
        jPanel = new JPanel();
        Menu.customTextField(jPanel, lblTaikhoan, txtTaikhoan, "Tài khoản: ");
        detailsPane.add(jPanel, gbc);
        gbc.gridy++;
        jPanel2 = new JPanel();
        Menu.customTextField(jPanel2, lblMatKhau, txtMatKhau, "Mật khẩu: ");
        detailsPane.add(jPanel2, gbc);

        JPanel buttonsPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 35));
        buttonsPane.setBackground(Color.white);
        btnLogin = new ButtonCustom();
        btnExit = new ButtonCustom();
        Menu.customButton(btnLogin, "Đăng nhập");
        Menu.customButton(btnExit, "Thoát");
        btnLogin.setPreferredSize(new Dimension(100, 35));
        btnExit.setPreferredSize(new Dimension(100, 35));
        buttonsPane.add(btnLogin);
        buttonsPane.add(btnExit);

        JPanel topPane = new JPanel();
        topPane.setBackground(new Color(103, 103, 103));
        JLabel lbltitle = new JLabel("Đăng nhập");
        lbltitle.setFont(new Font("Segoe UI", 1, 18));
        lbltitle.setForeground(Color.WHITE);
        topPane.add(lbltitle);
        this.add(topPane, BorderLayout.NORTH);
        this.add(detailsPane, BorderLayout.CENTER);
        this.add(buttonsPane, BorderLayout.SOUTH);
        Image im = null;
        try {
            im = ImageIO.read(getClass().getResource("./Images/icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(103, 103, 103)));
        this.setIconImage(im);
        this.setSize(330, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Quản lý điểm sinh viên");
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void addExitListener(ActionListener listener) {
        btnExit.addActionListener(listener);
    }
}
