package jogltest;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class PolygonLighting implements GLEventListener {
	private float rpoly;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glColor3f(1f, 0f, 0f);
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(rpoly, 0.0f, 1.0f, 0.0f);
		
		gl.glBegin(GL2.GL_POLYGON);
	    gl.glVertex3f( 0f,0.5f,0f ); 
	    gl.glVertex3f( -0.5f,0.2f,0f ); 
	    gl.glVertex3f( -0.5f,-0.2f,0f ); 
	    gl.glVertex3f( 0f,-0.5f,0f ); 
	    gl.glVertex3f( 0f,0.5f,0f ); 
	    gl.glVertex3f( 0.5f,0.2f,0f ); 
	    gl.glVertex3f( 0.5f,-0.2f,0f ); 
	    gl.glVertex3f( 0f,-0.5f,0f ); 
	    gl.glEnd();
	    
	    gl.glFlush();
	    
	    rpoly += 0.2f;
	    
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);
	    gl.glEnable(GL2.GL_NORMALIZE);
	    
	    float[] ambientLight = { 0.1f, 0.0f, 0.0f, 0.0f };
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, ambientLight, 0);
	    
	    float[] diffuseLight = { 1f, 2f, 1f, 0f };
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0);
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
		PolygonLighting polygon = new PolygonLighting();
		glcanvas.addGLEventListener(polygon);
		glcanvas.setSize(1400, 1400);
		
		final JFrame frame = new JFrame("Polygon lighting");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		animator.start();
	}
	
}
