package hu.co_de_pilot.mdcregister;

import javax.swing.JComponent;

public class AdditionalJCVisibilityAndButtonText {
	
	public AdditionalJCVisibilityAndButtonText(boolean isVisible, InsertSingleFlightClearance panelInsertSFCL,
			InsertAnnualFlightClearance panelInsertAFCL) {
		
		if (panelInsertAFCL == null) {
			for (JComponent jComponent : panelInsertSFCL.getListOfAdditonalJC()) {
				jComponent.setVisible(isVisible);
			}
			if (isVisible) {
				panelInsertSFCL.getInsertSFCLButton().setText("MÓDOSÍTÁS");
			} else {
				panelInsertSFCL.getInsertSFCLButton().setText("RÖGZÍTÉS");
			}			
		} else {
			for (JComponent jComponent : panelInsertAFCL.getListOfAdditonalJC()) {
				jComponent.setVisible(isVisible);
			}
			if (isVisible) {
				panelInsertAFCL.getInsertAFCLButton().setText("MÓDOSÍTÁS");
			} else {
				panelInsertAFCL.getInsertAFCLButton().setText("RÖGZÍTÉS");
			}			
		}
		
		
	}
	

}
