package hu.co_de_pilot.mdcregister;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;

import hu.co_de_pilot.mdcregister.buttons.TableButtonSFCL;

public class SingleFlightClearance extends FlightClearance implements Serializable {

	// Boolean-eket megnézni, hogy mehetnek-e a FlightClearance-be.
	// Lerepülési részletadatok bevezetése?
	private transient InsertSingleFlightClearance panelInsertSFCL;
	private transient SearchSingleFlightClearance panelSearchSFCL;

	private final String registrationOfAircraft;
	private final String typeOfAircraft;
	private final String radioCallSign;
	private final String departureAerodrome;
	private final String destinationAerodrome;
	private final String purposeOfFlight;
	private String remarks;
	private String originRegNumberOfMFA;
	private boolean modifier;
	private LocalDate dateOfRealflight;
	
	private String clearancePDFFilePath;
	private final transient TableButtonSFCL editSFCLJB;
	private final transient TableButtonSFCL deleteSFCLJB;
	private final transient TableButtonSFCL openSFCLJB;
	private final transient TableButtonSFCL infoSFCLJB;
	private final transient TableButtonSFCL flownSFCLJB;
	private final static transient long validDays = 3L;
	private final static transient String normalEditIconPath = "resources\\icons\\edit_icon.png";
	private final static transient String pressedEditIconPath = "resources\\icons\\edit_icon_pressed.png";
	private final static transient String normalDeleteIconPath = "resources\\icons\\delete_icon.png";
	private final static transient String pressedDeleteIconPath = "resources\\icons\\delete_icon_pressed.png";
	private final static transient String normalOpenIconPath = "resources\\icons\\open_icon.png";
	private final static transient String pressedOpenIconPath = "resources\\icons\\open_icon_pressed.png";
	private final static transient String normalInfoIconPath = "resources\\icons\\info_icon.png";
	private final static transient String pressedInfoIconPath = "resources\\icons\\info_icon_pressed.png";
	private final static transient String normalFlownIconPath = "resources\\icons\\flown_icon.png";
	private final static transient String pressedFlownIconPath = "resources\\icons\\flown_icon_pressed.png";

	public SingleFlightClearance(String regNumberOfMFA, String stateOfRegistry,
			InsertSingleFlightClearance panelInsertSFCL, SearchSingleFlightClearance panelSearchSFCL,
			String registrationOfAircraft, String typeOfAircraft, String radioCallSign, LocalDate dateValidFrom,
			String departureAerodrome, String destinationAerodrome, String purposeOfFlight, boolean approved,
			boolean active, boolean modified, boolean flownAway, String remarks, String originRegNumberOfMFA,
			boolean modifier, LocalDate dateOfRealflight, String clearancePDFFilePath) {
		super(regNumberOfMFA, stateOfRegistry, dateValidFrom, dateValidFrom.plusDays(validDays),
				approved, active, modified, flownAway);
		this.panelInsertSFCL = panelInsertSFCL;
		this.panelSearchSFCL = panelSearchSFCL;
		this.registrationOfAircraft = registrationOfAircraft;
		this.typeOfAircraft = typeOfAircraft;
		this.radioCallSign = radioCallSign;
		this.departureAerodrome = departureAerodrome;
		this.destinationAerodrome = destinationAerodrome;
		this.purposeOfFlight = purposeOfFlight;
		this.remarks = remarks;
		this.originRegNumberOfMFA = originRegNumberOfMFA;
		this.modifier = modifier;
		this.dateOfRealflight = dateOfRealflight;
		this.clearancePDFFilePath = clearancePDFFilePath;
		this.editSFCLJB = new TableButtonSFCL("edit", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
						.getColumnCount() - 5,
				normalEditIconPath, pressedEditIconPath, this);
		this.deleteSFCLJB = new TableButtonSFCL("deactivate", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
						.getColumnCount() - 4,
				normalDeleteIconPath, pressedDeleteIconPath, this);
		this.openSFCLJB = new TableButtonSFCL("open", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
						.getColumnCount() - 3,
				normalOpenIconPath, pressedOpenIconPath, this);
		this.infoSFCLJB = new TableButtonSFCL("info", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
				.getColumnCount() - 2,
				normalInfoIconPath, pressedInfoIconPath, this);
		this.flownSFCLJB = new TableButtonSFCL("flown", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
				.getColumnCount() - 1,
				normalFlownIconPath, pressedFlownIconPath, this);
	}

	public SingleFlightClearance(String regNumberOfMFA, String stateOfRegistry, String registrationOfAircraft,
			String typeOfAircraft, String radioCallSign, LocalDate dateValidFrom, String departureAerodrome,
			String destinationAerodrome, String purposeOfFlight, String remarks, InsertSingleFlightClearance panelInsertSFCL,
			SearchSingleFlightClearance panelSearchSFCL) {
		super(regNumberOfMFA, stateOfRegistry, dateValidFrom, dateValidFrom.plusDays(validDays),
				panelInsertSFCL.getIsApprovedSFCL().isSelected(), true, false, false);
		this.registrationOfAircraft = registrationOfAircraft;
		this.typeOfAircraft = typeOfAircraft;
		this.radioCallSign = radioCallSign;
		this.departureAerodrome = departureAerodrome;
		this.destinationAerodrome = destinationAerodrome;
		this.purposeOfFlight = purposeOfFlight;
		this.remarks = remarks;
		this.panelInsertSFCL = panelInsertSFCL;
		this.panelSearchSFCL = panelSearchSFCL;
		this.editSFCLJB = new TableButtonSFCL("edit", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
						.getColumnCount() - 5,
				normalEditIconPath, pressedEditIconPath, this);
		this.deleteSFCLJB = new TableButtonSFCL("deactivate", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
						.getColumnCount() - 4,
				normalDeleteIconPath, pressedDeleteIconPath, this);
		this.openSFCLJB = new TableButtonSFCL("open", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
						.getColumnCount() - 3,
				normalOpenIconPath, pressedOpenIconPath, this);
		this.infoSFCLJB = new TableButtonSFCL("info", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
				.getColumnCount() - 2,
				normalInfoIconPath, pressedInfoIconPath, this);
		this.flownSFCLJB = new TableButtonSFCL("flown", panelInsertSFCL, panelSearchSFCL,
				panelSearchSFCL.getSingleFlightClearanceTable()
				.getColumnCount() - 1,
				normalFlownIconPath, pressedFlownIconPath, this);
	}

	public String getRegistrationOfAircraft() {
		return registrationOfAircraft;
	}

	public String getTypeOfAircraft() {
		return typeOfAircraft;
	}

	public String getRadioCallSign() {
		return radioCallSign;
	}

	public String getDepartureAerodrome() {
		return departureAerodrome;
	}

	public String getDestinationAerodrome() {
		return destinationAerodrome;
	}

	public String getPurposeOfFlight() {
		return purposeOfFlight;
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

	public LocalDate getDateOfRealflight() {
		return dateOfRealflight;
	}

	public void setDateOfRealflight(LocalDate dateOfRealflight) {
		this.dateOfRealflight = dateOfRealflight;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SingleFlightClearance [KKM nyt.szám: ");
		builder.append(super.getRegNumberOfMFA());
		builder.append(", Regisztráló oszág: ");
		builder.append(super.getStateOfRegistry());
		builder.append("]");
		return builder.toString();
	}

	public Object[] getDatasInArray() {
		Object[] datasOfSFCL = new Object[panelSearchSFCL.getSingleFlightClearanceTable().getColumnCount()];
		datasOfSFCL[0] = super.getRegNumberOfMFA();
		datasOfSFCL[1] = super.getStateOfRegistry();
		datasOfSFCL[2] = registrationOfAircraft;
		datasOfSFCL[3] = typeOfAircraft;
		datasOfSFCL[4] = radioCallSign;
		datasOfSFCL[5] = super.getDateValidFrom().toString();
		datasOfSFCL[6] = departureAerodrome;
		datasOfSFCL[7] = destinationAerodrome;
		datasOfSFCL[8] = purposeOfFlight;
		datasOfSFCL[9] = super.isApproved() ? "Igen" : "Nem";
		datasOfSFCL[10] = super.isActive() ? "Igen" : "Nem";
		datasOfSFCL[11] = super.isModified() ? "Igen" : "Nem";
		datasOfSFCL[12] = super.isFlownAway() ? "Igen" : "Nem";
		datasOfSFCL[13] = editSFCLJB;
		datasOfSFCL[14] = deleteSFCLJB;
		datasOfSFCL[15] = openSFCLJB;
		datasOfSFCL[16] = infoSFCLJB;
		datasOfSFCL[17] = flownSFCLJB;
		return datasOfSFCL;
	}

	public Object[] getAdditonalDatasInArray() {
		Object[] datasOfSFCL = new Object[panelSearchSFCL.getAdditionalInfoTable().getColumnCount()];
		datasOfSFCL[0] = super.getRegNumberOfMFA();
		datasOfSFCL[1] = remarks;
		datasOfSFCL[2] = (originRegNumberOfMFA == null) ? "-" : originRegNumberOfMFA;
		datasOfSFCL[3] = modifier ? "Igen" : "Nem";
		datasOfSFCL[4] = (dateOfRealflight == null) ? "-" : dateOfRealflight.toString();
		return datasOfSFCL;
	}
	
	public List<String> getListOfPrintDailyReport() {
		List<String> listOfPrintDatas = new ArrayList<>();
		listOfPrintDatas.add(super.getRegNumberOfMFA());
		listOfPrintDatas.add(super.getStateOfRegistry());
		listOfPrintDatas.add(radioCallSign);
		listOfPrintDatas.add(super.getDateValidFrom().toString());
		listOfPrintDatas.add(departureAerodrome);
		listOfPrintDatas.add(destinationAerodrome);
		listOfPrintDatas.add(purposeOfFlight);
		listOfPrintDatas.add(super.isApproved() ? "Igen" : "Nem");
		return listOfPrintDatas;
	}
	
	public List<String> getListOfPrintFullReport() {
		List<String> listOfPrintDatas = new ArrayList<>();
		listOfPrintDatas.add(super.getRegNumberOfMFA());
		listOfPrintDatas.add(super.getStateOfRegistry());
		listOfPrintDatas.add(registrationOfAircraft);
		listOfPrintDatas.add(typeOfAircraft);
		listOfPrintDatas.add(radioCallSign);
		listOfPrintDatas.add(super.getDateValidFrom().toString());
		listOfPrintDatas.add(departureAerodrome);
		listOfPrintDatas.add(destinationAerodrome);
		listOfPrintDatas.add(purposeOfFlight);
		listOfPrintDatas.add(super.isApproved() ? "Igen" : "Nem");
		listOfPrintDatas.add(super.isActive() ? "Igen" : "Nem");
		listOfPrintDatas.add(super.isModified() ? "Igen" : "Nem");
		listOfPrintDatas.add(super.isFlownAway() ? "Igen" : "Nem");
		listOfPrintDatas.add((dateOfRealflight == null) ? "-" : dateOfRealflight.toString());
		listOfPrintDatas.add(remarks);
		return listOfPrintDatas;
	}
	
}
