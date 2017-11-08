package exams;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Cylinder implements GLEventListener {

	private float rotation = 0.0f;
	private float x = 0.0f;
	private float y = 0.5f;
	private float z = 0.0f;
	private float radius = 0.25f;
	private float inc = 0.1f;
	private float[] color = { 230/255.0f, 126/255.0f, 34/255.0f };
	
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
		gl.glScalef(1.25f, 1.25f, 1.25f);
		
		drawCylinder(gl);
		
		drawCircle(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
		drawCircle(gl);
		gl.glPopMatrix();
		
		gl.glFlush();
		rotation += 0.33f;
	}
	
	private void drawCylinder(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3fv(color, 0);
		
		for (float angle = 0.0f; angle < 2 * Math.PI; angle += inc) {
			float xnew = (float) (x + radius * Math.sin(angle));
			float znew = (float) (z + radius * Math.cos(angle));
			
			gl.glNormal3f(xnew, 0, znew);
			gl.glVertex3f(xnew, y, znew);
			gl.glVertex3f(xnew, -y, znew);
		}
		
		gl.glEnd();
		
		gl.glLineWidth(1);
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		
		for (float angle = 0.0f; angle < 2 * Math.PI; angle += inc) {
			float xnew = (float) (x + radius * Math.sin(angle));
			float znew = (float) (z + radius * Math.cos(angle));
			
			gl.glVertex3f(xnew, y, znew);
			gl.glVertex3f(xnew, -y, znew);
		}
		
		gl.glEnd();
	}
	
	private void drawCircle(GL2 gl) {		
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glColor3fv(color, 0);
		
		for (float angle = 0.0f; angle <= 2 * Math.PI; angle += inc) {
			float xnew = (float) (x + radius * Math.sin(angle));
			float znew = (float) (z + radius * Math.cos(angle));
			
			gl.glNormal3f(0, 1, 0);
			gl.glVertex3f(xnew, y, znew);
		}
		
		gl.glEnd();
		
		gl.glLineWidth(1);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		
		for (float angle = 0.0f; angle <= 2 * Math.PI; angle += 0.1) {
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
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHT1);
		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[] { 0.3f, 0.3f, 0.3f, 0.3f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, new float[] { 0.5f, 0.5f, 0.5f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, new float[] { 2.0f, 2.0f, -3.0f }, 0);
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[] { 0.5f, 0.5f, 0.5f, 0.5f }, 0);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 100.0f);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final GLProfile glprofile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(glprofile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Cylinder cylinder = new Cylinder();
		glcanvas.addGLEventListener(cylinder);
		glcanvas.setSize(1500, 1500);
		
		final JFrame frame = new JFrame("Rubik");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}
}
