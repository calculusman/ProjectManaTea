package com.bStone.engine.resource.registry;

import com.bStone.engine.resource.texture.TextureBase;

public class TextureRegistry extends RegistryMap<String, TextureBase>
{
	public static final TextureRegistry INSTANCE = new TextureRegistry();

	@Override
	public void load() {}

	@Override
	public void unload()
	{
		for(String name : this.toMap().keySet())
		{
			TextureBase texture = this.toMap().get(name);
			texture.unbind();
		}
	}

	@Override
	public void reload()
	{
		for(String name : this.toMap().keySet())
		{
			this.toMap().get(name).rebind();
		}
	}

	@Override
	public void update()
	{
		for(String name : this.toMap().keySet())
		{
			TextureBase texture = this.toMap().get(name);
			texture.update();
		}
	}
}
