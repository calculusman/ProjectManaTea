package com.bStone.util;

public interface IPositionable
{
	public void onPositionUpdate();

	public void setPosX(float parPosX);
	public void setPosY(float parPosY);

	public float getPosX();
	public float getPosY();

	public float getPosDX();
	public float getPosDY();
}
