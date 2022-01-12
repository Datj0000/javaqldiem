package Entity;

public class Lop {

    private String maLop, tenLop, tenGVCN, maKhoi, tenKhoi;

    public Lop() {

    }

    public Lop(String maLop, String tenLop, String tenGVCN, String maKhoi, String tenKhoi) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.tenGVCN = tenGVCN;
        this.maKhoi = maKhoi;
        this.tenKhoi = tenKhoi;
    }

    public Lop(String maLop, String tenLop, String tenGVCN, String maKhoi) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.tenGVCN = tenGVCN;
        this.maKhoi = maKhoi;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getTenGVCN() {
        return tenGVCN;
    }

    public void setTenGVCN(String tenGVCN) {
        this.tenGVCN = tenGVCN;
    }

    public String getMaKhoi() {
        return maKhoi;
    }

    public void setMaKhoi(String maKhoi) {
        this.maKhoi = maKhoi;
    }

    public String getTenKhoi() {
        return tenKhoi;
    }

    public void setTenKhoi(String tenKhoi) {
        this.tenKhoi = tenKhoi;
    }

    @Override
    public String toString() {
        return tenLop;
    }

}
