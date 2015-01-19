package com.bStone.application;

import java.util.ArrayList;
import java.util.List;

import com.bStone.Start;
import com.bStone.engine.Engine;
import com.bStone.engine.EngineSet;

/**
 * The basic engine and thread managing.
 * <br>Used only by {@link Application}
 * */
public abstract class ApplicationBase
{
	protected final String id;
	
	protected final Thread thread;
	private final EngineSet engines;

	private volatile ApplicationState state;

	public ApplicationBase(String parID)
	{
		this.id = parID;
		this.thread = Start.currentThread();
		this.engines = new EngineSet();

		this.state = ApplicationState.STARTING;
	}

	/**Call this to start application*/
	public abstract void start();

	/**Call this to stop application*/
	public void stop()
	{
		this.state = ApplicationState.STOPPING;
	}

	/**Called frequently by {@link Start} to update state*/
	public void update()
	{
		switch(this.state)
		{
			case STARTING:
				this.state = ApplicationState.RUNNING;
				break;
			case RUNNING:
				break;
			case STOPPING:
				boolean flag = false;
				for(Engine engine : this.engines)
				{
					flag |= engine.isAlive();
					if (engine.isAlive())
					{
						engine.stop();
						return;
					}
				}

				if (!flag)
				{
					this.state = ApplicationState.DEAD;
				}
				break;
			case DEAD:
				break;
			default:
				break;
		}
	}

	public Engine getEngineFrom(Thread parThread)
	{
		for(Engine engine : this.engines)
		{
			if (engine.toThread() == parThread) return engine;
		}

		return null;
	}

	public EngineSet getEngines()
	{
		return this.engines;
	}

	public ApplicationState getState()
	{
		return this.state;
	}

	public List<Thread> getThreads()
	{
		ArrayList<Thread> list = new ArrayList<Thread>();
		for(Engine engine : this.engines)
		{
			if (engine.toThread() != null)
			{
				list.add(engine.toThread());
			}
		}

		return list;
	}

	public boolean hasThread(Thread parThread)
	{
		if (parThread == this.thread)
		{
			return true;
		}

		for(Engine engine : this.engines)
		{
			if (engine.toThread() == parThread) return true;
		}

		return false;
	}

	public final Thread toThread()
	{
		return this.thread;
	}

	@Override
	public final String toString()
	{
		return this.id;
	}
}
