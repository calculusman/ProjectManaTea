package com.qSilver.render.gui;

import com.bStone.Start;
import com.bStone.engine.input.InputEngine;
import com.bStone.engine.render.drawable.Drawable;
import com.bStone.engine.render.drawable.view.ViewPort;
import com.qSilver.living.world.World;

public abstract class GuiScreen extends Screen
{
	public GuiScreen(World parParent)
	{
		super(parParent);
	}

	@Override
	protected abstract ViewPort setupViewPort();

	@Override
	public void onInput()
	{
		if (Start.getApplication().getInputEngine().isMouseReleased(InputEngine.MOUSE_LEFT))
		{
			this.clickDrawables(InputEngine.MOUSE_LEFT);
		}

		if (Start.getApplication().getInputEngine().isMouseReleased(InputEngine.MOUSE_RIGHT))
		{
			this.clickDrawables(InputEngine.MOUSE_RIGHT);
		}

		if (Start.getApplication().getInputEngine().isMouseReleased(InputEngine.MOUSE_MIDDLE))
		{
			this.clickDrawables(InputEngine.MOUSE_MIDDLE);
		}
	}

	private void clickDrawables(int parButton)
	{
		for(Drawable drawable : this.getDrawables())
		{
			if (drawable instanceof Gui)
			{
				Gui gui = (Gui) drawable;
				if (gui.toBox().intersects(this.getViewPort().getMousePos()))
				{
					gui.onClick(parButton);
				}
			}
		}
	}
}
