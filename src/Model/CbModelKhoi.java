package Model;

import Entity.Khoi;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class CbModelKhoi extends DefaultComboBoxModel<Khoi> {

    public CbModelKhoi(ArrayList<Khoi> dsKhoi) {
        if (dsKhoi.size() < 1) {
            Khoi khoi = new Khoi("", "Chưa có khối");
            this.addElement(khoi);
        } else {
            Khoi khoi = new Khoi("", "Chọn khối");
            this.addElement(khoi);
            for (int i = 0; i < dsKhoi.size(); i++) {
                Khoi k = dsKhoi.get(i);
                this.addElement(k);
            }
        }
    }
}
