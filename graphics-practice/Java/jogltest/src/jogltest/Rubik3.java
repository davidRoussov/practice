package jogltest;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class Rubik3 implements GLEventListener {

	JFrame jf;
	GLJPanel gljpanel;
	Dimension dim = new Dimension(800, 600);
	FPSAnimator animator;
	
	private float[] red = {1.0f, 0.0f, 0.0f};
	private float[] green = {0.0f, 1.0f, 0.0f};
	private float[] blue = {0.0f, 0.0f, 1.0f};
	private float[] yellow = {1.0f, 1.0f, 0.0f};
	private float[] cyan = {0.0f, 1.0f, 1.0f};
	private float[] magenta = {1.0f, 0.0f, 1.0f};	
	
	private float rotation = 0.0f;
	private float len = 100.0f;

	public Rubik3() {
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
		animator.start();
	}

	public static void main(String[] args) {
		new Rubik3();
	}

	public void display(GLAutoDrawable dr) {
		GL2 gl = (GL2) dr.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		gl.glRotatef(this.rotation, 1.0f, 1.0f, 1.0f);
		
		draw(gl);
		
		gl.glFlush();
		
		this.rotation += 0.5f;
	}
	
	private void draw(GL2 gl) {
		this.cube(gl);
	}
	
	private void cube(GL2 gl) {
		gl.glPushMatrix();
		this.square(gl, this.red);
		
		gl.glPushMatrix();
		gl.glRotatef(90, 0, 1, 0);
		this.square(gl, this.green);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(180, 0, 1, 0);
		this.square(gl, this.blue);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 0, 1, 0);
		this.square(gl, this.magenta);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
	
	private void square(GL2 gl, float[] color) {
		gl.glColor3fv(color, 0);
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glVertex3f(-len, -len, -len);
		gl.glVertex3f(len, -len, -len);
		gl.glVertex3f(len, len, -len);
		gl.glVertex3f(-len, len, -len);
		gl.glEnd();
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
