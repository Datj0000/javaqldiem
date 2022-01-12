package Model;

import Entity.HocSinh;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class CbModelHocSinh extends DefaultComboBoxModel<HocSinh> {

    public CbModelHocSinh(ArrayList<HocSinh> dsHocSinh) {
        if (dsHocSinh.size() < 1) {
            HocSinh khoi = new HocSinh("", "Chưa có học sinh", "", "", "", "", "");
            this.addElement(khoi);
        } else {
            HocSinh khoi = new HocSinh("", "Chọn học sinh", "", "", "", "", "");
            this.addElement(khoi);
            for (int i = 0; i < dsHocSinh.size(); i++) {
                HocSinh k = dsHocSinh.get(i);
                this.addElement(k);
            }
        }
    }
}
