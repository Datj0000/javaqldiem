package Controller;

import View.*;
import Entity.*;
import DAO.*;
import View.Component.MD5;
import java.awt.event.*;
import java.security.*;

public class ControllerDoiMatKhau {

    private final TaiKhoanDAO DAO;
    private final JDoiMatKhau jDoiMatKhau;
    private final String taikhoan;

    public ControllerDoiMatKhau(JDoiMatKhau _jDoiMatKhau, String _taikhoan) {
        this.jDoiMatKhau = _jDoiMatKhau;
        this.taikhoan = _taikhoan;
        DAO = new TaiKhoanDAO();
    }

    public void ShowData() {
        jDoiMatKhau.addSaveListener(new SaveListener());
        jDoiMatKhau.addExitListener(new ExitListener());
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TaiKhoan dn = getInfo();
            if (dn != null) {
                Menu.showMessage("Đổi mật khẩu thành công");
                jDoiMatKhau.setVisible(false);
                DAO.changePass(dn);
            }
        }
    }

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jDoiMatKhau.setVisible(false);
        }
    }

    public TaiKhoan getInfo() {
        try {
            TaiKhoan dn = new TaiKhoan();
            String matKhauCu = jDoiMatKhau.txtMatKhauCu.getText().trim();
            String matKhauMoi = jDoiMatKhau.txtMatKhauMoi.getText().trim();
            String matKhauMoi2 = jDoiMatKhau.txtMatKhauMoi2.getText().trim();
            if (matKhauMoi == null ? matKhauMoi2 == null : matKhauMoi.equals(matKhauMoi2)) {
                dn.setTaiKhoan(taikhoan);
                dn.setMatKhau(MD5.MD5(matKhauCu));
                if (!DAO.checkPass(dn)) {
                    Menu.showMessage("Mật khẩu cũ không đúng");
                    dn = null;
                }
            } else {
                Menu.showMessage("Vui lòng kiểm tra lại mật khẩu");
                dn = null;
            }
            return dn;
        } catch (NoSuchAlgorithmException e) {
            Menu.showMessage(e.getMessage());
        }
        return null;
    }
}
