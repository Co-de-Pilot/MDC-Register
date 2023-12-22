/**
 * 
 */
package hu.co_de_pilot.mdcregister;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import hu.co_de_pilot.mdcregister.buttons.MenuButton;

/**
 * @author CODE-PILOT piratosnogi@gmail.com
 *
 * TODO List:  
 * 1. AFCL Aircraft DTF ürítése és disable in CollectAircraftDatasFromJComp and CancelButton
 * 
 *
 */
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panelMenu;
	private JPanel panelLogo;
	private BufferedImage logo;
	private JLabel logoLabel;
	private String logopath = "resources\\logo\\ktik_logo.gif";
	private JLabel labelAFCLfirstrow;
	private JLabel labelAFCLsecondrow;
	private JLabel labelAFCLsecondrow_1;
	private MenuButton menubuttonSearchAFCL;
	private MenuButton menubuttonInsertAFCL;
	private MenuButton menubuttonReportAFCL;
	private JLabel labelSFCLfirstrow;
	private MenuButton menubuttonSearchSFCL;
	private MenuButton menubuttonInsertSFCL;
	private MenuButton menubuttonReportSFCL;
	private JPanel panelTitle;
	private JLabel titleLabel;
	public SearchAnnualFlightClearance panelSearchAFCL;
	public InsertAnnualFlightClearance panelInsertAFCL;
	public PrintAnnualFlightClearance panelPrintAFCL;
	public SearchSingleFlightClearance panelSearchSFCL;
	public InsertSingleFlightClearance panelInsertSFCL;
	public PrintSingleFlightClearance panelPrintSFCL;
	public JPanel actualPanel;
	public Font fontofButtonAndTitle = new Font("Stencil", Font.BOLD, 23);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow mainWindow = new MainWindow();
					mainWindow.setVisible(true);
//					new DatabaseConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() throws IOException {

		this.setResizable(false);
		this.setTitle("Katonai Dipomáciai Engedélyek Nyilvántartása");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255));
		this.setContentPane(contentPane);

		panelLogo = new JPanel();
		panelLogo.setBackground(new Color(255, 255, 255));
		panelLogo.setBounds(0, 0, 180, 180);
		try {
			logo = new BufferedImage(180, 180, BufferedImage.TYPE_INT_ARGB);
			logo = ImageIO.read(new File(logopath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		logoLabel = new JLabel(new ImageIcon(logo));
		panelLogo.add(logoLabel);
		contentPane.add(panelLogo);

		panelSearchSFCL = new SearchSingleFlightClearance(this);
		panelSearchSFCL.setBackground(new Color(255, 255, 255));
		panelSearchSFCL.setBounds(180, 60, 1000, 700);
		panelSearchSFCL.setVisible(true);
		this.setActualPanel(panelSearchSFCL);
		panelSearchSFCL.setLayout(null);
		contentPane.add(panelSearchSFCL);

		panelInsertSFCL = new InsertSingleFlightClearance(this);
		panelInsertSFCL.setBackground(new Color(255, 255, 255));
		panelInsertSFCL.setBounds(180, 60, 1000, 700);
		panelInsertSFCL.setVisible(false);
		panelInsertSFCL.setLayout(null);
		contentPane.add(panelInsertSFCL);

		panelPrintSFCL = new PrintSingleFlightClearance(this);
		panelPrintSFCL.setBackground(new Color(255, 255, 255));
		panelPrintSFCL.setBounds(180, 60, 1000, 700);
		panelPrintSFCL.setVisible(false);
		panelPrintSFCL.setLayout(null);
		contentPane.add(panelPrintSFCL);
		
		panelSearchAFCL = new SearchAnnualFlightClearance(this);
		panelSearchAFCL.setBackground(new Color(255, 255, 255));
		panelSearchAFCL.setBounds(180, 60, 1000, 700);
		panelSearchAFCL.setVisible(false);
		panelSearchAFCL.setLayout(null);
		contentPane.add(panelSearchAFCL);

		panelInsertAFCL = new InsertAnnualFlightClearance(this);
		panelInsertAFCL.setBackground(new Color(255, 255, 255));
		panelInsertAFCL.setBounds(180, 60, 1000, 700);
		panelInsertAFCL.setVisible(false);
		panelInsertAFCL.setLayout(null);
		contentPane.add(panelInsertAFCL);
		
		panelPrintAFCL = new PrintAnnualFlightClearance(this);
		panelPrintAFCL.setBackground(new Color(255, 255, 255));
		panelPrintAFCL.setBounds(180, 60, 1000, 700);
		panelPrintAFCL.setVisible(false);
		panelPrintAFCL.setLayout(null);
		contentPane.add(panelPrintAFCL);
		
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(255, 255, 255));
		panelMenu.setBounds(0, 180, 180, 580);
		panelMenu.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		labelAFCLfirstrow = new JLabel("ÉVES");
		labelAFCLfirstrow.setVerticalAlignment(SwingConstants.BOTTOM);
		labelAFCLfirstrow.setHorizontalTextPosition(SwingConstants.CENTER);
		labelAFCLfirstrow.setHorizontalAlignment(SwingConstants.CENTER);
		labelAFCLfirstrow.setBounds(10, 10, 160, 25);
		labelAFCLfirstrow.setFont(fontofButtonAndTitle);
		panelMenu.add(labelAFCLfirstrow);

		labelAFCLsecondrow = new JLabel("ENGEDÉLYEK");
		labelAFCLsecondrow.setVerticalAlignment(SwingConstants.BOTTOM);
		labelAFCLsecondrow.setHorizontalTextPosition(SwingConstants.CENTER);
		labelAFCLsecondrow.setHorizontalAlignment(SwingConstants.CENTER);
		labelAFCLsecondrow.setBounds(10, 35, 160, 25);
		labelAFCLsecondrow.setFont(fontofButtonAndTitle);
		panelMenu.add(labelAFCLsecondrow);

		menubuttonSearchAFCL = new MenuButton("KERESÉS", "Menu", this, panelSearchAFCL);
		menubuttonSearchAFCL.setBounds(10, 70, 160, 28);
		panelMenu.add(menubuttonSearchAFCL);

		menubuttonInsertAFCL = new MenuButton("RÖGZÍTÉS", "Menu", this, panelInsertAFCL);
		menubuttonInsertAFCL.setBounds(10, 108, 160, 28);
		panelMenu.add(menubuttonInsertAFCL);

		menubuttonReportAFCL = new MenuButton("NYOMTATÁS", "Menu", this, panelPrintAFCL);
		menubuttonReportAFCL.setBounds(10, 146, 160, 28);
		panelMenu.add(menubuttonReportAFCL);

		labelSFCLfirstrow = new JLabel("ESETI");
		labelSFCLfirstrow.setVerticalAlignment(SwingConstants.BOTTOM);
		labelSFCLfirstrow.setHorizontalTextPosition(SwingConstants.CENTER);
		labelSFCLfirstrow.setHorizontalAlignment(SwingConstants.CENTER);
		labelSFCLfirstrow.setFont(fontofButtonAndTitle);
		labelSFCLfirstrow.setBounds(10, 224, 160, 25);
		panelMenu.add(labelSFCLfirstrow);

		labelAFCLsecondrow_1 = new JLabel("ENGEDÉLYEK");
		labelAFCLsecondrow_1.setVerticalAlignment(SwingConstants.BOTTOM);
		labelAFCLsecondrow_1.setHorizontalTextPosition(SwingConstants.CENTER);
		labelAFCLsecondrow_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelAFCLsecondrow_1.setFont(fontofButtonAndTitle);
		labelAFCLsecondrow_1.setBounds(10, 249, 160, 25);
		panelMenu.add(labelAFCLsecondrow_1);

		menubuttonSearchSFCL = new MenuButton("KERESÉS", "Menu", this, panelSearchSFCL);
		menubuttonSearchSFCL.setBounds(10, 284, 160, 28);
		panelMenu.add(menubuttonSearchSFCL);

		menubuttonInsertSFCL = new MenuButton("RÖGZÍTÉS", "Menu", this, panelInsertSFCL);
		menubuttonInsertSFCL.setBounds(10, 322, 160, 28);
		panelMenu.add(menubuttonInsertSFCL);

		menubuttonReportSFCL = new MenuButton("NYOMTATÁS", "Menu", this, panelPrintSFCL);
		menubuttonReportSFCL.setBounds(10, 360, 160, 28);
		panelMenu.add(menubuttonReportSFCL);

		panelTitle = new JPanel();
		panelTitle.setBackground(new Color(255, 255, 255));
		panelTitle.setBounds(180, 0, 1000, 60);
		panelTitle.setLayout(null);
		contentPane.add(panelTitle);

		titleLabel = new JLabel("Katonai Diplomáciai Engedélyek Nyilvántartása");
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setFont(fontofButtonAndTitle);
		titleLabel.setBounds(90, 10, 850, 50);
		panelTitle.add(titleLabel);

//		panelSearchSFCL.getListOfSFCL()
//				.add(new SingleFlightClearance("HU-1/121/2023", "Katar", "QR-AAA", "A321", "QTR1111",
//						LocalDate.of(2023, 7, 11), "OTBD", "LHBP", "Head Flight", "Megjegyzés 121", getPanelInsertSFCL(),
//						getPanelSearchSFCL()));
//		panelSearchSFCL.getListOfSFCL()
//				.add(new SingleFlightClearance("HU-1/124/2023", "Japán", "JP-AAA", "B737", "JAF1111",
//						LocalDate.of(2023, 7, 15), "RJBE", "EDDF", "VIP Flight", "Megjegyzés 124", getPanelInsertSFCL(),
//						getPanelSearchSFCL()));
//		panelSearchSFCL.getListOfSFCL()
//				.add(new SingleFlightClearance("HU-1/122/2023", "Japán", "JP-AAA", "B737", "JAF1111",
//						LocalDate.of(2023, 7, 13), "RJBE", "EDDF", "Hazardous Cargo", "Megjegyzés 122",
//						getPanelInsertSFCL(), getPanelSearchSFCL()));
//		panelSearchSFCL.getListOfSFCL()
//				.add(new SingleFlightClearance("HU-1/123/2023", "Kína", "CH-AAA", "B77L", "CHF1111",
//						LocalDate.of(2023, 7, 14), "ZBAA", "EGKK", "VIP Flight", "Megjegyzés 123", getPanelInsertSFCL(),
//						getPanelSearchSFCL()));
//		panelSearchSFCL.getListOfSFCL()
//				.add(new SingleFlightClearance("HU-1/125/2023", "Kína", "CH-AAA", "B77L", "CHF1111",
//						LocalDate.of(2023, 7, 16), "ZBAA", "EGKK", "Head Flight", "Megjegyzés 125", getPanelInsertSFCL(),
//						getPanelSearchSFCL()));
				
		new SerializationSFCL(true, panelInsertSFCL, panelSearchSFCL);

		new RefillTableFromList(getPanelSearchSFCL().getSingleFlightClearanceTableModel(),
				getPanelSearchSFCL().getSingleFlightClearanceTableColumnNames().length,
				panelSearchSFCL.getListOfSFCL(), null);

		
//		panelSearchAFCL.getListOfAFCL()
//		.add(new AnnualFlightClearance("HU-1/121/2023", "Katar",
//				new ArrayList<>(Arrays.asList(new Aircraft("QR-AAA", "A321", "QTR1111"),
//						new Aircraft("CH-AAA", "B77L", "CHF1111"), new Aircraft("QR-BBB", "A319", "QTR2222"))),
//				LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), "Megjegyzés 121", getPanelInsertAFCL(),
//				getPanelSearchAFCL()));
//		panelSearchAFCL.getListOfAFCL()
//		.add(new AnnualFlightClearance("HU-1/122/2023", "Irán",
//				new ArrayList<>(Arrays.asList(new Aircraft("IR-AAA", "A321", "IRA1111"),
//						new Aircraft("II-AAA", "B77L", "IRN1111"), new Aircraft("IR-BBB", "A319", "IRR2222"))),
//				LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), "Megjegyzés 122", getPanelInsertAFCL(),
//				getPanelSearchAFCL()));
//		panelSearchAFCL.getListOfAFCL()
//		.add(new AnnualFlightClearance("HU-1/123/2023", "Egyiptom",
//				new ArrayList<>(Arrays.asList(new Aircraft("EG-AAA", "A321", "EGY1111"),
//						new Aircraft("EG-BBB", "B77L", "EGY2222"), new Aircraft("EG-CCC", "A319", "EGY3333"))),
//				LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), "Megjegyzés 123", getPanelInsertAFCL(),
//				getPanelSearchAFCL()));

		new SerializationAFCL(true, panelInsertAFCL, panelSearchAFCL);
		
		new RefillTableFromList(getPanelSearchAFCL().getAnnualFlightClearanceTableModel(),
				getPanelSearchAFCL().getAnnualFlightClearanceTableColumnNames().length, null,
				panelSearchAFCL.getListOfAFCL());
		
		System.out.println("Üdvözlet a MDC Register programban!");
		System.out.println("Amennyiben a program valamilyen hibát, vagy kivételt produkál, azt ezen a felületen fogja kiírni.");
		System.out.println("Kérlek segítsd a program fejlesztését azzal, hogy az itt kiírt hibaüzeneteket elküldöd a fejlesztő"
				+ " Nógrádi József százados nogradi.jozsef@mil.hu e-mail címére.");
		System.out.println("Segítségedet köszönöm!");
	
	}

	public SearchSingleFlightClearance getPanelSearchSFCL() {
		return panelSearchSFCL;
	}

	public InsertSingleFlightClearance getPanelInsertSFCL() {
		return panelInsertSFCL;
	}

	public PrintSingleFlightClearance getPanelPrintSFCL() {
		return panelPrintSFCL;
	}

	public SearchAnnualFlightClearance getPanelSearchAFCL() {
		return panelSearchAFCL;
	}

	public InsertAnnualFlightClearance getPanelInsertAFCL() {
		return panelInsertAFCL;
	}

	public PrintAnnualFlightClearance getPanelPrintAFCL() {
		return panelPrintAFCL;
	}

	public JPanel getActualPanel() {
		return actualPanel;
	}

	public void setActualPanel(JPanel actualPanel) {
		this.actualPanel = actualPanel;
	}

	public Font getFontofButtonAndTitle() {
		return fontofButtonAndTitle;
	}

}
