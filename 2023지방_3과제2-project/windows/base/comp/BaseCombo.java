package base.comp;

import java.util.Vector;

import javax.swing.JComboBox;

public class BaseCombo extends JComboBox<String>{

	public BaseCombo(String...val) {
		// TODO Auto-generated constructor stub
		for (String vals : val) {
		
			super.addItem(vals);
		}
	}

	public BaseCombo dataCombo(Vector<Vector<String>> nation) {
		// TODO Auto-generated method stub
		for (Vector<String> vals : nation) {
			super.addItem(vals.get(0));
		}
		
		return this;
	}


}
