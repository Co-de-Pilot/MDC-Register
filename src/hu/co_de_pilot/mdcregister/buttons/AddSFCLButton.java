package hu.co_de_pilot.mdcregister.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import hu.co_de_pilot.mdcregister.InsertSingleFlightClearance;
import hu.co_de_pilot.mdcregister.PopupMessages;
import hu.co_de_pilot.mdcregister.RefillTableFromList;
import hu.co_de_pilot.mdcregister.SearchSingleFlightClearance;
import hu.co_de_pilot.mdcregister.SingleFlightClearance;
import hu.co_de_pilot.mdcregister.AdditionalJCVisibilityAndButtonText;
import hu.co_de_pilot.mdcregister.CollectDatasFromJComp;
import hu.co_de_pilot.mdcregister.createPDFFilePath;

public class AddSFCLButton extends DesignButton implements ActionListener {
	
	public InsertSingleFlightClearance panelInsertSFCL;
	public SearchSingleFlightClearance panelSearchSFCL;
	private SingleFlightClearance actualSFCL;
	private List<JComponent> listOfJC;
	private List<JComponent> listOfMandatoryJC;

	
	public AddSFCLButton(String text, String name, JButton button,
			SearchSingleFlightClearance panelSearchSFCL,
			InsertSingleFlightClearance panelInsertSFCL) {
		super(text, name);
		this.listOfJC = panelInsertSFCL.getListOfJC();
		this.listOfMandatoryJC = panelInsertSFCL.getListOfMandatoryJC();
		this.panelSearchSFCL = panelSearchSFCL;
		this.panelInsertSFCL = panelInsertSFCL;
		this.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (new hu.co_de_pilot.mdcregister.IsJComponentsEmpty(listOfMandatoryJC, null).isEmpty()) { // A kötelező mezők nincsenek kitöltve
			JOptionPane.showMessageDialog(null,
					"Az adatok rögzítéséhez előbb töltse ki a *-gal jelölt kötelező mezőket!", "Adatok rögzítése",
					JOptionPane.OK_OPTION);
		} else {
			
			String[] inputDatas = new CollectDatasFromJComp(listOfJC).getInputDatas();
			actualSFCL = new SingleFlightClearance(inputDatas[0], inputDatas[1], inputDatas[2], inputDatas[3],
					inputDatas[4], LocalDate.parse(inputDatas[5]), inputDatas[6], inputDatas[7], inputDatas[8],
					inputDatas[10], panelInsertSFCL, panelSearchSFCL);
			
			if (super.getText().equals("MÓDOSÍTÁS")) {
				if (!panelInsertSFCL.getIsModifierSFCL().isSelected()) {
					panelSearchSFCL.getListOfSFCL().remove(panelInsertSFCL.getActualSFCL());
					int confirm = PopupMessages.showConfirmMessage(null,
							"Kívánja módosítani a PDF fájl elérési helyét?", "PDF elérési hely megadása");
					if (confirm == JOptionPane.YES_OPTION) {
						new createPDFFilePath(actualSFCL, null);
					}
				} else {
					panelInsertSFCL.getActualSFCL().setModified(true);
					new createPDFFilePath(actualSFCL, null);
				}
				actualSFCL.setOriginRegNumberOfMFA(panelInsertSFCL.getOriginRegNumberOfMFA().getText());
				actualSFCL.setModifier(panelInsertSFCL.getIsModifierSFCL().isSelected());
				if (!panelInsertSFCL.getDateOfRealFlight().getText().equals("")) {
					actualSFCL.setDateOfRealflight(LocalDate.parse(panelInsertSFCL.getDateOfRealFlight().getText()));
				}
			} else {
				new createPDFFilePath(actualSFCL, null);
			}
			panelSearchSFCL.getListOfSFCL().add(actualSFCL);
			new AdditionalJCVisibilityAndButtonText(false, panelInsertSFCL, null);
			new RefillTableFromList(panelSearchSFCL.getSingleFlightClearanceTableModel(),
					panelSearchSFCL.getSingleFlightClearanceTableColumnNames().length, panelSearchSFCL.getListOfSFCL(), null);
		}

	}
	
}
