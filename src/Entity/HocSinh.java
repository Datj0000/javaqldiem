package Entity;

public class HocSinh {

    private String maHocSinh, tenHocSinh, ngaySinh, gioiTinh, noiSinh, danToc, maLop;

    public HocSinh() {

    }

    public HocSinh(String maHocSinh, String tenHocSinh, String ngaySinh, String gioiTinh, String noiSinh, String danToc, String maLop) {
        this.maHocSinh = maHocSinh;
        this.tenHocSinh = tenHocSinh;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.noiSinh = noiSinh;
        this.danToc = danToc;
        this.maLop = maLop;
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

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Override
    public String toString() {
        return this.tenHocSinh;
    }

}
