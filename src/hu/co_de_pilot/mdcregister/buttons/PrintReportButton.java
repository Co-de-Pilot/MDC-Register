package hu.co_de_pilot.mdcregister.buttons;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hu.co_de_pilot.mdcregister.ActualDateTime;
import hu.co_de_pilot.mdcregister.DailyReportAFCL;
import hu.co_de_pilot.mdcregister.DailyReportSFCL;
import hu.co_de_pilot.mdcregister.DesignTextField;
import hu.co_de_pilot.mdcregister.DirectoryChooser;
import hu.co_de_pilot.mdcregister.FullOrPeriodReportAFCL;
import hu.co_de_pilot.mdcregister.FullOrPeriodReportSFCL;
import hu.co_de_pilot.mdcregister.MainWindow;
import hu.co_de_pilot.mdcregister.OtherFilter;
import hu.co_de_pilot.mdcregister.PeriodFilter;
import hu.co_de_pilot.mdcregister.PopupMessages;
import hu.co_de_pilot.mdcregister.RefillTableFromList;
import hu.co_de_pilot.mdcregister.SearchAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.SearchSingleFlightClearance;

public class PrintReportButton extends DesignButton implements ActionListener {
	
	private MainWindow mainWindow;
	private String title;
	private String filename;
	private String filepath;
	private String reportDirectoryPath = "reports";
	private File pdfFile;

	
	public PrintReportButton(String text, String name, MainWindow mainWindow, String title, String filename) {
		super(text, name);
		this.mainWindow = mainWindow;
		this.title = title;
		this.filename = filename;
		this.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		ActualDateTime actualDateTime = new ActualDateTime();
		DirectoryChooser directoryChooser = new DirectoryChooser(title, reportDirectoryPath);
		filepath = directoryChooser.chooseDirectory() + "\\" + filename + "_" + actualDateTime.printFileNameDateTime()
				+ ".pdf";
		
		if (super.getName().equals("PrintDailyReportSFCL")) {
			mainWindow.getPanelSearchSFCL().setListOfPrintedSFCL(mainWindow.getPanelSearchSFCL().getListOfSFCL().stream()
					.filter(new PeriodFilter(LocalDate.parse(mainWindow.getPanelPrintSFCL().
							getDateOfDailyReportSFCL().getText())))
					.filter(new OtherFilter("print", mainWindow.getPanelPrintSFCL().getIsOnlyApprovedSFCL().isSelected(),
							mainWindow.getPanelPrintSFCL().getIsOnlyActiveSFCL().isSelected(),
							mainWindow.getPanelPrintSFCL().getIsOnlyFlownAwaySFCL().isSelected()))
					.toList());
		} else if (super.getName().equals("PrintFullReportSFCL")) {
			mainWindow.getPanelSearchSFCL().setListOfPrintedSFCL(mainWindow.getPanelSearchSFCL().getListOfSFCL());
		} else if (super.getName().equals("PrintPeriodReportSFCL")) {
			mainWindow.getPanelSearchSFCL().setListOfPrintedSFCL(mainWindow.getPanelSearchSFCL().getListOfSFCL().stream()
					.filter(new PeriodFilter(LocalDate.parse(mainWindow.getPanelPrintSFCL().getDateFromPeriodReportSFCL().getText()),
							LocalDate.parse((mainWindow.getPanelPrintSFCL().getDateUntilPeriodReportSFCL().getText()))))
					.toList());
		}
		
		if (super.getName().equals("PrintDailyReportAFCL")) {
			mainWindow.getPanelSearchAFCL().setListOfPrintedAFCL(mainWindow.getPanelSearchAFCL().getListOfAFCL().stream()
					.filter(new PeriodFilter(LocalDate.parse(mainWindow.getPanelPrintAFCL().
							getDateOfDailyReportAFCL().getText())))
					.filter(new OtherFilter("print", mainWindow.getPanelPrintAFCL().getIsOnlyApprovedAFCL().isSelected(),
							mainWindow.getPanelPrintAFCL().getIsOnlyActiveAFCL().isSelected(), false))
					.toList());
		} else if (super.getName().equals("PrintFullReportAFCL")) {
			mainWindow.getPanelSearchAFCL().setListOfPrintedAFCL(mainWindow.getPanelSearchAFCL().getListOfAFCL());
		} else if (super.getName().equals("PrintPeriodReportAFCL")) {
			mainWindow.getPanelSearchAFCL().setListOfPrintedAFCL(mainWindow.getPanelSearchAFCL().getListOfAFCL().stream()
					.filter(new PeriodFilter(LocalDate.parse(mainWindow.getPanelPrintAFCL().getDateFromPeriodReportAFCL().getText()),
							LocalDate.parse((mainWindow.getPanelPrintAFCL().getDateUntilPeriodReportAFCL().getText()))))
					.toList());
		}
		
		try {
			if (super.getName().equals("PrintDailyReportSFCL")) {
				new DailyReportSFCL(title, LocalDate.parse(mainWindow.getPanelPrintSFCL().getDateOfDailyReportSFCL().getText()),
						mainWindow.getPanelSearchSFCL().getListOfPrintedSFCL())
				.createPdf(filepath);
			} else if (super.getName().equals("PrintFullReportSFCL")) {
				new FullOrPeriodReportSFCL(title, null, null, mainWindow.getPanelSearchSFCL().getListOfPrintedSFCL()).createPdf(filepath);
			} else if (super.getName().equals("PrintPeriodReportSFCL")) {
				new FullOrPeriodReportSFCL(title, LocalDate.parse(mainWindow.getPanelPrintSFCL().getDateFromPeriodReportSFCL().getText()),
						LocalDate.parse(mainWindow.getPanelPrintSFCL().getDateUntilPeriodReportSFCL().getText()),
						mainWindow.getPanelSearchSFCL().getListOfPrintedSFCL()).createPdf(filepath);
			}
			if (super.getName().equals("PrintDailyReportAFCL")) {
				new DailyReportAFCL(title, LocalDate.parse(mainWindow.getPanelPrintAFCL().getDateOfDailyReportAFCL().getText()),
						mainWindow.getPanelSearchAFCL().getListOfPrintedAFCL())
				.createPdf(filepath);
			} else if (super.getName().equals("PrintFullReportAFCL")) {
				new FullOrPeriodReportAFCL(title, null, null, mainWindow.getPanelSearchAFCL().getListOfPrintedAFCL()).createPdf(filepath);
			} else if (super.getName().equals("PrintPeriodReportAFCL")) {
				new FullOrPeriodReportAFCL(title, LocalDate.parse(mainWindow.getPanelPrintAFCL().getDateFromPeriodReportAFCL().getText()),
						LocalDate.parse(mainWindow.getPanelPrintAFCL().getDateUntilPeriodReportAFCL().getText()),
						mainWindow.getPanelSearchAFCL().getListOfPrintedAFCL()).createPdf(filepath);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"A jelentés nyomtatása nem sikerült. Kérje az adminisztrátor segítségét!", "Jelentés nyomtatása",
					JOptionPane.OK_OPTION);
		}
		int confirm = PopupMessages.showConfirmMessage(null,
				"A jelentés nyomtatása a kiválasztott mappában " + filename + "_"
						+ actualDateTime.printFileNameDateTime() + ".pdf néven sikeresen megtörtént."
				+"\nKívánja megnyitni a PDF fájlt?", "PDF fájl megnyitása");
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				pdfFile = new File(filepath);
				
				if (pdfFile.exists()) {
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().open(pdfFile);
					} else {
						System.out.println("Awt Desktop is not supported!");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"A jelzett elérési úton nem található a megadott fájl!", "PDF fájl megnyitása",
							JOptionPane.OK_OPTION);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"A fájl nem nyitható meg!", "PDF fájl megnyitása",
						JOptionPane.OK_OPTION);
			}

		}
	}


}
