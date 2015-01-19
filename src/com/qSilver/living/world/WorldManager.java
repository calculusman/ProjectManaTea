package com.qSilver.living.world;

public class WorldManager
{
	public static World theWorld;
	private World nextWorld;

	public WorldManager() {}
	
	public void initiate() {}
	
	public void terminate() {}

	public void update()
	{
		if (WorldManager.theWorld != null)
		{
			WorldManager.theWorld.onUpdate();
		}

		if (this.nextWorld != null)
		{
			this.nextWorld.onLoad();
			this.nextWorld.onInitiate();

			if (WorldManager.theWorld != null)
			{
				WorldManager.theWorld.onTerminate();
			}

			WorldManager.theWorld = this.nextWorld;
			this.nextWorld = null;
		}
	}
	
	public void updateContext()
	{
		if (WorldManager.theWorld != null)
		{
			WorldManager.theWorld.onContext();
		}
	}
	
	public void updateInput()
	{
		if (WorldManager.theWorld != null)
		{
			WorldManager.theWorld.onInput();
		}
	}

	public void setNextWorld(World parWorld)
	{
		this.nextWorld = parWorld;
	}

	public WorldBase getCurrentWorld()
	{
		return WorldManager.theWorld;
	}
}