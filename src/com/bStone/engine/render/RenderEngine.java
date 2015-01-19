package com.bStone.engine.render;

import java.util.Set;

import com.bStone.application.Application;
import com.bStone.engine.Engine;
import com.bStone.engine.render.drawable.Drawable;
import com.bStone.engine.render.drawable.DrawableSet;
import com.bStone.engine.render.drawable.IDrawableManager;
import com.bStone.engine.render.window.Window;

public abstract class RenderEngine extends Engine implements IRenderable, IDrawableManager
{
	public static final boolean DEPTH_TEST = false;

	protected final Window window;
	private DrawableSet drawables;

	public RenderEngine(Application parApplication, Engine... parDependencies)
	{
		super(parApplication, parDependencies);

		this.window = this.setupWindow();
		this.drawables = new DrawableSet(this);
	}

	protected abstract Window setupWindow();

	@Override
	protected void initiate()
	{
		this.window.open();
		this.drawables.initiate();
	}

	@Override
	protected void terminate()
	{
		this.drawables.terminate();
		this.window.close();
	}

	@Override
	public void update()
	{	
		if (this.getApplication().getTickEngine().requireDisplayUpdate())
		{
			this.window.update();

			this.getApplication().getTickEngine().setRequireDisplayUpdate(false);
		}
	}

	@Override
	public void onDisplay()
	{
		this.drawables.update();

		this.drawables.updateDisplay();
	}

	@Override
	public void onWindowResize()
	{
		this.drawables.updateWindowResize();
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
		return this.window.getWidth();
	}

	@Override
	public float getScreenHeight()
	{
		return this.window.getHeight();
	}

	public Window getWindow()
	{
		return this.window;
	}
}
