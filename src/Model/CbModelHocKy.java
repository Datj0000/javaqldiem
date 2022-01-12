package Model;

import Entity.HocKy;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class CbModelHocKy extends DefaultComboBoxModel<HocKy> {

    public CbModelHocKy(ArrayList<HocKy> dsHocKy) {
        if (dsHocKy.size() < 1) {
            HocKy hk = new HocKy("", "Chưa có học kỳ","");
            this.addElement(hk);
        } else {
            HocKy hk = new HocKy("", "Chọn học kỳ","");
            this.addElement(hk);
            for (int i = 0; i < dsHocKy.size(); i++) {
                HocKy k = dsHocKy.get(i);
                this.addElement(k);
            }
        }
    }
}
