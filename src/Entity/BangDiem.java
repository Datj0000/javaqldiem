package Entity;

public class BangDiem {

    private String maBangDiem, maHocKy, maHocSinh, tenHocSinh, maLop, tenLop;

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public BangDiem() {

    }

    public BangDiem(String maBangDiem, String maHocKy, String maLop, String maHocSinh, String tenHocSinh) {
        this.maBangDiem = maBangDiem;
        this.maHocKy = maHocKy;
        this.maLop = maLop;
        this.maHocSinh = maHocSinh;
        this.tenHocSinh = tenHocSinh;
    }

    public BangDiem(String maBangDiem, String maHocKy, String maLop, String maHocSinh, String tenHocSinh, String tenLop) {
        this.maBangDiem = maBangDiem;
        this.maHocKy = maHocKy;
        this.maLop = maLop;
        this.maHocSinh = maHocSinh;
        this.tenHocSinh = tenHocSinh;
        this.tenLop = tenLop;
    }

    public String getMaBangDiem() {
        return maBangDiem;
    }

    public void setMaBangDiem(String maBangDiem) {
        this.maBangDiem = maBangDiem;
    }

    public String getMaHocKy() {
        return maHocKy;
    }

    public void setMaHocKy(String maHocKy) {
        this.maHocKy = maHocKy;
    }

    public String getMaHocSinh() {
        return maHocSinh;
    }

    public void setMaHocSinh(String maHocSinh) {
        this.maHocSinh = maHocSinh;
    }

    public String getTenHocSinh() {
        return tenHocSinh;
    }

    public void setTenHocSinh(String tenHocSinh) {
        this.tenHocSinh = tenHocSinh;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
}
