package com.bStone.engine.resource.registry;

public abstract class Registry
{
	public abstract void load();
	public abstract void unload();
	public abstract void reload();

	public abstract void update();
}
