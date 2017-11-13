package exams;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class FourObjects implements GLEventListener {
	
	private GLU glu = new GLU();
	
	private float rotation1 = 0.0f;
	private float rotation2 = 0.0f;
	private float rotation3 = 0.0f;
	private float rotation4 = 0.0f;
	private float rotationIncrement = 0.33f;
	
	private float[] col1 = { 1.0f, 0.0f, 0.0f };
	private float[] col2 = { 0.0f, 1.0f, 0.0f };
	private float[] col3 = { 0.0f, 0.0f, 1.0f };
	private float[] col4 = { 1.0f, 1.0f, 0.0f };
	private float[] col5 = { 1.0f, 0.0f, 1.0f };
	private float[] col6 = { 0.0f, 1.0f, 1.0f };
	
	private float[] wallColour = { 0.5f, 0.5f, 0.5f };

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glPushMatrix();
		
//		gl.glRotatef(45, -1.0f, -1.0f, -1.0f);
		gl.glScalef(0.75f, 0.75f, 0.75f);
		
		drawWall(gl);
		
		drawCubes(gl);
		
		gl.glFlush();
		gl.glPopMatrix();
		
		rotation1 += rotationIncrement;
		rotation2 += rotationIncrement;
		rotation3 += rotationIncrement;
		rotation4 += rotationIncrement;
	}
	
	private void drawWall(GL2 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -1.0f, 0.0f);
//		gl.glScalef(1.25f, 0.5f, 1.25f);
		drawWallFace1(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
		drawWallFace1(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 0.0f, 1.0f, 0.0f);
		drawWallFace1(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(180, 0.0f, 1.0f, 0.0f);
		drawWallFace1(gl);
		gl.glPopMatrix();
		
		drawWallFace2(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
		drawWallFace2(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
	
	private void drawWallFace2(GL2 gl) {
		float a = 1.0f;
		float b = 0.25f;
		float c = 1.0f;
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3fv(wallColour, 0);
		gl.glNormal3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(-a, b, -c);
		gl.glVertex3f(a, b, -c);
		gl.glVertex3f(a, b, c);
		gl.glVertex3f(-a, b, c);
		gl.glVertex3f(-a, b, -c);
		gl.glEnd();
	}
	
	private void drawWallFace1(GL2 gl) {
		float a = 1.0f;
		float b = 0.25f;
		float c = -1.0f;
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3fv(wallColour, 0);
		gl.glNormal3f(0.0f, 0.0f, -1.0f);
		gl.glVertex3f(-a, -b, c);
		gl.glVertex3f(a, -b, c);
		gl.glVertex3f(a, b, c);
		gl.glVertex3f(-a, b, c);
		gl.glVertex3f(-a, -b, c);
		gl.glEnd();
		
		gl.glLineWidth(2.0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(-a, -b, c);
		gl.glVertex3f(a, -b, c);
		gl.glVertex3f(a, b, c);
		gl.glVertex3f(-a, b, c);
		gl.glVertex3f(-a, -b, c);
		gl.glEnd();
	}
	
	private void drawCubes(GL2 gl) {
		gl.glScalef(0.1f, 0.1f, 0.1f);
		
		float d = 5.0f;
		
		gl.glPushMatrix();
		gl.glTranslatef(-d, -d, 0.0f);
		gl.glRotatef(rotation1, 1.0f, 1.0f, 1.0f);
		drawCube(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(d, -d, 0.0f);
		gl.glRotatef(rotation2, 1.0f, 1.0f, 1.0f);
		drawCube(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(d, d, 0.0f);
		gl.glRotatef(rotation3, 1.0f, 1.0f, 1.0f);
		drawCube(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-d, d, 0.0f);
		gl.glRotatef(rotation4, 1.0f, 1.0f, 1.0f);
		drawCube(gl);
		gl.glPopMatrix();
	}
	
	private void drawCube(GL2 gl) {
		drawFace(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
		drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
		drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 0.0f, 1.0f, 0.0f);
		drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
		drawFace(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 1.0f, 0.0f, 0.0f);
		drawFace(gl);
		gl.glPopMatrix();
	}
	
	private void drawFace(GL2 gl) {
		float a = 1.0f;
		float b = 0.0f;
		
		gl.glPushMatrix();
		gl.glTranslatef(-a, -a, b);
		drawSquare(gl, col1);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -a, b);
		drawSquare(gl, col2);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(a, -a, b);
		drawSquare(gl, col3);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-a, 0.0f, b);
		drawSquare(gl, col4);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, b);
		drawSquare(gl, col5);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(a, 0.0f, b);
		drawSquare(gl, col6);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-a, a, b);
		drawSquare(gl, col1);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, a, b);
		drawSquare(gl, col2);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(a, a, b);
		drawSquare(gl, col3);
		gl.glPopMatrix();
	}
	
	private void drawSquare(GL2 gl, float[] color) {
		float a = 0.5f;
		float b = -1.5f;
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3fv(color, 0);
		gl.glNormal3f(0.0f, 0.0f, -1.0f);
		gl.glVertex3f(-a, -a, b);
		gl.glVertex3f(a, -a, b);
		gl.glVertex3f(a, a, b);
		gl.glVertex3f(-a, a, b);
		gl.glVertex3f(-a, -a, b);
		gl.glEnd();
		
		gl.glLineWidth(2.0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(-a, -a, b);
		gl.glVertex3f(a, -a, b);
		gl.glVertex3f(a, a, b);
		gl.glVertex3f(-a, a, b);
		gl.glVertex3f(-a, -a, b);
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45, 1.0, 0.01, 100);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
//		gl.glTranslatef(0.0f, 0.0f, -2.0f);
		glu.gluLookAt(-1.0f, 1.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_NORMALIZE);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHT1);
		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[] { 0.2f, 0.2f, 0.2f, 0.2f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[] { 0.6f, 0.6f, 0.6f, 0.6f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, new float[] { 0.0f, 0.0f, -2.0f, 0.0f }, 0);
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 12.0f);
		
		rotation2 += 30 * rotationIncrement;
		rotation3 += 60 * rotationIncrement;
		rotation4 += 90 * rotationIncrement;
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		FourObjects fourObjects = new FourObjects();
		
		final GLWindow window = GLWindow.create(capabilities);
		window.addGLEventListener(fourObjects);
		window.setSize(1500, 1500);
		window.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(window, 200, true);
		animator.start();
	}
}
