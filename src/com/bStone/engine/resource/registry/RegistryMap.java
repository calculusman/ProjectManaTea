package com.bStone.engine.resource.registry;

import java.util.HashMap;
import java.util.Map;

public abstract class RegistryMap<Key, Value> extends Registry
{
	private Map<Key, Value> registry;

	public RegistryMap()
	{
		this.registry = new HashMap<Key, Value>();
	}

	public Value get(Key parKey)
	{
		return this.registry.get(parKey);
	}

	public Value register(Key parKey, Value parValue)
	{
		this.registry.put(parKey, parValue);
		return parValue;
	}

	public void clear()
	{
		this.registry.clear();
	}
	
	public Map<Key, Value> toMap()
	{
		return this.registry;
	}
}
