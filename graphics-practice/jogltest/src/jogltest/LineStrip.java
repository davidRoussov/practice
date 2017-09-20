package jogltest;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class LineStrip implements GLEventListener {

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		
		gl.glBegin(GL2.GL_LINE_STRIP);
		gl.glVertex3f(-0.50f, -0.75f, 0);
		gl.glVertex3f(0.7f, 0.5f, 0);
		gl.glVertex3f(0.70f, -0.70f, 0);
		gl.glVertex3f(0f, 0.5f, 0);
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
		
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		LineStrip r = new LineStrip();
		glcanvas.addGLEventListener(r);
		glcanvas.setSize(400, 400);
		
		final JFrame frame = new JFrame("LineStrip");
		
		frame.getContentPane().add(glcanvas);
		
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}

}
