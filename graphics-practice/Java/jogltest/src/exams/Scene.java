package exams;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Scene implements GLEventListener {
	
	private float moveX = 0.0f;
	private float moveY = 0.0f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		gl.glPushMatrix();
		gl.glScalef(1.0f, 0.5f, 1.0f);
		this.drawRoad(gl);
		gl.glPopMatrix();
		
		
		
		gl.glPushMatrix();
		gl.glTranslatef(moveX, moveY, 0.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		this.drawCar(gl);
		this.drawWheel(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 0, 1, 0);
		this.drawCar(gl);
		this.drawWheel(gl);
		gl.glPopMatrix();
		
		
		
		gl.glPopMatrix();
		
		gl.glFlush();
		
		this.moveX += 0.008f;
		if(this.moveX > 1.5f) {
			this.moveX = -1.5f;
		}
		
	}
	
	private void drawWheel(GL2 gl) {
		gl.glLineWidth(10);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		
		float x = -0.25f;
		float y = -0.25f;
		float r = 0.15f;
		
		for(float angle = 0.0f; angle <= 2 * Math.PI; angle += 0.01) {
			float s = (float) (x + r * Math.sin(angle));
			float t = (float) (y + r * Math.cos(angle));
			gl.glVertex3f(s, t, 0.0f);
		}
		
		gl.glEnd();
	}
	
	private void drawCar(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		
		gl.glVertex3f(0.0f, -0.25f, 0.0f);
		gl.glVertex3f(-0.5f, -0.25f, 0.0f);
		gl.glVertex3f(-0.5f, 0.25f, 0.0f);
		gl.glVertex3f(0.0f, 0.25f, 0.0f);
		gl.glVertex3f(0.0f, -0.25f, 0.0f);

		gl.glEnd();
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		
		gl.glVertex3f(0.0f, 0.25f, 0.0f);
		gl.glVertex3f(-0.25f, 0.25f, 0.0f);
		gl.glVertex3f(-0.25f, 0.5f, 0.0f);
		gl.glVertex3f(0.0f, 0.5f, 0.0f);
		gl.glVertex3f(0.0f, 0.25f, 0.0f);
		
		gl.glEnd();
	}
	
	private void drawRoad(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3f(0.5f, 0.5f, 0.5f);
		
		gl.glVertex3f(-1.0f, -1.0f, 0.0f);
		gl.glVertex3f(-1.0f, 0.0f, 0.0f);
		gl.glVertex3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3f(1.0f, -1.0f, 0.0f);
		gl.glVertex3f(-1.0f, -1.0f, 0.0f);
		
		gl.glEnd();
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		
		gl.glVertex3f(-1.0f, -0.55f, 0.0f);
		gl.glVertex3f(-1.0f, -0.45f, 0.0f);
		gl.glVertex3f(1.0f, -0.55f, 0.0f);
		gl.glVertex3f(1.0f, -0.45f, 0.0f);
		gl.glVertex3f(-1.0f, -0.55f, 0.0f);
		
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Scene scene = new Scene();
		glcanvas.addGLEventListener(scene);
		glcanvas.setSize(1500, 1000);
		
		final JFrame frame = new JFrame("Scene");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}

}
