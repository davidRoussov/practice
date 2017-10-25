package jogltest;

import java.awt.DisplayMode;
import java.util.Arrays;
import java.util.Random;

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

public class GoodLightingExample implements GLEventListener {
	public static DisplayMode dm, dm_old;
	private GLU glu = new GLU();
	private GLUT glut = new GLUT();
	
	private float[] red = {1.0f, 0.0f, 0.0f};
	private float[] green = {0.0f, 1.0f, 0.0f};
	private float[] blue = {0.0f, 0.0f, 1.0f};
	private float[] yellow = {1.0f, 1.0f, 0.0f};
	private float[] cyan = {0.0f, 1.0f, 1.0f};
	private float[] magenta = {1.0f, 0.0f, 1.0f};	
	
	private float len = 0.25f;
	private float rotation = 0.0f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0f, 0f, -5.0f);
		
		gl.glRotatef(this.rotation, 1.0f, 1.0f, 1.0f);
		
//		this.draw(gl);
		glut.glutSolidTeapot(1);
		
		gl.glFlush();
			
        this.rotation += 0.33f;
	}
	
	private void draw(GL2 gl) {
//		gl.glBegin(GL2.GL_QUADS);
		
//		// front
//		gl.glColor3fv(red, 0);
//		gl.glVertex3f(-len, -len, -len);
//		gl.glVertex3f(len, -len, -len);
//		gl.glVertex3f(len, len, -len);
//		gl.glVertex3f(-len, len, -len);
//		
//		// back
//		gl.glColor3fv(green, 0);
//		gl.glVertex3f(-len, -len, len);
//		gl.glVertex3f(len, -len, len);
//		gl.glVertex3f(len, len, len);
//		gl.glVertex3f(-len, len, len);
//		
//		// left
//		gl.glColor3fv(blue, 0);
//		gl.glVertex3f(-len, -len, -len);
//		gl.glVertex3f(-len, -len, len);
//		gl.glVertex3f(-len, len, len);
//		gl.glVertex3f(-len, len, -len);
		
		this.cube(gl);
		
//		gl.glEnd();	
	}
	
	private void cube(GL2 gl) {
		gl.glPushMatrix();
		gl.glScalef(4f, 4f, 4f);
		
		this.square(gl, this.red);
		
		gl.glPushMatrix();
		gl.glRotatef(90, 0, 1, 0);
		this.square(gl, this.green);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 0, 1, 0);
		this.square(gl, this.blue);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(180, 0, 1, 0);
		this.square(gl, this.cyan);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(90, 1, 0, 0);
		this.square(gl, this.yellow);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 1, 0, 0);
		this.square(gl, this.magenta);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
	
	private void square(GL2 gl, float[] color) {
		gl.glColor3fv(color, 0);
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glVertex3f(-len, -len, len);
		gl.glVertex3f(len, -len, len);
		gl.glVertex3f(len, len, len);
		gl.glVertex3f(-len, len, len);
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		
		float ac[] = { 0.1f, 0.1f, 0.1f, 1.0f };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ac, 0);
		gl.glEnable(GL2.GL_LIGHT1);
		
		float f[] = { 5.0f, 0.0f, 0.0f, 1.0f };
		float dc[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, dc, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, f, 0);
		
//		float df[] = { 1.0f, 0.0f, 0.0f, 1.0f };
//		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, df, 0);
		
		float sf[] = { 1.0f, 1.0f, 1.0f, 0.0f };
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, sf, 0);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 10.0f);
		
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL2 gl = drawable.getGL().getGL2();
		if (height < 0) {
			height = 1;
		}
		
		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		glu.gluPerspective(45.0f, h, 1.0, 20.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		GoodLightingExample rubik = new GoodLightingExample();
		
		glcanvas.addGLEventListener(rubik);
		glcanvas.setSize(1500, 1000);
		
		final JFrame frame = new JFrame("Rubik's cube");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		animator.start();
	}
}
