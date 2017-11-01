package exams;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class Circle implements GLEventListener {

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		
		this.draw(gl, 0.0f, 0.0f, 0.5f);
		
		gl.glFlush();
	}
	
	private void draw(GL2 gl, float centerX, float centerY, float radius) {
		gl.glBegin(GL2.GL_LINE_LOOP);
		
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		
		for(float angle = 0; angle < 2 * Math.PI; angle += 0.001) {
			float s = (float) (centerX + radius * Math.sin(angle));
			float t = (float) (centerY + radius * Math.cos(angle));
			gl.glVertex3f(s, t, 0.0f);
		}
		
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Circle one = new Circle();
		
		glcanvas.addGLEventListener(one);
		glcanvas.setSize(1500, 1000);
		
		final JFrame frame = new JFrame("One");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}

}
