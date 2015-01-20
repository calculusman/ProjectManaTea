package com.bStone.engine.resource.timer;

import com.bStone.util.Counter;

public class Timer
{
	private int timeStart;
	private int timeStop;

	private final Counter counter;
	private final int maxCount;
	private final int interval;

	private ITimeable[] clients;

	public Timer(int parMaxCount, int parInterval)
	{
		this.maxCount = parMaxCount;
		this.interval = parInterval;

		this.counter = new Counter(0);
	}

	public Timer(int parDuration)
	{
		this(1, parDuration);
	}

	public Timer setClients(ITimeable... parClients)
	{
		this.clients = parClients;
		return this;
	}

	public void start()
	{
		this.counter.cycle();
		this.timeStart = Timer.getCurrentTime();
		this.timeStop = -1;
	}

	public void stop()
	{
		this.timeStop = Timer.getCurrentTime();
		for(ITimeable timeable : this.clients)
		{
			timeable.alarm(this);
		}
	}

	public void reset()
	{
		this.start();
	}

	public void update()
	{
		if (this.timeStop <= 0 && Timer.getCurrentTime() > this.timeStart + (this.counter.getCurrent() * this.interval))
		{
			this.counter.next();

			for(ITimeable timeable : this.clients)
			{
				timeable.onCount(this);
			}
			
			if (this.counter.getCurrent() >= this.maxCount)
			{
				this.stop();
			}
		}
	}

	public int getCount()
	{
		return this.counter.getCurrent();
	}

	public int getMaxCount()
	{
		return this.maxCount;
	}

	public int getDuration()
	{
		return this.maxCount * this.interval;
	}

	public int getInterval()
	{
		return this.interval;
	}

	public int getStartTime()
	{
		return this.timeStart;
	}

	public int getStopTime()
	{
		return this.timeStop;
	}

	public Counter toCounter()
	{
		return this.counter;
	}

	public static int getCurrentTime()
	{
		return (int) System.currentTimeMillis();
	}
}
