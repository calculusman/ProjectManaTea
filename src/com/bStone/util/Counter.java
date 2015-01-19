package com.bStone.util;

public class Counter
{
	private int oldValue;
	private int value;

	public Counter(int parValue)
	{
		this.value = parValue;
	}

	public void next()
	{
		this.value++;
	}

	public void cycle()
	{
		this.oldValue = this.value;
	}

	public void reset()
	{
		this.value = 0;
	}

	public int getCurrent()
	{
		return this.value - this.oldValue;
	}
	
	public int getTotal()
	{
		return this.value;
	}
}
