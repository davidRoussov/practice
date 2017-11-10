package exams;

import javax.swing.JFrame;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class Rubik4 implements GLEventListener, MouseListener {
	
	private GLU glu = new GLU();
	
	private float rotation = 0.0f;
	
	private float[] col1 = { 1.0f, 0.0f, 0.0f };
	private float[] col2 = { 0.0f, 1.0f, 0.0f };
	private float[] col3 = { 0.0f, 0.0f, 1.0f };
	private float[] col4 = { 1.0f, 1.0f, 0.0f };
	private float[] col5 = { 1.0f, 0.0f, 1.0f };
	private float[] col6 = { 0.0f, 1.0f, 1.0f };
	
	float d = 0.5f;
	float z = 1.5f;
	
	private boolean isSwiping = false;
	private int mousePressX;
	private int mousePressY;
	private float rotateX = 1.0f;
	private float rotateY = 1.0f;
	private float rotateZ = 1.0f;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glPushMatrix();
		
		gl.glScalef(0.35f, 0.35f, 0.35f);
		gl.glRotatef(40f, -1.0f, -1.0f, -0.8f);
		
		drawFloor(gl);
		
		gl.glScalef(0.25f, 0.25f, 0.25f);
		gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
		
		drawCube(gl);
		
		
		gl.glFlush();		
		gl.glPopMatrix();
		rotation += 0.33f;
	}
	
	private void drawFloor(GL2 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -1.0f, 0.0f);
		gl.glScalef(4.0f, 1.0f, 4.0f);
		
		drawFloorFace1(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
		drawFloorFace1(gl);
		gl.glPopMatrix();
		
		drawFloorFace2(gl);
		
		gl.glPushMatrix();
		gl.glRotatef(180, 0.0f, 1.0f, 0.0f);
		drawFloorFace2(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
		drawFloorFace2(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-90, 0.0f, 1.0f, 0.0f);
		drawFloorFace2(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
	
	private void drawFloorFace2(GL2 gl) {
		float a = 0.5f;
		float b = 0.1f;
		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glNormal3f(0.0f, 0.0f, -1.0f);
		gl.glColor3f(0.5f, 0.5f, 0.5f);
		
		gl.glVertex3f(-a, -b, -a);
		gl.glVertex3f(a, -b, -a);
		gl.glVertex3f(a, b, -a);
		gl.glVertex3f(-a, b, -a);
		gl.glVertex3f(-a, -b, -a);
		
		gl.glEnd();
		
		gl.glLineWidth(2);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(-a, -b, -a);
		gl.glVertex3f(a, -b, -a);
		gl.glVertex3f(a, b, -a);
		gl.glVertex3f(-a, b, -a);
		gl.glEnd();
	}
	
	private void drawFloorFace1(GL2 gl) {
		float a = 0.5f;
		float b = 0.1f;
	
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3f(0.5f, 0.5f, 0.5f);
		gl.glNormal3f(0.0f, 1.0f, 0.0f);
		
		gl.glVertex3f(-a, -b, -a);
		gl.glVertex3f(a, -b, -a);
		gl.glVertex3f(a, -b, a);
		gl.glVertex3f(-a, -b, a);
		gl.glVertex3f(-a, -b, -a);
		
		gl.glEnd();		
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
		
		gl.glPushMatrix();
		gl.glTranslatef(-a, -a, 0.0f);
		drawSquare(gl, col1);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -a, 0.0f);
		drawSquare(gl, col2);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(a, -a, 0.0f);
		drawSquare(gl, col3);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-a, 0.0f, 0.0f);
		drawSquare(gl, col4);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, 0.0f);
		drawSquare(gl, col5);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(a, 0.0f, 0.0f);
		drawSquare(gl, col6);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-a, a, 0.0f);
		drawSquare(gl, col1);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, a, 0.0f);
		drawSquare(gl, col2);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(a, a, 0.0f);
		drawSquare(gl, col3);
		gl.glPopMatrix();
	}
	
	private void drawSquare(GL2 gl, float[] color) {		
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		gl.glColor3fv(color, 0);
		
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3f(-d, -d, -z);
		gl.glVertex3f(d, -d, -z);
		gl.glVertex3f(d, d, -z);
		gl.glVertex3f(-d, d, -z);
		gl.glVertex3f(-d, -d, -z);
		
		gl.glEnd();
		
		gl.glLineWidth(3);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(-d, -d, -z);
		gl.glVertex3f(d, -d, -z);
		gl.glVertex3f(d, d, -z);
		gl.glVertex3f(-d, d, -z);
		gl.glVertex3f(-d, -d, -z);
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
		gl.glEnable(GL2.GL_NORMALIZE);
		
//		gl.glMatrixMode(GL2.GL_PROJECTION);
//		gl.glLoadIdentity();
//		glu.gluPerspective(180, 1.0, 0.1, 1000.0);
//		gl.glMatrixMode(GL2.GL_MODELVIEW);
//		gl.glLoadIdentity();
//		glu.gluLookAt(4.0f, 4.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHT1);
		
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[] { 0.2f, 0.2f, 0.2f, 0.2f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[] { 0.8f, 0.8f, 0.8f, 0.8f }, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glLightfv(GL2.GL_POSITION, GL2.GL_POSITION, new float[] { 3.0f, 2.0f, -4.0f, 0.0f }, 0);
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 128.0f);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
//		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Rubik4 rubik = new Rubik4();
//		glcanvas.addGLEventListener(rubik);
//		glcanvas.setSize(1500, 1500);
		
		final GLWindow window = GLWindow.create(capabilities);
		window.addGLEventListener(rubik);
		window.addMouseListener(rubik);
		window.setFullscreen(false);
		window.setSize(1500, 1500);
		window.setVisible(true);

//		
//		final JFrame frame = new JFrame("Toothbrush");
//		frame.getContentPane().add(glcanvas);
//		frame.setSize(frame.getContentPane().getPreferredSize());
//		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(window, 200, true);
		animator.start();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		System.out.println("hi there!");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
