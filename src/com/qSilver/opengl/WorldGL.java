package com.qSilver.opengl;

import javax.media.opengl.GL2;

import com.bStone.Start;
import com.bStone.engine.render.drawable.render.Render;
import com.qSilver.collision.BoundingBox;
import com.qSilver.living.world.World;

public abstract class WorldGL extends World
{
	public WorldGL(String parName)
	{
		super(parName);
	}

	@Override
	public void onRender()
	{
		super.onRender();

		GL2 gl = Render.gl();
		if (Start.DEBUG_MODE)
		{
			gl.glPushMatrix();
			for(BoundingBox boundingBox : this.getBoundingBoxPool())
			{
				gl.glPushMatrix();
				gl.glTranslatef(boundingBox.getPosX(), boundingBox.getPosY(), 1F);
				gl.glColor3f(0F, 1F, 1F);
				Render.drawBox(boundingBox.getWidth(), boundingBox.getHeight());
				gl.glPopMatrix();
			}
			gl.glPopMatrix();

			gl.glPushMatrix();
			{
				gl.glColor3f(1F, 0F, 0F);
				gl.glTranslatef(this.getViewPort().getMousePosX(), this.getViewPort().getMousePosY(), 0F);
				Render.drawBoxFill(4F, 4F);
			}
			gl.glPopMatrix();
		}
	}
}
