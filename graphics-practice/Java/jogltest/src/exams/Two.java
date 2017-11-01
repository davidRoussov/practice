package exams;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;

public class Two implements GLEventListener {
	
	private float rotation = 0.0f;
	private float radius = 0.25f;
	private float res = 0.01f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(this.rotation, 1.0f, 1.0f, 1.0f);
		
		GLU glu = new GLU();
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        GLUquadric quadratic = glu.gluNewQuadric();
        
        glu.gluQuadricDrawStyle(quadratic, GLU.GLU_FILL);
        glu.gluQuadricNormals(quadratic, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(quadratic, GLU.GLU_OUTSIDE);
        
        final float radius = 0.33f;
        final int slices = 50;
        final int stacks = 50;
        
        glu.gluSphere(quadratic, radius, slices, stacks);
		
		gl.glFlush();
		
		this.rotation += 0.33f;
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHT1);
		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[] { 0.1f, 0.1f, 0.1f, 0.1f }, 0);		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[] { 0.2f, 0.3f, 0.4f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, new float[] { 1.0f, 1.0f, -1.0f, 1.0f }, 0);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Two two = new Two();
		
		glcanvas.addGLEventListener(two);
		glcanvas.setSize(1500, 1000);
		
		final JFrame frame = new JFrame("Two");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		animator.start();
	}

}
