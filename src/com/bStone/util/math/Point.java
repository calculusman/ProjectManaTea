package com.bStone.util.math;

public class Point
{
	private float posX;
	private float posY;

	public Point(float parPosX, float parPosY)
	{
		this.posX = parPosX;
		this.posY = parPosY;
	}

	public float getPosX()
	{
		return this.posX;
	}

	public float getPosY()
	{
		return this.posY;
	}

	public boolean intersects(Point parPoint)
	{
		return this.equals(parPoint);
	}

	@Override
	public boolean equals(Object parObject)
	{
		if (parObject instanceof Point)
		{
			Point point = (Point) parObject;
			float f = Math.abs(point.getPosX()) - Math.abs(this.getPosX());
			float f1 = Math.abs(point.getPosY()) - Math.abs(this.getPosY());
			if (Math.abs(f) < 0.0001F && Math.abs(f1) < 0.0001F)
			{
				return true;
			}
		}

		return false;
	}
}
