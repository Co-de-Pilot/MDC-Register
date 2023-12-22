package hu.co_de_pilot.mdcregister;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class CollectDatasFromJComp {
	
	private String[] inputDatas;
	
	public CollectDatasFromJComp(List<JComponent> listOfJC) {
		
		inputDatas = new String[listOfJC.size()];
		for (int i = 0; i < listOfJC.size(); i++) {
			if (listOfJC.get(i) instanceof JTextField) {
				JTextField actualJTF = (JTextField) listOfJC.get(i);
				inputDatas[i] = actualJTF.getText();
				actualJTF.setText("");
			} else if (listOfJC.get(i) instanceof JComboBox) {
				JComboBox<String> actualJCB = (JComboBox<String>) listOfJC.get(i);
				inputDatas[i] = (String) actualJCB.getSelectedItem();
				actualJCB.setSelectedIndex(0);
			} else if (listOfJC.get(i) instanceof JCheckBox) {
				JCheckBox actualJCB = (JCheckBox) listOfJC.get(i);
				inputDatas[i] = (boolean) actualJCB.isSelected() ? "Igen" : "Nem";
				actualJCB.setSelected(true);
			}
		}
	}

	public String[] getInputDatas() {
		return inputDatas;
	}
	
	

}
