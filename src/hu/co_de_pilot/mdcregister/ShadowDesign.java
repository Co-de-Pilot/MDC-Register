package hu.co_de_pilot.mdcregister;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTextFieldUI;

public class ShadowDesign {

	private JComponent actualJComponent;

	private int round = 10;
	private Color shadowColor = Color.decode("#26a0da");
	private BufferedImage imageShadow;
	private final Insets shadowSize = new Insets(3, 5, 11, 8);

	public ShadowDesign(JComponent actualJComponent) {
		this.actualJComponent = actualJComponent;
		if (actualJComponent instanceof JTextField) {
			textfieldDesign();
		} else {
			basicDesign();
		}
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
		createImageShadow();
		actualJComponent.repaint();
	}

	public Color getShadowColor() {
		return shadowColor;
	}

	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
		createImageShadow();
		actualJComponent.repaint();
	}

	private void textfieldDesign() {
		((JTextField) actualJComponent).setUI(new TextUI());
		actualJComponent.setOpaque(false);
		actualJComponent.setForeground(new Color(80, 80, 80));
		((JTextField) actualJComponent).setSelectedTextColor(new Color(255, 255, 255));
		((JTextField) actualJComponent).setSelectionColor(new Color(133, 209, 255));
		actualJComponent.setBorder(new EmptyBorder(10, 12, 15, 12));
		actualJComponent.setBackground(new Color(255, 255, 255));
		createImageShadow();
		
	}

	private void basicDesign() {

	}

	private class Component extends JComponent {
		
		Graphics grphcs;
		
		public Component() {
			paintComponent(getComponentGraphics(grphcs));
		}
		
		@Override
		protected void paintComponent(Graphics grphcs) {
			Graphics2D g2 = (Graphics2D) grphcs.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			double width = actualJComponent.getWidth() - (shadowSize.left + shadowSize.right);
			double height = actualJComponent.getHeight() - (shadowSize.top + shadowSize.bottom);
			double x = shadowSize.left;
			double y = shadowSize.top;
			// Create Shadow Image
			g2.drawImage(imageShadow, 0, 0, null);
			// Create Background Color
			g2.setColor(actualJComponent.getBackground());
			Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
			g2.fill(area);
			g2.dispose();
			System.out.println("paintComponent() run");
			paintComponent(grphcs);
		}

	}

	private void createImageShadow() {
		int height = actualJComponent.getHeight();
		int width = actualJComponent.getWidth();
		if (width > 0 && height > 0) {
			imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = imageShadow.createGraphics();
			BufferedImage img = createShadow();
			if (img != null) {
				g2.drawImage(createShadow(), 0, 0, null);
			}
			g2.dispose();
//			new Component();
			System.out.println("createImageShadow() run");
		}
	}

	private BufferedImage createShadow() {
		int width = actualJComponent.getWidth() - (shadowSize.left + shadowSize.right);
		int height = actualJComponent.getHeight() - (shadowSize.top + shadowSize.bottom);
		if (width > 0 && height > 0) {
			BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = img.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));
			g2.dispose();
			System.out.println("createShadow() run");
			return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
		} else {
			return null;
		}
	}

	private class TextUI extends BasicTextFieldUI {

		// Override this method to remove background or not paint background
		@Override
		protected void paintBackground(Graphics grphcs) {

		}
	}
}
