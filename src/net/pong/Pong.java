package net.pong;

import com.bStone.Start;
import com.bStone.engine.resource.render.RenderRegistry;
import com.bStone.engine.resource.texture.TextureRegistry;
import com.bStone.engine.resource.timer.TimerRegistry;
import com.qSilver.living.world.WorldManager;
import com.qSilver.opengl.ApplicationGL;

public class Pong extends ApplicationGL
{
	public static final Pong INSTANCE = new Pong();

	public static void main(String[] parStrings)
	{
		Start.add(Pong.INSTANCE);
		Start.start();
	}

	protected WorldManager worldManager;

	public Pong()
	{
		super("Pong");
		this.worldManager = new WorldManager();
	}

	@Override
	public void onLoad()
	{
		super.onLoad();
		this.getResourceEngine().add(RenderRegistry.INSTANCE);
		this.getResourceEngine().add(TextureRegistry.INSTANCE);
		this.getResourceEngine().add(TimerRegistry.INSTANCE);
	}

	@Override
	public void onUnload()
	{
		super.onUnload();
	}

	@Override
	public void onReload()
	{
		super.onReload();
	}

	@Override
	public void onFirstTick()
	{
		this.worldManager.setNextWorld(new MenuWorld());
		this.worldManager.initiate();
	}

	@Override
	public void onLastTick()
	{
		this.worldManager.terminate();
	}

	@Override
	public void onTick(double parDelta)
	{
		super.onTick(parDelta);
		this.worldManager.update();
	}

	@Override
	public void onContext()
	{
		super.onContext();
		this.worldManager.updateContext();
	}

	@Override
	public void onInputUpdate()
	{
		super.onInputUpdate();
		this.worldManager.updateInput();
	}
}
