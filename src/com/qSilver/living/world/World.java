package com.qSilver.living.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bStone.engine.render.drawable.view.ViewPort;
import com.bStone.util.IPositionable;
import com.qSilver.collision.BoundingBox;
import com.qSilver.collision.IBoundingBoxPool;
import com.qSilver.render.gui.GuiScreen;
import com.qSilver.render.gui.ScreenSet;

public abstract class World extends WorldBase implements IBoundingBoxPool
{
	private List<BoundingBox> boundingBoxPool;
	private ScreenSet screens;

	public World(String parName)
	{
		super(parName);

		this.boundingBoxPool = new ArrayList<BoundingBox>();
		this.screens = new ScreenSet();
		this.screens.addAll(Arrays.asList(this.setupGuiScreen()));
	}

	protected abstract GuiScreen[] setupGuiScreen();

	@Override
	protected abstract ViewPort setupViewPort();

	@Override
	public void onInitiate()
	{
		super.onInitiate();
		this.screens.initiate();
	}

	@Override
	public void onTerminate()
	{
		super.onTerminate();
		this.screens.terminate();
	}

	@Override
	public void onInput()
	{
		super.onInput();
		this.screens.input();
	}

	@Override
	public BoundingBox addBoxToPool(BoundingBox parBoundingBox)
	{
		this.boundingBoxPool.add(parBoundingBox);
		return parBoundingBox;
	}

	@Override
	public BoundingBox removeBoxFromPool(BoundingBox parBoundingBox)
	{
		this.boundingBoxPool.remove(parBoundingBox);
		return parBoundingBox;
	}

	@Override
	public BoundingBox getBoxFromPool(IPositionable parParent)
	{
		for(BoundingBox boundingbox : this.boundingBoxPool)
		{
			if (boundingbox.getParent() == parParent)
			{
				return boundingbox;
			}
		}

		return null;
	}

	@Override
	public List<BoundingBox> getBoundingBoxPool()
	{
		return this.boundingBoxPool;
	}

	public void addScreen(GuiScreen parScreen)
	{
		this.screens.add(parScreen);
	}

	public ScreenSet getScreens()
	{
		return this.screens;
	}
}
