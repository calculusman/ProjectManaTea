package com.bStone.engine.resource.texture;

import com.bStone.engine.resource.Resource;

public abstract class TextureBase extends StreamedTexture
{
	private Resource resource;

	public TextureBase(Resource parResource)
	{
		super(parResource.toFile());
	}

	public Resource getResource()
	{
		return this.resource;
	}
}
