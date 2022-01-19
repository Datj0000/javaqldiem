package View;

import Controller.*;
import View.Component.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.table.*;
import javax.swing.border.*;

public class Menu extends JFrame {

    private JPanel slider, header, main, top, bot, left, right;
    private JPanel btnBD, btnHS, btnMH, btnLop, btnKhoi, btnHK, btnNH;
    private JPanel indBD, indHS, indMH, indLop, indKhoi, indHK, indNH;
    private JLabel jLabelBD, jLabelHS, jLabelMH, jLabelLop, jLabelKhoi, jLabelHK, jLabelNH;
    private JLabel iconBD, iconHS, iconMH, iconLop, iconKhoi, iconHK, iconNH;
    private JPanel pnlActions, pnlTitle;
    private JLabel lblMinimize, lblClose, lblTitle, lblSetting;
    private int xy, xx;
    public JTextField txtTaiKhoan;

    public Menu() {
        this.setLayout(new BorderLayout());
        Image im = null;
        try {
            im = ImageIO.read(getClass().getResource("Images/icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIconImage(im);
        Header();
        Slider();
        Main();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(1000, 600);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Quản lý điểm học sinh THPT");
        this.setUndecorated(true);
        this.setVisible(true);
    }

    private void Header() {
        header = new JPanel();
        pnlActions = new JPanel();
        pnlTitle = new JPanel();
        lblSetting = new JLabel();
        lblMinimize = new JLabel();
        lblClose = new JLabel();
        lblTitle = new JLabel();
        header.setBackground(new Color(77, 77, 77));
        header.setPreferredSize(new Dimension(673, 33));
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                headerMousePressed(evt);
            }
        });
        header.setLayout(new BorderLayout());

        pnlActions.setBackground(new Color(77, 77, 77));
        pnlActions.setLayout(new FlowLayout());
        lblSetting.setIcon(new ImageIcon(getClass().getResource("Images/setting.png")));
        lblSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                lblSettingMousePressed(evt);
            }
        });
        lblMinimize.setIcon(new ImageIcon(getClass().getResource("Images/minimize.png")));
        lblMinimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                lblMinimizeMousePressed(evt);
            }
        });
        lblClose.setIcon(new ImageIcon(getClass().getResource("Images/close.png")));
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                lblCloseMousePressed(evt);
            }
        });
        lblSetting.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblMinimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlActions.add(lblSetting);
        pnlActions.add(lblMinimize);
        pnlActions.add(lblClose);
        header.add(pnlActions, BorderLayout.LINE_END);
        pnlTitle.setBackground(new Color(77, 77, 77));
        pnlTitle.setPreferredSize(new Dimension(200, 30));
        pnlTitle.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 8));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setText("Quản lý điểm học sinh THPT");
        pnlTitle.add(lblTitle);
        header.add(pnlTitle, BorderLayout.LINE_START);
        this.add(header, BorderLayout.NORTH);
    }

    private void Slider() {
        slider = new JPanel();
        txtTaiKhoan = new JTextField();
        txtTaiKhoan.setVisible(false);
        slider.add(txtTaiKhoan);
        slider.setBackground(new Color(103, 103, 103));
        slider.setPreferredSize(new Dimension(220, 500));
        btnBD = new JPanel();
        btnHS = new JPanel();
        btnMH = new JPanel();
        btnLop = new JPanel();
        btnKhoi = new JPanel();
        btnHK = new JPanel();
        btnNH = new JPanel();
        indBD = new JPanel();
        indHS = new JPanel();
        indMH = new JPanel();
        indLop = new JPanel();
        indKhoi = new JPanel();
        indHK = new JPanel();
        indNH = new JPanel();
        btnBD.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                btnBD_MousePressed();
            }
        });
        btnHS.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                btnHS_MousePressed();
            }
        });
        btnMH.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                btnMH_MousePressed();
            }
        });
        btnLop.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                btnLop_MousePressed();
            }
        });
        btnKhoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                btnKhoi_MousePressed();
            }
        });
        btnHK.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                btnHK_MousePressed();
            }
        });
        btnNH.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                btnNH_MousePressed();
            }
        });
        slider.add(Box.createVerticalStrut(70));
        buttonSilder(btnHS, indHS, jLabelHS, iconHS, "Quản lý học sinh", "Images/student.png");
        buttonSilder(btnBD, indBD, jLabelBD, iconBD, "Quản lý bảng điểm", "Images/list.png");
        buttonSilder(btnMH, indMH, jLabelMH, iconMH, "Quản lý môn học", "Images/book.png");
        buttonSilder(btnNH, indNH, jLabelNH, iconNH, "Quản lý năm học", "Images/calendar.png");
        buttonSilder(btnHK, indHK, jLabelHK, iconHK, "Quản lý học kỳ", "Images/list2.png");
        buttonSilder(btnKhoi, indKhoi, jLabelKhoi, iconKhoi, "Quản lý khối", "Images/room.png");
        buttonSilder(btnLop, indLop, jLabelLop, iconLop, "Quản lý lớp", "Images/class.png");
        slider.add(Box.createVerticalStrut(70));
        BoxLayout boxlayout = new BoxLayout(slider, BoxLayout.Y_AXIS);
        slider.setLayout(boxlayout);
        slider.setBorder(BorderFactory.createLineBorder(new Color(103, 103, 103)));
        this.add(slider, BorderLayout.WEST);
    }

    private void Main() {
        main = new JPanel();
        main.setLayout(new BorderLayout(10, 25));
        top = new JPanel();
        bot = new JPanel();
        left = new JPanel();
        right = new JPanel();
        top.setBackground(Color.white);
        bot.setBackground(Color.white);
        left.setBackground(Color.white);
        right.setBackground(Color.white);
        main.setBorder(BorderFactory.createLineBorder(new Color(103, 103, 103)));
        main.setBackground(Color.white);
        setColor(btnHS);
        indHS.setOpaque(true);
        JHocSinh panelhs = new JHocSinh();
        ControllerHocSinh controller = new ControllerHocSinh(panelhs);
        controller.ShowData();
        main.add(top, BorderLayout.NORTH);
        main.add(bot, BorderLayout.SOUTH);
        main.add(left, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        main.add(panelhs, BorderLayout.CENTER);
        this.add(main, BorderLayout.CENTER);
    }

    //Function
    public void openPanel(JPanel panel) {
        main.removeAll();
        main.add(top, BorderLayout.NORTH);
        main.add(bot, BorderLayout.SOUTH);
        main.add(left, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        main.add(panel, BorderLayout.CENTER);
        this.add(main);
        this.setVisible(true);
    }

    public static JThongBao showMessage(String message) {
        JThongBao tb = new JThongBao(message);
        tb.setVisible(true);
        return tb;
    }

    public static JPanel customRadio(JPanel jPanel, JLabel lbl, RadioButtonCustom radio1, RadioButtonCustom radio2, String str) {
        lbl.setFont(new Font("Segoe UI", 0, 14));
        lbl.setForeground(new Color(77, 77, 77));
        lbl.setText(str);
        radio1.setFont(new Font("Segoe UI", 0, 14));
        radio1.setBackground(new Color(77, 77, 77));
        radio1.setForeground(new Color(102, 102, 102));
        radio1.setFocusPainted(false);
        radio1.setCursor(new Cursor(HAND_CURSOR));
        radio2.setFont(new Font("Segoe UI", 0, 14));
        radio2.setBackground(new Color(77, 77, 77));
        radio2.setForeground(new Color(102, 102, 102));
        radio2.setFocusPainted(false);
        radio2.setCursor(new Cursor(HAND_CURSOR));
        GroupLayout jPanel1Layout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel.setBackground(Color.WHITE);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(lbl)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(radio1)
                                        .addComponent(radio2)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl)
                                .addGap(10)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(radio1)
                                        .addComponent(radio2)))
        );
        return jPanel;
    }

    public static JPanel customTextField(JPanel jPanel, JLabel lbl, JTextField txt, String str) {
        lbl.setFont(new Font("Segoe UI", 0, 14));
        lbl.setForeground(new Color(77, 77, 77));
        lbl.setText(str);
        txt.setBackground(Color.white);
        txt.setFont(new Font("Segoe UI", 0, 14));
        txt.setForeground(new Color(102, 102, 102));
        txt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(77, 77, 77)));
        GroupLayout jPanel1Layout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel.setBackground(Color.WHITE);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbl)
                                        .addComponent(txt)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl)
                                .addComponent(txt)
                                .addGap(10, 10, 10))
        );
        return jPanel;
    }

    public static JPanel customCombobox(JPanel jPanel, JLabel lbl, ComboboxCustom cbo, String str) {
        lbl.setFont(new Font("Segoe UI", 0, 14));
        lbl.setForeground(new Color(77, 77, 77));
        lbl.setText(str);
        cbo.setFont(new Font("Segoe UI", 0, 14));
        cbo.setForeground(new Color(102, 102, 102));
        cbo.setPreferredSize(new Dimension(180, 25));
        cbo.setBackground(Color.WHITE);
        cbo.setFocusable(false);
        GroupLayout jPanel1Layout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel.setBackground(Color.WHITE);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbl)
                                        .addComponent(cbo)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl)
                                .addComponent(cbo)
                                .addGap(10, 10, 10))
        );
        return jPanel;
    }

    public static ButtonCustom customButton(ButtonCustom btn, String txt) {
        btn.setText(txt);
        btn.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.darkGray));
        btn.setPreferredSize(new Dimension(70, 35));
        btn.setkForeGround(Color.darkGray);
        btn.setkStartColor(Color.white);
        btn.setkEndColor(Color.white);
        btn.setkPressedColor(Color.white);
        btn.setkHoverColor(Color.yellow);
        btn.setkHoverEndColor(Color.darkGray);
        btn.setkHoverForeGround(Color.white);
        btn.setkHoverStartColor(Color.gray);
        btn.setkBackGroundColor(Color.white);
        return btn;
    }

    public static JScrollPane customTable(JTable jTable, JScrollPane jScrollPane1) {
        jTable.setFont(new Font("Segoe UI", 0, 14));
        jTable.setForeground(new Color(51, 51, 51));
        jTable.setGridColor(Color.white);
        jTable.setRowHeight(25);
        jTable.setSelectionBackground(new Color(103, 103, 103));
        jTable.setSelectionForeground(Color.white);
        jTable.setShowHorizontalLines(false);
        jTable.setShowVerticalLines(false);
        jTable.setBorder(new EmptyBorder(0, 5, 0, 5));
        JTableHeader jTableHeader = jTable.getTableHeader();
        jTableHeader.setReorderingAllowed(false);
        jTableHeader.setResizingAllowed(false);
        jTableHeader.setFont(new Font("Segoe UI", 1, 14));
        jTableHeader.setForeground(Color.white);
        jTableHeader.setBackground(new Color(77, 77, 77));
        jTableHeader.setBorder(new LineBorder(new Color(103, 103, 103)));
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(103, 103, 103));
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        jScrollPane1.getViewport().setBackground(Color.white);
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
        jScrollPane1.setBackground(Color.white);
        jScrollPane1.setBorder(new LineBorder(new Color(103, 103, 103)));
        jScrollPane1.setOpaque(false);
        jScrollPane1.setViewportView(jTable);
        return jScrollPane1;
    }

    private void buttonSilder(JPanel btn, JPanel ind, JLabel lbl, JLabel icon, String txtLbl, String txtIcon) {
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(new Color(103, 103, 103));
        lbl = new JLabel();
        lbl.setFont(new Font("Segoe UI", 1, 14));
        lbl.setForeground(Color.WHITE);
        lbl.setText(txtLbl);
        ind.setOpaque(false);
        ind.setPreferredSize(new Dimension(4, 60));
        icon = new JLabel();
        icon.setIcon(new ImageIcon(getClass().getResource(txtIcon)));
        GroupLayout btnLayout = new GroupLayout(btn);
        btn.setLayout(btnLayout);
        btnLayout.setHorizontalGroup(
                btnLayout.createParallelGroup()
                        .addGroup(btnLayout.createSequentialGroup()
                                .addComponent(ind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(20)
                                .addComponent(icon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addComponent(lbl)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        btnLayout.setVerticalGroup(
                btnLayout.createParallelGroup()
                        .addGroup(btnLayout.createSequentialGroup()
                                .addComponent(ind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(btnLayout.createSequentialGroup()
                                .addComponent(icon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGroup(btnLayout.createSequentialGroup()
                                .addComponent(lbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );
        slider.add(btn);
    }

    public void setTxtTaiKhoan(String txt) {
        txtTaiKhoan.setText(txt);
    }

    private void setColor(JPanel pane) {
        pane.setBackground(new Color(77, 77, 77));
    }

    private void resetColor(JPanel[] pane, JPanel[] indicators) {
        for (JPanel pane1 : pane) {
            pane1.setBackground(new Color(103, 103, 103));
        }
        for (JPanel indicator : indicators) {
            indicator.setOpaque(false);
        }
    }

    //Event
    private void lblSettingMousePressed(MouseEvent evt) {
        JDoiMatKhau dmk = new JDoiMatKhau();
        ControllerDoiMatKhau controller = new ControllerDoiMatKhau(dmk, txtTaiKhoan.getText());
        controller.ShowData();
    }

    private void lblCloseMousePressed(MouseEvent evt) {
        System.exit(0);
    }

    private void lblMinimizeMousePressed(MouseEvent evt) {
        this.setState(Frame.ICONIFIED);
    }

    private void headerMousePressed(MouseEvent evt) {
        xx = evt.getX();
        xy = evt.getY();
    }

    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }

    private void btnHS_MousePressed() {
        setColor(btnHS);
        indHS.setOpaque(true);
        resetColor(new JPanel[]{btnBD, btnMH, btnLop, btnKhoi, btnHK, btnNH}, new JPanel[]{indBD, indMH, indLop, indKhoi, indHK, indNH});
        JHocSinh panelhs = new JHocSinh();
        ControllerHocSinh controller = new ControllerHocSinh(panelhs);
        controller.ShowData();
        openPanel(panelhs);
    }

    private void btnBD_MousePressed() {
        setColor(btnBD);
        indBD.setOpaque(true);
        resetColor(new JPanel[]{btnHS, btnMH, btnLop, btnKhoi, btnHK, btnNH}, new JPanel[]{indHS, indMH, indLop, indKhoi, indHK, indNH});
        JBangDiem panelbd = new JBangDiem();
        ControllerBangDiem controller = new ControllerBangDiem(panelbd, this);
        controller.ShowData();
        openPanel(panelbd);
    }

    private void btnMH_MousePressed() {
        setColor(btnMH);
        indMH.setOpaque(true);
        resetColor(new JPanel[]{btnHS, btnBD, btnLop, btnKhoi, btnHK, btnNH}, new JPanel[]{indHS, indBD, indLop, indKhoi, indHK, indNH});
        JMonHoc panelmon = new JMonHoc();
        ControllerMonHoc controller = new ControllerMonHoc(panelmon);
        controller.ShowData();
        openPanel(panelmon);
    }

    private void btnLop_MousePressed() {
        setColor(btnLop);
        indLop.setOpaque(true);
        resetColor(new JPanel[]{btnHS, btnMH, btnBD, btnKhoi, btnHK, btnNH}, new JPanel[]{indHS, indMH, indBD, indKhoi, indHK, indNH});
        JLop panelLop = new JLop();
        ControllerLop controller = new ControllerLop(panelLop);
        controller.ShowData();
        openPanel(panelLop);
    }

    private void btnKhoi_MousePressed() {
        setColor(btnKhoi);
        indKhoi.setOpaque(true);
        resetColor(new JPanel[]{btnHS, btnMH, btnLop, btnBD, btnHK, btnNH}, new JPanel[]{indHS, indMH, indLop, indBD, indHK, indNH});
        JKhoi panelKhoi = new JKhoi();
        ControllerKhoi controller = new ControllerKhoi(panelKhoi);
        controller.ShowData();
        openPanel(panelKhoi);
    }

    private void btnHK_MousePressed() {
        setColor(btnHK);
        indHK.setOpaque(true);
        resetColor(new JPanel[]{btnHS, btnMH, btnLop, btnBD, btnKhoi, btnNH}, new JPanel[]{indHS, indMH, indLop, indBD, indKhoi, indNH});
        JHocKy panelHocKy = new JHocKy();
        ControllerHocKy controller = new ControllerHocKy(panelHocKy);
        controller.ShowData();
        openPanel(panelHocKy);
    }

    private void btnNH_MousePressed() {
        setColor(btnNH);
        indNH.setOpaque(true);
        resetColor(new JPanel[]{btnHS, btnMH, btnLop, btnBD, btnKhoi, btnHK}, new JPanel[]{indHS, indMH, indLop, indBD, indKhoi, indHK});
        JNamHoc panelNamHoc = new JNamHoc();
        ControllerNamHoc controller = new ControllerNamHoc(panelNamHoc);
        controller.ShowData();
        openPanel(panelNamHoc);
    }
}
