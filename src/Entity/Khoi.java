package Entity;

public class Khoi {

    private String maKhoi, tenKhoi;

    public Khoi() {

    }

    public Khoi(String maKhoi, String tenKhoi) {
        this.maKhoi = maKhoi;
        this.tenKhoi = tenKhoi;
    }

    public String getMaKhoi() {
        return maKhoi;
    }

    public String getTenKhoi() {
        return tenKhoi;
    }

    public void setMaKhoi(String maKhoi) {
        this.maKhoi = maKhoi;
    }

    public void setTenKhoi(String tenKhoi) {
        this.tenKhoi = tenKhoi;
    }

    @Override
    public String toString() {
        return tenKhoi;
    }
}
