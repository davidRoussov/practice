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

public class Rubik implements GLEventListener {
	
	private GLU glu = new GLU();
	
	private float[] red = { 1.0f, 0.0f, 0.0f };
	private float[] green = { 0.0f, 1.0f, 0.0f };
	private float[] blue = { 0.0f, 0.0f, 1.0f };
	private float[] one = { 1.0f, 1.0f, 0.0f };
	private float[] two = { 0.0f, 1.0f, 1.0f };
	private float[] three = { 1.0f, 0.0f, 1.0f };
	
	private float rotation = 0.0f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
		gl.glScalef(0.2f, 0.2f, 0.2f);
		
		this.drawFace(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(90, 0, 1, 0);
		this.drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 0, 1, 0);
		this.drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(180, 0, 1, 0);
		this.drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(90, 1, 0, 0);
		this.drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 1, 0, 0);
		this.drawFace(gl);
		gl.glPopMatrix();
		
		gl.glFlush();
		
		rotation += 0.33f;
	}
	
	private void drawFace(GL2 gl) {	
		float dist = 1.0f;
		
		gl.glPushMatrix();
		gl.glTranslatef(-dist, -dist, 0.0f);
		drawSquare(gl, red);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -dist, 0.0f);
		drawSquare(gl, green);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(dist, -dist, 0.0f);
		drawSquare(gl, blue);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-dist, 0.0f, 0.0f);
		drawSquare(gl, one);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, 0.0f);
		drawSquare(gl, two);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(dist, 0.0f, 0.0f);
		drawSquare(gl, three);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-dist, dist, 0.0f);
		drawSquare(gl, red);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, dist, 0.0f);
		drawSquare(gl, green);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(dist, dist, 0.0f);
		drawSquare(gl, blue);
		gl.glPopMatrix();
	}
	
	private void drawSquare(GL2 gl, float[] color) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3fv(color, 0);
		
		gl.glVertex3f(-0.5f, -0.5f, -1.5f);
		gl.glVertex3f(0.5f, -0.5f, -1.5f);
		gl.glVertex3f(0.5f, 0.5f, -1.5f);
		gl.glVertex3f(-0.5f, 0.5f, -1.5f);
		gl.glVertex3f(-0.5f, -0.5f, -1.5f);
		
		gl.glEnd();
		
		gl.glLineWidth(5);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		
		gl.glVertex3f(-0.5f, -0.5f, -1.5f);
		gl.glVertex3f(0.5f, -0.5f, -1.5f);
		gl.glVertex3f(0.5f, 0.5f, -1.5f);
		gl.glVertex3f(-0.5f, 0.5f, -1.5f);
		gl.glVertex3f(-0.5f, -0.5f, -1.5f);
		
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glEnable(GL2.GL_DEPTH_TEST);
//		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
//		gl.glMatrixMode(GL2.GL_PROJECTION);
//		gl.glLoadIdentity();
//		glu.gluPerspective(60, 1.0, 0.1, 1000.0);
//		gl.glMatrixMode(GL2.GL_MODELVIEW);
//		gl.glLoadIdentity();
//		glu.gluLookAt(0.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0, 1.0f, 0.0f);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHT1);
		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[] { 0.3f, 0.3f, 0.3f, 0.3f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[] { 1.0f, 1.0f, 1.0f, 0.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, new float[] { 1.0f, 0.0f, 0.0f, 0.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, new float[] { 0.0f, 5.0f, 0.0f }, 0);
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 16f);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Rubik rubik = new Rubik();
		glcanvas.addGLEventListener(rubik);
		glcanvas.setSize(1500, 1500);
		
		final JFrame frame = new JFrame("Rubik");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		animator.start();
	}

}
