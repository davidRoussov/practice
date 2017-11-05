package exams;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class BorderedCube implements GLEventListener {
	
	private GLU glu = new GLU();
	
	private float[] red = { 1.0f, 0.0f, 0.0f };
	private float[] green = { 0.0f, 1.0f, 0.0f };
	private float[] blue = { 0.0f, 0.0f, 1.0f };
	private float[] yellow = { 1.0f, 1.0f, 0.0f };
	private float[] cyan = { 0.0f, 1.0f, 1.0f };
	private float[] magenta = { 1.0f, 0.0f, 1.0f };
	
	private float rotation = 0.0f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glPushMatrix();
		gl.glRotatef(this.rotation, 0.0f, 0.0f, 1.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		
		gl.glColor3fv(red, 0);
		this.drawSquare(gl);
		
		gl.glPushMatrix();
		gl.glColor3fv(green, 0);
		gl.glRotatef(180, 1, 0, 0);
		this.drawSquare(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glColor3fv(blue, 0);
		gl.glRotatef(90, 0, 1, 0);
		this.drawSquare(gl);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glColor3fv(yellow, 0);
		gl.glRotatef(-90, 0, 1, 0);
		this.drawSquare(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glColor3fv(cyan, 0);
		gl.glRotatef(90, 1, 0, 0);
		this.drawSquare(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glColor3fv(magenta, 0);
		gl.glRotatef(-90, 1, 0, 0);
		this.drawSquare(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
		
		gl.glFlush();
		
		this.rotation += 0.5f;
	}
	
	public void drawSquare(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		
		gl.glVertex3f(-0.5f, -0.5f, -0.5f);
		gl.glVertex3f(0.5f, -0.5f, -0.5f);
		gl.glVertex3f(0.5f, 0.5f, -0.5f);
		gl.glVertex3f(-0.5f, 0.5f, -0.5f);
		gl.glVertex3f(-0.5f, -0.5f, -0.5f);
		
		gl.glEnd();
		
		gl.glLineWidth(3.0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		
		gl.glVertex3f(-0.5f, -0.5f, -0.5f);
		gl.glVertex3f(0.5f, -0.5f, -0.5f);
		gl.glVertex3f(0.5f, 0.5f, -0.5f);
		gl.glVertex3f(-0.5f, 0.5f, -0.5f);
		
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable dr) {
		GL2 gl2 = dr.getGL().getGL2();
		gl2.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		gl2.glEnable(GL2.GL_DEPTH_TEST);
		
		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();
		glu.gluPerspective(60, 1.0, 0.1, 1000.0);
		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();
		glu.gluLookAt(2.0, 2.0, 2.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		BorderedCube bc = new BorderedCube();
		glcanvas.addGLEventListener(bc);
		glcanvas.setSize(1500, 1500);
		
		final JFrame frame = new JFrame("Bordered Cube");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}

}
