package com.bStone.engine.tick;

/**Must implement this to be a client of {@link TickEngine}*/
public interface ITickable
{
	public void onFirstTick();
	public void onLastTick();

	public void onTick(double parDelta);
}
