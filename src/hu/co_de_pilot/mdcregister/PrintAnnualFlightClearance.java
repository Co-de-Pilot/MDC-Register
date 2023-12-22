package hu.co_de_pilot.mdcregister;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hu.co_de_pilot.mdcregister.buttons.DesignButton;
import hu.co_de_pilot.mdcregister.buttons.PrintReportButton;

public class PrintAnnualFlightClearance extends JPanel {

	private MainWindow mainWindow;

	private JLabel insertTitle;
	private DesignRadioButton dailyReportAFCL;
	private hu.co_de_pilot.datechooser.DateChooser dateOfDailyReportAFCLChooser;
	private DesignTextField dateOfDailyReportAFCL;
	private DesignCheckBox isOnlyApprovedAFCL;
	private DesignCheckBox isOnlyActiveAFCL;
	private DesignButton printDRButton;
	private DesignRadioButton fullReportAFCL;
	private DesignButton printFRButton;
	private hu.co_de_pilot.datechooser.DateChooser dateFromPeriodReportAFCLChooser;
	private DesignTextField dateFromPeriodReportAFCL;
	private hu.co_de_pilot.datechooser.DateChooser dateUntilPeriodReportAFCLChooser;
	private DesignTextField dateUntilPeriodReportAFCL;
	private DesignRadioButton periodReportAFCL;
	private DesignButton printPRButton;
	private ButtonGroup printAFCLButtonGroup;

	public PrintAnnualFlightClearance(MainWindow mainWindow) {

		this.mainWindow = mainWindow;

		
//		A panel címének beállítása
		insertTitle = new JLabel("ÉVES BEREPÜLÉSI ENGEDÉLY LEKÉRDEZÉSEK NYOMTATÁSA");
		insertTitle.setBounds(0, 10, 1000, 40);
		insertTitle.setFont(mainWindow.getFontofButtonAndTitle());
		insertTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		insertTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(insertTitle);
		
		
//		Napi jelentés űrlapelemek
		dateOfDailyReportAFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		dateOfDailyReportAFCL = new DesignTextField("Napi jelentés dátuma", null, 20, 130, 110, 53);
		dateOfDailyReportAFCL.setToolTipText("Napi jelentés dátuma");
		dateOfDailyReportAFCL.setEnabled(false);
		this.add(dateOfDailyReportAFCL);
		dateOfDailyReportAFCLChooser.setTextRefernce(dateOfDailyReportAFCL);
		
		isOnlyApprovedAFCL = new DesignCheckBox("Engedélyezett", 140, 130, 150, 50, false);
		isOnlyApprovedAFCL.setToolTipText("A jóváhagyott vagy nem jóváhagyott diplomáciai engedélyek listázásának beállítása");
		isOnlyApprovedAFCL.setSelected(true);
		isOnlyApprovedAFCL.setEnabled(false);
		this.add(isOnlyApprovedAFCL);
		
		isOnlyActiveAFCL = new DesignCheckBox("Aktív", 300, 130, 150, 50, false);
		isOnlyActiveAFCL.setToolTipText("Az aktív vagy nem aktív diplomáciai engedélyek listázásának beállítása");
		isOnlyActiveAFCL.setSelected(true);
		isOnlyActiveAFCL.setEnabled(false);
		this.add(isOnlyActiveAFCL);
		
		printDRButton = new PrintReportButton("NYOMTATÁS", "PrintDailyReportAFCL", mainWindow, "Napi Jelentés", "dailyreport_afcl");
		printDRButton.setBounds(620, 135, 180, 30);
		printDRButton.setEnabled(false);
		this.add(printDRButton);
		
		dailyReportAFCL = new DesignRadioButton("Éves engedélyek napi jelentése", 20, 70, 270, 50, false);
		dailyReportAFCL.setToolTipText("Adott dátumkor érvényes éves engedélyeket tartalmazza.");
		JComponent[] dailyReportComponents = { printDRButton, dateOfDailyReportAFCL, isOnlyApprovedAFCL,
				isOnlyActiveAFCL };
		dailyReportAFCL.addItemListener(new RadioButtonAction(dailyReportComponents));
		this.add(dailyReportAFCL);
		
		
//		Teljes nyilvántartás űrlapelemek
		printFRButton = new PrintReportButton("NYOMTATÁS", "PrintFullReportAFCL", mainWindow, "Teljes Nyilvántartás", "fullreport_afcl");
		printFRButton.setBounds(620, 205, 180, 30);
		printFRButton.setEnabled(false);
		this.add(printFRButton);
		
		fullReportAFCL = new DesignRadioButton("Éves engedélyek teljes nyilvántartása", 20, 200, 310, 50, false);
		fullReportAFCL.setToolTipText("Az összes nyilvántartott éves engedélyt tartalmazza.");
		JComponent[] fullReportComponents = { printFRButton };
		fullReportAFCL.addItemListener(new RadioButtonAction(fullReportComponents));
		this.add(fullReportAFCL);
		
		
//		időtartam nyilvántartás  űrlapelemek
		dateFromPeriodReportAFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		dateFromPeriodReportAFCL = new DesignTextField("Időtartam jelentés kezdődátuma", null, 370, 270, 110, 53);
		dateFromPeriodReportAFCL.setToolTipText("Időtartam jelentés kezdődátuma");
		dateFromPeriodReportAFCL.setEnabled(false);
		this.add(dateFromPeriodReportAFCL);
		dateFromPeriodReportAFCLChooser.setTextRefernce(dateFromPeriodReportAFCL);
		
		dateUntilPeriodReportAFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		dateUntilPeriodReportAFCL = new DesignTextField("Időtartam jelentés záródátuma", null, 490, 270, 110, 53);
		dateUntilPeriodReportAFCL.setToolTipText("Időtartam jelentés záródátuma");
		dateUntilPeriodReportAFCL.setEnabled(false);
		this.add(dateUntilPeriodReportAFCL);
		dateUntilPeriodReportAFCLChooser.setTextRefernce(dateUntilPeriodReportAFCL);
		
		printPRButton = new PrintReportButton("NYOMTATÁS", "PrintPeriodReportAFCL", mainWindow,
				"Időtartam Nyilvántartás", "periodreport_afcl");
		printPRButton.setBounds(620, 275, 180, 30);
		printPRButton.setEnabled(false);
		this.add(printPRButton);
		
		periodReportAFCL = new DesignRadioButton("Éves engedélyek időtartam nyilvántartása", 20, 270, 340, 50, false);
		periodReportAFCL.setToolTipText("Az adott időtartamra vonatkozó éves engedélyeket tartalmazza.");
		JComponent[] periodReportComponents = { printPRButton, dateFromPeriodReportAFCL, dateUntilPeriodReportAFCL };
		periodReportAFCL.addItemListener(new RadioButtonAction(periodReportComponents));
		this.add(periodReportAFCL);
		
		
//		A jelentési módok rádiógomb csoportjának beállítása
		printAFCLButtonGroup = new ButtonGroup();
		printAFCLButtonGroup.add(dailyReportAFCL);
		printAFCLButtonGroup.add(fullReportAFCL);
		printAFCLButtonGroup.add(periodReportAFCL);
				
	}
	

//	Getter-ek és Setter-ek
	public DesignCheckBox getIsOnlyApprovedAFCL() {
		return isOnlyApprovedAFCL;
	}

	public DesignCheckBox getIsOnlyActiveAFCL() {
		return isOnlyActiveAFCL;
	}

	public DesignTextField getDateOfDailyReportAFCL() {
		return dateOfDailyReportAFCL;
	}

	public DesignTextField getDateFromPeriodReportAFCL() {
		return dateFromPeriodReportAFCL;
	}

	public DesignTextField getDateUntilPeriodReportAFCL() {
		return dateUntilPeriodReportAFCL;
	}

}
