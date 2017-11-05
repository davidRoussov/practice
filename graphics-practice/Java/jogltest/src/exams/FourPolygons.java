package exams;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class FourPolygons implements GLEventListener {
	
	private float rotation = 0.0f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(this.rotation, 0.0f, 1.0f, 0.0f);
		gl.glScalef(0.3f, 0.3f, 1.0f);
		
		gl.glPushMatrix();
		gl.glTranslatef(-0.75f, -0.75f, 0.0f);
		drawPolygon(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.75f, -0.75f, 0.0f);
		drawPolygon(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.75f, 0.75f, 0.0f);
		drawPolygon(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-0.75f, 0.75f, 0.0f);
		drawPolygon(gl);
		gl.glPopMatrix();
		
		gl.glFlush();
		
		this.rotation += 0.33f;
	}
	
	private void drawPolygon(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLES);
		
		gl.glColor3f(1.0f, 1.0f, 0.0f);
		
		gl.glVertex3f(-0.5f, -0.5f, 0.0f);
		gl.glVertex3f(0.5f, -0.5f, 0.0f);
		gl.glVertex3f(0.0f, 0.5f, 0.0f);
		
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
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		FourPolygons fourPolygons = new FourPolygons();
		glcanvas.setSize(1500, 1000);
		glcanvas.addGLEventListener(fourPolygons);
		
		final JFrame frame = new JFrame("Four Polygons");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}

}
