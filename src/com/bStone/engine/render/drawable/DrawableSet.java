package com.bStone.engine.render.drawable;

import java.util.List;

import com.bStone.util.collection.ObjectSortedSet;

public class DrawableSet extends ObjectSortedSet<Drawable>
{
	private final IDrawableManager screenObj;

	public DrawableSet(IDrawableManager parScreen)
	{
		this.screenObj = parScreen;
	}

	public void updateDisplay()
	{
		for(Drawable drawable : this.livingList)
		{
			if (drawable.isDead())
			{
				this.remove(drawable);
			}
			else
			{
				drawable.onUpdate();
			}
		}
	}

	public void updateWindowResize()
	{
		for(Drawable drawable : this.livingList)
		{
			if (!drawable.isDead())
			{
				drawable.onWindowResize();
			}
		}
	}

	@Override
	public void dumpTrashFromList()
	{
		for(Drawable drawable : this.trashList)
		{
			drawable.onDestroy();
		}

		super.dumpTrashFromList();
	}

	@Override
	public List<Drawable> addSpawnToLivingList()
	{
		List<Drawable> list = super.addSpawnToLivingList();
		for(Drawable drawable : list)
		{
			if (!drawable.onInit(this.screenObj))
			{
				this.remove(drawable);
			}
		}

		return list;
	}
}
