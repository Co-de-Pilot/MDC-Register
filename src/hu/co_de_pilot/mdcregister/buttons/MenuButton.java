package hu.co_de_pilot.mdcregister.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import hu.co_de_pilot.mdcregister.MainWindow;

public class MenuButton extends DesignButton implements ActionListener {

	public MainWindow mainWindow;
	private JPanel selectedPanel;
	
	public MenuButton(String text, String name, MainWindow mainWindow, JPanel selectedPanel) {
		super(text, name);
		this.mainWindow = mainWindow;
		this.selectedPanel = selectedPanel;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.getActualPanel().setVisible(false);
		selectedPanel.setVisible(true);
		mainWindow.setActualPanel(selectedPanel);
	}
	
	
	
	
}
