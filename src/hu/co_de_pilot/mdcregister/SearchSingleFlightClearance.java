package hu.co_de_pilot.mdcregister;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import hu.co_de_pilot.mdcregister.buttons.ClearFiltersButton;
import hu.co_de_pilot.mdcregister.buttons.DesignButton;
import hu.co_de_pilot.mdcregister.buttons.SearchOtherFilterButton;
import hu.co_de_pilot.mdcregister.buttons.SearchPeriodButton;
import hu.co_de_pilot.mdcregister.buttons.SerializeButton;

public class SearchSingleFlightClearance extends JPanel {

	private List<SingleFlightClearance> listOfSFCL = new ArrayList<>();
	private List<SingleFlightClearance> listOfFilteredSFCL = new ArrayList<>();
	private List<SingleFlightClearance> listOfPrintedSFCL = new ArrayList<>();
	
	private MainWindow mainWindow;

	private List<JComponent> listOfJC = new ArrayList<>();
	private JLabel insertTitle;
	private DesignRadioButton dinamicSearchSFCL;
	private DesignTextField searchSFCL;
	private DesignButton clearFiltersButton;
	private DesignButton serializeListButton;
	private DesignRadioButton periodSearchSFCL;
	private hu.co_de_pilot.datechooser.DateChooser dateFromSearchSFCLChooser;
	private hu.co_de_pilot.datechooser.DateChooser dateUntilSearchSFCLChooser;
	private DesignTextField periodfromSFCL;
	private DesignTextField perioduntilSFCL;
	private DesignButton searchPeriodSFCLButton;
	private DesignRadioButton otherFilterSFCL;
	private DesignCheckBox isModifiedSFCL;
	private DesignCheckBox isActiveSFCL;
	private DesignCheckBox isApprovedSFCL;
	private DesignCheckBox isFlownAwaySFCL;
	private DesignButton searchOtherFilterSFCLButton;
	private DesignLabel additionalInfoLabel;
	private ButtonGroup searchSFCLButtonGroup;
	private FinderTable singleFlightClearanceTable;
	private FinderTable additionalInfoTable;
	private DefaultTableModel singleFlightClearanceTableModel;
	private DefaultTableModel additionalInfoTableModel;
	private String[] singleFlightClearanceTableColumnNames = { "<html><center>KKM<br>nyt. szám</html>",
			"<html><center>Regisztráló<br>ország</html>", "<html><center>Repülőgép<br>lajstromjele</html>",
			"<html><center>Repülőgép<br>típusa</html>", "<html><center>Repülőgép<br>hívójele</html>",
			"<html><center>Érvényesség<br>kezdődátuma</html>", "<html><center>Indulási<br>repülőtér</html>",
			"<html><center>Érkezési<br>repülőtér</html>", "<html><center>Repülés célja</html>",
			"<html><center>Enge-<br>délyezett</html>", "<html><center>Aktív</html>",
			"<html><center>Módo-<br>sított</html>", "<html><center>Elrepült</html>",
			"", "", "", "","" };
	private String[] additionalInfoTableColumnNames = { "<html><center>KKM<br>nyt. szám</html>",
			"<html><center>Megjegyzés</html>", "<html><center>Eredeti KKM<br>nyt. szám</html>",
			"<html><center>Módosító<br>engedély</html>", "<html><center>Repülés<br>valós dátuma</html>" };
	private JScrollPane singleFlightClearanceTableScrollPane;
	private JScrollPane additionalInfoTableScrollPane;

	public SearchSingleFlightClearance(MainWindow mainWindow) {

		this.mainWindow = mainWindow;
		
		
//		A kereső táblázat beállítása
		int[] tableColumnWidths = { 80, 115, 70, 60, 60, 70, 50, 50, 130, 50, 40, 40, 40, 20, 20, 20, 20, 20 };
		singleFlightClearanceTableModel = new DefaultTableModel(singleFlightClearanceTableColumnNames, 0);

		singleFlightClearanceTable = new FinderTable(singleFlightClearanceTableColumnNames, tableColumnWidths,
				singleFlightClearanceTableModel);

		TableRowSorter<DefaultTableModel> singleFlightClearanceTableSorter = new TableRowSorter<DefaultTableModel>(
				singleFlightClearanceTableModel);
		singleFlightClearanceTable.setRowSorter(singleFlightClearanceTableSorter);

		singleFlightClearanceTableScrollPane = new DesignScrollPane(singleFlightClearanceTable, null, 30, 250, 950, 370);
		singleFlightClearanceTableScrollPane.setVisible(true);
		this.add(singleFlightClearanceTableScrollPane);

		
//		A kiegészítő adatok táblázat beállítása
		int[] additionalInfoTableColumnWidths = { 80, 410, 100, 60, 80 };
		additionalInfoTableModel = new DefaultTableModel(additionalInfoTableColumnNames, 0);
		
		additionalInfoTable = new FinderTable(additionalInfoTableColumnNames, additionalInfoTableColumnWidths,
				additionalInfoTableModel);
		
		additionalInfoTableScrollPane = new DesignScrollPane(additionalInfoTable, null, 250, 630, 730, 50);
		additionalInfoTableScrollPane.setVisible(false);
		this.add(additionalInfoTableScrollPane);

		additionalInfoLabel = new DesignLabel("További információk:", 30, 630, 200, 50);
		this.add(additionalInfoLabel);
		
		
//		A panel címének beállítása
		insertTitle = new JLabel("EGYSZERI BEREPÜLÉSI ENGEDÉLY KERESÉSE");
		insertTitle.setBounds(0, 10, 1000, 40);
		insertTitle.setFont(mainWindow.getFontofButtonAndTitle());
		insertTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		insertTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(insertTitle);
		
		
//		Dinamikus keresési mód űrlapelemek
		searchSFCL = new DesignTextField("Dinamikus keresés", null, 250, 70, 230, 53);
		searchSFCL.setEnabled(false);
		searchSFCL.getDocument()
				.addDocumentListener(new DocumentAction(searchSFCL, singleFlightClearanceTableSorter));
		this.add(searchSFCL);
		listOfJC.add(searchSFCL);

		dinamicSearchSFCL = new DesignRadioButton("Dinamikus keresés", 30, 70, 200, 50, false);
		JComponent[] dinamicSearchComponents = { searchSFCL };
		dinamicSearchSFCL.addItemListener(new RadioButtonAction(dinamicSearchComponents));
		this.add(dinamicSearchSFCL);
		listOfJC.add(dinamicSearchSFCL);

		
//		Időintervallum keresési mód űrlapelemek
		dateFromSearchSFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		periodfromSFCL = new DesignTextField("Időponttól keresés", null, 250, 130, 110, 53);
		periodfromSFCL.setToolTipText("Keresési időintervallum kezdete");
		periodfromSFCL.setEnabled(false);
		this.add(periodfromSFCL);
		listOfJC.add(periodfromSFCL);
		dateFromSearchSFCLChooser.setTextRefernce(periodfromSFCL);

		dateUntilSearchSFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		perioduntilSFCL = new DesignTextField("Időpontig keresés", null, 370, 130, 110, 53);
		perioduntilSFCL.setToolTipText("Keresési időintervallum vége");
		perioduntilSFCL.setEnabled(false);
		this.add(perioduntilSFCL);
		listOfJC.add(perioduntilSFCL);
		dateUntilSearchSFCLChooser.setTextRefernce(perioduntilSFCL);

		searchPeriodSFCLButton = new SearchPeriodButton("KERESÉS", "SearchPeriod", null, this);
		searchPeriodSFCLButton.setBounds(500, 135, 180, 30);
		searchPeriodSFCLButton.setEnabled(false);
		this.add(searchPeriodSFCLButton);

		periodSearchSFCL = new DesignRadioButton("Időintervallum keresés", 30, 130, 200, 50, false);
		JComponent[] periodSearchComponents = { periodfromSFCL, perioduntilSFCL, searchPeriodSFCLButton };
		periodSearchSFCL.addItemListener(new RadioButtonAction(periodSearchComponents));
		this.add(periodSearchSFCL);
		listOfJC.add(periodSearchSFCL);

//		Egyéb szűrési mód beállítása
		isModifiedSFCL = new DesignCheckBox("Módosított", 250, 190, 120, 50, false);
		isModifiedSFCL.setEnabled(false);
		this.add(isModifiedSFCL);
		listOfJC.add(isModifiedSFCL);

		isActiveSFCL = new DesignCheckBox("Aktív", 380, 190, 120, 50, false);
		isActiveSFCL.setEnabled(false);
		this.add(isActiveSFCL);
		listOfJC.add(isActiveSFCL);

		isApprovedSFCL = new DesignCheckBox("Engedélyezett", 510, 190, 140, 50, false);
		isApprovedSFCL.setEnabled(false);
		this.add(isApprovedSFCL);
		listOfJC.add(isApprovedSFCL);
		
		isFlownAwaySFCL = new DesignCheckBox("Elrepült", 660, 190, 120, 50, false);
		isFlownAwaySFCL.setEnabled(false);
		this.add(isFlownAwaySFCL);
		listOfJC.add(isFlownAwaySFCL);
		
		searchOtherFilterSFCLButton = new SearchOtherFilterButton("KERESÉS", "OtherFilter", null, this);
		searchOtherFilterSFCLButton.setBounds(800, 195, 180, 30);
//		ActionListener searchOtherFilterSFCLAction = new ButtonAction(searchOtherFilterSFCLButton, this);
//		searchOtherFilterSFCLButton.addActionListener(searchOtherFilterSFCLAction);
		searchOtherFilterSFCLButton.setEnabled(false);
		this.add(searchOtherFilterSFCLButton);

		otherFilterSFCL = new DesignRadioButton("Egyéb szűrés", 30, 190, 200, 50, false);
		JComponent[] otherFilterComponents = { isModifiedSFCL, isActiveSFCL, isApprovedSFCL, isFlownAwaySFCL,
				searchOtherFilterSFCLButton };
		otherFilterSFCL.addItemListener(new RadioButtonAction(otherFilterComponents));
		this.add(otherFilterSFCL);
		listOfJC.add(otherFilterSFCL);

		
//		A keresési módok rádiógomb csoportjának beállítása
		searchSFCLButtonGroup = new ButtonGroup();
		searchSFCLButtonGroup.add(dinamicSearchSFCL);
		searchSFCLButtonGroup.add(periodSearchSFCL);
		searchSFCLButtonGroup.add(otherFilterSFCL);

		
//		TELJES LISTA(Szűrők törlése űrlapelem
		clearFiltersButton = new ClearFiltersButton("TELJES LISTA", "ClearFilters", null, this);
		clearFiltersButton.setBounds(600, 75, 180, 30);
		this.add(clearFiltersButton);
		
		
//		LISTA MENTÉSE(Szerializációval .obj fájlba) beállítása
		serializeListButton = new SerializeButton("LISTA MENTÉSE", "SaveList", null, null, mainWindow.getPanelInsertSFCL(), this);
		serializeListButton.setBounds(800, 75, 180, 30);
		this.add(serializeListButton);
		
	}

	
//	Getter-ek és Setter-ekSearchSingleFlightClearance.java
	public FinderTable getSingleFlightClearanceTable() {
		return singleFlightClearanceTable;
	}

	public DefaultTableModel getSingleFlightClearanceTableModel() {
		return singleFlightClearanceTableModel;
	}

	public String[] getSingleFlightClearanceTableColumnNames() {
		return singleFlightClearanceTableColumnNames;
	}

	public FinderTable getAdditionalInfoTable() {
		return additionalInfoTable;
	}

	public JScrollPane getAdditionalInfoTableScrollPane() {
		return additionalInfoTableScrollPane;
	}

	public List<SingleFlightClearance> getListOfSFCL() {
		return listOfSFCL;
	}

	public List<SingleFlightClearance> getListOfFilteredSFCL() {
		return listOfFilteredSFCL;
	}

	public List<SingleFlightClearance> getListOfPrintedSFCL() {
		return listOfPrintedSFCL;
	}

	public void setListOfPrintedSFCL(List<SingleFlightClearance> listOfPrintedSFCL) {
		this.listOfPrintedSFCL = listOfPrintedSFCL;
	}

	public void setListOfFilteredSFCL(List<SingleFlightClearance> listOfFilteredSFCL) {
		this.listOfFilteredSFCL = listOfFilteredSFCL;
	}

	public DesignTextField getPeriodfromSFCL() {
		return periodfromSFCL;
	}

	public DesignTextField getPerioduntilSFCL() {
		return perioduntilSFCL;
	}

	public DesignCheckBox getIsModifiedSFCL() {
		return isModifiedSFCL;
	}

	public DesignCheckBox getIsActiveSFCL() {
		return isActiveSFCL;
	}

	public DesignCheckBox getIsApprovedSFCL() {
		return isApprovedSFCL;
	}

	public DesignCheckBox getIsFlownAwaySFCL() {
		return isFlownAwaySFCL;
	}

	public DesignTextField getSearchSFCL() {
		return searchSFCL;
	}

	public List<JComponent> getListOfJC() {
		return listOfJC;
	}

}
