package com.qSilver.render.gui;

import java.util.Set;

import com.bStone.Start;
import com.bStone.engine.input.InputEngine;
import com.bStone.engine.render.drawable.Drawable;
import com.bStone.engine.render.drawable.DrawableSet;
import com.bStone.engine.render.drawable.IDrawableManager;
import com.bStone.engine.render.drawable.view.IViewable;
import com.bStone.engine.render.drawable.view.ViewPort;
import com.qSilver.living.world.World;

//TODO: Not fixed! Need optimization?
public abstract class GuiScreen implements IViewable, IDrawableManager
{
	private final World parent;
	private DrawableSet drawables;

	private ViewPort viewport;

	public GuiScreen(World parParent)
	{
		this.parent = parParent;
		this.drawables = new DrawableSet(this);
	}

	protected abstract ViewPort setupViewPort();

	public void initiate()
	{
		this.viewport = this.setupViewPort();
		Start.getApplication().getRenderEngine().addDrawable(this.viewport);

		this.drawables.initiate();
	}

	public void terminate()
	{
		Start.getApplication().getRenderEngine().removeDrawable(this.viewport);

		this.drawables.terminate();
	}

	@Override
	public void onRender()
	{
		this.drawables.update();
		this.drawables.updateDisplay();
	}

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
		for(Drawable drawable : this.drawables.toSet())
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

	@Override
	public void onViewScale()
	{
		//TODO: Should not acutally be here, but why not? :P
		this.drawables.updateWindowResize();
	}

	public Drawable instantiate(Drawable parDrawable)
	{
		return this.addDrawable(parDrawable);
	}

	@Override
	public ViewPort getViewPort()
	{
		return this.viewport;
	}

	@Override
	public Drawable addDrawable(Drawable parDrawable)
	{
		return this.drawables.add(parDrawable);
	}

	@Override
	public void removeDrawable(Drawable parDrawable)
	{
		this.drawables.remove(parDrawable);
	}

	@Override
	public Set<Drawable> getDrawables()
	{
		return this.drawables.toSet();
	}

	@Override
	public float getScreenWidth()
	{
		return this.getViewPort().getWidth();
	}

	@Override
	public float getScreenHeight()
	{
		return this.getViewPort().getHeight();
	}
	
	public World getParent()
	{
		return this.parent;
	}
}
