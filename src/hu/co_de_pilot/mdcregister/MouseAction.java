package hu.co_de_pilot.mdcregister;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MouseAction implements MouseListener{

	private DesignTextField textfield;
	private FinderTable table;
	private DefaultTableModel tableModel;
	
	public DesignTextField getTextfield() {
		return textfield;
	}

	public void setTextfield(DesignTextField textfield) {
		this.textfield = textfield;
	}

	/**
	 * Konstruktor a táblázatban egér kattintáshoz(sor kijelöléshez).
	 */
	public MouseAction() {
	}
	
	public MouseAction(FinderTable table, DefaultTableModel tableModel) {
		this.table = table;
		this.tableModel = tableModel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		textfield.setText(tableModel.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString());
		table.clearSelection();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
