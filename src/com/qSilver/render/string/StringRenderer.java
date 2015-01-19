package com.qSilver.render.string;

import javax.media.opengl.GL2;

import com.bStone.engine.render.drawable.render.Render;
import com.bStone.engine.resource.Resource;
import com.bStone.engine.resource.registry.TextureRegistry;
import com.qSilver.opengl.TextureGL;

public class StringRenderer
{
	public static final int LEFT_ALIGNED = 0;
	public static final int CENTERED = 1;
	public static final int RIGHT_ALIGNED = 2;

	private static FontRenderer font = new FontRenderer("tom_thumb", TextureRegistry.INSTANCE.register("tom_thumb", new TextureGL(new Resource("/tom_thumb.png"))), 16, 24);

	public static FontRenderer getFontRenderer()
	{
		return font;
	}

	public static void setFontRenderer(FontRenderer parFontRenderer)
	{
		font = parFontRenderer;
	}

	public static void renderString(String parString, float parPosX, float parPosY, float parFontScale)
	{
		if (font == null || parString == null) return;

		TextureGL bitmap = (TextureGL) font.getTexture();
		int charWidth = font.getCharWidth();
		int charHeight = font.getCharHeight();

		GL2 gl = Render.gl();
		bitmap.bind();

		gl.glPushMatrix();

		gl.glTranslatef(parPosX, parPosY, 0);
		gl.glScalef(parFontScale, parFontScale, 1F);

		gl.glBegin(GL2.GL_QUADS);
		{
			int x = 0;
			int y = 0;

			for(int i = 0; i < parString.length(); i++)
			{
				char c = parString.charAt(i);

				if (c == '\n')
				{
					y += charHeight / 2;
					x = -(i + 1) * (charHeight / 3);
					continue;
				}

				int u = font.getCharU(c);
				int v = font.getCharV(c);

				float u1 = (float) u / bitmap.getWidth();
				float v1 = (float) v / bitmap.getHeight();
				float u2 = (float) (u + charWidth) / bitmap.getWidth();
				float v2 = (float) (v + charHeight) / bitmap.getHeight();

				int j = i * (charHeight / 3);

				gl.glTexCoord2f(u1, v1);
				gl.glVertex2f(j + 0 + x, 0 + y);

				gl.glTexCoord2f(u2, v1);
				gl.glVertex2f(j + (charWidth / 2) + x, 0 + y);

				gl.glTexCoord2f(u2, v2);
				gl.glVertex2f(j + (charWidth / 2) + x, (charHeight / 2) + y);

				gl.glTexCoord2f(u1, v2);
				gl.glVertex2f(j + 0 + x, (charHeight / 2) + y);
			}
		}
		gl.glEnd();

		gl.glPopMatrix();
	}

	public static void renderString(String parString, float parPosX, float parPosY, float parFontScale, int parAlignment)
	{
		switch(parAlignment)
		{
			case StringRenderer.LEFT_ALIGNED:
				StringRenderer.renderString(parString, parPosX, parPosY, parFontScale);
				break;
			case StringRenderer.CENTERED:
				StringRenderer.renderString(parString, parPosX - (StringRenderer.getStringWidth(parString) / 2 * parFontScale), parPosY, parFontScale);
				break;
			case StringRenderer.RIGHT_ALIGNED:
				StringRenderer.renderString(parString, parPosX - (StringRenderer.getStringWidth(parString) * parFontScale), parPosY, parFontScale);
				break;
			default:
				break;
		}
	}

	public static void renderString(String parString)
	{
		StringRenderer.renderString(parString, 0F, 0F, 1F);
	}

	public static int getStringWidth(String parString)
	{
		return parString.length() * StringRenderer.font.getCharWidth() / 2;
	}
	
	public static int getStringHeight(String parString)
	{
		return StringRenderer.font.getCharHeight() / 2;
	}
}
