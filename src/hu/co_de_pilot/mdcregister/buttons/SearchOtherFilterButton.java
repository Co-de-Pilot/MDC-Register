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

import hu.co_de_pilot.mdcregister.DesignCheckBox;
import hu.co_de_pilot.mdcregister.DesignTextField;
import hu.co_de_pilot.mdcregister.OtherFilter;
import hu.co_de_pilot.mdcregister.PeriodFilter;
import hu.co_de_pilot.mdcregister.RefillTableFromList;
import hu.co_de_pilot.mdcregister.SearchAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.SearchSingleFlightClearance;

public class SearchOtherFilterButton extends DesignButton implements ActionListener {
	
	public SearchAnnualFlightClearance panelSearchAFCL;
	public SearchSingleFlightClearance panelSearchSFCL;
	private DesignCheckBox isApprovedJCHB;
	private DesignCheckBox isActiveJCHB;
	private DesignCheckBox isModifiedJCHB;
	private DesignCheckBox isFlownAwayJCHB;
	private DefaultTableModel tableModel;
	private int columnNumber;

	
	public SearchOtherFilterButton(String text, String name,
			SearchAnnualFlightClearance panelSearchAFCL,
			SearchSingleFlightClearance panelSearchSFCL) {
		super(text, name);
		this.panelSearchAFCL = panelSearchAFCL;
		this.panelSearchSFCL = panelSearchSFCL;
		if (panelSearchAFCL != null) {
			this.isApprovedJCHB = panelSearchAFCL.getIsApprovedAFCL();
			this.isActiveJCHB = panelSearchAFCL.getIsActiveAFCL();
			this.isModifiedJCHB = panelSearchAFCL.getIsModifiedAFCL();
			this.tableModel = panelSearchAFCL.getAnnualFlightClearanceTableModel();
			this.columnNumber = panelSearchAFCL.getAnnualFlightClearanceTableColumnNames().length;
		} else {
			this.isApprovedJCHB = panelSearchSFCL.getIsApprovedSFCL();
			this.isActiveJCHB = panelSearchSFCL.getIsActiveSFCL();
			this.isModifiedJCHB = panelSearchSFCL.getIsModifiedSFCL();
			this.isFlownAwayJCHB = panelSearchSFCL.getIsFlownAwaySFCL();
			this.tableModel = panelSearchSFCL.getSingleFlightClearanceTableModel();
			this.columnNumber = panelSearchSFCL.getSingleFlightClearanceTableColumnNames().length;
		}
		this.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (panelSearchAFCL != null) {
			panelSearchAFCL.setListOfFilteredAFCL(panelSearchAFCL.getListOfAFCL()
					.stream()
					.filter(new OtherFilter("annual", isApprovedJCHB.isSelected(),
							isActiveJCHB.isSelected(), isModifiedJCHB.isSelected()))
					.toList());
			new RefillTableFromList(tableModel, columnNumber, null, panelSearchAFCL.getListOfFilteredAFCL());
		} else {
			panelSearchSFCL.setListOfFilteredSFCL(panelSearchSFCL.getListOfSFCL()
					.stream()
					.filter(new OtherFilter("", isApprovedJCHB.isSelected(), isActiveJCHB.isSelected(),
							isModifiedJCHB.isSelected(), isFlownAwayJCHB.isSelected()))
					.toList());
			new RefillTableFromList(tableModel, columnNumber, panelSearchSFCL.getListOfFilteredSFCL(), null);
		}
	}

}
