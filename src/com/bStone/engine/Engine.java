package com.bStone.engine;

import com.bStone.Start;
import com.bStone.application.Application;

/**
 * Handles more complex, independent processes.
 * <br>Could be or is a threaded process.
 * <br>Used by {@link Application}
 * */
public abstract class Engine implements Runnable
{
	private final Application application;
	private final Engine[] dependencies;
	private final Thread thread;

	private volatile boolean running;
	private volatile boolean initiating;
	private volatile boolean terminating;

	public Engine(Application parApplication, Engine... parDependencies)
	{
		this.application = parApplication;
		this.dependencies = parDependencies;
		this.thread = this.setupThread();

		this.application.getEngines().add(this);
	}

	protected Thread setupThread()
	{
		return new Thread(this, this.getClass().getSimpleName());
	}

	public synchronized void start()
	{
		this.running = true;

		if (this.thread != null)
		{
			this.thread.start();
		}
		else
		{
			this.run();
		}
	}

	public synchronized void stop()
	{
		this.running = false;
	}

	public void destroy()
	{
		this.application.getEngines().remove(this);
		Start.out("...Terminated " + this.getClass().getSimpleName());

		if (this.thread != null)
		{
			try
			{
				this.thread.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run()
	{
		if (!this.initiating)
		{
			this.initiating = true;
			Start.out("Initiating " + this.getClass().getSimpleName() + "...");
			this.initiate();
		}

		while(this.running)
		{
			this.update();
			if (this.thread == null) break;
		}

		if (!this.running && !this.terminating)
		{
			this.terminating = true;
			Start.out("Terminating " + this.getClass().getSimpleName() + "...");
			this.terminate();
			this.destroy();
		}
	}

	protected abstract void initiate();
	protected abstract void terminate();
	public abstract void update();

	public final boolean isAlive()
	{
		return !this.terminating || (this.thread != null ? this.thread.isAlive() : false);
	}

	protected final Engine[] getDependencies()
	{
		return this.dependencies;
	}

	public final Application getApplication()
	{
		return this.application;
	}

	public final Thread toThread()
	{
		return this.thread;
	}
}
