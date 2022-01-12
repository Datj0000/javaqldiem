package Entity;

public class HocKy {

    private String maHocKy, tenHocKy, maNamHoc, tenNamHoc;

    public HocKy() {

    }

    public HocKy(String maHocKy, String tenHocKy, String maNamHoc) {
        this.maHocKy = maHocKy;
        this.tenHocKy = tenHocKy;
        this.maNamHoc = maNamHoc;
    }

    public HocKy(String maHocKy, String tenHocKy, String maNamHoc, String tenNamHoc) {
        this.maHocKy = maHocKy;
        this.tenHocKy = tenHocKy;
        this.maNamHoc = maNamHoc;
        this.tenNamHoc = tenNamHoc;
    }

    public String getTenNamHoc() {
        return tenNamHoc;
    }

    public void setTenNamHoc(String tenNamHoc) {
        this.tenNamHoc = tenNamHoc;
    }

    public String getMaNamHoc() {
        return maNamHoc;
    }

    public void setMaNamHoc(String maNamHoc) {
        this.maNamHoc = maNamHoc;
    }

    public String getMaHocKy() {
        return maHocKy;
    }

    public String getTenHocKy() {
        return tenHocKy;
    }

    public void setMaHocKy(String maHocKy) {
        this.maHocKy = maHocKy;
    }

    public void setTenHocKy(String tenHocKy) {
        this.tenHocKy = tenHocKy;
    }

    @Override
    public String toString() {
        return this.tenHocKy;
    }
}
