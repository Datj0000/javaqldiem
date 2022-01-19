package Model;

import javax.swing.table.*;
import java.util.*;
import Entity.NamHoc;

public class ModelNamHoc extends AbstractTableModel {

    public ArrayList<NamHoc> dsNamHoc = null;
    public Object[][] data = null;
    public String ColumnName[] = {"STT","Mã năm học", "Tên năm học"};

    public ModelNamHoc(ArrayList<NamHoc> _dsNamHoc) {
        dsNamHoc = _dsNamHoc;
        data = new Object[dsNamHoc.size()][];
        for (int i = 0; i < dsNamHoc.size(); i++) {
            NamHoc k = dsNamHoc.get(i);
            if (k.getMaNamHoc() != null) {
                Object[] row = {i+1, k.getMaNamHoc(), k.getTenNamHoc()};
                data[i] = row;
            }
            else{
                Object[] row = {"", k.getMaNamHoc(), k.getTenNamHoc()};
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
