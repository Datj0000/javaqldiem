package Entity;

public class MonHoc {

    private String maMonHoc, tenMonHoc;

    public MonHoc() {

    }

    public MonHoc(String maMonHoc, String tenMonHoc) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    @Override
    public String toString() {
        return this.tenMonHoc;
    }
}
