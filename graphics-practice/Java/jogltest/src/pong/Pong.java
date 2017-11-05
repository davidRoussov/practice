package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Pong implements GLEventListener, KeyListener {
	
	private static JFrame frame;
	
	private float playerY = 0.0f;
	private float enemyY = 0.0f;
	private float movementSpeed = 0.5f;
	
	private float puckX = 0.0f;
	private float puckY = 0.0f;
	private boolean puckDir = true;

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		gl.glPushMatrix();
		gl.glScalef(0.05f, 0.05f, 0.05f);
		gl.glTranslatef(puckX, puckY, 0.0f);
		drawPuck(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScalef(0.1f, 0.25f, 0.25f);
		gl.glTranslatef(7.0f, playerY, 0.0f);
		drawPlayer(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScalef(0.1f, 0.25f, 0.25f);
		gl.glTranslatef(-7.0f, enemyY, 0.0f);
		drawPlayer(gl);
		gl.glPopMatrix();
		
		gl.glFlush();

		
		if (puckDir) {
			puckX += 0.5f;
		} else {
			puckX -= 0.5f;
		}
		
		if (puckX >= 13.0f && puckY >= playerY - 1.0f && puckY <= playerY + 1.0f) {
			puckDir = false;
		} else if (puckX >= 20.0f) {
			frame.dispose();
		}
		
		if (puckX <= -11.0f && puckY >= enemyY - 1.0f && puckY <= enemyY + 1.0f) {
			puckDir = true;
		}
		
		
	}
	
	private void drawPlayer(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		
		gl.glVertex2f(0.0f, 1.0f);
		gl.glVertex2f(1.0f, 1.0f);
		gl.glVertex2f(1.0f, -1.0f);
		gl.glVertex2f(0.0f, -1.0f);
		gl.glVertex2f(0.0f, 1.0f);
		
		gl.glEnd();
	}
	
	private void drawPuck(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		
		gl.glVertex2f(-1.0f, -1.0f);
		gl.glVertex2f(-1.0f, 1.0f);
		gl.glVertex2f(1.0f, 1.0f);
		gl.glVertex2f(1.0f, -1.0f);
		gl.glVertex2f(-1.0f, -1.0f);
		
		gl.glEnd();
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
		Pong pong = new Pong();
		glcanvas.addGLEventListener(pong);
		glcanvas.addKeyListener(pong);
		glcanvas.setSize(1500, 1500);
		
		frame = new JFrame("Pong");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == 38 && playerY < 3.0f) {
			this.playerY += this.movementSpeed;
		} else if (code == 40 && playerY > -3.0f) {
			this.playerY -= this.movementSpeed;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
