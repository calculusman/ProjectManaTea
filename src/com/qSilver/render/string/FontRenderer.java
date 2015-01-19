package com.qSilver.render.string;

import com.bStone.engine.resource.texture.TextureBase;

public class FontRenderer
{
	private final String name;
	private final TextureBase bitmap;
	private final int charWidth;
	private final int charHeight;

	public FontRenderer(String parName, TextureBase parTexture, int parCharWidth, int parCharHeight)
	{
		this.name = parName;
		this.bitmap = parTexture;
		this.charWidth = parCharWidth;
		this.charHeight = parCharHeight;
	}

	public TextureBase getTexture()
	{
		return this.bitmap;
	}

	public int getCharWidth()
	{
		return this.charWidth;
	}

	public int getCharHeight()
	{
		return this.charHeight;
	}

	public int getCharU(char parChar)
	{
		return (((int) parChar) % (this.getTexture().getWidth() / this.charWidth)) * this.charWidth;
	}

	public int getCharV(char parChar)
	{
		return (((int) parChar) / (this.getTexture().getWidth() / this.charWidth)) * this.charHeight;
	}

	public String getName()
	{
		return this.name;
	}
}
