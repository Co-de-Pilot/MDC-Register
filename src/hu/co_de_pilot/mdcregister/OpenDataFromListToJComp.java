package hu.co_de_pilot.mdcregister;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class OpenDataFromListToJComp {
	
	private List<JComponent> listOfJC;
	private List<List<DesignTextField>> listOfAircraftDTF;
	private Object[] outputDatasSFCL;
	private Object[][] outputDatasAFCL;
	private String removeIconPath = "resources\\icons\\remove.png";
	
	public OpenDataFromListToJComp(InsertSingleFlightClearance panelInsertSFCL, SingleFlightClearance actualSFCL,
			InsertAnnualFlightClearance panelInsertAFCL, AnnualFlightClearance actualAFCL) {
		
		if (panelInsertAFCL == null && actualAFCL == null) {
			listOfJC = panelInsertSFCL.getListOfJC();
			outputDatasSFCL = actualSFCL.getDatasInArray();
		} else {
			listOfJC = panelInsertAFCL.getListOfJC();
			listOfAircraftDTF = panelInsertAFCL.getListOfAircraftDTF();
			outputDatasAFCL = actualAFCL.getDatasInArray();			
		}
		for (int i = 0; i < listOfJC.size(); i++) {
			if (listOfJC.get(i) instanceof JTextField) {
				JTextField actualJTF = (JTextField) listOfJC.get(i);
				if (listOfJC.get(i).getName().equals("Megjegyzés")) {
					if (panelInsertAFCL == null && actualAFCL == null) {
						actualJTF.setText(actualSFCL.getRemarks());						
					} else {
						actualJTF.setText(actualAFCL.getRemarks());												
					}
				} else {
					if (panelInsertAFCL == null && actualAFCL == null) {
						actualJTF.setText((String) outputDatasSFCL[i]);
					} else {
						actualJTF.setText((String) outputDatasAFCL[1][i]);
					}
				}
			} else if (listOfJC.get(i) instanceof JComboBox) {
				JComboBox<String> actualJCB = (JComboBox<String>) listOfJC.get(i);
				actualJCB.setSelectedItem((String) outputDatasAFCL[1][i]);
			} else if (listOfJC.get(i) instanceof JCheckBox) {
				JCheckBox actualJCHB = (JCheckBox) listOfJC.get(i);
				if (outputDatasAFCL[1][i].equals("Igen")) {
					actualJCHB.setSelected(true);
				} else if (outputDatasAFCL[1][i].equals("Nem")) {
					actualJCHB.setSelected(false);
				}
			}
		}
		
		if (listOfAircraftDTF != null) {
			for (int i = 0; i < actualAFCL.getListOfAircrafts().size(); i++) {
				List<DesignTextField> actualListOfDTF = listOfAircraftDTF.get(i);
				actualListOfDTF.get(0).setText(actualAFCL.getListOfAircrafts().get(i).getTypeOfAircraft());
				actualListOfDTF.get(0).setEnabled(true);
				actualListOfDTF.get(1).setText(actualAFCL.getListOfAircrafts().get(i).getRegistrationOfAircraft());
				actualListOfDTF.get(1).setEnabled(true);
				actualListOfDTF.get(2).setText(actualAFCL.getListOfAircrafts().get(i).getRadioCallSign());
				actualListOfDTF.get(2).setEnabled(true);
				new SettingStatusOfAircraftListButtons(false, actualAFCL.getListOfAircrafts().size(),
						panelInsertAFCL);
			}
		}
	}

}
