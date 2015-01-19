package com.qSilver.opengl;

import java.awt.Component;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.bStone.engine.render.drawable.IDrawableManager;
import com.bStone.engine.render.drawable.render.Render;
import com.bStone.engine.render.drawable.view.IViewable;
import com.bStone.engine.render.drawable.view.ViewPort;
import com.qSilver.render.string.StringRenderer;

public class ViewPortGL extends ViewPort
{
	public boolean DEPTH_TEST = false;

	public ViewPortGL(Component parComponent, int parWinX, int parWinY, float parDepth, int parWidth, int parHeight)
	{
		super(parComponent, parWinX, parWinY, parDepth, parWidth, parHeight);
	}

	@Override
	public boolean onInit(IDrawableManager parScreen)
	{
		boolean flag = super.onInit(parScreen);
		this.onWindowResize();
		return flag;
	}

	@Override
	public void render()
	{
		GL2 gl = Render.gl();
		gl.glMatrixMode(GL2.GL_MODELVIEW);

		//Enable Textures
		gl.glEnable(GL.GL_TEXTURE_2D);

		//Enable Depth Testing
		if (this.DEPTH_TEST)
		{
			gl.glEnable(GL.GL_DEPTH_TEST);
		}
		else
		{
			gl.glDisable(GL.GL_DEPTH_TEST);
		}

		//Enable Transparency
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

		gl.glPushMatrix();
		super.render();
		gl.glPopMatrix();

		//Disable Transparency
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glDisable(GL.GL_BLEND);

		//Disable Depth Testing
		gl.glDisable(GL.GL_DEPTH_TEST);

		//Disable Textures
		gl.glDisable(GL.GL_TEXTURE_2D);
	}

	@Override
	protected void renderView()
	{
		GL2 gl = Render.gl();
		gl.glTranslatef(this.viewX, this.viewY, 0F);

		gl.glPushMatrix();
		{
			//TODO: MAKE IT NOT CENTERED?
			gl.glTranslatef(this.getPortWidth() / 2, this.getPortHeight() / 2, 0F);
			gl.glScalef(this.getPortWidth() / this.getViewWidth(), this.getPortHeight() / this.getViewHeight(), 1F);
			gl.glTranslatef(this.posX, this.posY, 0F);

			for(IViewable viewable : this.clients)
			{
				viewable.onRender();
			}
		}
		gl.glPopMatrix();
	}

	@Override
	protected void renderDebug()
	{
		String str = "";
		for(IViewable viewable : this.clients)
		{
			str += viewable.getClass().getSimpleName() + ", ";
		}

		str = str.substring(0, str.length() - 2);

		GL2 gl = Render.gl();
		gl.glPushMatrix();
		{
			gl.glScalef(this.getPortWidth() / this.getViewWidth() / this.getScaleProgress(), this.getPortHeight() / this.getViewHeight() / this.getScaleProgress(), 1F);
			gl.glColor3f(1F, 0F, 1F);
			Render.drawBox(this.getViewWidth() * this.getScaleProgress(), this.getViewHeight() * this.getScaleProgress());
			gl.glTranslatef(4F, 4F, 0F);
			StringRenderer.renderString(str);
		}
		gl.glPopMatrix();
	}

	@Override
	public void onWindowResize()
	{
		super.onWindowResize();
		GL2 gl = Render.gl();

		gl.glViewport(this.getPortX(), this.getPortY(), this.getPortWidth(), this.getPortHeight());

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		gl.glOrtho(0, (float) this.getPortWidth(), (float) this.getPortHeight(), 0, -1, 1);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}
}
