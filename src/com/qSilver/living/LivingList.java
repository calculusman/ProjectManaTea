package com.qSilver.living;

import java.util.List;

import com.bStone.util.collection.ObjectList;

public class LivingList extends ObjectList<Living>
{
	private final ILivingManager livingManager;

	public LivingList(ILivingManager parLivingManager)
	{
		this.livingManager = parLivingManager;
	}

	@Override
	public List<Living> update()
	{
		List<Living> list = super.update();

		for(Living living : this.livingList)
		{
			if (living.isDead())
			{
				this.remove(living);
			}
			else
			{
				this.livingManager.update(living);
			}
		}

		return list;
	}

	@Override
	public void dumpTrashFromList()
	{
		for(Living living : this.trashList)
		{
			this.livingManager.destroy(living);
		}

		super.dumpTrashFromList();
	}

	@Override
	public List<Living> addSpawnToLivingList()
	{
		List<Living> list = super.addSpawnToLivingList();
		for(Living living : list)
		{
			if (!this.livingManager.initiate(living))
			{
				this.remove(living);
			}
		}

		return list;
	}
}
