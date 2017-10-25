package jogltest;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.gl2.GLUT; 

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.gl2.GLUT; 

public class FourLights extends JPanel implements GLEventListener {
	
	public static void main(String[] args) {
		JFrame window = new JFrame("A lighting demo");
		FourLights panel = new FourLights();
		window.setContentPane(panel);
		window.pack();
		window.setLocation(50, 50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	private JCheckBox animating;
	private JCheckBox viewpointLight;
	private JCheckBox redLight;
	private JCheckBox greenLight;
	private JCheckBox blueLight;
	private JCheckBox ambientLight;
	private JCheckBox drawBase;
	private GLJPanel display;
	private Timer animationTimer;
	private int frameNumber = 0;
	private Camera camera;
	private GLUT glut = new GLUT();
	
	public FourLights() {
		GLCapabilities caps = new GLCapabilities(null);
		display = new GLJPanel(caps);
		display.setPreferredSize(new Dimension(1500,1000));
		display.addGLEventListener(this);
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		camera = new Camera();
		camera.lookAt(5,  10, 30, 0, 0, 0, 0, 1, 0);
		camera.setScale(15);
		camera.installTrackball(display);
		animationTimer = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frameNumber++;
				display.repaint();
			}
		});
		ActionListener boxHandler = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == animating) {
					if (animating.isSelected()) {
						animationTimer.start();
					}
					else {
						animationTimer.stop();
					}
				}
				else {
					display.repaint();
				}
			}
		};
		viewpointLight = new JCheckBox("Viewpoint light", true);
		redLight = new JCheckBox("Red Light", true);
		blueLight = new JCheckBox("Blue Light", true);
		greenLight = new JCheckBox("Green Light", true);
		ambientLight = new JCheckBox("Global Ambient Light", true);
		animating = new JCheckBox("Animate", true);
		drawBase = new JCheckBox("Draw Base", true);
		viewpointLight.addActionListener(boxHandler);
		ambientLight.addActionListener(boxHandler);
		redLight.addActionListener(boxHandler);
		greenLight.addActionListener(boxHandler);
		blueLight.addActionListener(boxHandler);
		animating.addActionListener(boxHandler);
		drawBase.addActionListener(boxHandler);
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(2, 1));
		JPanel row1 = new JPanel();
		row1.add(animating);
		row1.add(drawBase);
		row1.add(ambientLight);
		bottom.add(row1);
		JPanel row2 = new JPanel();
		row2.add(viewpointLight);
		row2.add(redLight);
		row2.add(greenLight);
		row2.add(blueLight);
		bottom.add(row2);
		add(bottom, BorderLayout.SOUTH);
		animationTimer.setInitialDelay(500);
		animationTimer.start();
	}
	
	private void lights(GL2 gl) {
		gl.glColor3d(0.5, 0.5, 0.5);
		float zero[] = { 0, 0, 0, 1 };
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, zero, 0);
		
		if (viewpointLight.isSelected())
			gl.glEnable(GL2.GL_LIGHT0);
		else
			gl.glDisable(GL2.GL_LIGHT0);
		
		if (redLight.isSelected()) {
			float red[] = { 0.5f, 0, 0, 1 };
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, red, 0);
			gl.glEnable(GL2.GL_LIGHT1);
		}
		else {
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, zero, 0);
			gl.glDisable(GL2.GL_LIGHT1);
		}
		
		gl.glPushMatrix();
		gl.glRotated(-frameNumber, 0, 1, 0);
		gl.glTranslated(10, 7, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, zero, 0);
		glut.glutSolidSphere(0.5, 16, 8);
		gl.glPopMatrix();
		
		if (greenLight.isSelected()) {
			float green[] = { 0, 0.5f, 0, 1 };
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, green, 0);
			gl.glEnable(GL2.GL_LIGHT2);
		}
		else {
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, zero, 0);
			gl.glDisable(GL2.GL_LIGHT2);
		}
		gl.glPushMatrix();
		gl.glRotated((frameNumber+100)*0.8743, 0, 1, 0);
		gl.glTranslated(9, 8, 0);
		gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_POSITION, zero, 0);
		glut.glutSolidSphere(0.5, 16, 8);
		gl.glPopMatrix();
		
		if (blueLight.isSelected()) {
			float blue[] = { 0, 0, 0.5f, 1 };
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, blue, 0);
			gl.glEnable(GL2.GL_LIGHT3);
		} else {
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, zero, 0);
			gl.glDisable(GL2.GL_LIGHT3);
		}
		gl.glPushMatrix();
		gl.glRotated((frameNumber-100)*1.3057, 0, 1, 0);
		gl.glTranslated(9.5, 7.5, 0);
		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_POSITION, zero, 0);
		glut.glutSolidSphere(0.5, 16, 8);
		gl.glPopMatrix();
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, zero, 0);
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glClearColor(0, 0, 0,  0);;
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		
		camera.apply(gl);
		
		lights(gl);
		
		float zero[] = { 0, 0, 0, 1 };
		
		if (ambientLight.isSelected()) {
			gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, new float[] { 0.15f, 0.15f, 0.15f, 1 }, 0);
		} else {
			gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, zero, 0);
		}
		
		if (drawBase.isSelected()) {
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, zero, 0);
			gl.glPushMatrix();
			gl.glTranslated(0, -5, 0);
			gl.glRotated(-90, 1, 0, 0);
			gl.glScaled(10, 10, 0.5);
			drawCylinder(gl);
			gl.glPopMatrix();
		}
		
		gl.glColor3d(0.7, 0.7, 0.7);
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[] { 0.2f, 0.2f, 0.2f, 1 }, 0);
		
		gl.glPushMatrix();
		glut.glutSolidTeapot(6);
		gl.glPopMatrix();
	}
	
	private float[] colorArrayForHue(double hue) {
		Color c = Color.getHSBColor((float)hue, 1, 0.6F);
		return new float[] { c.getRed()/255.0F, c.getGreen()/255.0f, c.getBlue()/255.0F, 1 };	
	}
	
	private void drawCylinder(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		for (int i = 0; i <= 64; i++) {
			double angle = 2 * Math.PI / 64 * i;
			double x = Math.cos(angle);
			double y = Math.sin(angle);
			gl.glColor3fv(colorArrayForHue(i/64.0), 0);
			gl.glNormal3d(x, y, 0);
			gl.glVertex3d(x, y, 1);
			gl.glVertex3d(x, y, -1);
		}
		gl.glEnd();
		gl.glNormal3d(0, 0, 1);
		
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glColor3d(1, 1, 1);
		gl.glVertex3d(0, 0, 1);
		for (int i = 0; i <= 64; i++) {
			double angle = 2 * Math.PI / 64  * i;
			double x = Math.cos(angle);
			double y = Math.sin(angle);
			gl.glColor3fv(colorArrayForHue(i/64.0), 0);
			gl.glVertex3d(x, y, 1);
		}
		gl.glEnd();
		gl.glNormal3f(0, 0, -1);
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glColor3d(1, 1, 1);
		gl.glVertex3d(0, 0, -1);
		for (int i = 64; i >= 0; i--) {
			double angle = 2 * Math.PI/64 * i;
			double x = Math.cos(angle);
			double y = Math.sin(angle);
			gl.glColor3fv(colorArrayForHue(i/64.0), 0);
			gl.glVertex3d(x, y, -1);
		}
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0,  0, 0, 1);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glLightModeli(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, 1);
		gl.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 32);
		
		float dim[] = { 0.5F, 0.5F, 0.5F, 1 };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, dim, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, dim, 0);
		
		float red[] = { 0.5F, 0, 0, 1 };
		float reda[] = { 0.1F, 0, 0, 1 };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, reda, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, red, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, red, 0);
		
		float gr[] = { 0, 0.5F, 0, 1 };
		float gra[] = { 0, 0.1F, 0, 1 };
		gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_AMBIENT, gra, 0);
		gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_DIFFUSE, gr, 0);
		gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPECULAR, gr, 0);
		
		float bl[] = { 0, 0, 0.5F, 1 };
		float bla[] = { 0, 0, 0.1F, 1 };
		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_AMBIENT, bla, 0);
		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_DIFFUSE, bl, 0);
		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_SPECULAR, bl, 0);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

}
