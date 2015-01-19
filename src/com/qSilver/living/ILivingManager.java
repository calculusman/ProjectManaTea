package com.qSilver.living;

public interface ILivingManager
{
	public boolean initiate(Living parLiving);
	public void destroy(Living parLiving);
	public void update(Living parLiving);

	public Living instantiate(Living parLiving);
}
