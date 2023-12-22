package hu.co_de_pilot.mdcregister;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SettingStatusOfAircraftListButtons {
	
	
	public SettingStatusOfAircraftListButtons(boolean changeToAddIcon, int numberOfAircrafts,
			InsertAnnualFlightClearance panelInsertAFCL) {
		
		if (changeToAddIcon) {
			for (int i = 0; i < numberOfAircrafts; i++) {
				try {
					Image add = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
					add = ImageIO.read(new File("resources\\icons\\add.png"));
					ImageIcon addicon = new ImageIcon(add);
					panelInsertAFCL.getListOfAircraftListButton().get(i).setIcon(addicon);
				} catch (IOException e3) {
					e3.printStackTrace();
				}			
			}
			
		} else {
			for (int i = 0; i < numberOfAircrafts; i++) {
				try {
					Image remove = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
					remove = ImageIO.read(new File("resources\\icons\\remove.png"));
					ImageIcon removeicon = new ImageIcon(remove);
					panelInsertAFCL.getListOfAircraftListButton().get(i).setIcon(removeicon);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		

		
	}
	
}
