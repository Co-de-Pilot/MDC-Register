package hu.co_de_pilot.mdcregister;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class DailyReportSFCL {
	private String logopath = "resources\\logo\\ktik_report_logo.png";
	private String watermarkpath = "resources\\watermark_portrait.jpg";
	private String title;
	private LocalDate dateOfReport;
	private List<SingleFlightClearance> listOfPrintedSFCL;

	public DailyReportSFCL(String title, LocalDate dateOfReport, List<SingleFlightClearance> listOfPrintedSFCL) {
		this.title = title;
		this.dateOfReport = dateOfReport;
		this.listOfPrintedSFCL = listOfPrintedSFCL;
	}

	public void createPdf(String dest) throws IOException {
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		pdf.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdf);
		document.setMargins(20, 20, 20, 20);
		PdfFont boldFont = PdfFontFactory.createFont(FontConstants.TIMES_BOLD, PdfEncodings.CP1250, false);
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN, PdfEncodings.CP1250, false);
		Image watermark = new Image(ImageDataFactory.create(watermarkpath));
		watermark.setFixedPosition(pdf.getDefaultPageSize().getWidth() / 2 - watermark.getImageWidth() / 2,
				pdf.getDefaultPageSize().getHeight() / 2 - watermark.getImageHeight() / 2);
		document.add(watermark);

		float[] headerColumnWidth = { 110f, 720f };
		Table headerTable = new Table(headerColumnWidth);
		headerTable.setBorderBottom(new SolidBorder(1));
		Image headerimage = new Image(ImageDataFactory.create(logopath));
		Cell imageCell = new Cell().add(headerimage);
		imageCell.setBorder(Border.NO_BORDER);
		headerTable.addCell(imageCell);
		Text header = new Text(
				"Magyar Honvédség Légi Műveleti Vezetési és Irányítási Központ\nLégi Irányító Központ"
				+ "\nKiképző Tartalék Irányító Központ\nKecskemét");
		header.setFont(boldFont);
		header.setFontSize(14);
		Cell textCell = new Cell();
		Paragraph p1 = new Paragraph(header);
		p1.setTextAlignment(TextAlignment.LEFT);
		textCell.add(p1).setVerticalAlignment(VerticalAlignment.BOTTOM);
		textCell.setBorder(Border.NO_BORDER);
		headerTable.addCell(textCell);
		document.add(headerTable);

		Text titleOfPDF = new Text("\n" + title + "\n" + "az aktuális eseti katonai diplomáciai engedélyekről"
		+ "\n" + dateOfReport.toString() + "\n\n");
		titleOfPDF.setFont(boldFont);
		titleOfPDF.setFontSize(22);
		Paragraph p2 = new Paragraph(titleOfPDF);
		p2.setTextAlignment(TextAlignment.CENTER);
		document.add(p2);

		float[] tableHeaderColumnWidth = { 80f, 90f, 60f, 60f, 50f, 50f, 120f, 20f };
		Table dataTable = new Table(tableHeaderColumnWidth);
		dataTable.setBorder(new SolidBorder(1));
		String[] dailyReportColumnNames = { "KKM\nnyt. szám", "Regisztráló\nország", "Repülőgép\nhívójele",
				 "Érvényesség\nkezdődátuma", "Indulási\nrepülőtér", "Érkezési\nrepülőtér", "Repülés célja", "Enge-\ndélyezett" };
		for (int i = 0; i < dailyReportColumnNames.length; i++) {
			dataTable.addHeaderCell(new Cell().add(dailyReportColumnNames[i])
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setFont(boldFont).setFontSize(11));
		}
		for (SingleFlightClearance clearance : listOfPrintedSFCL) {
			for (String data : clearance.getListOfPrintDailyReport()) {
				dataTable.addCell(new Cell().add(data).setTextAlignment(TextAlignment.CENTER).setFont(font).setFontSize(11));
			}
		}
		document.add(dataTable);

		float[] footerColumnWidth = { 830f };
		Table footerTable = new Table(footerColumnWidth);
		footerTable.setBorderTop(new SolidBorder(1));
		ActualDateTime actualDateTime = new ActualDateTime();
		Text footer = new Text("Jelen dokumentumot a MDC Register program készítette " + actualDateTime.printDate()
				+ actualDateTime.printTime()
				+ "\nA dokumentum érzékeny adatokat tartalmazhat, kérjük kezelje bizalmasan!");
		footer.setFont(boldFont);
		footer.setFontSize(12);
		Cell footertextCell = new Cell();
		Paragraph p3 = new Paragraph(footer);
		p3.setTextAlignment(TextAlignment.LEFT);
		footertextCell.add(p3).setVerticalAlignment(VerticalAlignment.BOTTOM);
		footertextCell.setBorder(Border.NO_BORDER);
		footerTable.addCell(footertextCell);
		footerTable.setFixedPosition(pdf.getPageNumber(pdf.getLastPage()),
				pdf.getLastPage().getPageSize().getLeft() + document.getLeftMargin(),
				pdf.getLastPage().getPageSize().getBottom() + document.getBottomMargin(),
				pdf.getDefaultPageSize().getWidth() - document.getLeftMargin() - document.getRightMargin());
		document.add(footerTable);

		document.close();
	}
}
