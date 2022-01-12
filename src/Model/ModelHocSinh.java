package Model;

import Entity.HocSinh;
import java.util.ArrayList;
import javax.swing.table.*;

public class ModelHocSinh extends AbstractTableModel {

    private ArrayList<HocSinh> dsHocSinh = null;
    private Object[][] data = null;
    String ColumnName[] = {"STT", "Mã học sinh", "Họ và tên", "Giới tính", "Ngày sinh", "Nơi sinh", "Dân tộc"};

    public ModelHocSinh(ArrayList<HocSinh> _dsHocSinh) {
        dsHocSinh = _dsHocSinh;
        data = new Object[dsHocSinh.size()][];
        for (int i = 0; i < dsHocSinh.size(); i++) {
            HocSinh hs = dsHocSinh.get(i);
            if (hs.getMaHocSinh() != null) {
                Object[] row = {i + 1, hs.getMaHocSinh(), hs.getTenHocSinh(), hs.getGioiTinh(), hs.getNgaySinh(), hs.getNoiSinh(), hs.getDanToc()};
                data[i] = row;
            } else {
                Object[] row = {"", hs.getMaHocSinh(), hs.getTenHocSinh(), hs.getGioiTinh(), hs.getNgaySinh(), hs.getNoiSinh(), hs.getDanToc()};
                data[i] = row;
            }
        }
    }

    @Override
    public int getRowCount() {
        return dsHocSinh.size();
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int colunnIdex) {
        return ColumnName[colunnIdex];
    }
}
