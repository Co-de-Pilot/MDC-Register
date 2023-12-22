package hu.co_de_pilot.mdcregister;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class createPDFFilePath {
	
	public createPDFFilePath(SingleFlightClearance actualSFCL, AnnualFlightClearance actualAFCL) {
		
		JFileChooser fileChooser;
		fileChooser = new JFileChooser(); 
		fileChooser.setDialogTitle("Az engedély PDF fájl elérési helyének megadása");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null,
					"Az eredeti engedély PDF fájl elérési helye az alábbiak szerint rögzítésre került:"
					+ fileChooser.getSelectedFile().getPath(), "Az engedély PDF fájl elérési helye",
					JOptionPane.OK_OPTION);
			if (actualSFCL != null) {
				actualSFCL.setClearancePDFFilePath(fileChooser.getSelectedFile().getPath());
			} else {
				actualAFCL.setClearancePDFFilePath(fileChooser.getSelectedFile().getPath());
			}
			
		} else {
			JOptionPane.showMessageDialog(null,
					"Az eredeti engedély PDF fájl elérési helye nem került rögzítésre/módosításra.",
					"Az engedély PDF fájl elérési helye",
							JOptionPane.OK_OPTION);
		}

	}
	
}
