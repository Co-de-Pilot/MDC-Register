package hu.co_de_pilot.mdcregister.buttons;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import hu.co_de_pilot.mdcregister.AdditionalJCVisibilityAndButtonText;
import hu.co_de_pilot.mdcregister.AnnualFlightClearance;
import hu.co_de_pilot.mdcregister.FinderTable;
import hu.co_de_pilot.mdcregister.InsertAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.OpenDataFromListToJComp;
import hu.co_de_pilot.mdcregister.PopupMessages;
import hu.co_de_pilot.mdcregister.RefillTableFromList;
import hu.co_de_pilot.mdcregister.SearchAnnualFlightClearance;

public class TableButtonAFCL extends AbstractCellEditor
		implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener {

	private InsertAnnualFlightClearance panelInsertAFCL;
	private SearchAnnualFlightClearance panelSearchAFCL;
	
	private String name;
	private FinderTable table;
	private JButton normalButton;
	private JButton pressedButton;
	private Object editorValue;
	private boolean isButtonColumnEditor;
	private String normalIconPath;
	private String pressedIconPath;
	private File pdfFile;
	private AnnualFlightClearance clearance;

	public TableButtonAFCL(String name, InsertAnnualFlightClearance panelInsertAFCL,
			SearchAnnualFlightClearance panelSearchAFCL, int column, String normalIconPath, String pressedIconPath,
			AnnualFlightClearance clearance) {
		this.name = name;
		this.panelInsertAFCL = panelInsertAFCL;
		this.panelSearchAFCL = panelSearchAFCL;
		this.table = panelSearchAFCL.getAnnualFlightClearanceTable();
		this.normalIconPath = normalIconPath;
		this.pressedIconPath = pressedIconPath;
		this.clearance = clearance;

		normalButton = new JButton();
		normalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		switch (name) {
		case "edit":
			normalButton.setToolTipText("Engedély módosítása");
			normalButton.setName("EditData");
			break;
		case "deactivate":
			normalButton.setToolTipText("Engedély aktiválása/deaktiválása");
			break;
		case "open":
			normalButton.setToolTipText("Eredeti engedély megnyitása");
			break;
		}
		pressedButton = new JButton();
		pressedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pressedButton.addActionListener(this);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
		table.addMouseListener(this);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        try {
        	Image pressed = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        	pressed = ImageIO.read(new File(pressedIconPath));
        	ImageIcon pressedIcon = new ImageIcon(pressed);
        	pressedButton.setIcon(pressedIcon); // NOI18N
        } catch (IOException e) {
        	e.printStackTrace();
        }
		pressedButton.setContentAreaFilled(false);
		pressedButton.setBorder(new EmptyBorder(3, 3, 3, 3));
		this.editorValue = value;
		return pressedButton;
	}

	@Override
	public Object getCellEditorValue() {
		return editorValue;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		try {
			Image normal = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
			normal = ImageIO.read(new File(normalIconPath));
			ImageIcon normalIcon = new ImageIcon(normal);
			normalButton.setIcon(normalIcon); // NOI18N
		} catch (IOException e) {
			e.printStackTrace();
		}
		normalButton.setContentAreaFilled(false);
		normalButton.setBorder(new EmptyBorder(3, 3, 3, 3));
		return normalButton;
	}

	public void actionPerformed(ActionEvent e) {		
		TableButtonAFCL button = (TableButtonAFCL) table.getValueAt(table.convertRowIndexToModel(table.getEditingRow()),
				panelSearchAFCL.getAnnualFlightClearanceTable().getColumnCount() - 1);
		AnnualFlightClearance actualclearance = button.getClearance();
		fireEditingStopped();
		switch (name) {
		case "edit":
			new OpenDataFromListToJComp(null, null, panelInsertAFCL, actualclearance);
			panelSearchAFCL.setVisible(false);
			panelInsertAFCL.setVisible(true);
			panelInsertAFCL.getOriginRegNumberOfMFA().setText(actualclearance.getRegNumberOfMFA());
			panelInsertAFCL.setActualAFCL(actualclearance);
			new AdditionalJCVisibilityAndButtonText(true, null, panelInsertAFCL);
			break;
		case "deactivate":
			if (!panelSearchAFCL.getListOfFilteredAFCL().isEmpty()) {
				if (actualclearance.isActive()) {
					int confirm = PopupMessages.showDeleteConfirmMessage(null,
							"Törölni vagy csak deaktiválni kívánja a kiválasztott engedélyt?", "Engedély törlése/deaktiválása");
					if (confirm == JOptionPane.YES_OPTION) {
						panelSearchAFCL.getListOfFilteredAFCL().remove(actualclearance);
					} else if (confirm == JOptionPane.NO_OPTION) {
						actualclearance.setActive(false);
					}
				} else {
					actualclearance.setActive(true);
				}
				new RefillTableFromList((DefaultTableModel) table.getModel(), table.getColumnCount(), null,
						panelSearchAFCL.getListOfFilteredAFCL());
			} else {
				if (actualclearance.isActive()) {
					int confirm = PopupMessages.showDeleteConfirmMessage(null,
							"Törölni vagy csak deaktiválni kívánja a kiválasztott engedélyt?", "Engedély törlése/deaktiválása");
					if (confirm == JOptionPane.YES_OPTION) {
						panelSearchAFCL.getListOfAFCL().remove(actualclearance);
					} else if (confirm == JOptionPane.NO_OPTION) {
						actualclearance.setActive(false);
					}
				} else {
					actualclearance.setActive(true);
				}
				new RefillTableFromList((DefaultTableModel) table.getModel(), table.getColumnCount(), null,
						panelSearchAFCL.getListOfAFCL());
			}
			break;
		case "open":
			try {
				if (!panelSearchAFCL.getListOfFilteredAFCL().isEmpty()) {
					pdfFile = new File(actualclearance.getClearancePDFFilePath());
				} else {
					pdfFile = new File(actualclearance.getClearancePDFFilePath());
				}
				
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
						"A fájl elérési útja nincs megadva!", "PDF fájl megnyitása",
						JOptionPane.OK_OPTION);
			}
			break;
		}
	}

	public void mousePressed(MouseEvent e) {
		if (table.isEditing() && table.getCellEditor() == this)
			isButtonColumnEditor = true;
	}

	public void mouseReleased(MouseEvent e) {
		if (isButtonColumnEditor && table.isEditing())
			table.getCellEditor()
					.stopCellEditing();

		isButtonColumnEditor = false;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public String getName() {
		return name;
	}

	public AnnualFlightClearance getClearance() {
		return clearance;
	}

}