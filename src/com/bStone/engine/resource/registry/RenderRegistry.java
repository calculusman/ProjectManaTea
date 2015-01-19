package com.bStone.engine.resource.registry;

import com.bStone.engine.render.drawable.render.Render;
import com.qSilver.living.Living;

public class RenderRegistry extends RegistryMap<Class<? extends Living>, Render>
{
	public static final RenderRegistry INSTANCE = new RenderRegistry();

	@Override
	public void load() {}

	@Override
	public void unload() {}

	@Override
	public void reload() {}

	@Override
	public void update() {}
}