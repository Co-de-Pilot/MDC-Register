package hu.co_de_pilot.mdcregister;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class FinderTable extends JTable {
	
	private String[] columnNames;
	private DefaultTableModel tableModel;
	private TableColumnModel columnModel;
	private int[] columnwidths;
	
	public FinderTable(String[] columnNames, int[] columnwidths, DefaultTableModel tableModel) {
		this.setSelectionMode(0);
		this.columnNames = columnNames;
		this.tableModel = tableModel;
		this.setModel(tableModel);
		columnModel = this.getColumnModel();
		this.columnwidths = columnwidths;
		for (int i = 0; i < columnwidths.length; i++) {
			columnModel.getColumn(i).setPreferredWidth(columnwidths[i]);
			columnModel.getColumn(i).setMaxWidth(columnwidths[i]);
		}
		this.setIntercellSpacing(new Dimension(0,0));
		this.setShowGrid(false);
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		this.setSelectionBackground(Color.decode("#f85032"));
		this.getTableHeader().setFont(new Font("SegoeUI", Font.PLAIN, 12));
		this.getTableHeader().setOpaque(false);
		this.getTableHeader().setBackground(Color.decode("#26a0da"));
		this.getTableHeader().setForeground(Color.WHITE);
		UIManager.getDefaults().put("TableHeader.cellBorder",BorderFactory.createEmptyBorder());
	}

	public String[] getColumnNames() {
		return columnNames;
	}
	
}
