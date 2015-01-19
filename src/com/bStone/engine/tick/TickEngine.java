package com.bStone.engine.tick;

import com.bStone.application.Application;
import com.bStone.engine.Engine;

public class TickEngine extends Engine
{
	protected static final double NANOS_PER_TICK = 1000000000D / 60D;
	protected static final int SLEEP_INT = 10;

	private long initTime;

	private double tickDelta;
	private long tickInit;

	protected ITickable[] clients;

	private boolean requireDisplayUpdate = true;

	public TickEngine(Application parApplication, Engine... parDependencies)
	{
		super(parApplication, parDependencies);
	}

	public TickEngine setClients(ITickable... parClients)
	{
		this.clients = parClients;
		return this;
	}

	@Override
	protected void initiate()
	{
		for(ITickable tickable : this.clients)
		{
			tickable.onFirstTick();
		}

		this.initTime = System.currentTimeMillis();
		this.tickInit = System.nanoTime();
	}

	@Override
	protected void terminate()
	{
		for(ITickable tickable : this.clients)
		{
			tickable.onLastTick();
		}
	}

	@Override
	public void update()
	{
		long tick = System.nanoTime();
		this.tickDelta += (tick - this.tickInit) / TickEngine.NANOS_PER_TICK;
		this.tickInit = tick;

		while(this.tickDelta >= 1)
		{
			this.tickDelta--;
			for(ITickable tickable : this.clients)
			{
				tickable.onTick(this.tickDelta);
			}
			
			this.setRequireDisplayUpdate(true);
		}

		try
		{
			Thread.sleep(TickEngine.SLEEP_INT);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public final ITickable[] getClients()
	{
		return this.clients;
	}

	public long getInitialTime()
	{
		return this.initTime;
	}

	public void setInitialTime(long parTime)
	{
		this.initTime = parTime;
	}
	
	public synchronized void setRequireDisplayUpdate(boolean parRequire)
	{
		this.requireDisplayUpdate = parRequire;
	}
	
	public synchronized boolean requireDisplayUpdate()
	{
		return this.requireDisplayUpdate;
	}
}

