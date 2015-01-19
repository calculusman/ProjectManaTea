package com.bStone.util.math;

public class Vector
{
	private float magX;
	private float magY;
	private float magnitude;

	private float direction;

	public Vector(float parMagnitude, float parDirection)
	{
		this.magX = MathUtilF.getVectorX(parMagnitude, parDirection);
		this.magY = MathUtilF.getVectorY(parMagnitude, parDirection);

		this.magnitude = parMagnitude;
		this.direction = parDirection;
	}

	public Vector(float parMagnitudeX, float parMagnitudeY, float parDirection)
	{
		this.magX = parMagnitudeX;
		this.magY = parMagnitudeY;
		this.magnitude = MathUtilF.getDistance(0, 0, parMagnitudeX, parMagnitudeY);

		this.direction = parDirection;
	}

	public float getMagnitudeX()
	{
		return this.magX;
	}

	public float getMagnitudeY()
	{
		return this.magY;
	}

	public float getMagnitude()
	{
		return this.magnitude;
	}

	public float getDirection()
	{
		return this.direction;
	}
}
