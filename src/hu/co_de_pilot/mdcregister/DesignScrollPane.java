package hu.co_de_pilot.mdcregister;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class DesignScrollPane extends JScrollPane {

	public DesignScrollPane(FinderTable table, JPanel panel, int x, int y, int width, int height) {

        if (table != null) {
            this.setViewportView(table);
        } else {
        	this.setViewportView(panel);
        }
		this.setBackground(new Color(255, 255, 255));
		this.setBounds(x, y, width, height);
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setBackground(Color.decode("#26a0da"));
		this.getVerticalScrollBar()
				.setBackground(Color.WHITE);
		this.getVerticalScrollBar()
				.setUI(new BasicScrollBarUI() {
					@Override
					protected void configureScrollBarColors() {
						this.thumbColor = Color.decode("#26a0da");
						this.minimumThumbSize = new Dimension(10, 25);
					}
				});
		this.getVerticalScrollBar()
				.getComponent(0)
				.setBackground(Color.decode("#26a0da"));
		this.getVerticalScrollBar()
				.getComponent(1)
				.setBackground(Color.decode("#26a0da"));
		this.setVisible(false);
	}

}
