package jogltest;

import java.awt.DisplayMode;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class Rubik implements GLEventListener {
	public static DisplayMode dm, dm_old;
	private GLU glu = new GLU();
	private float rquad = 0.0f;
	
	private int[] randomColors;
	int squareColorIncrement;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0f, 0f, -5.0f);
		
		gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f);
		
//		gl.glEnable(GL2.GL_LIGHTING);
//		gl.glEnable(GL2.GL_COLOR_MATERIAL);
//		gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR);
//		float[] floats = {0.0f, 0.7f, 0.5f, 1.0f};
//		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, floats, 0);
		
		float[] gold = { 0.24725F, 0.1995F, 0.0745F, 1.0F,
						 0.75164F, 0.60648F, 0.22648F, 1.0F,
						 0.628281F, 0.555802F, 0.366065F, 1.0F,
						 50.0F
		};
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, gold, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, gold, 4);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, gold, 8);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, gold[12]);
		
		
//		gl.glEnable(GL2.GL_LIGHTING);
//		gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPECULAR, gold, 0);
		
		
//		gl.glEnable(GL2.GL_LIGHTING); 
//		gl.glEnable(GL2.GL_LIGHT0);  
//		gl.glEnable(GL2.GL_NORMALIZE); 
//
//		float[] ambientLight = { 0.1f, 0.f, 0.f,0f };  // weak RED ambient 
//		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0); 
//
//		float[] diffuseLight = { 1f,2f,1f,0f };  // multicolor diffuse 
//		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0);
		
		
//		gl.glEnable(GL2.GL_LIGHTING);
//		float[] blue1 = { 0.4f, 0.4f, 0.6f, 1f };
//		float[] blue2 = { 0.0f,   0f, 0.8f, 1f };
//		float[] blue3 = { 0.0f,   0f, 0.15f, 1f };
//		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, blue1, 0);
//		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, blue2, 0);
//		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, blue3, 0);
		
//		float[] position = { 1, 2, 3, 1};
//		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position, 0);
		
		
		drawCube(gl, -0.75f, -0.75f, 0.0f, 0.5f);
		
		
//		gl.glEnable(GL2.GL_LIGHTING);
//		float[] floats = {0.0f, 0.7f, 0.5f, 1.0f};
//		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, floats, 0);
//		gl.glEnable(GL2.GL_LIGHT0);
////		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 128f);
		
		gl.glFlush();
			
        rquad -= 0.75f;
	}
	
	public void drawCube(GL2 gl, float startX, float startY, float startZ, float length) {
		gl.glBegin(GL2.GL_QUADS);
		
		squareColorIncrement = 0;
		
		float x = startX;
		float y = startY;
		float z = startZ;
		gl = drawFace(gl, x, y, z, length);

		z = z + length * 3;
		gl = drawFace(gl, x, y, z, length);
		
		x = startX + length * 3;
		y = startY;
		z = startZ;
		gl = drawFace2(gl, x, y, z, length);
		
		x = startX;
		gl = drawFace2(gl, x, y, z, length);
		
		x = startX;
		y = startY;
		z = startZ;
		gl = drawFace3(gl, x, y, z, length);
		
		y = y + length * 3;
		gl = drawFace3(gl, x, y, z, length);

		
		gl.glEnd();
	}
	
	public GL2 drawFace3(GL2 gl, float x, float y, float z, float length) {
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 2);
		
		x = x + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 2);
		
		x = x + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 2);
		
		z = z + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 2);
		
		x = x - length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 2);
		
		x = x - length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 2);
		
		z = z + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 2);
		
		x = x + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 2);
		
		x = x + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 2);
		
		return gl;
	}
	
	public GL2 drawFace2(GL2 gl, float x, float y, float z, float length) {
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 1);
		
		z = z + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 1);
		
		z = z + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 1);
		
		y = y + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 1);
		
		z = z - length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 1);
		
		z = z - length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 1);
		
		y = y + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 1);
		
		z = z + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 1);
		
		z = z + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 1);
		
		return gl;
	}
	
	public GL2 drawFace(GL2 gl, float x, float y, float z, float length) {
		
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 0);
		
		x = x + length; 
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 0);
		
		x = x + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 0);
		
		y = y + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 0);
		
		x = x - length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 0);
	
		x = x - length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 0);
		
		y = y + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 0);
		
		x = x + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 0);
		
		x = x + length;
		gl = drawSquare(gl, x, y, z, length, randomColors[squareColorIncrement++], 0);
		
		return gl;
	}
	
	public GL2 drawSquare(GL2 gl, float x, float y, float z, float length, int color, int face) {
		if (color == 0) {
			gl.glColor3f(1.0f, 0.0f, 0.0f);
		} else if (color == 1) {
			gl.glColor3f(0.0f, 1.0f, 0.0f);
		} else if (color == 2) {
			gl.glColor3f(0.0f, 0.0f, 1.0f);
		} else if (color == 3) {
			gl.glColor3f(1.0f, 1.0f, 0.0f);
		} else if (color == 4) {
			gl.glColor3f(0.0f, 1.0f, 0.0f);
		} else if (color == 5) {
			gl.glColor3f(1.0f, 0.0f, 1.0f);
		}
		
		if (face == 0) {
			gl.glVertex3f(x, y, z);
			gl.glVertex3f(x + length, y, z);
			gl.glVertex3f(x + length, y + length, z);
			gl.glVertex3f(x, y + length, z);
		} else if (face == 1) {
			gl.glVertex3f(x, y, z);
			gl.glVertex3f(x, y, z + length);
			gl.glVertex3f(x, y + length, z + length);
			gl.glVertex3f(x, y + length, z);
		} else if (face == 2) {
			gl.glVertex3f(x, y, z);
			gl.glVertex3f(x + length, y, z);
			gl.glVertex3f(x + length, y, z + length);
			gl.glVertex3f(x, y, z + length);
		}
		
		return gl;
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
		
			
		Random random = new Random();
		randomColors = new int[100];
		for (int i = 0; i < randomColors.length; i++) {
			randomColors[i] = random.nextInt(6);
		}
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL2 gl = drawable.getGL().getGL2();
		if (height < 0) {
			height = 1;
		}
		
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
		Rubik rubik = new Rubik();
		
		glcanvas.addGLEventListener(rubik);
		glcanvas.setSize(1500, 1000);
		
		final JFrame frame = new JFrame("Rubik's cube");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		animator.start();
	}
}
