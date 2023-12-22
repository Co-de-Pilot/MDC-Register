package hu.co_de_pilot.mdcregister;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import hu.co_de_pilot.mdcregister.buttons.AddSFCLButton;
import hu.co_de_pilot.mdcregister.buttons.CancelButton;
import hu.co_de_pilot.mdcregister.buttons.DesignButton;

public class InsertSingleFlightClearance extends JPanel {

	private MainWindow mainWindow;
	private SingleFlightClearance actualSFCL;

	private JLabel insertTitle;
	private DesignTextField regNumberOfMFA;
	private DesignLabel regNumberOfMFALabel;
	private DesignComboBox<String> stateOfRegistry;
	private DesignLabel stateOfRegistryLabel;
	private DesignTextField registrationOfAircraft;
	private DesignLabel registrationOfAircraftLabel;
	private DesignTextField typeOfAircraft;
	private DesignLabel typeOfAircraftLabel;
	private DesignTextField radioCallSign;
	private DesignLabel radioCallSignLabel;
	private DesignTextField dateOfInflight;
	private DesignLabel dateOfInflightLabel;
	private hu.co_de_pilot.datechooser.DateChooser dateOfInflightChooser;
	private DesignTextField dateOfRealFlight;
	private DesignLabel dateOfRealFlightLabel;
	private hu.co_de_pilot.datechooser.DateChooser dateOfRealFlightChooser;
	private DesignTextField departureAerodrome;
	private DesignLabel departureAerodromeLabel;
	private DesignTextField destinationAerodrome;
	private DesignLabel destinationAerodromeLabel;
	private DesignTextField purposeOfFlight;
	private DesignLabel purposeOfFlightLabel;
	private DesignCheckBox isApprovedSFCL;
	private DesignTextField remarks;
	private DesignLabel remarksLabel;
	private DesignTextField originRegNumberOfMFA;
	private DesignLabel originRegNumberOfMFALabel;
	private DesignCheckBox isModifierSFCL;
	private DesignButton insertSFCLButton;
	private DesignButton cancelSFCLButton;
	private List<JComponent> listOfJC = new ArrayList<>();
	private List<JComponent> listOfMandatoryJC = new ArrayList<>();
	private List<JComponent> listOfAdditonalJC = new ArrayList<>();
	private FinderTable depAerodromeTable;
	private FinderTable desAerodromeTable;
	private FinderTable aircraftTable;
	private DefaultTableModel depAerodromeTableModel;
	private DefaultTableModel desAerodromeTableModel;
	private DefaultTableModel aircraftTableModel;
	private JScrollPane depAerodromeTableScrollPane;
	private JScrollPane desAerodromeTableScrollPane;
	private JScrollPane aircraftTableScrollPane;
	private String aerodromesCsvPath = "resources\\aerodromes.csv";
	private String aircraftsCsvPath = "resources\\aircrafts.csv";

	public InsertSingleFlightClearance(MainWindow mainWindow) {

		this.mainWindow = mainWindow;

		
//		A kereső táblázatokra vonatkozó alapadatok
		String[] aerodromeTableColumnNames = { "ICAO kód", "Repülőtér megnevezése", "Ország" };
		String[] aircraftTableColumnNames = { "ICAO kód", "Gyártó", "Típus" };
		int[] tableColumnWidths = { 60, 250, 150 };

		
//		A repülőgép típus kereső táblázat
		aircraftTableModel = new DefaultTableModel(aircraftTableColumnNames, 0);
		aircraftTable = new FinderTable(aircraftTableColumnNames, tableColumnWidths, aircraftTableModel);
		TableRowSorter<DefaultTableModel> aircraftTableSorter = new TableRowSorter<DefaultTableModel>(
				aircraftTableModel);
		aircraftTable.setRowSorter(aircraftTableSorter);
		MouseAction mouseActionOfAircraftTable = new MouseAction(aircraftTable, aircraftTableModel);
		aircraftTable.addMouseListener(mouseActionOfAircraftTable);
		aircraftTableScrollPane = new DesignScrollPane(aircraftTable, null, 530, 60, 460, 390);
		this.add(aircraftTableScrollPane);

		
//		A repülőgép típus kereső táblázat feltöltése adatokkal
		ReadDataFromCSV scanAircrafts = new ReadDataFromCSV(aircraftsCsvPath, aircraftTable, aircraftTableModel);
		try {
			scanAircrafts.scanDatafromCSVtoTable();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Az adatok megnyitása nem sikerült. A megadott mappa nem érhető el. Kérje az adminisztrátor segítségét!",
					"Adatok megnyitása", JOptionPane.OK_OPTION);
			e.printStackTrace();
		}

		
//		Az indulási reptér kereső táblázat
		depAerodromeTableModel = new DefaultTableModel(aerodromeTableColumnNames, 0);
		depAerodromeTable = new FinderTable(aerodromeTableColumnNames, tableColumnWidths, depAerodromeTableModel);
		TableRowSorter<DefaultTableModel> depAerodromeTableSorter = new TableRowSorter<DefaultTableModel>(
				depAerodromeTableModel);
		depAerodromeTable.setRowSorter(depAerodromeTableSorter);
		MouseAction mouseActionOfDepAerodromeTable = new MouseAction(depAerodromeTable, depAerodromeTableModel);
		depAerodromeTable.addMouseListener(mouseActionOfDepAerodromeTable);
		depAerodromeTableScrollPane = new DesignScrollPane(depAerodromeTable, null, 530, 60, 460, 390);
		this.add(depAerodromeTableScrollPane);

		
//		Az indulási reptér kereső táblázat feltöltése adatokkal
		ReadDataFromCSV scanDepAerodromes = new ReadDataFromCSV(aerodromesCsvPath, depAerodromeTable,
				depAerodromeTableModel);
		try {
			scanDepAerodromes.scanDatafromCSVtoTable();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Az adatok megnyitása nem sikerült. A megadott mappa nem érhető el. Kérje az adminisztrátor segítségét!",
					"Adatok megnyitása", JOptionPane.OK_OPTION);
			e.printStackTrace();
		}

		
//		Az érkezési reptér kereső táblázat
		desAerodromeTableModel = new DefaultTableModel(aerodromeTableColumnNames, 0);
		desAerodromeTable = new FinderTable(aerodromeTableColumnNames, tableColumnWidths, desAerodromeTableModel);
		TableRowSorter<DefaultTableModel> desAerodromeTableSorter = new TableRowSorter<DefaultTableModel>(
				desAerodromeTableModel);
		desAerodromeTable.setRowSorter(desAerodromeTableSorter);
		MouseAction mouseActionOfDesAerodromeTable = new MouseAction(desAerodromeTable, desAerodromeTableModel);
		desAerodromeTable.addMouseListener(mouseActionOfDesAerodromeTable);
		desAerodromeTableScrollPane = new DesignScrollPane(desAerodromeTable, null, 530, 60, 460, 390);
		this.add(desAerodromeTableScrollPane);

		
//		Az érkezési reptér kereső táblázat feltöltése adatokkal
		ReadDataFromCSV scanDesAerodromes = new ReadDataFromCSV(aerodromesCsvPath, desAerodromeTable,
				desAerodromeTableModel);
		try {
			scanDesAerodromes.scanDatafromCSVtoTable();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Az adatok megnyitása nem sikerült. A megadott mappa nem érhető el. Kérje az adminisztrátor segítségét!",
					"Adatok megnyitása", JOptionPane.OK_OPTION);
			e.printStackTrace();
		}

		
//		A panel címének beállítása
		insertTitle = new JLabel("EGYSZERI BEREPÜLÉSI ENGEDÉLY RÖGZÍTÉSE");
		insertTitle.setBounds(0, 10, 1000, 40);
		insertTitle.setFont(mainWindow.getFontofButtonAndTitle());
		insertTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		insertTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(insertTitle);

		
//		Az űrlap elemek beállítása:

		
//		KKM nyilvántartási szám űrlapelemek:
		regNumberOfMFA = new DesignTextField("KKM nyilvántartási szám", "HU-1/121/2023", 300, 70, 200, 50);
		regNumberOfMFA.addFocusListener(new FocusActionOfAnInputField(regNumberOfMFA, "regNumberOfMFA"));
		this.add(regNumberOfMFA);
		listOfJC.add(regNumberOfMFA);
		listOfMandatoryJC.add(regNumberOfMFA);

		regNumberOfMFALabel = new DesignLabel(regNumberOfMFA.getName() + ":*", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY(), regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		this.add(regNumberOfMFALabel);

		
//		Regisztráló ország űrlapelemek:
		stateOfRegistry = new DesignComboBox<String>(Countries.getcountryHunNameList(), "Regisztráló ország",
				regNumberOfMFA.getX(), regNumberOfMFA.getY() + 50, regNumberOfMFA.getWidth(),
				regNumberOfMFA.getHeight());
		stateOfRegistry.addFocusListener(new FocusActionOfAnInputField(stateOfRegistry));
		this.add(stateOfRegistry);
		listOfJC.add(stateOfRegistry);
		listOfMandatoryJC.add(stateOfRegistry);

		stateOfRegistryLabel = new DesignLabel(stateOfRegistry.getName() + ":*", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 50, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		this.add(stateOfRegistryLabel);

		
//		Repülőgép lajstromjele űrlapelemek:
		registrationOfAircraft = new DesignTextField("Repülőgép lajstromjele", "QQ-AAA", regNumberOfMFA.getX(),
				regNumberOfMFA.getY() + 100, regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		registrationOfAircraft
				.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft, "registrationOfAircraft"));
		this.add(registrationOfAircraft);
		listOfJC.add(registrationOfAircraft);
		listOfMandatoryJC.add(registrationOfAircraft);

		registrationOfAircraftLabel = new DesignLabel(registrationOfAircraft.getName() + ":*",
				regNumberOfMFA.getX() - 260, regNumberOfMFA.getY() + 100, regNumberOfMFA.getWidth() + 40,
				regNumberOfMFA.getHeight());
		this.add(registrationOfAircraftLabel);

		
//		Repülőgép típusa űrlapelemek:
		typeOfAircraft = new DesignTextField("Repülőgép típusa", "", regNumberOfMFA.getX(), regNumberOfMFA.getY() + 150,
				regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		typeOfAircraft.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft.getDocument()
				.addDocumentListener(new DocumentAction(typeOfAircraft, aircraftTableSorter));
		this.add(typeOfAircraft);
		listOfJC.add(typeOfAircraft);
		listOfMandatoryJC.add(typeOfAircraft);

		typeOfAircraftLabel = new DesignLabel(typeOfAircraft.getName() + ":*", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 150, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		this.add(typeOfAircraftLabel);

		
//		Repülőgép hívójele űrlapelemek:
		radioCallSign = new DesignTextField("Repülőgép hívójele", "QTR1111", regNumberOfMFA.getX(),
				regNumberOfMFA.getY() + 200, regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		radioCallSign.addFocusListener(new FocusActionOfAnInputField(radioCallSign, "radioCallSign"));
		this.add(radioCallSign);
		listOfJC.add(radioCallSign);
		listOfMandatoryJC.add(radioCallSign);

		radioCallSignLabel = new DesignLabel(radioCallSign.getName() + ":*", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 200, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		this.add(radioCallSignLabel);

		
//		A repülés tervezett dátuma űrlapelemek:
		dateOfInflightChooser = new hu.co_de_pilot.datechooser.DateChooser();
		dateOfInflight = new DesignTextField("Az érvényesség kezdődátuma", "", regNumberOfMFA.getX(), regNumberOfMFA.getY() + 250,
				regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		this.add(dateOfInflight);
		listOfJC.add(dateOfInflight);
		listOfMandatoryJC.add(dateOfInflight);
		dateOfInflightChooser.setTextRefernce(dateOfInflight);

		dateOfInflightLabel = new DesignLabel(dateOfInflight.getName() + ":*", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 250, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		this.add(dateOfInflightLabel);

		
//		Indulási reptér űrlapelemek:
		departureAerodrome = new DesignTextField("Indulási reptér", "", regNumberOfMFA.getX(),
				regNumberOfMFA.getY() + 300, regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		departureAerodrome.addFocusListener(new FocusActionOfAnInputField(departureAerodrome, "departureAerodrome",
				depAerodromeTableScrollPane, mouseActionOfDepAerodromeTable));
		departureAerodrome.getDocument()
				.addDocumentListener(new DocumentAction(departureAerodrome, depAerodromeTableSorter));
		this.add(departureAerodrome);
		listOfJC.add(departureAerodrome);
		listOfMandatoryJC.add(departureAerodrome);

		departureAerodromeLabel = new DesignLabel(departureAerodrome.getName() + ":*", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 300, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		this.add(departureAerodromeLabel);

		
//		Érkezési reptér űrlapelemek:
		destinationAerodrome = new DesignTextField("Érkezési reptér", "", regNumberOfMFA.getX(),
				regNumberOfMFA.getY() + 350, regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		destinationAerodrome.addFocusListener(new FocusActionOfAnInputField(destinationAerodrome,
				"destinationAerodrome", desAerodromeTableScrollPane, mouseActionOfDesAerodromeTable));
		destinationAerodrome.getDocument()
				.addDocumentListener(new DocumentAction(destinationAerodrome, desAerodromeTableSorter));
		this.add(destinationAerodrome);
		listOfJC.add(destinationAerodrome);
		listOfMandatoryJC.add(destinationAerodrome);

		destinationAerodromeLabel = new DesignLabel(destinationAerodrome.getName() + ":*", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 350, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		this.add(destinationAerodromeLabel);

		
//		Repülés célja űrlapelemek:
		purposeOfFlight = new DesignTextField("Repülés célja", "VIP Flight", regNumberOfMFA.getX(),
				regNumberOfMFA.getY() + 400, regNumberOfMFA.getWidth() + 300, regNumberOfMFA.getHeight());
		this.add(purposeOfFlight);
		listOfJC.add(purposeOfFlight);
		listOfMandatoryJC.add(purposeOfFlight);

		purposeOfFlightLabel = new DesignLabel(purposeOfFlight.getName() + ":*", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 400, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		this.add(purposeOfFlightLabel);

		
//		Engedélyezett űrlapelem:
		isApprovedSFCL = new DesignCheckBox("Engedélyezett", regNumberOfMFA.getX() + 510, regNumberOfMFA.getY() + 400,
				regNumberOfMFA.getWidth() - 50, regNumberOfMFA.getHeight(), true);
		isApprovedSFCL.setToolTipText("Akkor kell bejelölni, ha az engedélyt jóváhagyták.");
		this.add(isApprovedSFCL);
		listOfJC.add(isApprovedSFCL);
		

//		Megjegyzés űrlapelemek:
		remarks = new DesignTextField("Megjegyzés", "", regNumberOfMFA.getX(),
				regNumberOfMFA.getY() + 450, regNumberOfMFA.getWidth() +300, regNumberOfMFA.getHeight());
		remarks.setVisible(true);
		this.add(remarks);
		listOfJC.add(remarks);
		
		remarksLabel = new DesignLabel(remarks.getName() + ":", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 450, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		remarksLabel.setVisible(true);
		this.add(remarksLabel);

		
//		Eredeti KKM nyt.szám űrlapelemek:
		originRegNumberOfMFA = new DesignTextField("Eredeti KKM nyt.szám", "", regNumberOfMFA.getX(),
				regNumberOfMFA.getY() + 500, regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		originRegNumberOfMFA.addFocusListener(new FocusActionOfAnInputField(originRegNumberOfMFA, "regNumberOfMFA"));
		originRegNumberOfMFA.setVisible(false);
		this.add(originRegNumberOfMFA);
		listOfAdditonalJC.add(originRegNumberOfMFA);
		
		originRegNumberOfMFALabel = new DesignLabel(originRegNumberOfMFA.getName() + ":", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 500, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		originRegNumberOfMFALabel.setVisible(false);
		this.add(originRegNumberOfMFALabel);
		listOfAdditonalJC.add(originRegNumberOfMFALabel);

		
//		Módosító Engedély űrlapelem:
		isModifierSFCL = new DesignCheckBox("Módosító Engedély", regNumberOfMFA.getX() + 220, regNumberOfMFA.getY() + 500,
				regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight(), false);
		isModifierSFCL.setVisible(false);
		this.add(isModifierSFCL);
		listOfAdditonalJC.add(isModifierSFCL);

		
//		A repülés valós dátuma űrlapelemek:
		dateOfRealFlightChooser = new hu.co_de_pilot.datechooser.DateChooser();
		dateOfRealFlight = new DesignTextField("A repülés valós dátuma", "", regNumberOfMFA.getX(), regNumberOfMFA.getY() + 550,
				regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		dateOfRealFlight.setVisible(false);
		this.add(dateOfRealFlight);
		listOfAdditonalJC.add(dateOfRealFlight);
		dateOfRealFlightChooser.setTextRefernce(dateOfRealFlight);
		
		dateOfRealFlightLabel = new DesignLabel(dateOfRealFlight.getName() + ":", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 550, regNumberOfMFA.getWidth() + 40, regNumberOfMFA.getHeight());
		dateOfRealFlightLabel.setVisible(false);
		this.add(dateOfRealFlightLabel);
		listOfAdditonalJC.add(dateOfRealFlightLabel);

		
//		RÖGZÍTÉS űrlapelem:
//		insertSFCLButton = new DesignButton("RÖGZÍTÉS", "AddData");
		insertSFCLButton = new AddSFCLButton("RÖGZÍTÉS", "AddData", insertSFCLButton, mainWindow.getPanelSearchSFCL(), this);
		insertSFCLButton.setBounds(regNumberOfMFA.getX() + 370, regNumberOfMFA.getY() + 590,
				regNumberOfMFA.getWidth() - 50, regNumberOfMFA.getHeight() - 20);
//		ActionListener insertSFCAction = new ButtonAction(insertSFCLButton, mainWindow.getPanelSearchSFCL(), this);
//		insertSFCLButton.addActionListener(insertSFCAction);
		this.add(insertSFCLButton);

		
//		ELVETÉS űrlapelem:
		cancelSFCLButton = new CancelButton("ELVETÉS", "Cancel", listOfJC, null, mainWindow);
		cancelSFCLButton.setBounds(regNumberOfMFA.getX() + 530, regNumberOfMFA.getY() + 590,
				regNumberOfMFA.getWidth() - 50, regNumberOfMFA.getHeight() - 20);
		this.add(cancelSFCLButton);
		
	}
	
	
//	Getter-ek és Setter-ek
	public List<JComponent> getListOfJC() {
		return listOfJC;
	}

	public List<JComponent> getListOfMandatoryJC() {
		return listOfMandatoryJC;
	}

	public List<JComponent> getListOfAdditonalJC() {
		return listOfAdditonalJC;
	}

	public DesignButton getInsertSFCLButton() {
		return insertSFCLButton;
	}

	public DesignCheckBox getIsApprovedSFCL() {
		return isApprovedSFCL;
	}

	public DesignTextField getOriginRegNumberOfMFA() {
		return originRegNumberOfMFA;
	}

	public DesignLabel getOriginRegNumberOfMFALabel() {
		return originRegNumberOfMFALabel;
	}

	public DesignCheckBox getIsModifierSFCL() {
		return isModifierSFCL;
	}

	public DesignTextField getDateOfRealFlight() {
		return dateOfRealFlight;
	}

	public DesignLabel getDateOfRealFlightLabel() {
		return dateOfRealFlightLabel;
	}

	public SingleFlightClearance getActualSFCL() {
		return actualSFCL;
	}

	public void setActualSFCL(SingleFlightClearance actualSFCL) {
		this.actualSFCL = actualSFCL;
	}

}

