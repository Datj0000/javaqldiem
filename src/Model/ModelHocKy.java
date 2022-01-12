package Model;

import javax.swing.table.*;
import java.util.*;
import Entity.HocKy;

public class ModelHocKy extends AbstractTableModel {

    private ArrayList<HocKy> dsHocKy = null;
    private Object[][] data = null;
    private String ColumnName[] = {"STT", "Mã học kỳ", "Tên học kỳ"};

    public ModelHocKy(ArrayList<HocKy> _dsHocKy) {
        dsHocKy = _dsHocKy;
        data = new Object[dsHocKy.size()][];
        for (int i = 0; i < dsHocKy.size(); i++) {
            HocKy k = dsHocKy.get(i);
            if (k.getMaHocKy() != null) {
                Object[] row = {i+1, k.getMaHocKy(), k.getTenHocKy()};
                data[i] = row;
            }
            else{
                Object[] row = {"", k.getMaHocKy(), k.getTenHocKy()};
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
