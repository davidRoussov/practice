package lab3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/*
 * DrawArea - a simple JComponent for drawing.  The "offscreen" BufferedImage is 
 * used to draw to,  this image is then used to paint the component.
 * Eric McCreath 2009 2015, 2017
 */

public class DrawArea extends JComponent implements MouseMotionListener,
		MouseListener {

	private BufferedImage offscreen;
	Dimension dim;
	DrawIt drawit;
	
	private boolean isDrawing = false;
	private int posX;
	private int posY;
	
	private int thickness = 50;
	private int transparency = 50;
	private boolean sprayMode = false;
	
	private boolean mousePressed = false;
	
	public DrawArea(Dimension dim, DrawIt drawit) {
		this.setPreferredSize(dim);
		offscreen = new BufferedImage(dim.width, dim.height,
				BufferedImage.TYPE_INT_RGB);
		this.dim = dim;
		this.drawit = drawit;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);

		clearOffscreen();
	}
	
	public void changeThickness(int newValue) {
		this.thickness = newValue;
	}
	
	public void setTransparency(int newValue) {
		this.transparency = newValue;
	}
	
	public void toggleSprayMode() {
		sprayMode = !sprayMode;
	}

	public void clearOffscreen() {
		Graphics2D g = offscreen.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
		repaint();
	}

	public Graphics2D getOffscreenGraphics() {
		return offscreen.createGraphics();
	}

	public void drawOffscreen() {
		repaint();
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(offscreen, 0, 0, null);
	}
	
	public void mouseDragged(MouseEvent m) {
		
		if (sprayMode) {
			spray(m.getX(), m.getY());
			return;
		}
		
		Graphics2D g = offscreen.createGraphics();
		
		Color color = (Color) drawit.colorToolbar.getSelectCommand();
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		Color newColor = new Color(red, green, blue, transparency);
		g.setColor(newColor);
		
//		g.setColor((Color) drawit.colorToolbar.getSelectCommand());
		// System.out.println("Slide Value is : " + drawit.aSlider.getValue());
		
		if (isDrawing) {
			g.setStroke(new BasicStroke(this.thickness));
			g.drawLine(posX, posY, m.getX(), m.getY());
			posX = m.getX();
			posY = m.getY();
		} else {
			isDrawing = true;
			posX = m.getX();
			posY = m.getY();
		}
	
//		g.fill(new Ellipse2D.Double(m.getX() - 1.0, m.getY() - 1.0, 2.0, 2.0));
		drawOffscreen();
	}
	
	private void spray(int x, int y) {
		Graphics2D g = offscreen.createGraphics();
		
		Color color = (Color) drawit.colorToolbar.getSelectCommand();
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		Color newColor = new Color(red, green, blue, transparency);
		g.setColor(newColor);
		
		int radius = 40;
		Random random = new Random();
		int sprayX = random.nextInt(radius) - radius;
		int sprayY = random.nextInt(radius) - radius;		
		g.fill(new Ellipse2D.Double(x + sprayX, y + sprayY, 4.0, 4.0));
		
		drawOffscreen();
	}

	public void mouseMoved(MouseEvent m) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (sprayMode) {
			mousePressed = true;
			spray(e.getX(), e.getY());
		}
	}

	public void mouseReleased(MouseEvent e) {
		isDrawing = false;
	}

	public void export(File file) {
		try {
			ImageIO.write(offscreen, "png", file);
		} catch (IOException e) {
			System.out.println("problem saving file");
		}
	}
}
