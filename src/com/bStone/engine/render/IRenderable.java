package com.bStone.engine.render;

import com.bStone.engine.render.window.Window;

/**Must implement this to be a client of {@link Window}*/
public interface IRenderable
{
	public void onDisplay();
	public void onWindowResize();
}
