package com.bStone.engine.render.drawable.view;

import java.awt.Component;
import java.awt.MouseInfo;

import com.bStone.Start;
import com.bStone.engine.render.drawable.Drawable;
import com.bStone.util.CardinalDirection;
import com.bStone.util.math.Box;
import com.bStone.util.math.Point;

public abstract class ViewPort extends Drawable
{
	protected Component component;
	protected int winX;
	protected int winY;
	protected final int WIDTH;
	protected final int HEIGHT;
	protected int portWidth;
	protected int portHeight;

	protected int viewX;
	protected int viewY;

	private static final float MOTION_SPEED_MIN = 0.002F;

	protected float posX;
	protected float posY;
	private float nextPosX;
	private float nextPosY;

	private float posMotionX;
	private float posMotionY;
	private float posMotionSpeed = 8F;

	private float size;
	private float nextSize;

	private float sizeMotion;
	private float sizeSpeed = 12F;

	protected IViewable[] clients;

	public ViewPort(Component parComponent, int parWinX, int parWinY, float parDepth, int parWidth, int parHeight)
	{
		super(parDepth);
		this.component = parComponent;
		this.winX = parWinX;
		this.winY = parWinY;
		this.portWidth = this.WIDTH = parWidth;
		this.portHeight = this.HEIGHT = parHeight;

		this.viewX = this.viewY = 0;

		this.size = this.nextSize = 1F;
	}

	public ViewPort setClients(IViewable... parClients)
	{
		this.clients = parClients;
		return this;
	}

	@Override
	public void onUpdate()
	{
		float f;
		if (Math.abs(f = this.nextPosX - this.posX) > MOTION_SPEED_MIN)
		{
			this.posMotionX = f / this.posMotionSpeed;
		}
		else
		{
			this.posMotionX = 0F;
		}
		this.posX += this.posMotionX;

		if (Math.abs(f = this.nextPosY - this.posY) > MOTION_SPEED_MIN)
		{
			this.posMotionY = f / this.posMotionSpeed;
		}
		else
		{
			this.posMotionY = 0F;
		}
		this.posY += this.posMotionY;

		float f1;
		if (Math.abs(f1 = this.nextSize - this.size) > MOTION_SPEED_MIN)
		{
			this.sizeMotion = f1 / this.sizeSpeed;
			for(IViewable viewable : this.clients)
			{
				viewable.onViewScale();
			}
		}
		else
		{
			this.size = this.nextSize;
			this.sizeMotion = 0F;
		}

		this.size += this.sizeMotion;
		if (this.size < 0F)
		{
			this.size = this.sizeMotion = 0F;
		}

		this.render();
	}

	public void render()
	{
		if (this.clients.length == 0) return;

		this.renderView();

		if (Start.DEBUG_MODE)
		{
			this.renderDebug();
		}
	}

	protected abstract void renderView();
	protected abstract void renderDebug();

	public void onWindowResize()
	{
		this.portWidth = this.component.getWidth();
		this.portHeight = (int) (this.HEIGHT * (((float) this.HEIGHT / this.WIDTH) * this.getPortWidth()) / this.HEIGHT);

		if (this.getPortHeight() > this.component.getHeight())
		{
			this.portHeight = this.component.getHeight();
			this.portWidth = (int) (this.HEIGHT * (((float) this.WIDTH / this.HEIGHT) * this.getPortHeight()) / this.HEIGHT);
		}
	}

	/**Scale original dimension by parScale*/
	public void setViewScale(float parScale)
	{
		this.nextSize = parScale;
	}

	/**Returns "look-at" position x in world*/
	@Override
	public float getPosX()
	{
		return -this.posX;
	}

	/**Returns "look-at" position y in world*/
	@Override
	public float getPosY()
	{
		return -this.posY;
	}

	/**Set world "look-at" position*/
	public void setPosition(float parPosX, float parPosY)
	{
		this.nextPosX = -parPosX;
		this.nextPosY = -parPosY;
		this.onPositionUpdate();
	}

	public int getViewX()
	{
		return this.viewX;
	}

	public int getViewY()
	{
		return this.viewY;
	}

	public void setViewPosition(int parViewX, int parViewY)
	{
		this.viewX = parViewX;
		this.viewY = parViewY;
	}

	/**Returns current screen width; What-You-Can-See Width*/
	public float getViewWidth()
	{
		return this.WIDTH / this.size;
	}

	/**Returns current screen height; What-You-Can-See Height*/
	public float getViewHeight()
	{
		return this.HEIGHT / this.size;
	}

	public Box getViewBox()
	{
		return new Box(this.getPosX() - this.getViewWidth() / 2F, this.getPosY() - this.getViewHeight() / 2F, this.getViewWidth(), this.getViewHeight());
	}

	/**Get ViewPort X; X in Window*/
	public int getPortX()
	{
		return this.winX + (this.component.getWidth() - this.getPortWidth()) / 2;
	}

	/**Get ViewPort Y; Y in Window*/
	public int getPortY()
	{
		return this.winY + (this.component.getHeight() - this.getPortHeight()) / 2;
	}

	/**Get ViewPort Width; Width in Window*/
	public int getPortWidth()
	{
		return this.portWidth;
	}

	/**Get ViewPort Height; Height in Window*/
	public int getPortHeight()
	{
		return this.portHeight;
	}

	public Box getPortBox()
	{
		return new Box(this.getPortX(), this.getPortY(), this.getPortWidth(), this.getPortHeight());
	}

	public float getScaleProgress()
	{
		return this.size;
	}

	public float getScaleFinal()
	{
		return this.nextSize;
	}

	/*****************************/
	/**--------MOUSE POS--------**/
	/*****************************/

	/**Returns mouse x on window*/
	public float getMouseWinX()
	{
		return (float) (MouseInfo.getPointerInfo().getLocation().getX() - this.component.getLocationOnScreen().x);
	}

	/**Returns mouse y on window*/
	public float getMouseWinY()
	{
		return (float) (MouseInfo.getPointerInfo().getLocation().getY() - this.component.getLocationOnScreen().y);
	}

	/**Returns mouse x on screen*/
	public float getMouseViewX()
	{
		return this.getMouseWinX() - this.getPortX() - this.getViewX();
	}

	/**Returns mouse y on screen*/
	public float getMouseViewY()
	{
		return this.getMouseWinY() - this.getPortY() - this.getViewY();
	}

	/**Returns mouse x position in world*/
	public float getMousePosX()
	{
		return ((this.getMouseViewX() / this.getPortWidth() - 0.5F) * this.getViewWidth()) + this.getPosX();
	}

	/**Returns mouse y position in world*/
	public float getMousePosY()
	{
		return ((this.getMouseViewY() / this.getPortHeight() - 0.5F) * this.getViewHeight()) + this.getPosY();
	}

	/**Returns mouse position in world*/
	public Point getMousePos()
	{
		return new Point(this.getMousePosX(), this.getMousePosY());
	}

	@Override
	public void onPositionUpdate() {}

	@Override
	public float getOrientationPosX(CardinalDirection parOrientation)
	{
		switch(parOrientation)
		{
			case CENTER: return this.getPosX();
			case EAST: return this.getPosX() + this.getWidth() / 2;
			case NORTHEAST: return this.getPosX() + this.getWidth() / 2;
			case NORTH: return this.getPosX();
			case NORTHWEST: return this.getPosX() - this.getWidth() / 2;
			case WEST: return this.getPosX() + this.getWidth() / 2;
			case SOUTHWEST: return this.getPosX() - this.getWidth() / 2;
			case SOUTH: return this.getPosX();
			case SOUTHEAST: return this.getPosX() + this.getWidth() / 2;
			default: return this.getPosX();
		}
	}

	@Override
	public float getOrientationPosY(CardinalDirection parOrientation)
	{
		switch(parOrientation)
		{
			case CENTER: return this.getPosY();
			case EAST: return this.getPosY();
			case NORTHEAST: return this.getPosY() - this.getHeight() / 2;
			case NORTH: return this.getPosY() - this.getHeight() / 2;
			case NORTHWEST: return this.getPosY() - this.getHeight() / 2;
			case WEST: return this.getPosY();
			case SOUTHWEST: return this.getPosY() + this.getHeight() / 2;
			case SOUTH: return this.getPosY() + this.getHeight() / 2;
			case SOUTHEAST: return this.getPosY() + this.getHeight() / 2;
			default: return this.getPosY();
		}
	}

	@Override
	public void setPosX(float parPosX)
	{
		this.posX = parPosX;
	}

	@Override
	public void setPosY(float parPosY)
	{
		this.posY = parPosY;
	}

	@Override
	public float getPosDX()
	{
		return this.nextPosX - this.posX;
	}

	@Override
	public float getPosDY()
	{
		return this.nextPosY - this.posY;
	}

	@Override
	public float getWidth()
	{
		return this.getViewWidth();
	}

	@Override
	public float getHeight()
	{
		return this.getViewHeight();
	}
}
