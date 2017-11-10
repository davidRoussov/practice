package exams;

import java.awt.Component;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Cylinder2 implements GLEventListener {

	private float rotation = 0.0f;
	private float angleIncrement = 0.01f;
	
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
		
		drawEnd(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 1, 0, 0);
		drawEnd(gl);
		gl.glPopMatrix();
		
		drawCylinder(gl);
		
		gl.glFlush();
		rotation += 0.33f;
	}
	
	private void drawCylinder(GL2 gl) {
		float x = 0.0f;
		float y = 0.5f;
		float z = 0.0f;
		float radius = 0.25f;
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		for (float angle = 0.0f; angle < 2 * Math.PI; angle += angleIncrement) {
			float xnew = (float) (x + radius * Math.sin(angle));
			float znew = (float) (z + radius * Math.cos(angle));
			gl.glNormal3f(xnew, 0, znew);
			gl.glVertex3f(xnew, y, znew);
			gl.glVertex3f(xnew, -y, znew);
		}
		gl.glEnd();
	}
	
	private void drawEnd(GL2 gl) {
		float x = 0.0f;
		float y = 0.5f;
		float z = 0.0f;
		float radius = 0.25f;
		
		gl.glNormal3f(0, 1, 0);
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		for (float angle = 0.0f; angle < 2 * Math.PI; angle += angleIncrement) {
			float xnew = (float) (x + radius * Math.sin(angle));
			float znew = (float) (z + radius * Math.cos(angle));
			gl.glVertex3f(xnew, y, znew);
		}
		gl.glEnd();
		
		gl.glLineWidth(2);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		for (float angle = 0.0f; angle < 2 * Math.PI; angle += angleIncrement) {
			float xnew = (float) (x + radius * Math.sin(angle));
			float znew = (float) (z + radius * Math.cos(angle));
			gl.glVertex3f(xnew, y, znew);
		}
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
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glHint(GL2.GL_POLYGON_SMOOTH_HINT, GL2.GL_NICEST);
		
		gl.glEnable(GL2.GL_LIGHTING);
//		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHT1);
		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[] { 0.2f, 0.2f, 0.2f, 0.2f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[] { 0.6f, 0.6f, 0.6f, 0.6f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, new float[] { 0.0f, 0.0f, -2.0f, 0.0f }, 0);
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 0.1f);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, new float[] { 1.0f, 0.0f, 0.0f, 0.0f }, 0);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Cylinder2 cylinder = new Cylinder2();
		glcanvas.addGLEventListener(cylinder);
		glcanvas.setSize(1500, 1500);
		
		final JFrame frame = new JFrame("Cylinder");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}

}
