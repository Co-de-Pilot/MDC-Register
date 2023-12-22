package hu.co_de_pilot.mdcregister;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReadDataFromCSV {
	
	private String filepath;
	private JTable table;
	private DefaultTableModel tablemodel;
	
	public ReadDataFromCSV(String filepath, JTable table, DefaultTableModel tablemodel) {
		this.filepath = filepath;
		this.table = table;
		this.tablemodel = tablemodel;
	}

	public void scanDatafromCSVtoTable() throws FileNotFoundException {
		String[] arrayOfNewRowDatas = new String[table.getColumnCount()];
		try (Scanner fileReader = new Scanner(new File(filepath))) {
		    while (fileReader.hasNextLine()) {
		        String line = fileReader.nextLine();
		    	try (Scanner rowScanner = new Scanner(line)) {
		            rowScanner.useDelimiter(";");
		            for (int i = 0; i < table.getColumnCount(); i++) {
		            	arrayOfNewRowDatas[i] = rowScanner.next();
		            }
		            tablemodel.addRow(arrayOfNewRowDatas);
		        }
		    }
		}
		
		
		
	}

}
