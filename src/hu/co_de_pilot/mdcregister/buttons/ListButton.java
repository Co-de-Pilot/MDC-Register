package hu.co_de_pilot.mdcregister.buttons;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import hu.co_de_pilot.mdcregister.DesignTextField;

public class ListButton extends JButton implements ActionListener {

	private List<DesignTextField> listOfJTF;
	private boolean isAddButton;

	public ListButton(List<DesignTextField> listOfJTF) {
		this.listOfJTF = listOfJTF;
		for (DesignTextField designTextField : listOfJTF) {
			designTextField.setEnabled(false);
			designTextField.setText("");
		}
		this.isAddButton = true;
        try {
        	Image add = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        	add = ImageIO.read(new File("resources\\icons\\add.png"));
        	ImageIcon addicon = new ImageIcon(add);
        	this.setIcon(addicon); // NOI18N
        } catch (IOException e1) {
        	e1.printStackTrace();
        }
		this.setContentAreaFilled(false);
		this.setBorder(new EmptyBorder(1, 1, 1, 1));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setToolTipText("Repülőgép hozzáadása/eltávolítása");
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (isAddButton) {
			for (DesignTextField designTextField : listOfJTF) {
				designTextField.setEnabled(true);
			}
			this.isAddButton = false;
			try {
				Image remove = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
				remove = ImageIO.read(new File("resources\\icons\\remove.png"));
				ImageIcon removeicon = new ImageIcon(remove);
				this.setIcon(removeicon); // NOI18N
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} else {
			for (DesignTextField designTextField : listOfJTF) {
				designTextField.setEnabled(false);
				designTextField.setText("");
			}
			this.isAddButton = true;
			try {
				Image add = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
				add = ImageIO.read(new File("resources\\icons\\add.png"));
				ImageIcon addicon = new ImageIcon(add);
				this.setIcon(addicon); // NOI18N
			} catch (IOException e3) {
				e3.printStackTrace();
			}			
		}
	}

}