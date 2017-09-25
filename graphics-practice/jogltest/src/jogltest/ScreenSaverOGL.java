package jogltest;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class ScreenSaverOGL implements GLEventListener {
	JFrame jf;
	GLJPanel gljpanel;
	Dimension dim = new Dimension(1400, 1200);
	FPSAnimator animator;
	
	float xpos;
	float xvel;
	
	int triangleDisplayTime = 0;
	float triangleOneBottomLeftX = 0;
	float triangleOneBottomLeftY = 0;
	float triangleOneRotation = 0;
	
	public ScreenSaverOGL() {
		jf = new JFrame();
		gljpanel = new GLJPanel();
		gljpanel.addGLEventListener(this);
		gljpanel.requestFocusInWindow();
		jf.getContentPane().add(gljpanel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setPreferredSize(dim);
		jf.pack();
		animator = new FPSAnimator(gljpanel, 500);
		xpos = 100.0f;
		xvel = 10f;
		animator.start();
	}
	
	public static void main(String[] args) {
		new ScreenSaverOGL();
	}
	
	@Override
	public void display(GLAutoDrawable dr) {
//		GL2 gl = (GL2) dr.getGL();
//		GLU glu = new GLU();
//		GLUT glut = new GLUT();
//		
//		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//		gl.glColor3f(1.0f, 0.0f, 0.0f);
//		gl.glRasterPos2f(xpos, 300.0f);
//		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Save the screens");
//		gl.glFlush();
//		
//		xpos += xvel;
//		if (xpos > dim.getWidth())
//			xpos = 0.0f;
		

//		if(this.triangleDisplayTime > 50) {
//			Random rand = new Random();
//			this.triangleOneBottomLeftX = (float) rand.nextInt(this.dim.width - 100);
//			this.triangleOneBottomLeftY = (float) rand.nextInt(this.dim.height - 100);
//			this.triangleDisplayTime = 0;
//		}
//		this.triangleDisplayTime++;
//		
//		float x1 = this.triangleOneBottomLeftX;
//		float y1 = this.triangleOneBottomLeftY;
//
//		float x2 = (float) x1 + 200;
//		float y2 = y1;
//		
//		float x3 = (float) (x2 - x1) / 2 + x1;
//		float y3 = (float) y1 + 100;
//		
//		// random triangle colors
//		Random rand = new Random();
//		float col1 = rand.nextFloat();
//		float col2 = rand.nextFloat();
//		float col3 = rand.nextFloat();
//		float col4 = rand.nextFloat();
//		float col5 = rand.nextFloat();
//		float col6 = rand.nextFloat();
//		float col7 = rand.nextFloat();
//		float col8 = rand.nextFloat();
//		float col9 = rand.nextFloat();
//		
//		gl.glBegin(GL2.GL_POLYGON);
//		gl.glColor3f(col1, col2, col3);
//		gl.glVertex3f(x1, y1, 0);
//		gl.glColor3f(col4, col5, col6);
//		gl.glVertex3f(x2, y2, 0);
//		gl.glColor3f(col7, col8, col9);
//		gl.glVertex3f(x3, y3, 0);
//		gl.glEnd();
//		gl.glFlush();
//		
//		float houseX1 = (this.dim.width / 2) - 200;
//		float houseY1 = 0;
//		
//		float hx2 = houseX1;
//		float hy2 = houseY1 + 400f;
//		
//		float hx3 = (this.dim.width / 2);
//		float hy3 = hy2 + 200f;
//		
//		
//		gl.glBegin(GL2.GL_LINE_STRIP);
//		gl.glColor3f(0.5f, 0.5f, 0.5f);
//		gl.glVertex3f(houseX1, houseY1, 0f);
//		gl.glVertex3f(hx2, hy2, 0f);
//		gl.glVertex3f(hx3, hy3, 0f);
//		gl.glEnd();
		
		
		
		final GL2 gl = dr.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0f, 0f);
		gl.glRotatef(this.triangleOneRotation, 0f, 1.0f, 0f);
		gl.glBegin(GL.GL_TRIANGLES);
		
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3f(100f, 100f, 0.0f);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(400f, 100f, 0.0f);
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3f(250f, 400f, 0.0f);
		
		gl.glEnd();
		gl.glPopMatrix();
		
		
		this.triangleOneRotation += 0.01;
		if (this.triangleOneRotation > 1)
			this.triangleOneRotation = 0;
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable dr) {
		GL2 gl = dr.getGL().getGL2();
		GLU glu = new GLU();
		GLUT glut = new GLUT();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		glu.gluOrtho2D(0.0, dim.getWidth(), 0.0, dim.getHeight());
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

}
