package Controller;

import View.*;
import Entity.*;
import DAO.*;
import View.Component.MD5;
import java.awt.event.*;
import java.security.*;

public class ControllerDangNhap {

    private final TaiKhoanDAO DAO;
    private final JDangNhap jDangNhap;

    public ControllerDangNhap(JDangNhap _jDangNhap) {
        this.jDangNhap = _jDangNhap;
        DAO = new TaiKhoanDAO();
    }

    public void ShowData() {
        jDangNhap.addLoginListener(new LoginListener());
        jDangNhap.addExitListener(new ExitListener());
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TaiKhoan dn = getInfo();
            if (dn != null) {
                if (DAO.checkLogin(dn)) {
                    jDangNhap.setVisible(false);
                    Menu menu = new Menu();
                    menu.setTxtTaiKhoan(dn.getTaiKhoan());
                } else {
                    Menu.showMessage("Sai tài khoản hoặc mật khẩu");
                }
            }
        }
    }

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public TaiKhoan getInfo() {
        try {
            TaiKhoan dn = new TaiKhoan();
            String taiKhoan = jDangNhap.txtTaikhoan.getText().trim();
            String matKhau = jDangNhap.txtMatKhau.getText().trim();
            if (!"".equals(taiKhoan) && !"".equals(matKhau)) {
                dn.setTaiKhoan(taiKhoan);
                dn.setMatKhau(MD5.MD5(matKhau));
            } else {
                Menu.showMessage("Vui lòng điền tài khoản và mật khẩu");
                dn = null;
            }
            return dn;
        } catch (NoSuchAlgorithmException e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }
}
