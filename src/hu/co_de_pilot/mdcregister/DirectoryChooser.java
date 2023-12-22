package hu.co_de_pilot.mdcregister;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DirectoryChooser extends JFrame {

	private String title;
	private String currentDirectoryPath;
	
	public DirectoryChooser(String title, String currentDirectoryPath) {
		this.title = title;
		this.currentDirectoryPath = currentDirectoryPath;
	}
	
		
	public String chooseDirectory() {	
		JFileChooser fileChooser;
		fileChooser = new JFileChooser(); 
		fileChooser.setCurrentDirectory(new java.io.File(currentDirectoryPath));
		fileChooser.setDialogTitle(title + " mentése");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
	    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

	    } else {
			JOptionPane.showMessageDialog(null,
					"Nem választott ki mappát a PDF fájl mentéséhez, így a nyomtatás meghiúsult.", "PDF fájl nyomtatása",
					JOptionPane.OK_OPTION);
	      }
		
		return fileChooser.getSelectedFile().getPath();
	}
	

}
