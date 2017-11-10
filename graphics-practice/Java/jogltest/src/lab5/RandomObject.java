package lab5;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class RandomObject implements GLEventListener {
	
	private float coneBaseX = 0.0f;
	private float coneBaseY = 1.0f;
	private float coneBaseZ = 0.0f;
	private float coneTipY = 1.5f;
	private float radius = 0.25f;
	
	private float rotation = 0.0f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
		gl.glScalef(0.2f, 0.2f, 0.2f);
		
		float dist = 1.5f;
		
		gl.glPushMatrix();
		gl.glTranslatef(-dist, -dist, 0.0f);
		drawPencil(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(dist, -dist, 0.0f);
		drawPencil(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(dist, dist, 0.0f);
		drawPencil(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-dist, dist, 0.0f);
		drawPencil(gl);
		gl.glPopMatrix();
		
		gl.glFlush();
		rotation += 0.33f;
	}
	
	private void drawPencil(GL2 gl) {
		drawCone(gl);
		drawCylinder(gl);
		drawCircle(gl);
	}
	
	private void drawCircle(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		
		for (float angle = 0.0f; angle <= 2 * Math.PI + 0.01; angle += 0.01) {
			float x = (float)(coneBaseX + radius * Math.sin(angle));
			float z = (float)(coneBaseZ + radius * Math.cos(angle));
			gl.glNormal3f(0, -1, 0);
			gl.glVertex3f(x, -coneBaseY, z);
		}
		
		gl.glEnd();
	}
	
	private void drawCylinder(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		
		float inc = 0.5f;
		for (float angle = 0.0f; angle <= 2 * Math.PI + inc; angle += inc) {
			float x = (float)(coneBaseX + radius * Math.sin(angle));
			float z = (float)(coneBaseZ + radius * Math.cos(angle));
			gl.glNormal3f(x, 0, z);
			gl.glVertex3f(x, -coneBaseY, z);
			gl.glVertex3f(x, coneBaseY, z);
		}
		
		gl.glEnd();
	}
	
	private void drawCone(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		
		for (float angle = 0.0f; angle < 2 * Math.PI; angle += 0.01) {
			float x = (float) (coneBaseX + radius * Math.sin(angle));
			float z = (float) (coneBaseZ + radius * Math.cos(angle));
			gl.glNormal3f(x, coneTipY, z);
			gl.glVertex3f(x, coneBaseY, z);
			gl.glVertex3f(0, coneTipY, 0);
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
		RandomObject cylinder = new RandomObject();
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
