package jogltest;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class Polygon implements GLEventListener {

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		
	   gl.glBegin(GL2.GL_POLYGON);
	   
	   gl.glVertex3f(0f,0.5f,0f);
	   gl.glVertex3f(-0.5f,0.2f,0f);
	   gl.glVertex3f(-0.5f,-0.2f,0f);
	   gl.glVertex3f(0f,-0.5f,0f);
	   gl.glVertex3f(0f,0.5f,0f);
	   gl.glVertex3f(0.5f,0.2f,0f);
	   gl.glVertex3f(0.5f,-0.2f,0f);
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
		Polygon p = new Polygon();
		glcanvas.addGLEventListener(p);
		glcanvas.setSize(1200, 1000);
		
		final JFrame frame = new JFrame("A Polygon");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}
}
