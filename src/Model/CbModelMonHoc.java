package Model;

import Entity.MonHoc;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class CbModelMonHoc extends DefaultComboBoxModel<MonHoc> {

    public CbModelMonHoc(ArrayList<MonHoc> dsMonHoc) {
        if (dsMonHoc.size() < 1) {
            MonHoc monHoc = new MonHoc("", "Chưa có môn học");
            this.addElement(monHoc);
        } else {
            MonHoc monHoc = new MonHoc("", "Chọn môn học");
            this.addElement(monHoc);
            for (int i = 0; i < dsMonHoc.size(); i++) {
                MonHoc k = dsMonHoc.get(i);
                this.addElement(k);
            }
        }
    }
}
