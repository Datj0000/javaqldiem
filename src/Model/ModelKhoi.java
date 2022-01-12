package Model;

import javax.swing.table.*;
import java.util.*;
import Entity.Khoi;

public class ModelKhoi extends AbstractTableModel {

    private ArrayList<Khoi> dsKhoi = null;
    private Object[][] data = null;
    private String ColumnName[] = {"STT","Mã khối", "Tên khối"};

    public ModelKhoi(ArrayList<Khoi> _dsKhoi) {
        dsKhoi = _dsKhoi;
        data = new Object[dsKhoi.size()][];
        for (int i = 0; i < dsKhoi.size(); i++) {
            Khoi k = dsKhoi.get(i);
            if (k.getMaKhoi() != null) {
                Object[] row = {i+1, k.getMaKhoi(), k.getTenKhoi()};
                data[i] = row;
            }
            else{
                Object[] row = {"", k.getMaKhoi(), k.getTenKhoi()};
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
