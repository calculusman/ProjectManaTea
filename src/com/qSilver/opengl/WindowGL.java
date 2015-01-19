package com.qSilver.opengl;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.bStone.Start;
import com.bStone.engine.render.IRenderable;
import com.bStone.engine.render.drawable.render.Render;
import com.bStone.engine.render.window.Window;

public class WindowGL extends Window implements GLEventListener
{
	static
	{
		GLProfile.initSingleton();
	}

	private final GLProfile glProfile;
	private final GLCapabilities glCapabilities;
	private final GLCanvas canvas;

	public WindowGL(IRenderable... parClients)
	{
		super(new JFrame());

		for(IRenderable renderable : parClients)
		{
			this.addListener(renderable);
		}

		this.glProfile = GLProfile.getDefault();
		this.glCapabilities = new GLCapabilities(this.glProfile);

		this.canvas = new GLCanvas(this.glCapabilities);
		this.canvas.addGLEventListener(this);
	}

	@Override
	public void display(GLAutoDrawable parDrawable)
	{
		GL2 gl = parDrawable.getGL().getGL2();
		Render.setGLContext(gl);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glPushMatrix();
		for(IRenderable renderable : this.getListeners())
		{
			renderable.onDisplay();
		}
		gl.glPopMatrix();

		Render.setGLContext(null);
	}

	@Override
	public void init(GLAutoDrawable parDrawable)
	{
		GL2 gl = parDrawable.getGL().getGL2();
		Render.setGLContext(gl);

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		Render.setGLContext(null);
	}

	@Override
	public void dispose(GLAutoDrawable parDrawable) {}

	@Override
	public void reshape(GLAutoDrawable parDrawable, int parWinX, int parWinY, int parWinWidth, int parWinHeight)
	{
		GL2 gl = parDrawable.getGL().getGL2();
		Render.setGLContext(gl);

		for(IRenderable renderable : this.getListeners())
		{
			Start.out("...Resizing " + renderable.getClass().getSimpleName() + "...");
			renderable.onWindowResize();
		}

		Render.setGLContext(null);
	}

	@Override
	public void update()
	{
		this.canvas.display();
	}

	@Override
	public GLCanvas getCanvas()
	{
		return this.canvas;
	}
}
