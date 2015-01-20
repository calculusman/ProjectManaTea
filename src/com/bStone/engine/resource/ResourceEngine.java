package com.bStone.engine.resource;

import java.util.Set;

import com.bStone.application.Application;
import com.bStone.engine.Engine;
import com.bStone.engine.render.IRenderable;
import com.bStone.engine.render.window.Window;
import com.bStone.engine.resource.registry.Registry;
import com.bStone.util.collection.SmallSet;

public class ResourceEngine extends Engine implements IRenderable
{
	private final Window window;

	protected ILoadable[] clients;
	protected Set<Registry> registryList;

	private boolean requireResourceReload = false;

	public ResourceEngine(Application parApplication, Window parWindow, Engine... parDependencies)
	{
		super(parApplication, parDependencies);
		this.window = parWindow;
		this.window.addListener(this);

		this.registryList = new SmallSet<Registry>();
	}

	public ResourceEngine setClients(ILoadable... parClients)
	{
		this.clients = parClients;
		return this;
	}

	@Override
	protected Thread setupThread()
	{
		return null;
	}

	@Override
	protected void initiate()
	{
		for(ILoadable loadable : this.clients)
		{
			loadable.onLoad();
		}

		for(Registry registry : this.registryList)
		{
			registry.load();
		}
	}

	@Override
	protected void terminate()
	{
		for(ILoadable loadable : this.clients)
		{
			loadable.onUnload();
		}

		for(Registry registry : this.registryList)
		{
			registry.unload();
		}
	}

	@Override
	public void update()
	{
		if (this.requireResourceReload())
		{
			this.reload();

			this.setRequireResourceReload(false);
			return;
		}

		for(ILoadable loadable : this.clients)
		{
			loadable.onContext();
		}

		for(Registry registry : this.registryList)
		{
			registry.update();
		}
	}

	public void reload()
	{
		for(ILoadable loadable : this.clients)
		{
			loadable.onReload();
		}

		for(Registry registry : this.registryList)
		{
			registry.reload();
		}

		this.setRequireResourceReload(false);
	}

	@Override
	public void onDisplay()
	{
		this.run();
	}

	@Override
	public void onWindowResize()
	{
		this.reload();
	}

	public void add(Registry parRegistry)
	{
		this.registryList.add(parRegistry);
	}

	public synchronized void setRequireResourceReload(boolean parRequire)
	{
		this.requireResourceReload = parRequire;
	}

	public synchronized boolean requireResourceReload()
	{
		return this.requireResourceReload;
	}
}
