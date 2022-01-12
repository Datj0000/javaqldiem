package Model;

import Entity.NamHoc;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class CbModelNamHoc extends DefaultComboBoxModel<NamHoc> {

    public CbModelNamHoc(ArrayList<NamHoc> dsNamHoc) {
        if (dsNamHoc.size() < 1) {
            NamHoc khoi = new NamHoc("", "Chưa có năm học");
            this.addElement(khoi);
        } else {
            NamHoc khoi = new NamHoc("", "Chọn năm học");
            this.addElement(khoi);
            for (int i = 0; i < dsNamHoc.size(); i++) {
                NamHoc k = dsNamHoc.get(i);
                this.addElement(k);
            }
        }
    }
}
