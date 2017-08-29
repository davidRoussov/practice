import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GL2;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.*;

public class HelloWorld implements GLEventListener {

  JFrame jf;
  GLJPanel gljpanel;
  Dimension dim = new Dimension(800, 600);
  FPSAnimator animator;

  float xpos;
  float xvel;
  public HelloWorld() {
    jf = new JFrame();
    gljpanel = new GLJPanel();
    gljpanel.addGLEventListener(this);
    gljpanel.requestFocusInWindow();
    jf.getContentPane().add(gljpanel);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
    jf.setPreferredSize(dim);
    jf.pack();
    animator = new FPSAnimator(gljpanel, 20);
    xpos = 100.0f;
    xvel = 1.0f;
    animator.start();
  }

  public static void main(String[] args) {
    new HelloWorld();
  }

  public void display(GLAutoDrawable dr) {
    GL2 gl = (GL2) dr.getGL();
    GLU glu = new GLU();
    GLUT glut = new GLUT();

    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    gl.glColor3f(1.0f, 0.0f, 0.0f);
    gl.glRasterPos2f(xpos, 300.0f);
    glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Save the Screens");
    gl.glFlush();

    xpos += xvel;
    if (xpos > dim.getWidth())
      xpos = 0.0f;

  }
  public void displayChanged(GLAutoDrawable dr, boolean arg1, boolean arg2) {
  }

  public void init(GLAutoDrawable dr) {
    GL2 gl = dr.getGL().getGL2();
    GLU glu = new GLU();
    GLUT glut = new GLUT();
    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glMatrixMode(GL2.GL_PROJECTION);
    glu.gluOrtho2D(0.0, dim.getWidth(), 0.0, dim.getHeight());
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }

  public void reshape(GLAutoDrawable dr, int arg1, int arg2, int arg3,
                      int arg4) {
  }

  public void dispose(GLAutoDrawable arg0) {

  }
}
