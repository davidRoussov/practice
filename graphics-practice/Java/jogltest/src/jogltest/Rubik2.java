package jogltest;

import java.awt.DisplayMode;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class Rubik2 implements GLEventListener {
	
	public static DisplayMode dm, dm_old;
	private GLU glu = new GLU();
	
	private float[] red = {1.0f, 0.0f, 0.0f};
	private float[] green = {0.0f, 1.0f, 0.0f};
	private float[] blue = {0.0f, 0.0f, 1.0f};
	private float[] yellow = {1.0f, 1.0f, 0.0f};
	private float[] cyan = {0.0f, 1.0f, 1.0f};
	private float[] magenta = {1.0f, 0.0f, 1.0f};	
	
	private float len = 0.25f;
	private float rotation = 0.0f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(this.rotation, 1.0f, 1.0f, 1.0f);
		
		draw(gl);
		
		gl.glFlush();
		
		this.rotation += 0.5f;
	}
	
	private void draw(GL2 gl) {
		gl.glBegin(GL2.GL_QUADS);
		
		// front
		gl.glColor3fv(red, 0);
		gl.glVertex3f(-len, -len, -len);
		gl.glVertex3f(len, -len, -len);
		gl.glVertex3f(len, len, -len);
		gl.glVertex3f(-len, len, -len);
		
		// back
		gl.glColor3fv(green, 0);
		gl.glVertex3f(-len, -len, len);
		gl.glVertex3f(len, -len, len);
		gl.glVertex3f(len, len, len);
		gl.glVertex3f(-len, len, len);
		
		// left
		gl.glColor3fv(blue, 0);
		gl.glVertex3f(-len, -len, -len);
		gl.glVertex3f(-len, -len, len);
		gl.glVertex3f(-len, len, len);
		gl.glVertex3f(-len, len, -len);
		
		

		

		
		gl.glEnd();	
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {	
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL2 gl = drawable.getGL().getGL2();
		if(height < 0)
			height = 1;
		
		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		glu.gluPerspective(45.0f, h, 1.0, 20.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Rubik2 rubik = new Rubik2();
		glcanvas.addGLEventListener(rubik);
		glcanvas.setSize(1500, 1000);
		
		final JFrame frame = new JFrame("Rubik");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		animator.start();
	}
	
	
}