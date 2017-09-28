package jogltest;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.swing.JFrame;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class ShaderDemo implements GLEventListener {

	@Override
	public void display(GLAutoDrawable dr) {
		GL2 gl = dr.getGL().getGL2();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		
		
		
		String vertstr[] = 
			{ 
			  "#version 330 core\n" + 
			  "attribute vec3 position;\n" + 
			  "void main() {\n" + 
			  "    gl_Position = vec4(position.x, position.y, position.z, 1.0);\n" + 
			  "}\n" 
			};
		
		String fragstr[] = 
			{ 
				"#version 330 core\n" + 
				"void main() {\n" +
				"gl_FragColor = vec4(1, 0, 0, 1);\n" +
				"}\n" 
			};
		
		
		
		
		
		
		
		
		
		
		
		int vlens[] = new int[1];
		int flens[] = new int[1];
		
		int vertexShader = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
		vlens[0] = vertstr[0].length();
		gl.glShaderSource(vertexShader, 1, vertstr, vlens, 0);
		gl.glCompileShader(vertexShader);
		
		checkcompileok(gl, vertexShader, GL2.GL_COMPILE_STATUS);
		
		int fragshader = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);
		flens[0] = fragstr[0].length();
		gl.glShaderSource(fragshader, 1, fragstr, flens, 0);
		gl.glCompileShader(fragshader);
		
		checkcompileok(gl, fragshader, GL2.GL_COMPILE_STATUS);
		
		int program = gl.glCreateProgram();
		gl.glAttachShader(program, vertexShader);
		gl.glAttachShader(program, fragshader);
		gl.glLinkProgram(program);
		
		checkok(gl, program, GL2.GL_LINK_STATUS);
		
		gl.glUseProgram(program);
		
		
		
		
		
		
		
		
		
		
		float[] vertices = 
			{ 
				-0.5f, -0.5f, 0.0f,
				0.5f, -0.5f, 0.0f,
				0.0f, 0.5f, 0.0f
			};

		FloatBuffer triangleVertexBuffer = Buffers
				.newDirectFloatBuffer(vertices);

		int[] vertexbuffer = new int[1];
		gl.glGenBuffers(1, vertexbuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, vertexbuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, (long) vertices.length *4,
				triangleVertexBuffer, GL2.GL_STATIC_DRAW);
	
		
		
		
		
		
		
		
		
		int programPosition = gl.glGetAttribLocation(program, "position");
		gl.glEnableVertexAttribArray(programPosition);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, vertexbuffer[0]);
		gl.glVertexAttribPointer(programPosition, 3, GL2.GL_FLOAT, false, 0, 0);

		gl.glDrawArrays(GL2.GL_TRIANGLES, 0, 3);

		gl.glFlush();
		
		
		
		
		
		
		
		
		
	}
	
private void checkok(GL2 gl2, int program, int type) {
		
		IntBuffer intBuffer = IntBuffer.allocate(1);
				
		gl2.glGetProgramiv(program, type, intBuffer);
		
		if (intBuffer.get(0) != GL.GL_TRUE) {
			int[] len = new int[1];
			gl2.glGetProgramiv(program, GL2.GL_INFO_LOG_LENGTH, len, 0);
			if (len[0] != 0) {

				byte[] errormessage = new byte[len[0]];
				gl2.glGetProgramInfoLog(program, len[0], len, 0, errormessage,
						0);
				System.err.println("problem\n" + new String(errormessage));
				System.exit(0);
			}
		}
	}
	
private void checkcompileok(GL2 gl2, int program, int type) {
		
		IntBuffer intBuffer = IntBuffer.allocate(1);
		gl2.glGetShaderiv(program, GL2.GL_COMPILE_STATUS, intBuffer);
		
		if (intBuffer.get(0) == GL.GL_FALSE) {
			int[] len = new int[1];
			gl2.glGetShaderiv(program, GL2.GL_INFO_LOG_LENGTH, len, 0);
			if (len[0] != 0) {

				byte[] errormessage = new byte[len[0]];
				gl2.glGetShaderInfoLog(program, len[0], len, 0, errormessage,
						0);
				System.err.println("problem\n" + new String(errormessage));
				System.exit(0);
			}
		}
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
		ShaderDemo shaderdemo = new ShaderDemo();
		glcanvas.addGLEventListener(shaderdemo);
		glcanvas.setSize(1200, 1200);
		
		final JFrame frame = new JFrame("Shader demo");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		FPSAnimator animator = new FPSAnimator(glcanvas, 200, true);
		animator.start();
	}

}
