package jogltest;

import java.awt.Frame;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class BasicFrame implements GLEventListener {

	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
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
      //getting the capabilities object of GL2 profile
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      BasicFrame b = new BasicFrame();
      glcanvas.addGLEventListener(b);        
      glcanvas.setSize(400, 400);
      
      final Frame frame = new Frame("Basic Frame");
      
      frame.add(glcanvas);
      frame.setSize(640, 480);
      frame.setVisible(true);
	}
}