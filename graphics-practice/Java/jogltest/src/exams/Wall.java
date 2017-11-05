package exams;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;

public class Wall implements GLEventListener {
	
	private GLU glu = new GLU();
	
	private float sphereRadius = 0.1f;
	private int sphereSlices = 50;
	private int sphereStacks = 50;
	
	private float[][] position = {
		{ -0.25f, -0.25f, 0.0f },
		{ 0.25f, -0.25f, 0.0f },
		{ 0.0f, 0.25f, 0.0f }
	};
	
	private float angleRotation = 0.0f;
	
	private float wallThickness = 0.02f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		gl.glRotatef(this.angleRotation, 0.0f, 0.0f, 1.0f);
		
		float[] red = { 1.0f, 0.0f, 0.0f };
		float[] green = { 0.0f, 1.0f, 0.0f };
		float[] blue = { 0.0f, 0.0f, 1.0f };
		float[] maroon = { 0.5f, 0.0f, 0.0f };
		
		this.drawWall(gl, maroon);
		
		this.drawSphere(gl, red, position[0]);
		this.drawSphere(gl, green, position[1]);
		this.drawSphere(gl, blue, position[2]);
		
		gl.glFlush();
		
		this.angleRotation += 0.66f;
	}
	
	private void drawWall(GL2 gl, float[] color) {
		gl.glPushMatrix();
		gl.glLoadIdentity();
		
		gl.glColor3fv(color, 0);
		gl.glTranslatef(-0.3f, -0.4f, 0.5f);
		gl.glRotatef(-60, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(40, 1.0f, 0.0f, 0.0f);
		gl.glScalef(2.0f, 2.0f, 2.0f);
		
		this.drawSquare(gl);
		gl.glPushMatrix();
		gl.glRotatef(180, 1, 0, 0);
		this.drawSquare(gl);
		gl.glPopMatrix();
		
		this.drawSquare2(gl);
		gl.glPushMatrix();
		gl.glRotatef(90, 0, 0, 1);
		this.drawSquare2(gl);
		gl.glRotatef(90, 0, 0, 1);
		this.drawSquare2(gl);
		gl.glRotatef(90, 0, 0, 1);
		this.drawSquare2(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
	
	private void drawSquare2(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glVertex3f(0.5f, 0.5f, -wallThickness);
		gl.glVertex3f(0.5f, -0.5f, -wallThickness);
		gl.glVertex3f(0.5f, -0.5f, wallThickness);
		gl.glVertex3f(0.5f, 0.5f, wallThickness);
		gl.glVertex3f(0.5f, 0.5f, -wallThickness);
		gl.glEnd();
	}
	
	private void drawSquare(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glVertex3f(-0.5f, -0.5f, -wallThickness);
		gl.glVertex3f(0.5f, -0.5f, -wallThickness);
		gl.glVertex3f(0.5f, 0.5f, -wallThickness);
		gl.glVertex3f(-0.5f, 0.5f, -wallThickness);
		gl.glVertex3f(-0.5f, -0.5f, -wallThickness);
		gl.glEnd();
	}
	
	private void drawSphere(GL2 gl, float[] color, float[] coords) {
		gl.glPushMatrix();
		
		gl.glColor3fv(color, 0);
		gl.glTranslatef(coords[0], coords[1], coords[2]);
		
		GLUquadric quadric = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
		glu.gluQuadricNormals(quadric, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(quadric, GLU.GLU_OUTSIDE);
		
		glu.gluSphere(quadric, this.sphereRadius, this.sphereSlices, this.sphereStacks);
		
		gl.glPopMatrix();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glEnable(GL2.GL_DEPTH_TEST);	
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHT1);
		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[] { 0.3f, 0.3f, 0.3f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[] { 0.6f, 0.6f, 0.6f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, new float[] { 8.0f, 8.0f, 8.0f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, new float[] { 1.0f, 1.0f, -1.0f }, 0);
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[] { 8.0f, 8.0f, 8.0f, 8.0f }, 0);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 100.0f);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Wall wall = new Wall();
		
		glcanvas.addGLEventListener(wall);
		glcanvas.setSize(1500, 1000);
		
		final JFrame frame = new JFrame("Wall");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}

}
