package hu.co_de_pilot.mdcregister.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import hu.co_de_pilot.mdcregister.AdditionalJCVisibilityAndButtonText;
import hu.co_de_pilot.mdcregister.AnnualFlightClearance;
import hu.co_de_pilot.mdcregister.CollectAircraftDatasFromJComp;
import hu.co_de_pilot.mdcregister.DesignTextField;
import hu.co_de_pilot.mdcregister.InsertAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.IsJComponentsEmpty;
import hu.co_de_pilot.mdcregister.PopupMessages;
import hu.co_de_pilot.mdcregister.RefillTableFromList;
import hu.co_de_pilot.mdcregister.SearchAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.SettingStatusOfAircraftListButtons;
import hu.co_de_pilot.mdcregister.createPDFFilePath;
import hu.co_de_pilot.mdcregister.CollectDatasFromJComp;

public class AddAFCLButton extends DesignButton implements ActionListener {
	
	public InsertAnnualFlightClearance panelInsertAFCL;
	public SearchAnnualFlightClearance panelSearchAFCL;
	private AnnualFlightClearance actualAFCL;
	private List<JComponent> listOfJC;
	private List<List<DesignTextField>> listOfAircraftDTF;
	private List<JComponent> listOfMandatoryJC;

	
	public AddAFCLButton(String text, String name, JButton button,
			SearchAnnualFlightClearance panelSearchAFCL,
			InsertAnnualFlightClearance panelInsertAFCL) {
		super(text, name);
		this.listOfJC = panelInsertAFCL.getListOfJC();
		this.listOfAircraftDTF = panelInsertAFCL.getListOfAircraftDTF();
		this.listOfMandatoryJC = panelInsertAFCL.getListOfMandatoryJC();
		this.panelSearchAFCL = panelSearchAFCL;
		this.panelInsertAFCL = panelInsertAFCL;
		this.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		IsJComponentsEmpty isJComponentsEmpty = new IsJComponentsEmpty(listOfMandatoryJC, listOfAircraftDTF); 
		
		if (isJComponentsEmpty.isEmpty()) { // A kötelező mezők nincsenek kitöltve
			JOptionPane.showMessageDialog(null,
					"Az adatok rögzítéséhez előbb töltse ki a *-gal jelölt kötelező mezőket!", "Adatok rögzítése",
					JOptionPane.OK_OPTION);
		} else if (isJComponentsEmpty.getCompleteAircraftCounter() < 1) {
			JOptionPane.showMessageDialog(null,
					"Az engedély rögzítéséhez adja meg legalább egy repülőgép minden bekért adatát!", "Repülőgépek rögzítése",
					JOptionPane.OK_OPTION);
		} else if (isJComponentsEmpty.getIncompleteAircraftCounter() > 0) {
			JOptionPane.showMessageDialog(null,
					"Az engedély rögzítéséhez adja meg a repülőgép(ek) minden bekért adatát!", "Repülőgépek rögzítése",
					JOptionPane.OK_OPTION);		
		} else {
			
			String[] inputDatas = new CollectDatasFromJComp(listOfJC).getInputDatas();
			actualAFCL = new AnnualFlightClearance(inputDatas[0], inputDatas[1],
					new CollectAircraftDatasFromJComp(listOfAircraftDTF).getListOfAircrafts(), LocalDate.parse(inputDatas[2]),
					LocalDate.parse(inputDatas[3]), inputDatas[5], panelInsertAFCL, panelSearchAFCL);
			
			if (super.getText().equals("MÓDOSÍTÁS")) {
				if (!panelInsertAFCL.getIsModifierAFCL().isSelected()) {
					panelSearchAFCL.getListOfAFCL().remove(panelInsertAFCL.getActualAFCL());
					int confirm = PopupMessages.showConfirmMessage(null,
							"Kívánja módosítani a PDF fájl elérési helyét?", "PDF elérési hely megadása");
					if (confirm == JOptionPane.YES_OPTION) {
						new createPDFFilePath(null, actualAFCL);
					}
				} else {
					panelInsertAFCL.getActualAFCL().setModified(true);
					new createPDFFilePath(null, actualAFCL);
				}
				actualAFCL.setOriginRegNumberOfMFA(panelInsertAFCL.getOriginRegNumberOfMFA().getText());
				actualAFCL.setModifier(panelInsertAFCL.getIsModifierAFCL().isSelected());
			} else {
				new createPDFFilePath(null, actualAFCL);
			}
			panelSearchAFCL.getListOfAFCL().add(actualAFCL);
			new AdditionalJCVisibilityAndButtonText(false, null, panelInsertAFCL);
			new SettingStatusOfAircraftListButtons(true, listOfAircraftDTF.size(),
					panelInsertAFCL);

			new RefillTableFromList(panelSearchAFCL.getAnnualFlightClearanceTableModel(),
					panelSearchAFCL.getAnnualFlightClearanceTableColumnNames().length, null, panelSearchAFCL.getListOfAFCL());
		}

	}
	
}
