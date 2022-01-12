package Model;

import javax.swing.table.*;
import java.util.*;
import Entity.Lop;

public class ModelLop extends AbstractTableModel {

    private ArrayList<Lop> dsLop = null;
    private Object[][] data = null;
    private String ColumnName[] = {"STT", "Mã lớp", "Tên lớp", "Giáo viên chủ nghiệm"};

    public ModelLop(ArrayList<Lop> _dsLop) {
        dsLop = _dsLop;
        data = new Object[dsLop.size()][];
        for (int i = 0; i < dsLop.size(); i++) {
            Lop lop = dsLop.get(i);
            if (lop.getMaLop() != null) {
                Object[] row = {i+1, lop.getMaLop(), lop.getTenLop(), lop.getTenGVCN()};
                data[i] = row;
            }
            else{
                Object[] row = {"", lop.getMaLop(), lop.getTenLop(), lop.getTenGVCN()};
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
