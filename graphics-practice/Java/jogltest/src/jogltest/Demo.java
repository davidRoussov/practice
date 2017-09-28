package jogltest;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Demo implements GLEventListener {
	
	private float rotationAngle = 0;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(this.rotationAngle, 0f, 1f, 0f);
		
//		gl.glColor3f(1.0f, 0.0f, 0.0f);
//		for (float i = 0.5f; i > 0; i -= 0.1f) {
//			for (float j = 0; j < 0.5f; j += 0.1f) {
//				gl.glBegin(GL2.GL_LINE_LOOP);
//				drawCircle(gl, i, j);
//				gl.glEnd();
//			}
//		}
//		gl.glEnable(GL2.GL_LIGHTING);
		

		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3f(-0.25f, -0.25f, 0);
		gl.glVertex3f(0.25f, -0.25f, 0);
		gl.glVertex3f(0.25f, 0.75f, 0);
		gl.glVertex3f(-0.25f, 0.75f, 0);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(0.0f, -0.5f, 0.25f);
		gl.glVertex3f(0.5f, -0.5f, 0.25f);
		gl.glVertex3f(0.5f, 0.5f, 0.25f);
		gl.glVertex3f(0.0f, 0.5f, 0.25f);
		gl.glEnd();
		
		gl.glFlush();
		
		this.rotationAngle += 1;
	}
	
	private static void drawCircle(GL2 gl, float radius, float depth) {
		for (int i = 0; i < 64; i++) {
			float angle = (2 * (float) Math.PI) * i / 64;
			float x = (float) (radius * Math.cos(angle));
			float y = (float) (radius * Math.sin(angle));
			gl.glVertex3f(x, y, depth);			
		}	
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
		Demo demo = new Demo();
		glcanvas.addGLEventListener(demo);
		glcanvas.setSize(1500, 1000);
		
		final JFrame frame = new JFrame("Demo");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}

}
