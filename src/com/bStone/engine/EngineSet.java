package com.bStone.engine;

import com.bStone.util.collection.SmallSet;

/**List of {@link Engine} sorted by dependencies*/
public class EngineSet extends SmallSet<Engine>
{
	@Override
	public boolean add(Engine parEngine)
	{
		if (this.isEmpty()) return super.add(parEngine);

		int i = 0;
		for(Engine engine : parEngine.getDependencies())
		{
			if (this.contains(engine))
			{
				int j = this.indexOf(engine);
				i = i > j ? j : i;
			}
		}

		return super.add(i, parEngine);
	}
}
