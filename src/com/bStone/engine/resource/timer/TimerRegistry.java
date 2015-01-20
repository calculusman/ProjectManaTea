package com.bStone.engine.resource.timer;

import com.bStone.engine.resource.registry.RegistryMap;

public class TimerRegistry extends RegistryMap<String, Timer>
{
	public static final TimerRegistry INSTANCE = new TimerRegistry();

	@Override
	public void load() {}

	@Override
	public void unload() {}

	@Override
	public void reload() {}

	@Override
	public void update()
	{
		for(String id : this.toMap().keySet())
		{
			this.get(id).update();
		}
	}
}

