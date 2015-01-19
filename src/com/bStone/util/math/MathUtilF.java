package com.bStone.util.math;

public class MathUtilF
{
	public static float getDistance(float parPosX1, float parPosY1, float parPosX2, float parPosY2)
	{
		return (float) Math.sqrt(MathUtilF.getDistanceSqu(parPosX1, parPosY1, parPosX2, parPosY2));
	}

	public static float getDistanceSqu(float parPosX1, float parPosY1, float parPosX2, float parPosY2)
	{
		return ((parPosX1 - parPosX2) * (parPosX1 - parPosX2)) + ((parPosY1 - parPosY2) * (parPosY1 - parPosY2));
	}

	public static float getManhattanDistance(float parPosX1, float parPosY1, float parPosX2, float parPosY2)
	{
		return (parPosX2 - parPosX1) + (parPosY2 - parPosY1);
	}

	public static float getVectorX(float parDistance, float parDirection)
	{
		return (float) (Math.cos(Math.toRadians(parDirection)) * parDistance);
	}

	public static float getVectorY(float parDistance, float parDirection)
	{
		return (float) (-Math.sin(Math.toRadians(parDirection)) * parDistance);
	}

	public static float getRotationToPosition(float parPosX1, float parPosY1, float parPosX2, float parPosY2)
	{
		return (float) Math.toDegrees(Math.atan2(parPosY1 - parPosY2, parPosX2 - parPosX1));
	}

	public static float clamp(float parFloat, float parMin, float parMax)
	{
		return parFloat < parMin ? parFloat : parFloat > parMax ? parMax : parFloat;
	}

	public static float clampWithMod(float parFloat, float parMax)
	{
		return (parFloat + parMax) % parMax;
	}

	public static float calcSmoothRotation(float parRotation, float parTargetRotation, int parIterations, float parRange)
	{
		float i = 0;

		for(int j = 0; j < parIterations; j++)
		{
			i = parTargetRotation - parRotation;
			i += i < -180F ? 360F : i > 180F ? -360F : 0F;

			parRotation += i < -parRange ? -1F : i > parRange ? 1F : 0F;
			parRotation = clampWithMod(parRotation, 360F);
		}

		return parRotation;
	}
}
