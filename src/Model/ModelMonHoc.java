package Model;

import javax.swing.table.*;
import java.util.*;
import Entity.MonHoc;

public class ModelMonHoc extends AbstractTableModel {

    private ArrayList<MonHoc> dsMonHoc = null;
    private Object[][] data = null;
    private String ColumnName[] = {"STT","Mã môn học", "Tên môn học"};

    public ModelMonHoc(ArrayList<MonHoc> _dsMonHoc) {
        dsMonHoc = _dsMonHoc;
        data = new Object[dsMonHoc.size()][];
        for (int i = 0; i < dsMonHoc.size(); i++) {
            MonHoc k = dsMonHoc.get(i);
            if (k.getMaMonHoc() != null) {
                Object[] row = {i+1, k.getMaMonHoc(), k.getTenMonHoc()};
                data[i] = row;
            }
            else{
                Object[] row = {"", k.getMaMonHoc(), k.getTenMonHoc()};
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
