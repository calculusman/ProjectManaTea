package com.qSilver.opengl;

public class Icon
{
	public final String texture;

	private int offsetX;
	private int offsetY;
	private int width;
	private int height;

	public Icon(String parTextureID, int parWidth, int parHeight)
	{
		this(parTextureID, 0, 0, parWidth, parHeight);
	}

	public Icon(String parTextureID, int parOffsetX, int parOffsetY, int parWidth, int parHeight)
	{
		this.texture = parTextureID;
		this.offsetX = parOffsetX;
		this.offsetY = parOffsetY;
		this.width = parWidth;
		this.height = parHeight;
	}

	public String getTextureID()
	{
		return this.texture;
	}

	public int getU()
	{
		return this.offsetX;
	}

	public int getV()
	{
		return this.offsetY;
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public static Icon createPNGIcon(TextureGL parTextureBase, int parWidth, int parHeight)
	{
		return new Icon(parTextureBase.getFile().getName().substring(0, parTextureBase.getFile().getName().length() - 4), parWidth, parHeight);
	}

	@Override
	public boolean equals(Object parObject)
	{
		if (parObject instanceof Icon)
		{
			Icon icon = (Icon) parObject;
			return this.texture.equals(icon.texture) &&
					this.width == icon.width && this.height == icon.height &&
					this.offsetX == icon.offsetX && this.offsetY == icon.offsetY;
		}

		return false;
	}
}
