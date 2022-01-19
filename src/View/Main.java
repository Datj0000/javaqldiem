package View;

import Controller.ControllerDangNhap;

public class Main {

    public static void main(String[] args) {
        JDangNhap panelDangNhap = new JDangNhap();
        ControllerDangNhap controller = new ControllerDangNhap(panelDangNhap);
        controller.ShowData();
    }
}
