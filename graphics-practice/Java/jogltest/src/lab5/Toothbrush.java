package lab5;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Toothbrush implements GLEventListener {
	
	private float rotation = 0.0f;
	
	private float faceOneWidth = 0.25f;
	private float faceOneHeight = 2.0f;
	private float faceTwoWidth = 0.1f;
	private float faceTwoHeight = faceOneHeight;
	private float faceThreeWidth = faceOneWidth;
	private float faceThreeHeight = faceTwoWidth;
	
	private float inc = 0.01f;
	private float cylinderY = 0.5f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
		gl.glScalef(0.3f, 0.3f, 0.3f);
		
		
		float d = 2f;
		
		gl.glPushMatrix();
		gl.glTranslatef(-d, -d, 0.0f);
		drawToothbrush(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(d, -d, 0.0f);
		drawToothbrush(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(d, d, 0.0f);
		drawToothbrush(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-d, d, 0.0f);
		drawToothbrush(gl);
		gl.glPopMatrix();
		
		gl.glFlush();
		rotation += 0.66f;
	}
	
	private void drawToothbrush(GL2 gl) {
		drawHandle(gl);
		drawCylinders(gl);
	}
	
	private void drawCylinders(GL2 gl) {
		gl.glPushMatrix();
		gl.glScalef(0.06f, 0.06f, 0.16f);
		gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
		gl.glTranslatef(0.5f, 1.0f, -14.0f);
		drawCylinder(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScalef(0.06f, 0.06f, 0.16f);
		gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
		gl.glTranslatef(-0.5f, 1.0f, -14.0f);
		drawCylinder(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScalef(0.06f, 0.06f, 0.16f);
		gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
		gl.glTranslatef(0.5f, 1.0f, -10.0f);
		drawCylinder(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScalef(0.06f, 0.06f, 0.16f);
		gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
		gl.glTranslatef(-0.5f, 1.0f, -10.0f);
		drawCylinder(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScalef(0.06f, 0.06f, 0.16f);
		gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
		gl.glTranslatef(0.5f, 1.0f, -12.0f);
		drawCylinder(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScalef(0.06f, 0.06f, 0.16f);
		gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
		gl.glTranslatef(-0.5f, 1.0f, -12.0f);
		drawCylinder(gl);
		gl.glPopMatrix();
	}
	
	private void drawCylinder(GL2 gl) {
		drawEnd(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
		drawEnd(gl);
		gl.glPopMatrix();
		
		drawMain(gl);
	}
	
	private void drawMain(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		float radius = 0.25f;
		for (float angle = 0.0f; angle <= 2 * Math.PI + inc; angle += inc) {
			float x = (float) (radius * Math.sin(angle));
			float z = (float) (radius * Math.cos(angle));
			gl.glNormal3f(x, 0.0f, z);
			gl.glVertex3f(x, cylinderY, z);
			gl.glVertex3f(x, -cylinderY, z);
		}
		gl.glEnd();
	}
	
	private void drawEnd(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		float radius = 0.25f;
		for (float angle = 0.0f; angle <= 2 * Math.PI + inc; angle += inc) {
			float x = (float) (radius * Math.sin(angle));
			float z = (float) (radius * Math.cos(angle));
			gl.glNormal3f(0.0f, 1.0f, 0.0f);
			gl.glVertex3f(x, cylinderY, z);
		}
		gl.glEnd();
	}
	
	private void drawHandle(GL2 gl) {
		drawFace1(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
		drawFace1(gl);
		gl.glPopMatrix();
		
		drawFace2(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 0.0f, 0.0f, 1.0f);
		drawFace2(gl);
		gl.glPopMatrix();
		
		drawFace3(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
		drawFace3(gl);
		gl.glPopMatrix();
	}
	
	private void drawFace3(GL2 gl) {
		float d1 = faceThreeWidth / 2;
		float d2 = faceOneHeight/2;
		float d3 = faceThreeHeight/2;
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glNormal3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(-d1, d2, -d3);
		gl.glVertex3f(d1, d2, -d3);
		gl.glVertex3f(d1, d2, d3);
		gl.glVertex3f(-d1, d2, d3);
		gl.glVertex3f(-d1, d2, -d3);
		gl.glEnd();
	}
	
	private void drawFace2(GL2 gl) {
		float d1 = faceOneWidth / 2;
		float d2 = faceTwoHeight / 2;
		float d3 = faceTwoWidth / 2;
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glNormal3f(-1.0f, 0.0f, 0.0f);
		gl.glVertex3f(-d1, -d2, -d3);
		gl.glVertex3f(-d1, -d2, d3);
		gl.glVertex3f(-d1, d2, d3);
		gl.glVertex3f(-d1, d2, -d3);
		gl.glVertex3f(-d1, -d2, -d3);
		gl.glEnd();
	}
	
	private void drawFace1(GL2 gl) {
		float d1 = faceOneWidth/2;
		float d2 = faceOneHeight/2;
		float d3 = faceTwoWidth/2;
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glNormal3f(0, 0, -1);
		gl.glVertex3f(-d1, -d2, -d3);
		gl.glVertex3f(d1, -d2, -d3);
		gl.glVertex3f(d1, d2, -d3);
		gl.glVertex3f(-d1, d2, -d3);
		gl.glVertex3f(-d1, -d2, -d3);
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
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT1);
		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[] { 0.2f, 0.2f, 0.2f, 0.2f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[] { 0.4f, 0.4f, 0.4f, 0.4f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, new float[] { 0.0f, 0.0f, -2.0f, 0.0f }, 0);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Toothbrush toothbrush = new Toothbrush();
		glcanvas.addGLEventListener(toothbrush);
		glcanvas.setSize(1500, 1500);
		
		final JFrame frame = new JFrame("Toothbrush");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}
}
