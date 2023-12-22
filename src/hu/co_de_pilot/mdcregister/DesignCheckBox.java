package hu.co_de_pilot.mdcregister;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class DesignCheckBox extends JCheckBox {
	
    private int round = 10;
    private Color shadowColor = Color.decode("#26a0da");
    private BufferedImage imageShadow;
    private final Insets shadowSize = new Insets(3, 5, 11, 8);

    public DesignCheckBox(String name, int x, int y, int width, int height, boolean selected) {
    	setOpaque(false);
    	setFocusable(false);
        setForeground(new Color(80, 80, 80));
        setBorder(new EmptyBorder(10, 12, 15, 12));
        setBackground(new Color(255, 255, 255));
        setFont(new Font("Times New Roman", Font.BOLD, 16));
        setName(name);
        setText(name);
        setSelected(selected);
        setBounds(x, y, width, height);
    }

    public int getRound() {
    	return round;
    }
    
    public void setRound(int round) {
    	this.round = round;
    	createImageShadow();
    	repaint();
    }
    
    public Color getShadowColor() {
    	return shadowColor;
    }
    
    public void setShadowColor(Color shadowColor) {
    	this.shadowColor = shadowColor;
    	createImageShadow();
    	repaint();
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;
        //  Create Shadow Image
        g2.drawImage(imageShadow, 0, 0, null);
        //  Create Background Color
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageShadow();
    }

    private void createImageShadow() {
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            BufferedImage img = createShadow();
            if (img != null) {
                g2.drawImage(createShadow(), 0, 0, null);
            }
            g2.dispose();
        }
    }

    private BufferedImage createShadow() {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        if (width > 0 && height > 0) {
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));
            g2.dispose();
            return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
        } else {
            return null;
        }
    }
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DesignCheckBox [");
		builder.append("Name: ");
		builder.append(this.getName());
		builder.append("]");
		return builder.toString();
	}
}