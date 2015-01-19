package com.bStone.application;

import java.awt.event.KeyEvent;

import com.bStone.Start;
import com.bStone.engine.input.IKeyable;
import com.bStone.engine.input.InputEngine;
import com.bStone.engine.render.RenderEngine;
import com.bStone.engine.resource.ILoadable;
import com.bStone.engine.resource.ResourceEngine;
import com.bStone.engine.tick.ITickable;
import com.bStone.engine.tick.TickEngine;
import com.bStone.util.Counter;
import com.qSilver.opengl.RenderEngineGL;

/**
 * Main class. Starts/runs/stops all engines and processes.
 * <br>
 * <br>MUST HAVE MAIN METHOD!
 * */
public class Application extends ApplicationBase implements ITickable, ILoadable, IKeyable
{
	protected final TickEngine theTickEngine;
	protected final RenderEngine theRenderEngine;
	protected final ResourceEngine theResourceEngine;
	protected final InputEngine theInputEngine;

	protected final Counter tickCounter;
	protected final Counter frameCounter;

	public Application(String parID)
	{
		super(parID);

		this.theRenderEngine = new RenderEngineGL(this);
		this.theTickEngine = new TickEngine(this, this.theRenderEngine).setClients(this);
		this.theResourceEngine = new ResourceEngine(this, this.theRenderEngine.getWindow(), this.theTickEngine, this.theRenderEngine).setClients(this);
		this.theInputEngine = new InputEngine(this, this.theRenderEngine.getWindow(), this.theTickEngine, this.theRenderEngine).setClients(this);

		this.tickCounter = new Counter(0);
		this.frameCounter = new Counter(0);
	}

	@Override
	public void start()
	{
		this.theInputEngine.start();
		this.theResourceEngine.start();
		this.theTickEngine.start();
		this.theRenderEngine.start();
	}

	@Override
	public void onLoad() {}

	@Override
	public void onUnload() {}

	@Override
	public void onReload() {}

	@Override
	public void onFirstTick() {}

	@Override
	public void onLastTick() {}

	@Override
	public void onTick(double parDelta)
	{
		this.tickCounter.next();

		this.theInputEngine.run();

		if (System.currentTimeMillis() - this.theTickEngine.getInitialTime() > 1000)
		{
			this.theTickEngine.setInitialTime(this.theTickEngine.getInitialTime() + 1000);
			this.theRenderEngine.getWindow().setTitle(this.id + " " + this.tickCounter.getCurrent() + " / " + this.frameCounter.getCurrent());

			this.tickCounter.cycle();
			this.frameCounter.cycle();
		}
	}

	@Override
	public void onContext()
	{
		this.frameCounter.next();
	}

	@Override
	public void onInputUpdate()
	{
		if (this.theInputEngine.isKeyReleased(KeyEvent.VK_F4))
		{
			this.theRenderEngine.getWindow().setFullScreen(!this.theRenderEngine.getWindow().isFullScreen());
			this.theInputEngine.consumeKey(KeyEvent.VK_F4);
		}

		if (this.theInputEngine.isKeyReleased(KeyEvent.VK_F3))
		{
			Start.DEBUG_MODE = !Start.DEBUG_MODE;
			this.theInputEngine.consumeKey(KeyEvent.VK_F3);
		}

		if (this.theInputEngine.isKeyReleased(KeyEvent.VK_ESCAPE))
		{
			if (this.theRenderEngine.getWindow().isFullScreen())
			{
				this.theRenderEngine.getWindow().setFullScreen(false);
			}
			else
			{
				Start.stop();
			}
		}
	}

	public TickEngine getTickEngine()
	{
		return this.theTickEngine;
	}

	public RenderEngine getRenderEngine()
	{
		return this.theRenderEngine;
	}

	public ResourceEngine getResourceEngine()
	{
		return this.theResourceEngine;
	}

	public InputEngine getInputEngine()
	{
		return this.theInputEngine;
	}
}
