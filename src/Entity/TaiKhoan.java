package Entity;

public class TaiKhoan {

    private String taiKhoan, matKhau;
    private int quyen;

    public TaiKhoan() {

    }

    public TaiKhoan(String taiKhoan, String tenKhoi, int quyen) {
        this.taiKhoan = taiKhoan;
        this.matKhau = tenKhoi;
        this.quyen = quyen;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

}
