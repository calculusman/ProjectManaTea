package com.bStone.engine.render.drawable.render;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.bStone.util.math.Box;

public abstract class Render extends RenderBase
{
	private static GL2 glContext;

	public static GL2 gl()
	{
		return glContext;
	}

	public static boolean isGLContext()
	{
		return gl() != null;
	}

	public static void setGLContext(GL2 parGL2)
	{
		glContext = parGL2;
	}

	@Override
	public abstract void onRender(Object parObject);

	public static void drawBoxFill(Box parBox)
	{
		gl().glPushMatrix();
		{
			gl().glTranslatef(parBox.getPosX(), parBox.getPosY(), 0F);
			Render.drawBoxFill(parBox.getWidth(), parBox.getHeight());
		}
		gl().glPopMatrix();
	}

	public static void drawBoxFill(float parWidth, float parHeight)
	{
		gl().glBindTexture(GL2.GL_TEXTURE_2D, 0);

		gl().glBegin(GL2.GL_QUADS);
		{
			gl().glVertex2f(0, 0);
			gl().glVertex2f(0, parHeight);
			gl().glVertex2f(parWidth, parHeight);
			gl().glVertex2f(parWidth, 0);
		}
		gl().glEnd();
	}

	public static void drawBox(Box parBox)
	{
		gl().glPushMatrix();
		{
			gl().glTranslatef(parBox.getPosX(), parBox.getPosY(), 0F);
			Render.drawBox(parBox.getWidth(), parBox.getHeight());
		}
		gl().glPopMatrix();
	}

	public static void drawBox(float parWidth, float parHeight)
	{
		gl().glBindTexture(GL2.GL_TEXTURE_2D, 0);

		gl().glBegin(GL2.GL_LINE_LOOP);
		{
			gl().glVertex2f(0, 0);
			gl().glVertex2f(0, parHeight);
			gl().glVertex2f(parWidth, parHeight);
			gl().glVertex2f(parWidth, 0);
		}
		gl().glEnd();
	}

	public static void drawLine(float parPosX1, float parPosY1, float parPosX2, float parPosY2)
	{
		gl().glBegin(GL.GL_LINES);
		{
			gl().glVertex2f(parPosX1, parPosY1);
			gl().glVertex2f(parPosX2, parPosY2);
		}
		gl().glEnd();
	}

	public static void rotateWithOriginOffset(float parRotation, float parOffsetX, float parOffsetY)
	{
		gl().glTranslatef(parOffsetX, parOffsetY, 0F);
		gl().glRotatef(parRotation, 0F, 0F, 1F);
		gl().glTranslatef(-parOffsetX, -parOffsetY, 0F);
	}
}
