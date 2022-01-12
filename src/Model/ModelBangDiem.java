package Model;

import javax.swing.table.*;
import java.util.*;
import Entity.BangDiem;

public class ModelBangDiem extends AbstractTableModel {

    private ArrayList<BangDiem> dsBangDiem = null;
    private Object[][] data = null;
    private String ColumnName[] = {"STT", "Mã bảng điểm", "Mã học sinh", "Tên học sinh"};

    public ModelBangDiem(ArrayList<BangDiem> _dsBangDiem) {
        dsBangDiem = _dsBangDiem;
        data = new Object[dsBangDiem.size()][];
        for (int i = 0; i < dsBangDiem.size(); i++) {
            BangDiem k = dsBangDiem.get(i);
            if (k.getMaBangDiem() != null) {
                Object[] row = {i+1, k.getMaBangDiem(), k.getMaHocSinh(), k.getTenHocSinh()};
                data[i] = row;
            }
            else{
                Object[] row = {"", k.getMaBangDiem(), k.getMaHocSinh(), k.getTenHocSinh()};
                data[i] = row;
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
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
