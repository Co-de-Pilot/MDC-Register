package hu.co_de_pilot.mdcregister.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hu.co_de_pilot.mdcregister.AdditionalJCVisibilityAndButtonText;
import hu.co_de_pilot.mdcregister.DesignTextField;
import hu.co_de_pilot.mdcregister.InsertAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.InsertSingleFlightClearance;
import hu.co_de_pilot.mdcregister.MainWindow;
import hu.co_de_pilot.mdcregister.SearchAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.SearchSingleFlightClearance;
import hu.co_de_pilot.mdcregister.SettingStatusOfAircraftListButtons;

public class CancelButton extends DesignButton implements ActionListener {

	private List<JComponent> listOfJC;
	private List<List<DesignTextField>> listOfAircraftDTF;
	private MainWindow mainWindow;
	
	public CancelButton(String text, String name, List<JComponent> listOfJC,
			List<List<DesignTextField>> listOfAircraftDTF, MainWindow mainWindow) {
		super(text, name);
		this.listOfJC = listOfJC;
		this.listOfAircraftDTF = listOfAircraftDTF;
		this.mainWindow = mainWindow;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < listOfJC.size(); i++) {
			if (listOfJC.get(i) instanceof JTextField) {
				JTextField actualJTF = (JTextField) listOfJC.get(i);
				actualJTF.setText("");
			} else if (listOfJC.get(i) instanceof JComboBox) {
				JComboBox<String> actualJCB = (JComboBox<String>) listOfJC.get(i);
				actualJCB.setSelectedIndex(0);
			} else if (listOfJC.get(i) instanceof JCheckBox) {
				JCheckBox actualJCB = (JCheckBox) listOfJC.get(i);
				actualJCB.setSelected(false);
			}
		}
		
		if (listOfAircraftDTF != null) {
			for (int i = 0; i < listOfAircraftDTF.size(); i++) {
				List<DesignTextField> actualListOfDTF = listOfAircraftDTF.get(i);
				if (actualListOfDTF.get(1).isEnabled()) {
					for (int j = 0; j < actualListOfDTF.size(); j++) {
						DesignTextField actualDTF = actualListOfDTF.get(j);
						actualDTF.setText("");
						actualDTF.setEnabled(false);
					}
				}
			}
		}
		
		new SettingStatusOfAircraftListButtons(true, listOfAircraftDTF.size(),
				mainWindow.getPanelInsertAFCL());
		
		if (mainWindow.getPanelInsertSFCL().getInsertSFCLButton().getText().equals("MÓDOSÍTÁS")) {
			new AdditionalJCVisibilityAndButtonText(false, mainWindow.getPanelInsertSFCL(), null);
			mainWindow.getPanelInsertSFCL().setVisible(false);
			mainWindow.getPanelSearchSFCL().setVisible(true);
		} else if (mainWindow.getPanelInsertAFCL().getInsertAFCLButton().getText().equals("MÓDOSÍTÁS")) {
			new AdditionalJCVisibilityAndButtonText(false, null, mainWindow.getPanelInsertAFCL());
			mainWindow.getPanelInsertAFCL().setVisible(false);
			mainWindow.getPanelSearchAFCL().setVisible(true);
		}

	}
	
}
