package hu.co_de_pilot.mdcregister;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;

import hu.co_de_pilot.mdcregister.buttons.TableButtonAFCL;
import hu.co_de_pilot.mdcregister.buttons.TableButtonSFCL;

public class AnnualFlightClearance extends FlightClearance implements Serializable {

	private transient InsertAnnualFlightClearance panelInsertAFCL;
	private transient SearchAnnualFlightClearance panelSearchAFCL;

	private List<Aircraft> listOfAircrafts;
	private String remarks;
	private String originRegNumberOfMFA;
	private boolean modifier;
	
	private String clearancePDFFilePath;
	private final transient TableButtonAFCL editSFCLJB;
	private final transient TableButtonAFCL deleteSFCLJB;
	private final transient TableButtonAFCL openSFCLJB;
	private final static transient String normalEditIconPath = "resources\\icons\\edit_icon.png";
	private final static transient String pressedEditIconPath = "resources\\icons\\edit_icon_pressed.png";
	private final static transient String normalDeleteIconPath = "resources\\icons\\delete_icon.png";
	private final static transient String pressedDeleteIconPath = "resources\\icons\\delete_icon_pressed.png";
	private final static transient String normalOpenIconPath = "resources\\icons\\open_icon.png";
	private final static transient String pressedOpenIconPath = "resources\\icons\\open_icon_pressed.png";

	public AnnualFlightClearance(String regNumberOfMFA, String stateOfRegistry,
			InsertAnnualFlightClearance panelInsertAFCL, SearchAnnualFlightClearance panelSearchAFCL,
			List<Aircraft> listOfAircrafts, LocalDate dateValidFrom, LocalDate dateValidUntil,
			boolean approved, boolean active, boolean modified, String remarks, String originRegNumberOfMFA,
			boolean modifier, String clearancePDFFilePath) {
		super(regNumberOfMFA, stateOfRegistry, dateValidFrom, dateValidUntil, approved, active, modified, false);
		this.panelInsertAFCL = panelInsertAFCL;
		this.panelSearchAFCL = panelSearchAFCL;
		this.listOfAircrafts = listOfAircrafts;
		this.remarks = remarks;
		this.originRegNumberOfMFA = originRegNumberOfMFA;
		this.modifier = modifier;
		this.clearancePDFFilePath = clearancePDFFilePath;
		this.editSFCLJB = new TableButtonAFCL("edit", panelInsertAFCL, panelSearchAFCL,
				panelSearchAFCL.getAnnualFlightClearanceTable()
						.getColumnCount() - 3,
				normalEditIconPath, pressedEditIconPath, this);
		this.deleteSFCLJB = new TableButtonAFCL("deactivate", panelInsertAFCL, panelSearchAFCL,
				panelSearchAFCL.getAnnualFlightClearanceTable()
						.getColumnCount() - 2,
				normalDeleteIconPath, pressedDeleteIconPath, this);
		this.openSFCLJB = new TableButtonAFCL("open", panelInsertAFCL, panelSearchAFCL,
				panelSearchAFCL.getAnnualFlightClearanceTable()
						.getColumnCount() - 1,
				normalOpenIconPath, pressedOpenIconPath, this);
	}

	public AnnualFlightClearance(String regNumberOfMFA, String stateOfRegistry, List<Aircraft> listOfAircrafts,
			LocalDate dateValidFrom, LocalDate dateValidUntil, String remarks, InsertAnnualFlightClearance panelInsertAFCL,
			SearchAnnualFlightClearance panelSearchAFCL) {
		super(regNumberOfMFA, stateOfRegistry, dateValidFrom, dateValidUntil,
				panelInsertAFCL.getIsApprovedAFCL().isSelected(), true, false, false);
		this.listOfAircrafts = listOfAircrafts;
		this.remarks = remarks;
		this.modifier = false;
		this.panelInsertAFCL = panelInsertAFCL;
		this.panelSearchAFCL = panelSearchAFCL;
		this.editSFCLJB = new TableButtonAFCL("edit", panelInsertAFCL, panelSearchAFCL,
				panelSearchAFCL.getAnnualFlightClearanceTable()
						.getColumnCount() - 3,
				normalEditIconPath, pressedEditIconPath, this);
		this.deleteSFCLJB = new TableButtonAFCL("deactivate", panelInsertAFCL, panelSearchAFCL,
				panelSearchAFCL.getAnnualFlightClearanceTable()
						.getColumnCount() - 2,
				normalDeleteIconPath, pressedDeleteIconPath, this);
		this.openSFCLJB = new TableButtonAFCL("open", panelInsertAFCL, panelSearchAFCL,
				panelSearchAFCL.getAnnualFlightClearanceTable()
						.getColumnCount() - 1,
				normalOpenIconPath, pressedOpenIconPath, this);
	}
	
	public List<Aircraft> getListOfAircrafts() {
		return listOfAircrafts;
	}

	public void setListOfAircrafts(List<Aircraft> listOfAircrafts) {
		this.listOfAircrafts = listOfAircrafts;
	}

	public String getOriginRegNumberOfMFA() {
		return originRegNumberOfMFA;
	}

	public void setOriginRegNumberOfMFA(String originRegNumberOfMFA) {
		this.originRegNumberOfMFA = originRegNumberOfMFA;
	}

	public boolean isModifier() {
		return modifier;
	}

	public void setModifier(boolean modifier) {
		this.modifier = modifier;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getClearancePDFFilePath() {
		return clearancePDFFilePath;
	}

	public void setClearancePDFFilePath(String clearancePDFFilePath) {
		this.clearancePDFFilePath = clearancePDFFilePath;
	}

	public TableButtonAFCL getEditSFCLJB() {
		return editSFCLJB;
	}

	public TableButtonAFCL getDeleteSFCLJB() {
		return deleteSFCLJB;
	}

	public TableButtonAFCL getOpenSFCLJB() {
		return openSFCLJB;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnnualFlightClearance [KKM nyt.szám: ");
		builder.append(super.getRegNumberOfMFA());
		builder.append(", Regisztráló oszág: ");
		builder.append(super.getStateOfRegistry());
		builder.append("]");
		return builder.toString();
	}

	public Object[][] getDatasInArray() {
		Object[][] datasOfSFCL = new Object[listOfAircrafts.size()][panelSearchAFCL.getAnnualFlightClearanceTable().getColumnCount()];
		for (int i = 0; i < listOfAircrafts.size(); i++) {
			datasOfSFCL[i][0] = super.getRegNumberOfMFA();
			datasOfSFCL[i][1] = super.getStateOfRegistry();
			datasOfSFCL[i][2] = super.getDateValidFrom().toString();
			datasOfSFCL[i][3] = super.getDateValidUntil().toString();
			datasOfSFCL[i][4] = listOfAircrafts.get(i).getTypeOfAircraft();
			datasOfSFCL[i][5] = listOfAircrafts.get(i).getRegistrationOfAircraft();
			datasOfSFCL[i][6] = listOfAircrafts.get(i).getRadioCallSign();
			datasOfSFCL[i][7] = remarks;
			datasOfSFCL[i][8] = super.isApproved() ? "Igen" : "Nem";
			datasOfSFCL[i][9] = super.isActive() ? "Igen" : "Nem";
			datasOfSFCL[i][10] = super.isModified() ? "Igen" : "Nem";
			datasOfSFCL[i][11] = editSFCLJB;
			datasOfSFCL[i][12] = deleteSFCLJB;
			datasOfSFCL[i][13] = openSFCLJB;
		}
		return datasOfSFCL;
	}

	public List<String> getListOfPrintDailyReport() {
		List<String> listOfPrintDatas = new ArrayList<>();
		for (int i = 0; i < listOfAircrafts.size(); i++) {
			listOfPrintDatas.add(super.getRegNumberOfMFA());
			listOfPrintDatas.add(super.getStateOfRegistry());
			listOfPrintDatas.add(super.getDateValidFrom().toString());
			listOfPrintDatas.add(super.getDateValidUntil().toString());
			listOfPrintDatas.add(listOfAircrafts.get(i).getTypeOfAircraft());
			listOfPrintDatas.add(listOfAircrafts.get(i).getRegistrationOfAircraft());
			listOfPrintDatas.add(listOfAircrafts.get(i).getRadioCallSign());
			listOfPrintDatas.add(super.isApproved() ? "Igen" : "Nem");
		}
		return listOfPrintDatas;
	}
	
	public List<String> getListOfPrintFullReport() {
		List<String> listOfPrintDatas = new ArrayList<>();
		for (int i = 0; i < listOfAircrafts.size(); i++) {
			listOfPrintDatas.add(super.getRegNumberOfMFA());
			listOfPrintDatas.add(super.getStateOfRegistry());
			listOfPrintDatas.add(super.getDateValidFrom().toString());
			listOfPrintDatas.add(super.getDateValidUntil().toString());
			listOfPrintDatas.add(listOfAircrafts.get(i).getTypeOfAircraft());
			listOfPrintDatas.add(listOfAircrafts.get(i).getRegistrationOfAircraft());
			listOfPrintDatas.add(listOfAircrafts.get(i).getRadioCallSign());
			listOfPrintDatas.add(super.isApproved() ? "Igen" : "Nem");
			listOfPrintDatas.add(super.isActive() ? "Igen" : "Nem");
			listOfPrintDatas.add(super.isModified() ? "Igen" : "Nem");
			listOfPrintDatas.add(remarks);
		}
		return listOfPrintDatas;
	}
	
}
