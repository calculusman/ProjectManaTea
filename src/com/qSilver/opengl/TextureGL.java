package com.qSilver.opengl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLProfile;

import com.bStone.Start;
import com.bStone.engine.render.drawable.render.Render;
import com.bStone.engine.resource.Resource;
import com.bStone.engine.resource.texture.TextureBase;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class TextureGL extends TextureBase
{
	private Texture texture;

	public TextureGL(Resource parResourceFile)
	{
		super(parResourceFile);
	}

	@Override
	public Texture bind()
	{
		GL2 gl = Render.gl();
		Texture obj = (Texture) super.bind();
		obj.enable(gl);
		obj.bind(gl);

		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		return obj;
	}

	@Override
	protected Texture loadTexture()
	{
		String path = "/" + this.getFile().getName();
		if (path == null || path.isEmpty()) return null;

		try
		{
			InputStream stream = Texture.class.getResourceAsStream(path);
			BufferedImage image = ImageIO.read(stream);
			Texture texture = AWTTextureIO.newTexture(GLProfile.getDefault(), image, true);
			return texture;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			Start.out("ERROR: Unable to load texture: " + path);
		}

		return null;
	}

	@Override
	protected void unloadTexture()
	{
		if (this.texture != null)
		{
			this.texture.destroy(Render.gl());
		}
	}

	@Override
	public void setTexture(Object parTexture)
	{
		this.texture = (Texture) parTexture;
	}

	public float getTexU1(Icon parIcon)
	{
		return (float) parIcon.getU() / this.getWidth();
	}

	public float getTexV1(Icon parIcon)
	{
		return (float)parIcon.getV() / this.getHeight();
	}

	public float getTexU2(Icon parIcon)
	{
		return (float) (parIcon.getU() + parIcon.getWidth()) / this.getWidth();
	}

	public float getTexV2(Icon parIcon)
	{
		return (float) (parIcon.getV() + parIcon.getHeight()) / this.getHeight();
	}

	@Override
	public Texture getTexture()
	{
		return this.texture;
	}

	@Override
	public int getWidth()
	{
		return this.getTexture().getImageWidth();
	}

	@Override
	public int getHeight()
	{
		return this.getTexture().getImageHeight();
	}
}
