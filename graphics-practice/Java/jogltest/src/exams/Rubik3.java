package exams;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Rubik3 implements GLEventListener {
	
	private float rotation = 0.0f;
	
	private float[] col1 = { 1.0f, 0.0f, 0.0f };
	private float[] col2 = { 0.0f, 1.0f, 0.0f };
	private float[] col3 = { 0.0f, 0.0f, 1.0f };
	private float[] col4 = { 1.0f, 1.0f, 0.0f };
	private float[] col5 = { 1.0f, 0.0f, 1.0f };
	private float[] col6 = { 0.0f, 1.0f, 1.0f };

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
		gl.glScalef(0.2f, 0.2f, 0.2f);
		
		drawFace(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(90, 0, 1, 0);
		drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 0, 1, 0);
		drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(180, 0, 1, 0);
		drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(90, 1, 0, 0);
		drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 1, 0, 0);
		drawFace(gl);
		gl.glPopMatrix();
		
		gl.glFlush();
		rotation += 0.33f;
	}
	
	private void drawFace(GL2 gl) {
		float dist = 1.0f;
		
		gl.glPushMatrix();
		gl.glTranslatef(-dist, -dist, 0.0f);
     	drawSquare(gl, col1);
     	gl.glPopMatrix();
     	
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -dist, 0.0f);
     	drawSquare(gl, col2);
     	gl.glPopMatrix();
     	
		gl.glPushMatrix();
		gl.glTranslatef(dist, -dist, 0.0f);
     	drawSquare(gl, col3);
     	gl.glPopMatrix();
     	
		gl.glPushMatrix();
		gl.glTranslatef(-dist, 0.0f, 0.0f);
     	drawSquare(gl, col4);
     	gl.glPopMatrix();
     	
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, 0.0f);
     	drawSquare(gl, col5);
     	gl.glPopMatrix();
     	
		gl.glPushMatrix();
		gl.glTranslatef(dist, 0.0f, 0.0f);
     	drawSquare(gl, col6);
     	gl.glPopMatrix();
     	
		gl.glPushMatrix();
		gl.glTranslatef(-dist, dist, 0.0f);
     	drawSquare(gl, col1);
     	gl.glPopMatrix();
     	
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, dist, 0.0f);
     	drawSquare(gl, col2);
     	gl.glPopMatrix();
     	
		gl.glPushMatrix();
		gl.glTranslatef(dist, dist, 0.0f);
     	drawSquare(gl, col3);
     	gl.glPopMatrix();
	}
	
	private void drawSquare(GL2 gl, float[] color) {
		float a = 0.5f;
		float b = 1.5f;
		
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3fv(color, 0);
		gl.glVertex3f(-a, -a, b);
		gl.glVertex3f(a, -a, b);
		gl.glVertex3f(a, a, b);
		gl.glVertex3f(-a, a, b);
		gl.glVertex3f(-a, -a, b);
		gl.glEnd();
		
		gl.glLineWidth(4);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(-a, -a, b);
		gl.glVertex3f(a, -a, b);
		gl.glVertex3f(a, a, b);
		gl.glVertex3f(-a, a, b);
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glLoadIdentity();
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glShadeModel(GL2.GL_SMOOTH);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHT1);
		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[] { 0.3f, 0.3f, 0.3f, 0.3f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[] { 0.6f, 0.6f, 0.6f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, new float[] { 0.0f, 0.0f, -2.0f, 1.0f }, 0);
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[] { 2.0f, 2.0f, 2.0f, 2.0f }, 0);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 0.1f);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Rubik3 rubik = new Rubik3();
		glcanvas.addGLEventListener(rubik);
		glcanvas.setSize(1500, 1500);
		
		final JFrame frame = new JFrame("RUBIK");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}
}
