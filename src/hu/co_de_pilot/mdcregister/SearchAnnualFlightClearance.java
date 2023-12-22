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

public class SearchAnnualFlightClearance extends JPanel {

	private List<AnnualFlightClearance> listOfAFCL = new ArrayList<>();
	private List<AnnualFlightClearance> listOfFilteredAFCL = new ArrayList<>();
	private List<AnnualFlightClearance> listOfPrintedAFCL = new ArrayList<>();
	
	private MainWindow mainWindow;

	private List<JComponent> listOfJC = new ArrayList<>();
	private JLabel insertTitle;
	private DesignRadioButton dinamicSearchAFCL;
	private DesignTextField searchAFCL;
	private ClearFiltersButton clearFiltersButton;
	private DesignButton serializeListButton;
	private DesignRadioButton periodSearchAFCL;
	private hu.co_de_pilot.datechooser.DateChooser dateFromSearchAFCLChooser;
	private hu.co_de_pilot.datechooser.DateChooser dateUntilSearchAFCLChooser;
	private DesignTextField periodfromAFCL;
	private DesignTextField perioduntilAFCL;
	private DesignButton searchPeriodAFCLButton;
	private DesignRadioButton otherFilterAFCL;
	private DesignCheckBox isModifiedAFCL;
	private DesignCheckBox isActiveAFCL;
	private DesignCheckBox isApprovedAFCL;
	private DesignButton searchOtherFilterAFCLButton;
	private ButtonGroup searchAFCLButtonGroup;
	private FinderTable annualFlightClearanceTable;
	private DefaultTableModel annualFlightClearanceTableModel;
	private String[] annualFlightClearanceTableColumnNames = { "<html><center>KKM<br>nyt. szám</html>",
			"<html><center>Regisztráló<br>ország</html>", "<html><center>Érvényesség<br>kezdődátuma</html>",
			"<html><center>Érvényesség<br>záródátuma</html>", "<html><center>Repülőgép<br>típusa</html>",
			"<html><center>Repülőgép<br>lajstromjele</html>", "<html><center>Repülőgép<br>hívójele</html>",
			"<html><center>Megjegyzés</html>", "<html><center>Enge-<br>délyezett</html>", "<html><center>Aktív</html>",
			"<html><center>Módo-<br>sított</html>",	"", "", "" };
	private JScrollPane singleFlightClearanceTableScrollPane;

	public SearchAnnualFlightClearance(MainWindow mainWindow) {

		this.mainWindow = mainWindow;
		
		
//		A kereső táblázat beállítása
		int[] tableColumnWidths = { 80, 120, 80, 80, 60, 60, 60, 220, 50, 40, 40, 20, 20, 20 };
		annualFlightClearanceTableModel = new DefaultTableModel(annualFlightClearanceTableColumnNames, 0);

		annualFlightClearanceTable = new FinderTable(annualFlightClearanceTableColumnNames, tableColumnWidths,
				annualFlightClearanceTableModel);

		TableRowSorter<DefaultTableModel> annualFlightClearanceTableSorter = new TableRowSorter<DefaultTableModel>(
				annualFlightClearanceTableModel);
		annualFlightClearanceTable.setRowSorter(annualFlightClearanceTableSorter);

		singleFlightClearanceTableScrollPane = new DesignScrollPane(annualFlightClearanceTable, null, 30, 250, 950, 430);
		singleFlightClearanceTableScrollPane.setVisible(true);
		this.add(singleFlightClearanceTableScrollPane);

		
//		A panel címének beállítása
		insertTitle = new JLabel("ÉVES BEREPÜLÉSI ENGEDÉLY KERESÉSE");
		insertTitle.setBounds(0, 10, 1000, 40);
		insertTitle.setFont(mainWindow.getFontofButtonAndTitle());
		insertTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		insertTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(insertTitle);
		
		
//		Dinamikus keresési mód űrlapelemek
		searchAFCL = new DesignTextField("Dinamikus keresés", null, 250, 70, 230, 53);
		searchAFCL.setEnabled(false);
		searchAFCL.getDocument()
				.addDocumentListener(new DocumentAction(searchAFCL, annualFlightClearanceTableSorter));
		this.add(searchAFCL);
		listOfJC.add(searchAFCL);

		dinamicSearchAFCL = new DesignRadioButton("Dinamikus keresés", 30, 70, 200, 50, false);
		JComponent[] dinamicSearchComponents = { searchAFCL };
		dinamicSearchAFCL.addItemListener(new RadioButtonAction(dinamicSearchComponents));
		this.add(dinamicSearchAFCL);

		
//		Időintervallum keresési mód űrlapelemek
//		Fordított időintervallumon, vagy egyetlen érvényességi dátum lekérdezésen elgondolkozni!!!
		dateFromSearchAFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		periodfromAFCL = new DesignTextField("Időponttól keresés", null, 250, 130, 110, 53);
		periodfromAFCL.setToolTipText("Keresési időintervallum kezdete");
		periodfromAFCL.setEnabled(false);
		this.add(periodfromAFCL);
		listOfJC.add(periodfromAFCL);
		dateFromSearchAFCLChooser.setTextRefernce(periodfromAFCL);

		dateUntilSearchAFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		perioduntilAFCL = new DesignTextField("Időpontig keresés", null, 370, 130, 110, 53);
		perioduntilAFCL.setToolTipText("Keresési időintervallum vége");
		perioduntilAFCL.setEnabled(false);
		this.add(perioduntilAFCL);
		listOfJC.add(perioduntilAFCL);
		dateUntilSearchAFCLChooser.setTextRefernce(perioduntilAFCL);

		searchPeriodAFCLButton = new SearchPeriodButton("KERESÉS", "SearchPeriod", this, null);
		searchPeriodAFCLButton.setBounds(500, 135, 180, 30);
		searchPeriodAFCLButton.setEnabled(false);
		this.add(searchPeriodAFCLButton);

		periodSearchAFCL = new DesignRadioButton("Időintervallum keresés", 30, 130, 200, 50, false);
		JComponent[] periodSearchComponents = { periodfromAFCL, perioduntilAFCL, searchPeriodAFCLButton };
		periodSearchAFCL.addItemListener(new RadioButtonAction(periodSearchComponents));
		this.add(periodSearchAFCL);

//		Egyéb szűrési mód beállítása
		isModifiedAFCL = new DesignCheckBox("Módosított", 250, 190, 120, 50, false);
		isModifiedAFCL.setEnabled(false);
		this.add(isModifiedAFCL);
		listOfJC.add(isModifiedAFCL);

		isActiveAFCL = new DesignCheckBox("Aktív", 380, 190, 120, 50, false);
		isActiveAFCL.setEnabled(false);
		this.add(isActiveAFCL);
		listOfJC.add(isActiveAFCL);

		isApprovedAFCL = new DesignCheckBox("Engedélyezett", 510, 190, 140, 50, false);
		isApprovedAFCL.setEnabled(false);
		this.add(isApprovedAFCL);
		listOfJC.add(isApprovedAFCL);
		
		searchOtherFilterAFCLButton = new SearchOtherFilterButton("KERESÉS", "OtherFilter", this, null);
		searchOtherFilterAFCLButton.setBounds(670, 195, 180, 30);
		searchOtherFilterAFCLButton.setEnabled(false);
		this.add(searchOtherFilterAFCLButton);

		otherFilterAFCL = new DesignRadioButton("Egyéb szűrés", 30, 190, 200, 50, false);
		JComponent[] otherFilterComponents = { isModifiedAFCL, isActiveAFCL, isApprovedAFCL, searchOtherFilterAFCLButton };
		otherFilterAFCL.addItemListener(new RadioButtonAction(otherFilterComponents));
		this.add(otherFilterAFCL);

		
//		A keresési módok rádiógomb csoportjának beállítása
		searchAFCLButtonGroup = new ButtonGroup();
		searchAFCLButtonGroup.add(dinamicSearchAFCL);
		searchAFCLButtonGroup.add(periodSearchAFCL);
		searchAFCLButtonGroup.add(otherFilterAFCL);

		
//		TELJES LISTA(Szűrők törlése űrlapelem
		clearFiltersButton = new ClearFiltersButton("TELJES LISTA", "ClearFilters", this, null);
		clearFiltersButton.setBounds(600, 75, 180, 30);
		this.add(clearFiltersButton);
		
		
//		LISTA MENTÉSE(Szerializációval .obj fájlba) beállítása
		serializeListButton = new SerializeButton("LISTA MENTÉSE", "SaveList", mainWindow.getPanelInsertAFCL(), this, null, null);
		serializeListButton.setBounds(800, 75, 180, 30);
		this.add(serializeListButton);
		
	}

	
//	Getter-ek és Setter-ek
	public List<AnnualFlightClearance> getListOfAFCL() {
		return listOfAFCL;
	}

	public List<AnnualFlightClearance> getListOfFilteredAFCL() {
		return listOfFilteredAFCL;
	}

	public void setListOfFilteredAFCL(List<AnnualFlightClearance> listOfFilteredAFCL) {
		this.listOfFilteredAFCL = listOfFilteredAFCL;
	}

	public List<AnnualFlightClearance> getListOfPrintedAFCL() {
		return listOfPrintedAFCL;
	}

	public void setListOfPrintedAFCL(List<AnnualFlightClearance> listOfPrintedAFCL) {
		this.listOfPrintedAFCL = listOfPrintedAFCL;
	}

	public FinderTable getAnnualFlightClearanceTable() {
		return annualFlightClearanceTable;
	}

	public DefaultTableModel getAnnualFlightClearanceTableModel() {
		return annualFlightClearanceTableModel;
	}

	public String[] getAnnualFlightClearanceTableColumnNames() {
		return annualFlightClearanceTableColumnNames;
	}

	public DesignTextField getPeriodfromAFCL() {
		return periodfromAFCL;
	}

	public DesignTextField getPerioduntilAFCL() {
		return perioduntilAFCL;
	}

	public DesignCheckBox getIsModifiedAFCL() {
		return isModifiedAFCL;
	}

	public DesignCheckBox getIsActiveAFCL() {
		return isActiveAFCL;
	}

	public DesignCheckBox getIsApprovedAFCL() {
		return isApprovedAFCL;
	}

	public DesignTextField getSearchAFCL() {
		return searchAFCL;
	}

	public List<JComponent> getListOfJC() {
		return listOfJC;
	}
	
	
}
