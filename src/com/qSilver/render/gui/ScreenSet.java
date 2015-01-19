package com.qSilver.render.gui;

import com.bStone.util.collection.SmallSet;

public class ScreenSet extends SmallSet<GuiScreen>
{
	public void initiate()
	{
		for(GuiScreen screen : this)
		{
			screen.initiate();
		}
	}

	public void terminate()
	{
		for(GuiScreen screen : this)
		{
			screen.terminate();
		}
	}

	public void input()
	{
		for(GuiScreen screen : this)
		{
			screen.onInput();
		}
	}
}
