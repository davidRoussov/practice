package exams;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw2D extends JPanel {
	
	private BufferedImage offscreen;

	public static void main(String[] args) {
		JFrame frame = new JFrame("frame");
		frame.getContentPane().add(new Draw2D());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 1000);
		frame.setVisible(true);
	}
	
	public Draw2D() {
		
		offscreen = new BufferedImage(1500, 1000, BufferedImage.TYPE_INT_RGB);
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("MOUSE CLICKED!");
				Graphics2D g = offscreen.createGraphics();
				
				g.setColor(Color.BLUE);
				g.drawRect(100, 200, 300, 400);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		
		g.setColor(Color.BLACK);
		g.drawLine(20, 30, 100, 200);
		
	}
}
