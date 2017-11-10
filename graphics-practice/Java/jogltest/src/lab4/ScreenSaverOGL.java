package lab4;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class ScreenSaverOGL implements GLEventListener {

	/**
	 * ScreenSaverOGL - this is a simple screen saver that uses JOGL 
	 * Eric McCreath 2009,2011,2017
	 * 
	 * You need to include the jogl jar files (gluegen2-rt.jar and jogl2.jar). In
	 * eclipse use "add external jars" in Project->Properties->Libaries
	 * otherwise make certain they are in the class path.  In the current linux 
         * computers there files are in the /usr/share/java directory.
	 * 
         * If you are executing from the command line then something like:
         *   javac -cp .:/usr/share/java/jogl2.jar:/usr/share/java/gluegen2-rt.jar ScreenSaverOGL.java
         *   java -cp .:/usr/share/java/jogl2.jar:/usr/share/java/gluegen2-rt.jar ScreenSaverOGL
         * should work.
         *
	 * You may also need set up the LD_LIBRARY_PATH environment variable. It should point to a
	 * directory that contains the required libraries such as: libgluegen2-rt.so, libjogl_cg.so, libjogl_awt.so,
	 * and libjogl.so. In eclipse this can be done in the "Run Configurations.."
	 * by adding an environment variable (so click on the "Environment" tab, add a "New" variable with the Variable
         * being "LD_LIBRARY_PATH" and the value "/usr/lib/jni").  
         * In Intellij it is in "Run Debug Configurations" and set the VM options as "-Djava.library.path=/usr/lib/jni" I think???) 
         * If you run from the command line then you may need to first run:

            LD_LIBRARY_PATH=/usr/lib/jni
            export LD_LIBRARY_PATH

	 * 
	 */

	JFrame jf;
	GLJPanel gljpanel;
	Dimension dim = new Dimension(1500, 1500);
	FPSAnimator animator;

	float xpos;
	float xvel;
	
	private float rotation = 0.0f;
	private float scale = 0.0f;
	private float x = dim.width/2;
	private float y = dim.height/2;
	private float translateRotation = 0.0f;

	public ScreenSaverOGL() {
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
		new ScreenSaverOGL();
	}

	public void display(GLAutoDrawable dr) {
		GL2 gl = (GL2) dr.getGL();
		GLU glu = new GLU();
		GLUT glut = new GLUT();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glRasterPos2f(xpos, 300.0f);
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Save the Screens");
		
		gl.glPushMatrix();	
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex2f(x - 200, y - 200);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex2f(x + 200, y - 200);
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glVertex2f(x, y + 200);
		gl.glEnd();
		
		
		gl.glPopMatrix();
		
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(ScreenSaverOGL.class.getResource("img.png"));
		} catch(Exception e) {
			System.out.println("error");
			e.printStackTrace();
			System.exit(1);
		}
		
		
		gl.glFlush();
		
		xpos += xvel;
		if (xpos > dim.getWidth())
			xpos = 0.0f;

		x = (float) (dim.width/2 + 400 * Math.sin(translateRotation));
		y = (float) (dim.height/2 + 400 * Math.cos(translateRotation));
		translateRotation += 0.1f;
		
		rotation += 1f;
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

	@Override
	public void dispose(GLAutoDrawable arg0) {
	
	}
}
