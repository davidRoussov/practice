import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.event.WindowListener;

public class Demo extends Frame implements GLEventListener {

  public Demo() {
    super("DEMO");
    this.setLayout(new BorderLayout());
    this.setSize(600, 400);
    this.setLocation(40, 40);
    this.setVisible(true);
  }

  public void init(GLAutoDrawable dr) {

  }

  public void display(GLAutoDrawable dr) {

  }

  public void dispose(GLAutoDrawable dr) {

  }

  public void reshape(GLAutoDrawable dr, int arg1, int arg2, int arg3, int arg4) {

  }

  public static void main(String[] args) {
    new Demo();
  }
}
