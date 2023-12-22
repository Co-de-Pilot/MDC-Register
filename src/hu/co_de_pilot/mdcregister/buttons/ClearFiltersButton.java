package hu.co_de_pilot.mdcregister.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hu.co_de_pilot.mdcregister.RefillTableFromList;
import hu.co_de_pilot.mdcregister.SearchAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.SearchSingleFlightClearance;

public class ClearFiltersButton extends DesignButton implements ActionListener {
	
	public SearchAnnualFlightClearance panelSearchAFCL;
	public SearchSingleFlightClearance panelSearchSFCL;
	private List<JComponent> listOfJC;
	private DefaultTableModel tableModel;
	private int columnNumber;

	
	public ClearFiltersButton(String text, String name,
			SearchAnnualFlightClearance panelSearchAFCL,
			SearchSingleFlightClearance panelSearchSFCL) {
		super(text, name);
		this.panelSearchAFCL = panelSearchAFCL;
		this.panelSearchSFCL = panelSearchSFCL;
		this.setToolTipText("Minden szűrő törlése, az engedélyek teljes listájának megjelenítése.");
		this.addActionListener(this);
		if (panelSearchAFCL != null) {
			this.listOfJC = panelSearchAFCL.getListOfJC();
			this.tableModel = panelSearchAFCL.getAnnualFlightClearanceTableModel();
			this.columnNumber = panelSearchAFCL.getAnnualFlightClearanceTableColumnNames().length;
		} else {
			this.listOfJC = panelSearchSFCL.getListOfJC();		
			this.tableModel = panelSearchSFCL.getSingleFlightClearanceTableModel();
			this.columnNumber = panelSearchSFCL.getSingleFlightClearanceTableColumnNames().length;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (int i = 0; i < listOfJC.size(); i++) {
			if (listOfJC.get(i) instanceof JTextField) {
				JTextField actualJTF = (JTextField) listOfJC.get(i);
				actualJTF.setText("");
			} else if (listOfJC.get(i) instanceof JCheckBox) {
				JCheckBox actualJCHB = (JCheckBox) listOfJC.get(i);
				actualJCHB.setSelected(false);
			}
		}
		if (panelSearchAFCL != null) {
			panelSearchAFCL.setListOfFilteredAFCL(null);
			new RefillTableFromList(tableModel, columnNumber, null, panelSearchAFCL.getListOfAFCL());
		} else {
			panelSearchSFCL.setListOfFilteredSFCL(null);
			new RefillTableFromList(tableModel, columnNumber, panelSearchSFCL.getListOfSFCL(), null);
		}
	}

}
