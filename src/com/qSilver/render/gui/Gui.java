package com.qSilver.render.gui;

import com.bStone.engine.render.drawable.Drawable;
import com.bStone.engine.render.drawable.render.Render;
import com.bStone.util.ColorUtil;
import com.bStone.util.math.Box;

public class Gui extends Drawable
{
	protected float posX;
	protected float posY;
	private float prevPosX;
	private float prevPosY;

	protected int color;

	private Box box;

	public Gui(float parPosX, float parPosY, float parDepth, float parWidth, float parHeight)
	{
		super(parDepth);
		this.prevPosX = this.posX = parPosX;
		this.prevPosY = this.posY = parPosY;

		this.color = this.hashCode();

		this.box = new Box(parPosX, parPosY, parWidth, parHeight);
	}

	public Gui setColor(int parColor)
	{
		this.color = parColor;
		return this;
	}

	@Override
	public void onUpdate()
	{
		float i = this.toBox().intersects(this.getScreenObj().getViewPort().getMousePos()) ? 400F : 255F;
		Render.gl().glColor3f(ColorUtil.getRed(this.color) / i, ColorUtil.getGreen(this.color) / i, ColorUtil.getBlue(this.color) / i);

		Render.drawBoxFill(this.box);
	}

	public void onClick(int parButton) {}

	@Override
	public void onPositionUpdate()
	{
		this.box.setPosition(this.posX, this.posY);
	}

	@Override
	public void setPosX(float parPosX)
	{
		this.prevPosX = this.posX;
		this.posX = parPosX;
		this.onPositionUpdate();
	}

	@Override
	public void setPosY(float parPosY)
	{
		this.prevPosY = this.posY;
		this.posY = parPosY;
		this.onPositionUpdate();
	}

	@Override
	public float getPosX()
	{
		return this.posX;
	}

	@Override
	public float getPosY()
	{
		return this.posY;
	}

	@Override
	public float getPosDX()
	{
		return this.posX - this.prevPosX;
	}

	@Override
	public float getPosDY()
	{
		return this.posY - this.prevPosY;
	}

	@Override
	public float getWidth()
	{
		return this.box.getWidth();
	}

	@Override
	public float getHeight()
	{
		return this.box.getHeight();
	}

	public int getColor()
	{
		return this.color;
	}

	@Override
	public GuiScreen getScreenObj()
	{
		return (GuiScreen) super.getScreenObj();
	}

	public Box toBox()
	{
		return this.box;
	}
}
