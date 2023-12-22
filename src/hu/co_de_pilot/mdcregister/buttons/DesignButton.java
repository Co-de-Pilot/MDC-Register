package hu.co_de_pilot.mdcregister.buttons;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class DesignButton extends JButton {
	
	private Color color1 = Color.decode("#314755");
	private Color color2 = Color.decode("#26a0da");
	private final Timer timer;
	private final Timer timerPressed;
	private float alpha = 0.3f;
	private boolean pressed;
	private Point pressedLocation;
	private float pressedSize;
	private float sizeSpeed = 7f;
	private float alphaPressed = 0.5f;

    public DesignButton(String text, String name) {
    	this.setText(text);
    	this.setName(name);
    	
    	this.setContentAreaFilled(false);
    	this.setForeground(Color.WHITE);
    	this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	this.setBorder(new EmptyBorder(10, 10, 10, 10));
    	this.setFont(new Font("Stencil", Font.BOLD, 18));

    	this.setFocusPainted(false);
    	this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                pressedSize = 0;
                alphaPressed = 0.5f;
                pressed = true;
                pressedLocation = me.getPoint();
                timerPressed.setDelay(0);
                timerPressed.start();
            }
        });
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    if (alpha > 0.3f) {
                        alpha -= 0.05f;
                        repaint();
                    } else {
                        alpha = 0.3f;
                        timer.stop();
                        repaint();
                    }
            }
        });
        timerPressed = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pressedSize += sizeSpeed;
                if (alphaPressed <= 0) {
                    pressed = false;
                    timerPressed.stop();
                } else {
                    repaint();
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, color1, getWidth(), 0, color2);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        if (pressed) {
            paintPressed(g2);
        }
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }

    private void paintPressed(Graphics2D g2) {
        if (pressedLocation.x - (pressedSize / 2) < 0 && pressedLocation.x + (pressedSize / 2) > getWidth()) {
            timerPressed.setDelay(20);
            alphaPressed -= 0.05f;
            if (alphaPressed < 0) {
                alphaPressed = 0;
            }
        }
        g2.setColor(Color.WHITE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alphaPressed));
        float x = pressedLocation.x - (pressedSize / 2);
        float y = pressedLocation.y - (pressedSize / 2);
        g2.fillOval((int) x, (int) y, (int) pressedSize, (int) pressedSize);
    }
	
	
	
}
