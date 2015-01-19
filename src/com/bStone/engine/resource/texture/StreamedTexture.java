package com.bStone.engine.resource.texture;

import java.io.File;

public abstract class StreamedTexture
{
	public static boolean RELOAD;
	private static final int UNLOAD_TIME = 10000;

	protected File file;

	private boolean inUse;
	private long lastUsedTime;

	public StreamedTexture(File parFile)
	{
		this.file = parFile;
		this.setBound(false);
	}

	public Object bind()
	{
		Object obj = this.getTexture();
		if (!this.isBound())
		{
			obj = this.loadTexture();
			this.setTexture(obj);
			this.setBound(true);
		}

		this.lastUsedTime = System.currentTimeMillis();

		return obj;
	}

	public void unbind()
	{
		if (this.isBound())
		{
			this.unloadTexture();
			this.setTexture(null);
			this.setBound(false);
		}
	}

	public void rebind()
	{
		if (this.isBound())
		{
			this.unbind();
			this.bind();
		}
	}

	public void update()
	{
		if (this.shouldUnload())
		{
			this.unbind();
		}
	}

	protected abstract Object loadTexture();
	protected abstract void unloadTexture();

	public abstract void setTexture(Object parTexture);
	public abstract Object getTexture();

	public boolean shouldUnload()
	{
		return this.isBound() && System.currentTimeMillis() - this.lastUsedTime > StreamedTexture.UNLOAD_TIME;
	}

	public boolean isBound()
	{
		return this.inUse;
	}

	protected void setBound(boolean parIsBind)
	{
		this.inUse = parIsBind;
	}

	public File getFile()
	{
		return this.file;
	}

	public abstract int getWidth();
	public abstract int getHeight();
}

