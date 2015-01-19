package com.bStone.engine.render.drawable;

import com.bStone.util.CardinalDirection;
import com.bStone.util.IPositionable;
import com.bStone.util.math.Point;

public abstract class Drawable implements IPositionable, Comparable<Drawable>
{
	private IDrawableManager screenObj;
	protected boolean dead;
	private float depth;

	public Drawable(float parDepth)
	{
		this.depth = parDepth;
		this.dead = false;
	}

	public boolean onInit(IDrawableManager parScreen)
	{
		this.setScreenObj(parScreen);
		return true;
	}

	public void onDestroy() {}

	public abstract void onUpdate();

	public boolean isDead()
	{
		return this.dead;
	}

	public void setDead()
	{
		this.dead = true;
	}

	public IDrawableManager getScreenObj()
	{
		return this.screenObj;
	}

	public Drawable setScreenObj(IDrawableManager parScreen)
	{
		this.screenObj = parScreen;
		return this;
	}

	public Drawable setDepth(float parDepth)
	{
		this.depth = parDepth;
		return this;
	}

	public float getDepth()
	{
		return this.depth;
	}

	public float getOrientationPosX(CardinalDirection parOrientation)
	{
		switch(parOrientation)
		{
			case CENTER: return this.getPosX() + this.getWidth() / 2;
			case EAST: return this.getPosX() + this.getWidth();
			case NORTHEAST: return this.getPosX() + this.getWidth();
			case NORTH: return this.getPosX() + this.getWidth() / 2;
			case NORTHWEST: return this.getPosX();
			case WEST: return this.getPosX();
			case SOUTHWEST: return this.getPosX();
			case SOUTH: return this.getPosX() + this.getWidth() / 2;
			case SOUTHEAST: return this.getPosX() + this.getWidth();
			default: return this.getPosX() + this.getWidth() / 2;
		}
	}

	public float getOrientationPosY(CardinalDirection parOrientation)
	{
		switch(parOrientation)
		{
			case CENTER: return this.getPosY() + this.getHeight() / 2;
			case EAST: return this.getPosY() + this.getHeight() / 2;
			case NORTHEAST: return this.getPosY();
			case NORTH: return this.getPosY();
			case NORTHWEST: return this.getPosY();
			case WEST: return this.getPosY() + this.getHeight() / 2;
			case SOUTHWEST: return this.getPosY() + this.getHeight();
			case SOUTH: return this.getPosY() + this.getHeight();
			case SOUTHEAST: return this.getPosY() + this.getHeight();
			default: return this.getPosY() + this.getHeight() / 2;
		}
	}

	public Point getOrientationPoint(CardinalDirection parOrientation)
	{
		return new Point(this.getOrientationPosX(parOrientation), this.getOrientationPosY(parOrientation));
	}

	public void onWindowResize() {}

	@Override
	public abstract void onPositionUpdate();

	@Override
	public abstract void setPosX(float parPosX);

	@Override
	public abstract void setPosY(float parPosY);

	@Override
	public abstract float getPosX();

	@Override
	public abstract float getPosY();

	@Override
	public abstract float getPosDX();

	@Override
	public abstract float getPosDY();

	public abstract float getWidth();
	public abstract float getHeight();

	@Override
	public int compareTo(Drawable parDrawable)
	{
		return this.depth > parDrawable.depth ? 1 : this.depth < parDrawable.depth ? -1 : this.hashCode() - parDrawable.hashCode();
	}

	@Override
	public String toString()
	{
		return this.screenObj.toString() + " : " + (this.isDead() ? "-DEAD : " : "") + super.toString();
	}
}
