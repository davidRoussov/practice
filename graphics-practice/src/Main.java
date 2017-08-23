import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements MouseMotionListener, MouseListener {

  private BufferedImage canvas;
  private int x, y;
  private boolean alreadyDragged = false;

  public Main(int width, int height) {
    canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    fillCanvas(Color.WHITE);
    this.addMouseMotionListener(this);
    this.addMouseListener(this);
  }

  public Dimension getPreferredSize() {
    return new Dimension(canvas.getWidth(), canvas.getHeight());
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(canvas, 0, 0, null);
  }

  public void fillCanvas(Color c) {
    int color = c.getRGB();
    for (int x = 0; x < canvas.getWidth(); x++) {
      for (int y = 0; y < canvas.getHeight(); y++) {
        canvas.setRGB(x, y, color);
      }
    }
    repaint();
  }

  public void mouseDragged(MouseEvent e) {
    Graphics2D g2 = canvas.createGraphics();

    int newx = e.getX();
    int newy = e.getY();

    g2.setColor(Color.BLACK);
    g2.setStroke(new BasicStroke(10));
    if (alreadyDragged) g2.drawLine(x, y, newx, newy);
    else alreadyDragged = true;
    x = newx;
    y = newy;

    repaint();
  }

  public void mouseMoved(MouseEvent e) {

  }
  public void mouseExited(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {
    alreadyDragged = false;
  }
  public void mouseClicked(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {
  }
  public void mouseEntered(MouseEvent e) {}

  public static void main(String[] args) {
    int width = 1800;
    int height = 1200;
    JFrame frame = new JFrame("Direct draw demo");

    Main panel = new Main(width, height);

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}