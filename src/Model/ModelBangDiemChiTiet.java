package Model;

import javax.swing.table.*;
import java.util.*;
import Entity.BangDiemChiTiet;

public class ModelBangDiemChiTiet extends AbstractTableModel {

    private ArrayList<BangDiemChiTiet> dsBangDiemChiTiet = null;
    private Object[][] data = null;
    private  String ColumnName[] = {"STT", "Môn học", "Điểm miệng", "Điểm kiểm tra 1", "Điểm kiểm tra 2", "Điểm thi", "Điểm trung bình"};

    public ModelBangDiemChiTiet(ArrayList<BangDiemChiTiet> _dsBangDiemChiTiet) {
        dsBangDiemChiTiet = _dsBangDiemChiTiet;
        data = new Object[dsBangDiemChiTiet.size()][];
        for (int i = 0; i < dsBangDiemChiTiet.size(); i++) {
            BangDiemChiTiet k = dsBangDiemChiTiet.get(i);
            if (k.getTenMon()!= null) {
                Object[] row = {i+1, k.getTenMon(), k.getDiemMieng(), k.getDiem1(), k.getDiem2(), k.getDiemThi(), k.getDiemTB()};
                data[i] = row;
            }
            else{
                Object[] row = {"", "", "", "", "", "", ""};
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
