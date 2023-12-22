package hu.co_de_pilot.mdcregister;

import java.awt.Color;
import java.awt.Dimension;
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

import hu.co_de_pilot.mdcregister.buttons.AddAFCLButton;
import hu.co_de_pilot.mdcregister.buttons.CancelButton;
import hu.co_de_pilot.mdcregister.buttons.DesignButton;
import hu.co_de_pilot.mdcregister.buttons.ListButton;

public class InsertAnnualFlightClearance extends JPanel {

	private MainWindow mainWindow;
	private AnnualFlightClearance actualAFCL;

	private JLabel insertTitle;
	private DesignTextField regNumberOfMFA;
	private DesignLabel regNumberOfMFALabel;
	private DesignComboBox<String> stateOfRegistry;
	private DesignLabel stateOfRegistryLabel;
	private JPanel aircraftDataPanel;
	private JScrollPane aircraftDataScrollPane;
	private DesignTextField typeOfAircraft_1;
	private DesignTextField registrationOfAircraft_1;
	private DesignTextField radioCallSign_1;
	private List<DesignTextField> listOfJTF_1 = new ArrayList<>();
	private ListButton addAnAircraft_1;
	private DesignTextField typeOfAircraft_2;
	private DesignTextField registrationOfAircraft_2;
	private DesignTextField radioCallSign_2;
	private List<DesignTextField> listOfJTF_2 = new ArrayList<>();
	private ListButton addAnAircraft_2;
	private DesignTextField typeOfAircraft_3;
	private DesignTextField registrationOfAircraft_3;
	private DesignTextField radioCallSign_3;
	private List<DesignTextField> listOfJTF_3 = new ArrayList<>();
	private ListButton addAnAircraft_3;
	private DesignTextField typeOfAircraft_4;
	private DesignTextField registrationOfAircraft_4;
	private DesignTextField radioCallSign_4;
	private List<DesignTextField> listOfJTF_4 = new ArrayList<>();
	private ListButton addAnAircraft_4;
	private DesignTextField typeOfAircraft_5;
	private DesignTextField registrationOfAircraft_5;
	private DesignTextField radioCallSign_5;
	private List<DesignTextField> listOfJTF_5 = new ArrayList<>();
	private ListButton addAnAircraft_5;
	private DesignTextField typeOfAircraft_6;
	private DesignTextField registrationOfAircraft_6;
	private DesignTextField radioCallSign_6;
	private List<DesignTextField> listOfJTF_6 = new ArrayList<>();
	private ListButton addAnAircraft_6;
	private DesignTextField typeOfAircraft_7;
	private DesignTextField registrationOfAircraft_7;
	private DesignTextField radioCallSign_7;
	private List<DesignTextField> listOfJTF_7 = new ArrayList<>();
	private ListButton addAnAircraft_7;
	private DesignTextField typeOfAircraft_8;
	private DesignTextField registrationOfAircraft_8;
	private DesignTextField radioCallSign_8;
	private List<DesignTextField> listOfJTF_8 = new ArrayList<>();
	private ListButton addAnAircraft_8;
	private DesignTextField typeOfAircraft_9;
	private DesignTextField registrationOfAircraft_9;
	private DesignTextField radioCallSign_9;
	private List<DesignTextField> listOfJTF_9 = new ArrayList<>();
	private ListButton addAnAircraft_9;
	private DesignTextField typeOfAircraft_10;
	private DesignTextField registrationOfAircraft_10;
	private DesignTextField radioCallSign_10;
	private List<DesignTextField> listOfJTF_10 = new ArrayList<>();
	private ListButton addAnAircraft_10;
	private DesignTextField typeOfAircraft_11;
	private DesignTextField registrationOfAircraft_11;
	private DesignTextField radioCallSign_11;
	private List<DesignTextField> listOfJTF_11 = new ArrayList<>();
	private ListButton addAnAircraft_11;
	private DesignTextField typeOfAircraft_12;
	private DesignTextField registrationOfAircraft_12;
	private DesignTextField radioCallSign_12;
	private List<DesignTextField> listOfJTF_12 = new ArrayList<>();
	private ListButton addAnAircraft_12;
	private DesignTextField typeOfAircraft_13;
	private DesignTextField registrationOfAircraft_13;
	private DesignTextField radioCallSign_13;
	private List<DesignTextField> listOfJTF_13 = new ArrayList<>();
	private ListButton addAnAircraft_13;
	private DesignTextField typeOfAircraft_14;
	private DesignTextField registrationOfAircraft_14;
	private DesignTextField radioCallSign_14;
	private List<DesignTextField> listOfJTF_14 = new ArrayList<>();
	private ListButton addAnAircraft_14;
	private DesignTextField typeOfAircraft_15;
	private DesignTextField registrationOfAircraft_15;
	private DesignTextField radioCallSign_15;
	private List<DesignTextField> listOfJTF_15 = new ArrayList<>();
	private ListButton addAnAircraft_15;
	private DesignTextField startdateOfValidity;
	private DesignLabel startdateOfValidityLabel;
	private hu.co_de_pilot.datechooser.DateChooser startdateOfValidityChooser;
	private DesignTextField enddateOfValidity;
	private DesignLabel enddateOfValidityLabel;
	private hu.co_de_pilot.datechooser.DateChooser enddateOfValidityChooser;
	private DesignCheckBox isApprovedAFCL;
	private DesignTextField remarks;
	private DesignLabel remarksLabel;
	private DesignLabel aircraftTypeLabel;
	private DesignLabel aircraftRegistrationLabel;
	private DesignLabel aircraftCallSignLabel;
	private DesignTextField originRegNumberOfMFA;
	private DesignLabel originRegNumberOfMFALabel;
	private DesignCheckBox isModifierAFCL;
	private AddAFCLButton insertAFCLButton;
	private DesignButton cancelAFCLButton;
	private List<JComponent> listOfJC = new ArrayList<>();
	private List<List<DesignTextField>> listOfAircraftDTF = new ArrayList<>();
	private List<ListButton> listOfAircraftListButton = new ArrayList<>();
	private List<JComponent> listOfMandatoryJC = new ArrayList<>();
	private List<JComponent> listOfAdditonalJC = new ArrayList<>();
	private FinderTable aircraftTable;
	private DefaultTableModel aircraftTableModel;
	private JScrollPane aircraftTableScrollPane;
	private String aircraftsCsvPath = "resources\\aircrafts.csv";

	public InsertAnnualFlightClearance(MainWindow mainWindow) {

		this.mainWindow = mainWindow;

		
//		A kereső táblázatokra vonatkozó alapadatok
		String[] aircraftTableColumnNames = { "ICAO kód", "Gyártó", "Típus" };
		int[] tableColumnWidths = { 60, 140, 160 };

		
//		A repülőgép típus kereső táblázat
		aircraftTableModel = new DefaultTableModel(aircraftTableColumnNames, 0);
		aircraftTable = new FinderTable(aircraftTableColumnNames, tableColumnWidths, aircraftTableModel);
		TableRowSorter<DefaultTableModel> aircraftTableSorter = new TableRowSorter<DefaultTableModel>(
				aircraftTableModel);
		aircraftTable.setRowSorter(aircraftTableSorter);
		MouseAction mouseActionOfAircraftTable = new MouseAction(aircraftTable, aircraftTableModel);
		aircraftTable.addMouseListener(mouseActionOfAircraftTable);
		aircraftTableScrollPane = new DesignScrollPane(aircraftTable, null, 620, 220, 380, 430);
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

		
//		A panel címének beállítása
		insertTitle = new JLabel("ÉVES BEREPÜLÉSI ENGEDÉLY RÖGZÍTÉSE");
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
				regNumberOfMFA.getY(), regNumberOfMFA.getWidth() + 30, regNumberOfMFA.getHeight());
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
				regNumberOfMFA.getY() + 50, regNumberOfMFA.getWidth() + 30, regNumberOfMFA.getHeight());
		this.add(stateOfRegistryLabel);

		
//		Az érvényesség kezdődátuma űrlapelemek:
		startdateOfValidityChooser = new hu.co_de_pilot.datechooser.DateChooser();
		startdateOfValidity = new DesignTextField("Érvényesség kezdődátuma", "", regNumberOfMFA.getX() + 490, regNumberOfMFA.getY(),
				regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		this.add(startdateOfValidity);
		listOfJC.add(startdateOfValidity);
		listOfMandatoryJC.add(startdateOfValidity);
		startdateOfValidityChooser.setTextRefernce(startdateOfValidity);

		startdateOfValidityLabel = new DesignLabel(startdateOfValidity.getName() + ":*", regNumberOfMFA.getX() + 230,
				regNumberOfMFA.getY(), regNumberOfMFA.getWidth() + 30, regNumberOfMFA.getHeight());
		this.add(startdateOfValidityLabel);

		
//		Az érvényesség záródátuma űrlapelemek:
		enddateOfValidityChooser = new hu.co_de_pilot.datechooser.DateChooser();
		enddateOfValidity = new DesignTextField("Érvényesség záródátuma", "", regNumberOfMFA.getX() + 490, regNumberOfMFA.getY() + 50,
				regNumberOfMFA.getWidth(), regNumberOfMFA.getHeight());
		this.add(enddateOfValidity);
		listOfJC.add(enddateOfValidity);
		listOfMandatoryJC.add(enddateOfValidity);
		enddateOfValidityChooser.setTextRefernce(enddateOfValidity);
		
		enddateOfValidityLabel = new DesignLabel(enddateOfValidity.getName() + ":*" + ":", regNumberOfMFA.getX() + 230,
				regNumberOfMFA.getY() + 50, regNumberOfMFA.getWidth() + 30, regNumberOfMFA.getHeight());
		this.add(enddateOfValidityLabel);
		
		
//		Engedélyezett űrlapelem:
		isApprovedAFCL = new DesignCheckBox("Engedélyezett", regNumberOfMFA.getX() + 535, regNumberOfMFA.getY() + 100,
				regNumberOfMFA.getWidth() - 50, regNumberOfMFA.getHeight(), true);
		isApprovedAFCL.setToolTipText("Akkor kell bejelölni, ha az engedélyt jóváhagyták.");
		this.add(isApprovedAFCL);
		listOfJC.add(isApprovedAFCL);
		

//		Megjegyzés űrlapelemek:
		remarks = new DesignTextField("Megjegyzés", "", regNumberOfMFA.getX(),
				regNumberOfMFA.getY() + 100, regNumberOfMFA.getWidth() +325, regNumberOfMFA.getHeight());
		this.add(remarks);
		listOfJC.add(remarks);
		
		remarksLabel = new DesignLabel(remarks.getName() + ":", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 100, regNumberOfMFA.getWidth() + 30, regNumberOfMFA.getHeight());
		this.add(remarksLabel);


//		Repülőgép adatok görgethető panelje
		aircraftDataPanel = new JPanel();
		aircraftDataPanel.setBackground(new Color(255, 255, 255));
		aircraftDataPanel.setLayout(null);
		aircraftDataPanel.setPreferredSize(new Dimension(550, 800));
		aircraftDataPanel.setMinimumSize(new Dimension(550, 800));
		aircraftDataScrollPane = new DesignScrollPane(null, aircraftDataPanel, 40, 220, 570, 400);
		aircraftDataScrollPane.setVisible(true);
		this.add(aircraftDataScrollPane);
		
		
//		Repülőgépek adatainak fejléc elemei 
		aircraftTypeLabel = new DesignLabel("Repülőgép típusa*", 40,
				0, regNumberOfMFA.getWidth() - 50, regNumberOfMFA.getHeight());
		aircraftDataPanel.add(aircraftTypeLabel);
		
		aircraftRegistrationLabel = new DesignLabel("Repülőgép lajstromjele*", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY(), aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		aircraftDataPanel.add(aircraftRegistrationLabel);
		
		aircraftCallSignLabel = new DesignLabel("Repülőgép hívójele*", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY(), aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		aircraftDataPanel.add(aircraftCallSignLabel);
		
		
//		Repülőgépek adatainak szövegbeviteli elemei

		
//		1. Repülőgép
		typeOfAircraft_1 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 50,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_1.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_1, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_1.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_1, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_1);
		listOfJTF_1.add(typeOfAircraft_1);
		
		registrationOfAircraft_1 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 50, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_1
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_1, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_1);
		listOfJTF_1.add(registrationOfAircraft_1);
		
		radioCallSign_1 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 50, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_1.addFocusListener(new FocusActionOfAnInputField(radioCallSign_1, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_1);
		listOfJTF_1.add(radioCallSign_1);
		listOfAircraftDTF.add(listOfJTF_1);
		
		addAnAircraft_1 = new ListButton(listOfJTF_1);
		addAnAircraft_1.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 55, 30, 30);
		aircraftDataPanel.add(addAnAircraft_1);
		listOfAircraftListButton.add(addAnAircraft_1);

		
//		2. Repülőgép
		typeOfAircraft_2 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 100,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_2.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_2, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_2.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_2, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_2);
		listOfJTF_2.add(typeOfAircraft_2);
		
		registrationOfAircraft_2 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 100, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_2
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_2, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_2);
		listOfJTF_2.add(registrationOfAircraft_2);
		
		radioCallSign_2 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 100, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_2.addFocusListener(new FocusActionOfAnInputField(radioCallSign_2, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_2);
		listOfJTF_2.add(radioCallSign_2);
		listOfAircraftDTF.add(listOfJTF_2);
		
		addAnAircraft_2 = new ListButton(listOfJTF_2);
		addAnAircraft_2.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 105, 30, 30);
		aircraftDataPanel.add(addAnAircraft_2);
		listOfAircraftListButton.add(addAnAircraft_2);

		
//		3. Repülőgép
		typeOfAircraft_3 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 150,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_3.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_3, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_3.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_3, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_3);
		listOfJTF_3.add(typeOfAircraft_3);
		
		registrationOfAircraft_3 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 150, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_3
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_3, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_3);
		listOfJTF_3.add(registrationOfAircraft_3);
		
		radioCallSign_3 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 150, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_3.addFocusListener(new FocusActionOfAnInputField(radioCallSign_3, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_3);
		listOfJTF_3.add(radioCallSign_3);
		listOfAircraftDTF.add(listOfJTF_3);
		
		addAnAircraft_3 = new ListButton(listOfJTF_3);
		addAnAircraft_3.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 155, 30, 30);
		aircraftDataPanel.add(addAnAircraft_3);
		listOfAircraftListButton.add(addAnAircraft_3);

		
//		4. Repülőgép
		typeOfAircraft_4 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 200,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_4.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_4, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_4.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_4, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_4);
		listOfJTF_4.add(typeOfAircraft_4);
		
		registrationOfAircraft_4 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 200, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_4
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_4, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_4);
		listOfJTF_4.add(registrationOfAircraft_4);
		
		radioCallSign_4 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 200, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_4.addFocusListener(new FocusActionOfAnInputField(radioCallSign_4, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_4);
		listOfJTF_4.add(radioCallSign_4);
		listOfAircraftDTF.add(listOfJTF_4);
		
		addAnAircraft_4 = new ListButton(listOfJTF_4);
		addAnAircraft_4.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 205, 30, 30);
		aircraftDataPanel.add(addAnAircraft_4);
		listOfAircraftListButton.add(addAnAircraft_4);
		
		
//		5. Repülőgép
		typeOfAircraft_5 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 250,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_5.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_5, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_5.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_5, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_5);
		listOfJTF_5.add(typeOfAircraft_5);
		
		registrationOfAircraft_5 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 250, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_5
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_5, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_5);
		listOfJTF_5.add(registrationOfAircraft_5);
		
		radioCallSign_5 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 250, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_5.addFocusListener(new FocusActionOfAnInputField(radioCallSign_5, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_5);
		listOfJTF_5.add(radioCallSign_5);
		listOfAircraftDTF.add(listOfJTF_5);

		addAnAircraft_5 = new ListButton(listOfJTF_5);
		addAnAircraft_5.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 255, 30, 30);
		aircraftDataPanel.add(addAnAircraft_5);
		listOfAircraftListButton.add(addAnAircraft_5);
				
		
//		6. Repülőgép
		typeOfAircraft_6 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 300,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_6.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_6, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_6.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_6, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_6);
		listOfJTF_6.add(typeOfAircraft_6);
		
		registrationOfAircraft_6 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 300, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_6
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_6, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_6);
		listOfJTF_6.add(registrationOfAircraft_6);
		
		radioCallSign_6 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 300, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_6.addFocusListener(new FocusActionOfAnInputField(radioCallSign_6, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_6);
		listOfJTF_6.add(radioCallSign_6);
		listOfAircraftDTF.add(listOfJTF_6);
		
		addAnAircraft_6 = new ListButton(listOfJTF_6);
		addAnAircraft_6.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 305, 30, 30);
		aircraftDataPanel.add(addAnAircraft_6);
		listOfAircraftListButton.add(addAnAircraft_6);

		
//		7. Repülőgép
		typeOfAircraft_7 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 350,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_7.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_7, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_7.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_7, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_7);
		listOfJTF_7.add(typeOfAircraft_7);
		
		registrationOfAircraft_7 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 350, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_7
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_7, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_7);
		listOfJTF_7.add(registrationOfAircraft_7);
		
		radioCallSign_7 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 350, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_7.addFocusListener(new FocusActionOfAnInputField(radioCallSign_7, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_7);
		listOfJTF_7.add(radioCallSign_7);
		listOfAircraftDTF.add(listOfJTF_7);
		
		addAnAircraft_7 = new ListButton(listOfJTF_7);
		addAnAircraft_7.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 355, 30, 30);
		aircraftDataPanel.add(addAnAircraft_7);
		listOfAircraftListButton.add(addAnAircraft_7);
		
		
//		8. Repülőgép
		typeOfAircraft_8 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 400,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_8.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_8, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_8.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_8, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_8);
		listOfJTF_8.add(typeOfAircraft_8);
		
		registrationOfAircraft_8 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 400, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_8
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_8, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_8);
		listOfJTF_8.add(registrationOfAircraft_8);
		
		radioCallSign_8 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 400, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_8.addFocusListener(new FocusActionOfAnInputField(radioCallSign_8, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_8);
		listOfJTF_8.add(radioCallSign_8);		
		listOfAircraftDTF.add(listOfJTF_8);
		
		addAnAircraft_8 = new ListButton(listOfJTF_8);
		addAnAircraft_8.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 405, 30, 30);
		aircraftDataPanel.add(addAnAircraft_8);
		listOfAircraftListButton.add(addAnAircraft_8);

				
//		9. Repülőgép
		typeOfAircraft_9 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 450,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_9.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_9, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_9.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_9, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_9);
		listOfJTF_9.add(typeOfAircraft_9);
		
		registrationOfAircraft_9 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 450, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_9
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_9, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_9);
		listOfJTF_9.add(registrationOfAircraft_9);
		
		radioCallSign_9 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 450, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_9.addFocusListener(new FocusActionOfAnInputField(radioCallSign_9, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_9);
		listOfJTF_9.add(radioCallSign_9);
		listOfAircraftDTF.add(listOfJTF_9);
		
		addAnAircraft_9 = new ListButton(listOfJTF_9);
		addAnAircraft_9.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 455, 30, 30);
		aircraftDataPanel.add(addAnAircraft_9);
		listOfAircraftListButton.add(addAnAircraft_9);
		
		
//		10. Repülőgép
		typeOfAircraft_10 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 500,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_10.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_10, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_10.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_10, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_10);
		listOfJTF_10.add(typeOfAircraft_10);
		
		registrationOfAircraft_10 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 500, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_10
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_10, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_10);
		listOfJTF_10.add(registrationOfAircraft_10);
		
		radioCallSign_10 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 500, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_10.addFocusListener(new FocusActionOfAnInputField(radioCallSign_10, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_10);
		listOfJTF_10.add(radioCallSign_10);
		listOfAircraftDTF.add(listOfJTF_10);
		
		addAnAircraft_10 = new ListButton(listOfJTF_10);
		addAnAircraft_10.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 505, 30, 30);
		aircraftDataPanel.add(addAnAircraft_10);
		listOfAircraftListButton.add(addAnAircraft_10);
		
				
//		11. Repülőgép
		typeOfAircraft_11 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 550,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_11.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_11, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_11.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_11, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_11);
		listOfJTF_11.add(typeOfAircraft_11);
		
		registrationOfAircraft_11 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 550, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_11
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_11, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_11);
		listOfJTF_11.add(registrationOfAircraft_11);
		
		radioCallSign_11 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 550, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_11.addFocusListener(new FocusActionOfAnInputField(radioCallSign_11, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_11);
		listOfJTF_11.add(radioCallSign_11);		
		listOfAircraftDTF.add(listOfJTF_11);
		
		addAnAircraft_11 = new ListButton(listOfJTF_11);
		addAnAircraft_11.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 555, 30, 30);
		aircraftDataPanel.add(addAnAircraft_11);
		listOfAircraftListButton.add(addAnAircraft_11);
		
				
//		12. Repülőgép
		typeOfAircraft_12 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 600,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_12.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_12, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_12.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_12, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_12);
		listOfJTF_12.add(typeOfAircraft_12);
		
		registrationOfAircraft_12 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 600, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_12
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_12, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_12);
		listOfJTF_12.add(registrationOfAircraft_12);
		
		radioCallSign_12 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 600, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_12.addFocusListener(new FocusActionOfAnInputField(radioCallSign_12, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_12);
		listOfJTF_12.add(radioCallSign_12);		
		listOfAircraftDTF.add(listOfJTF_12);
		
		addAnAircraft_12 = new ListButton(listOfJTF_12);
		addAnAircraft_12.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 605, 30, 30);
		aircraftDataPanel.add(addAnAircraft_12);
		listOfAircraftListButton.add(addAnAircraft_12);

		
//		13. Repülőgép
		typeOfAircraft_13 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 650,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_13.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_13, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_13.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_13, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_13);
		listOfJTF_13.add(typeOfAircraft_13);
		
		registrationOfAircraft_13 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 650, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_13
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_13, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_13);
		listOfJTF_13.add(registrationOfAircraft_13);
		
		radioCallSign_13 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 650, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_13.addFocusListener(new FocusActionOfAnInputField(radioCallSign_13, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_13);
		listOfJTF_13.add(radioCallSign_13);		
		listOfAircraftDTF.add(listOfJTF_13);
		
		addAnAircraft_13 = new ListButton(listOfJTF_13);
		addAnAircraft_13.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 655, 30, 30);
		aircraftDataPanel.add(addAnAircraft_13);
		listOfAircraftListButton.add(addAnAircraft_13);

		
//		14. Repülőgép
		typeOfAircraft_14 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 700,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_14.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_14, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_14.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_14, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_14);
		listOfJTF_14.add(typeOfAircraft_14);
		
		registrationOfAircraft_14 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 700, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_14
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_14, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_14);
		listOfJTF_14.add(registrationOfAircraft_14);
		
		radioCallSign_14 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 700, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_14.addFocusListener(new FocusActionOfAnInputField(radioCallSign_14, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_14);
		listOfJTF_14.add(radioCallSign_14);		
		listOfAircraftDTF.add(listOfJTF_14);
		
		addAnAircraft_14 = new ListButton(listOfJTF_14);
		addAnAircraft_14.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 705, 30, 30);
		aircraftDataPanel.add(addAnAircraft_14);
		listOfAircraftListButton.add(addAnAircraft_14);

		
//		15. Repülőgép
		typeOfAircraft_15 = new DesignTextField("Repülőgép típusa", "", aircraftTypeLabel.getX(), aircraftTypeLabel.getY() + 750,
				aircraftTypeLabel.getWidth(), aircraftTypeLabel.getHeight());
		typeOfAircraft_15.addFocusListener(new FocusActionOfAnInputField(typeOfAircraft_15, "typeOfAircraft",
				aircraftTableScrollPane, mouseActionOfAircraftTable));
		typeOfAircraft_15.getDocument()
		.addDocumentListener(new DocumentAction(typeOfAircraft_15, aircraftTableSorter));
		aircraftDataPanel.add(typeOfAircraft_15);
		listOfJTF_15.add(typeOfAircraft_15);		
		
		registrationOfAircraft_15 = new DesignTextField("Repülőgép lajstromjele", "", aircraftTypeLabel.getX() + 150,
				aircraftTypeLabel.getY() + 750, aircraftTypeLabel.getWidth() + 40, aircraftTypeLabel.getHeight());
		registrationOfAircraft_15
		.addFocusListener(new FocusActionOfAnInputField(registrationOfAircraft_15, "registrationOfAircraft"));
		aircraftDataPanel.add(registrationOfAircraft_15);
		listOfJTF_15.add(registrationOfAircraft_15);
		
		radioCallSign_15 = new DesignTextField("Repülőgép hívójele", "", aircraftTypeLabel.getX() + 340,
				aircraftTypeLabel.getY() + 750, aircraftTypeLabel.getWidth() + 20, aircraftTypeLabel.getHeight());
		radioCallSign_15.addFocusListener(new FocusActionOfAnInputField(radioCallSign_15, "radioCallSign"));
		aircraftDataPanel.add(radioCallSign_15);
		listOfJTF_15.add(radioCallSign_15);		
		listOfAircraftDTF.add(listOfJTF_15);
		
		addAnAircraft_15 = new ListButton(listOfJTF_15);
		addAnAircraft_15.setBounds(aircraftTypeLabel.getX() - 35, aircraftTypeLabel.getY() + 755, 30, 30);
		aircraftDataPanel.add(addAnAircraft_15);
		listOfAircraftListButton.add(addAnAircraft_15);
		
		
//		Eredeti KKM nyt.szám űrlapelemek:
		originRegNumberOfMFA = new DesignTextField("Eredeti KKM nyt.szám", "", regNumberOfMFA.getX(),
				regNumberOfMFA.getY() + 560, regNumberOfMFA.getWidth() - 30, regNumberOfMFA.getHeight());
		originRegNumberOfMFA.addFocusListener(new FocusActionOfAnInputField(originRegNumberOfMFA, "regNumberOfMFA"));
		originRegNumberOfMFA.setVisible(false);
		this.add(originRegNumberOfMFA);
		listOfAdditonalJC.add(originRegNumberOfMFA);
		
		originRegNumberOfMFALabel = new DesignLabel(originRegNumberOfMFA.getName() + ":", regNumberOfMFA.getX() - 260,
				regNumberOfMFA.getY() + 560, regNumberOfMFA.getWidth() + 30, regNumberOfMFA.getHeight());
		originRegNumberOfMFALabel.setVisible(false);
		this.add(originRegNumberOfMFALabel);
		listOfAdditonalJC.add(originRegNumberOfMFALabel);

		
//		Módosító Engedély űrlapelem:
		isModifierAFCL = new DesignCheckBox("Módosító Engedély", regNumberOfMFA.getX() + 190, regNumberOfMFA.getY() + 560,
				regNumberOfMFA.getWidth() - 30, regNumberOfMFA.getHeight(), false);
		isModifierAFCL.setVisible(false);
		this.add(isModifierAFCL);
		listOfAdditonalJC.add(isModifierAFCL);

		
//		RÖGZÍTÉS űrlapelem:
		insertAFCLButton = new AddAFCLButton("RÖGZÍTÉS", "AddData", insertAFCLButton, mainWindow.getPanelSearchAFCL(), this);
		insertAFCLButton.setBounds(regNumberOfMFA.getX() + 370, regNumberOfMFA.getY() + 590,
				regNumberOfMFA.getWidth() - 50, regNumberOfMFA.getHeight() - 20);
		this.add(insertAFCLButton);

		
//		ELVETÉS űrlapelem:
		cancelAFCLButton = new CancelButton("ELVETÉS", "Cancel", listOfJC, listOfAircraftDTF, mainWindow);
		cancelAFCLButton.setBounds(regNumberOfMFA.getX() + 530, regNumberOfMFA.getY() + 590,
				regNumberOfMFA.getWidth() - 50, regNumberOfMFA.getHeight() - 20);
		this.add(cancelAFCLButton);
		
	}
	
	
//	Getter-ek és Setter-ek
	public List<JComponent> getListOfJC() {
		return listOfJC;
	}

	public List<List<DesignTextField>> getListOfAircraftDTF() {
		return listOfAircraftDTF;
	}
	
	public List<ListButton> getListOfAircraftListButton() {
		return listOfAircraftListButton;
	}

	public List<JComponent> getListOfMandatoryJC() {
		return listOfMandatoryJC;
	}
	
	public List<JComponent> getListOfAdditonalJC() {
		return listOfAdditonalJC;
	}

	public DesignCheckBox getIsApprovedAFCL() {
		return isApprovedAFCL;
	}

	public DesignButton getInsertAFCLButton() {
		return insertAFCLButton;
	}

	public DesignCheckBox getIsModifierAFCL() {
		return isModifierAFCL;
	}

	public AnnualFlightClearance getActualAFCL() {
		return actualAFCL;
	}

	public void setActualAFCL(AnnualFlightClearance actualAFCL) {
		this.actualAFCL = actualAFCL;
	}
	
	public DesignTextField getOriginRegNumberOfMFA() {
		return originRegNumberOfMFA;
	}

}

