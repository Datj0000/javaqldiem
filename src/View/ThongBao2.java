package View;

import View.Component.ButtonCustom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ThongBao2 extends JFrame implements ActionListener {

    private ButtonCustom btnOk, btnCancel;
    static int k;

    public ThongBao2() {
        this.setLayout(new BorderLayout());
        //Top
        JPanel topPane = new JPanel();
        topPane.setBackground(new Color(103, 103, 103));
        JLabel lbltitle = new JLabel("Thông báo");
        lbltitle.setFont(new Font("Segoe UI", 1, 18));
        lbltitle.setForeground(Color.WHITE);
        topPane.add(lbltitle);
        //Mid
        JPanel detailsPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 40));
        detailsPane.setBackground(Color.white);
        Label lblThongbao = new Label("Bạn chắc chắn muốn xoá không?");
        lblThongbao.setFont(new Font("Segoe UI", 0, 15));
        detailsPane.add(lblThongbao);
        //Bottom
        JPanel buttonsPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttonsPane.setBackground(Color.white);
        btnOk = new ButtonCustom();
        btnOk.addActionListener(this);
        btnCancel = new ButtonCustom();
        btnCancel.addActionListener(this);
        Menu.customButton(btnOk, "Có");
        Menu.customButton(btnCancel, "Thoát");
        btnOk.setPreferredSize(new Dimension(80, 35));
        btnCancel.setPreferredSize(new Dimension(80, 35));
        buttonsPane.add(btnOk);
        buttonsPane.add(btnCancel);
        this.add(topPane, BorderLayout.NORTH);
        this.add(detailsPane, BorderLayout.CENTER);
        this.add(buttonsPane, BorderLayout.SOUTH);
        Image im = null;
        try {
            im = ImageIO.read(getClass().getResource("Images/icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIconImage(im);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(103, 103, 103)));
        this.setBounds(670, 300, 300, 170);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Thông báo");
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public static JFrame Khoitao() {
        ThongBao2 tb = new ThongBao2();
        tb.setVisible(true);
        return tb;
    }

    // public static void main(String[] args) {
    //     ThongBao2 tb = new ThongBao2();
    //     tb.setVisible(true);
    // }
    public static int Check() {
        return k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            this.setVisible(false);
            k = 1;
            Check();
        }
        if (e.getSource() == btnOk) {
            this.setVisible(false);
            k = 2;
            Check();
        }
    }
}
