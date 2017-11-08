package exams;

import java.util.Random;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Circle2 implements GLEventListener {

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		float r = 0.25f;
		
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		Random random = new Random();
		for (float angle = 0.0f; angle < 2 * Math.PI; angle += 0.001) {
			
			
			float red = random.nextFloat();
			float g = random.nextFloat();
			float b = random.nextFloat();
			gl.glColor3f(red, g, b);
			
			float s = (float) (r * Math.sin(angle));
			float t = (float) (r * Math.cos(angle));
			gl.glVertex2f(s, t);
		}
		
		gl.glEnd();
		
		gl.glFlush();
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
		final GLProfile glprofile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(glprofile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Circle2 circle = new Circle2();
		glcanvas.addGLEventListener(circle);
		glcanvas.setSize(1500, 1500);
		
		final JFrame frame = new JFrame("Rubik");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}

}
