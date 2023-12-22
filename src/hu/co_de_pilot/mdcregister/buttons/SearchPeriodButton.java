package hu.co_de_pilot.mdcregister.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hu.co_de_pilot.mdcregister.DesignTextField;
import hu.co_de_pilot.mdcregister.PeriodFilter;
import hu.co_de_pilot.mdcregister.RefillTableFromList;
import hu.co_de_pilot.mdcregister.SearchAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.SearchSingleFlightClearance;

public class SearchPeriodButton extends DesignButton implements ActionListener {
	
	public SearchAnnualFlightClearance panelSearchAFCL;
	public SearchSingleFlightClearance panelSearchSFCL;
	private DesignTextField dateFromJTF;
	private DesignTextField dateUntilJTF;
	private DefaultTableModel tableModel;
	private int columnNumber;

	
	public SearchPeriodButton(String text, String name,
			SearchAnnualFlightClearance panelSearchAFCL,
			SearchSingleFlightClearance panelSearchSFCL) {
		super(text, name);
		this.panelSearchAFCL = panelSearchAFCL;
		this.panelSearchSFCL = panelSearchSFCL;
		this.addActionListener(this);
		if (panelSearchAFCL != null) {
			this.dateFromJTF = panelSearchAFCL.getPeriodfromAFCL();
			this.dateUntilJTF = panelSearchAFCL.getPerioduntilAFCL();
			this.tableModel = panelSearchAFCL.getAnnualFlightClearanceTableModel();
			this.columnNumber = panelSearchAFCL.getAnnualFlightClearanceTableColumnNames().length;
		} else {
			this.dateFromJTF = panelSearchSFCL.getPeriodfromSFCL();
			this.dateUntilJTF = panelSearchSFCL.getPerioduntilSFCL();
			this.tableModel = panelSearchSFCL.getSingleFlightClearanceTableModel();
			this.columnNumber = panelSearchSFCL.getSingleFlightClearanceTableColumnNames().length;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (panelSearchAFCL != null) {
			panelSearchAFCL.setListOfFilteredAFCL(panelSearchAFCL.getListOfAFCL()
					.stream()
					.filter(new PeriodFilter(LocalDate.parse(dateFromJTF.getText()),
							LocalDate.parse(dateUntilJTF.getText())))
					.toList());
			new RefillTableFromList(tableModel, columnNumber, null, panelSearchAFCL.getListOfFilteredAFCL());
		} else {
			panelSearchSFCL.setListOfFilteredSFCL(panelSearchSFCL.getListOfSFCL()
					.stream()
					.filter(new PeriodFilter(LocalDate.parse(dateFromJTF.getText()),
							LocalDate.parse(dateUntilJTF.getText())))
					.toList());
			new RefillTableFromList(tableModel, columnNumber, panelSearchSFCL.getListOfFilteredSFCL(), null);
		}
	}

}
