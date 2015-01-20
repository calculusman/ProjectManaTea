package com.qSilver.living.world;

import com.bStone.Start;
import com.bStone.engine.render.drawable.render.Render;
import com.bStone.engine.render.drawable.view.IViewable;
import com.bStone.engine.render.drawable.view.ViewPort;
import com.bStone.engine.resource.render.RenderRegistry;
import com.bStone.util.Counter;
import com.qSilver.living.ILivingManager;
import com.qSilver.living.Living;
import com.qSilver.living.LivingList;

public abstract class WorldBase implements IViewable, ILivingManager
{
	private final String name;
	private Counter worldTicks;

	private final LivingList livingList;

	private ViewPort viewport;

	public WorldBase(String parName)
	{
		this.name = parName;
		this.livingList = new LivingList(this);
	}

	protected abstract ViewPort setupViewPort();

	public void onLoad() {}

	public void onUnload() {}

	public void onInitiate()
	{
		this.viewport = this.setupViewPort();
		Start.getApplication().getRenderEngine().addDrawable(this.viewport);

		this.worldTicks = new Counter(0);
	}

	public void onTerminate()
	{
		Start.getApplication().getRenderEngine().removeDrawable(this.viewport);
		this.livingList.terminate();
	}

	public void onUpdate()
	{
		this.livingList.update();

		this.worldTicks.next();
	}

	public void onContext() {}

	@Override
	public void onRender()
	{
		for(Living living : this.livingList.getList())
		{
			this.render(living);
		}
	}

	public void onInput() {}

	@Override
	public void onViewScale() {}

	@Override
	public ViewPort getViewPort()
	{
		return this.viewport;
	}

	@Override
	public boolean initiate(Living parLiving)
	{
		return parLiving.onInit(this);
	}

	@Override
	public void destroy(Living parLiving)
	{
		parLiving.onDestroy();
	}

	@Override
	public void update(Living parLiving)
	{
		parLiving.onUpdate();
	}

	public boolean render(Living parLiving)
	{
		Render render = RenderRegistry.INSTANCE.get(parLiving.getClass());
		if (render != null)
		{
			render.onRender(parLiving);
			return true;
		}

		return false;
	}

	@Override
	public Living instantiate(Living parLiving)
	{
		this.livingList.add(parLiving);
		return parLiving;
	}

	public int getWorldTicks()
	{
		return this.worldTicks.getTotal();
	}

	public final String getName()
	{
		return this.name;
	}

	@Override
	public String toString()
	{
		return this.name;
	}
}
