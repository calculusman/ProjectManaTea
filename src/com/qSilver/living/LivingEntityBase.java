package com.qSilver.living;

import com.bStone.util.IPositionable;

public abstract class LivingEntityBase extends Living implements IPositionable
{
	protected float posX;
	protected float posY;
	protected float posRot;
	private float prevPosX;
	private float prevPosY;
	private float prevPosRot;

	public LivingEntityBase(float parPosX, float parPosY, float parPosRot)
	{
		this.prevPosX = this.posX = parPosX;
		this.prevPosY = this.posY = parPosY;
		this.prevPosRot = this.posRot = parPosRot;
	}

	@Override
	public abstract void onUpdate();

	@Override
	public abstract void onPositionUpdate();

	public abstract void onRotationUpdate();

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

	public void setPosRot(float parPosRot)
	{
		this.prevPosRot = this.posRot;
		this.posRot = parPosRot;
		this.onRotationUpdate();
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

	public float getPosRot()
	{
		return this.posRot;
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

	public float getPosDRot()
	{
		return this.posRot - this.prevPosRot;
	}
}
