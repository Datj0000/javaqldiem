package Entity;

public class NamHoc {

    private String maNamHoc, tenNamHoc;

    public NamHoc() {

    }

    public NamHoc(String maNamHoc, String tenNamHoc) {
        this.maNamHoc = maNamHoc;
        this.tenNamHoc = tenNamHoc;
    }

    public String getMaNamHoc() {
        return maNamHoc;
    }

    public String getTenNamHoc() {
        return tenNamHoc;
    }

    public void setMaNamHoc(String maNamHoc) {
        this.maNamHoc = maNamHoc;
    }

    public void setTenNamHoc(String tenNamHoc) {
        this.tenNamHoc = tenNamHoc;
    }

    @Override
    public String toString() {
        return tenNamHoc;
    }
    
}
