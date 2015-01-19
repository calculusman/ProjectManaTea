package com.bStone.engine.resource;

/**Must implement this to be a client of {@link ResourceEngine}*/
public interface ILoadable
{
	public void onLoad();
	public void onUnload();
	public void onReload();

	public void onContext();
}
