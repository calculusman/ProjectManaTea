package com.bStone.engine.render.drawable;

import java.util.Set;

public interface IDrawableManager
{
	public Drawable addDrawable(Drawable parDrawable);
	public void removeDrawable(Drawable parDrawable);
	public Set<Drawable> getDrawables();

	public float getScreenWidth();
	public float getScreenHeight();
}
