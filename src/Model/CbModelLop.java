package Model;

import Entity.Lop;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class CbModelLop extends DefaultComboBoxModel<Lop> {

    public CbModelLop(ArrayList<Lop> dsLop) {
        if (dsLop.size() < 1) {
            Lop lop = new Lop("", "Chưa có lớp", "", "");
            this.addElement(lop);
        } else {
            Lop lop = new Lop("", "Chọn lớp", "", "");
            this.addElement(lop);
            for (int i = 0; i < dsLop.size(); i++) {
                Lop l = dsLop.get(i);
                this.addElement(l);
            }
        }
    }
}
