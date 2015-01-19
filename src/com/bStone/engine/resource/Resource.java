package com.bStone.engine.resource;

import java.io.File;

public class Resource
{
	protected String path;

	public Resource(String parPath)
	{
		this.path = parPath;
	}

	public void setPath(String parPath)
	{
		this.path = parPath;
	}

	public File toFile()
	{
		return new File(this.path);
	}

	public String toPath()
	{
		return this.path;
	}

	@Override
	public String toString()
	{
		return this.toPath();
	}
}
