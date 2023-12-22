package hu.co_de_pilot.mdcregister;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class DocumentAction implements DocumentListener {
	
	private JTextField textfield;
	private TableRowSorter<DefaultTableModel> tableRowSorter;
	
	public DocumentAction(JTextField textfield, TableRowSorter<DefaultTableModel> tableRowSorter) {
		this.textfield = textfield;
		this.tableRowSorter = tableRowSorter;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		String text = textfield.getText();
		if (text.trim().length() == 0) {
			tableRowSorter.setRowFilter(null);
		} else {
			tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		String text = textfield.getText();
		if (text.trim().length() == 0) {
			tableRowSorter.setRowFilter(null);
		} else {
			tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		throw new UnsupportedOperationException("Not supported yet.");		
	}

}
