package hu.co_de_pilot.mdcregister;

import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class IsJComponentsEmpty {
	
	private boolean isEmpty = false;
	private int completeAircraftCounter = 0;
	private int incompleteAircraftCounter = 0;
	
	public IsJComponentsEmpty(List<JComponent> listOfMandatoryJC, List<List<DesignTextField>> listOfAircraftDTF) {
		
		int jCHBCounter = 0;
		int activeJCHBCounter = 0;
		for (int i = 0; i < listOfMandatoryJC.size(); i++) {
			if (listOfMandatoryJC.get(i) instanceof JTextField) {
				JTextField actualJTF = (JTextField) listOfMandatoryJC.get(i);
				if (actualJTF.getText()
						.equals("")) {
					isEmpty = true;
					break;
				}
			} else if (listOfMandatoryJC.get(i) instanceof JCheckBox) {
				JCheckBox actualJCHB = (JCheckBox) listOfMandatoryJC.get(i);
				jCHBCounter++;
				if (actualJCHB.isSelected()) {
					activeJCHBCounter++;
				}
			}
		}
		if (jCHBCounter > 0 && activeJCHBCounter == 0) {
			isEmpty = true;
		}
		
		int filledDTFCounter = 0;
		if (listOfAircraftDTF != null) {
			for (int i = 0; i < listOfAircraftDTF.size(); i++) {
				List<DesignTextField> actualListOfDTF = listOfAircraftDTF.get(i);
				if (actualListOfDTF.get(1).isEnabled()) {
					for (int j = 0; j < actualListOfDTF.size(); j++) {
						DesignTextField actualDTF = actualListOfDTF.get(j);
						if (!actualDTF.getText().equals("")) {
							filledDTFCounter++;
						}
					}
					if (filledDTFCounter < actualListOfDTF.size()) {
						incompleteAircraftCounter++;					
					} else {
						completeAircraftCounter++;
					}
					filledDTFCounter = 0;
				}
			}
		}
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}

	public int getCompleteAircraftCounter() {
		return completeAircraftCounter;
	}

	public int getIncompleteAircraftCounter() {
		return incompleteAircraftCounter;
	}
	
}
