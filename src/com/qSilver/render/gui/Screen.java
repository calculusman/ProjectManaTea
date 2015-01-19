package com.qSilver.render.gui;

import java.util.Set;

import com.bStone.Start;
import com.bStone.engine.render.drawable.Drawable;
import com.bStone.engine.render.drawable.DrawableSet;
import com.bStone.engine.render.drawable.IDrawableManager;
import com.bStone.engine.render.drawable.view.IViewable;
import com.bStone.engine.render.drawable.view.ViewPort;
import com.qSilver.living.world.World;

public abstract class Screen implements IViewable, IDrawableManager
{
	private final World parent;
	private DrawableSet drawables;

	private ViewPort viewport;

	public Screen(World parParent)
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
		//this.drawables.updateWindowResize();
	}

	public void onInput() {}

	@Override
	public void onViewScale() {}

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