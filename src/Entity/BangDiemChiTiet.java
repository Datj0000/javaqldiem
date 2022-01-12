package Entity;

public class BangDiemChiTiet {

    private String maBangDiem, maMon, tenMon;
    private float diemMieng, diem1, diem2, diemThi, diemTB;

    public BangDiemChiTiet() {

    }

    public BangDiemChiTiet(String maBangDiem, String maMon, String tenMon, float diemMieng, float diem1, float diem2, float diemThi, float diemTB) {
        this.maBangDiem = maBangDiem;
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.diemMieng = diemMieng;
        this.diem1 = diem1;
        this.diem2 = diem2;
        this.diemThi = diemThi;
        this.diemTB = diemTB;
    }

    public String getMaBangDiem() {
        return maBangDiem;
    }

    public void setMaBangDiem(String maBangDiem) {
        this.maBangDiem = maBangDiem;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public float getDiemMieng() {
        return diemMieng;
    }

    public void setDiemMieng(float diemMieng) {
        this.diemMieng = diemMieng;
    }

    public float getDiem1() {
        return diem1;
    }

    public void setDiem1(float diem1) {
        this.diem1 = diem1;
    }

    public float getDiem2() {
        return diem2;
    }

    public void setDiem2(float diem2) {
        this.diem2 = diem2;
    }

    public float getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(float diemThi) {
        this.diemThi = diemThi;
    }

    public float getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(float diemTB) {
        this.diemTB = diemTB;
    }
}
