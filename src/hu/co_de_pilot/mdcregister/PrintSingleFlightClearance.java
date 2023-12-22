package hu.co_de_pilot.mdcregister;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hu.co_de_pilot.mdcregister.buttons.DesignButton;
import hu.co_de_pilot.mdcregister.buttons.PrintReportButton;

public class PrintSingleFlightClearance extends JPanel {

	private MainWindow mainWindow;

	private JLabel insertTitle;
	private DesignRadioButton dailyReportSFCL;
	private hu.co_de_pilot.datechooser.DateChooser dateOfDailyReportSFCLChooser;
	private DesignTextField dateOfDailyReportSFCL;
	private DesignCheckBox isOnlyApprovedSFCL;
	private DesignCheckBox isOnlyActiveSFCL;
	private DesignCheckBox isOnlyFlownAwaySFCL;
	private DesignButton printDRButton;
	private DesignRadioButton fullReportSFCL;
	private DesignButton printFRButton;
	private hu.co_de_pilot.datechooser.DateChooser dateFromPeriodReportSFCLChooser;
	private DesignTextField dateFromPeriodReportSFCL;
	private hu.co_de_pilot.datechooser.DateChooser dateUntilPeriodReportSFCLChooser;
	private DesignTextField dateUntilPeriodReportSFCL;
	private DesignRadioButton periodReportSFCL;
	private DesignButton printPRButton;
	private ButtonGroup printSFCLButtonGroup;

	public PrintSingleFlightClearance(MainWindow mainWindow) {

		this.mainWindow = mainWindow;

		
//		A panel címének beállítása
		insertTitle = new JLabel("EGYSZERI BEREPÜLÉSI ENGEDÉLY LEKÉRDEZÉSEK NYOMTATÁSA");
		insertTitle.setBounds(0, 10, 1000, 40);
		insertTitle.setFont(mainWindow.getFontofButtonAndTitle());
		insertTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		insertTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(insertTitle);
		
		
//		Napi jelentés űrlapelemek
		dateOfDailyReportSFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		dateOfDailyReportSFCL = new DesignTextField("Napi jelentés dátuma", null, 20, 130, 110, 53);
		dateOfDailyReportSFCL.setToolTipText("Napi jelentés dátuma");
		dateOfDailyReportSFCL.setEnabled(false);
		this.add(dateOfDailyReportSFCL);
		dateOfDailyReportSFCLChooser.setTextRefernce(dateOfDailyReportSFCL);
		
		isOnlyApprovedSFCL = new DesignCheckBox("Engedélyezett", 140, 130, 150, 50, false);
		isOnlyApprovedSFCL.setToolTipText("A jóváhagyott vagy nem jóváhagyott diplomáciai engedélyek listázásának beállítása");
		isOnlyApprovedSFCL.setSelected(true);
		isOnlyApprovedSFCL.setEnabled(false);
		this.add(isOnlyApprovedSFCL);
		
		isOnlyActiveSFCL = new DesignCheckBox("Aktív", 300, 130, 150, 50, false);
		isOnlyActiveSFCL.setToolTipText("Az aktív vagy nem aktív diplomáciai engedélyek listázásának beállítása");
		isOnlyActiveSFCL.setSelected(true);
		isOnlyActiveSFCL.setEnabled(false);
		this.add(isOnlyActiveSFCL);
		
		isOnlyFlownAwaySFCL = new DesignCheckBox("Elrepült", 460, 130, 150, 50, false);
		isOnlyFlownAwaySFCL.setToolTipText("Az elrepült vagy el nem repült diplomáciai engedélyek listázásának beállítása");
		isOnlyFlownAwaySFCL.setSelected(false);
		isOnlyFlownAwaySFCL.setEnabled(false);
		this.add(isOnlyFlownAwaySFCL);

		printDRButton = new PrintReportButton("NYOMTATÁS", "PrintDailyReportSFCL", mainWindow, "Napi Jelentés", "dailyreport_sfcl");
		printDRButton.setBounds(620, 135, 180, 30);
		printDRButton.setEnabled(false);
		this.add(printDRButton);
		
		dailyReportSFCL = new DesignRadioButton("Eseti engedély napi jelentés", 20, 70, 240, 50, false);
		dailyReportSFCL.setToolTipText("Adott dátumra, illetve az azt megelőző 3 napra szóló eseti engedélyeket tartalmazza.");
		JComponent[] dailyReportComponents = { printDRButton, dateOfDailyReportSFCL, isOnlyApprovedSFCL,
				isOnlyActiveSFCL, isOnlyFlownAwaySFCL };
		dailyReportSFCL.addItemListener(new RadioButtonAction(dailyReportComponents));
		this.add(dailyReportSFCL);
		
		
//		Teljes nyilvántartás űrlapelemek
		printFRButton = new PrintReportButton("NYOMTATÁS", "PrintFullReportSFCL", mainWindow, "Teljes Nyilvántartás", "fullreport_sfcl");
		printFRButton.setBounds(620, 205, 180, 30);
		printFRButton.setEnabled(false);
		this.add(printFRButton);
		
		fullReportSFCL = new DesignRadioButton("Eseti engedélyek teljes nyilvántartása", 20, 200, 310, 50, false);
		fullReportSFCL.setToolTipText("Az összes nyilvántartott eseti engedélyt tartalmazza.");
		JComponent[] fullReportComponents = { printFRButton };
		fullReportSFCL.addItemListener(new RadioButtonAction(fullReportComponents));
		this.add(fullReportSFCL);
		
		
//		időtartam nyilvántartás  űrlapelemek
		dateFromPeriodReportSFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		dateFromPeriodReportSFCL = new DesignTextField("Időtartam jelentés kezdődátuma", null, 370, 270, 110, 53);
		dateFromPeriodReportSFCL.setToolTipText("Időtartam jelentés kezdődátuma");
		dateFromPeriodReportSFCL.setEnabled(false);
		this.add(dateFromPeriodReportSFCL);
		dateFromPeriodReportSFCLChooser.setTextRefernce(dateFromPeriodReportSFCL);
		
		dateUntilPeriodReportSFCLChooser = new hu.co_de_pilot.datechooser.DateChooser();
		dateUntilPeriodReportSFCL = new DesignTextField("Időtartam jelentés záródátuma", null, 490, 270, 110, 53);
		dateUntilPeriodReportSFCL.setToolTipText("Időtartam jelentés záródátuma");
		dateUntilPeriodReportSFCL.setEnabled(false);
		this.add(dateUntilPeriodReportSFCL);
		dateUntilPeriodReportSFCLChooser.setTextRefernce(dateUntilPeriodReportSFCL);
		
		printPRButton = new PrintReportButton("NYOMTATÁS", "PrintPeriodReportSFCL", mainWindow,
				"Időtartam Nyilvántartás", "periodreport_sfcl");
		printPRButton.setBounds(620, 275, 180, 30);
		printPRButton.setEnabled(false);
		this.add(printPRButton);
		
		periodReportSFCL = new DesignRadioButton("Eseti engedélyek időtartam nyilvántartása", 20, 270, 340, 50, false);
		periodReportSFCL.setToolTipText("Az adott időtartamra vonatkozó eseti engedélyeket tartalmazza.");
		JComponent[] periodReportComponents = { printPRButton, dateFromPeriodReportSFCL, dateUntilPeriodReportSFCL };
		periodReportSFCL.addItemListener(new RadioButtonAction(periodReportComponents));
		this.add(periodReportSFCL);
		
		
//		A jelentési módok rádiógomb csoportjának beállítása
		printSFCLButtonGroup = new ButtonGroup();
		printSFCLButtonGroup.add(dailyReportSFCL);
		printSFCLButtonGroup.add(fullReportSFCL);
		printSFCLButtonGroup.add(periodReportSFCL);
				
	}
	

//	Getter-ek és Setter-ek
	public DesignCheckBox getIsOnlyApprovedSFCL() {
		return isOnlyApprovedSFCL;
	}

	public DesignCheckBox getIsOnlyActiveSFCL() {
		return isOnlyActiveSFCL;
	}

	public DesignCheckBox getIsOnlyFlownAwaySFCL() {
		return isOnlyFlownAwaySFCL;
	}

	public DesignTextField getDateOfDailyReportSFCL() {
		return dateOfDailyReportSFCL;
	}

	public DesignTextField getDateFromPeriodReportSFCL() {
		return dateFromPeriodReportSFCL;
	}

	public DesignTextField getDateUntilPeriodReportSFCL() {
		return dateUntilPeriodReportSFCL;
	}
	
	

}
