package com.bStone.util;

import com.bStone.util.math.MathUtilF;

public class CardinalDirectionUtil
{
	public static boolean isNorthSouth(CardinalDirection parDirection)
	{
		return parDirection == CardinalDirection.NORTH || parDirection == CardinalDirection.SOUTH;
	}

	public static boolean isWestEast(CardinalDirection parDirection)
	{
		return parDirection == CardinalDirection.WEST || parDirection == CardinalDirection.EAST;
	}

	public static boolean isDiagonal(CardinalDirection parDirection)
	{
		return parDirection == CardinalDirection.NORTHEAST || parDirection == CardinalDirection.NORTHWEST || parDirection == CardinalDirection.SOUTHEAST || parDirection == CardinalDirection.SOUTHWEST;
	}

	public static boolean isOpposite(CardinalDirection parDirection, CardinalDirection parDirection2)
	{
		return parDirection2 == getOpposite(parDirection);
	}

	public static CardinalDirection getOpposite(CardinalDirection parDirection)
	{
		switch(parDirection)
		{
			case EAST: return CardinalDirection.WEST;
			case NORTHEAST: return CardinalDirection.SOUTHWEST;
			case NORTH: return CardinalDirection.SOUTH;
			case NORTHWEST: return CardinalDirection.SOUTHEAST;
			case WEST: return CardinalDirection.EAST;
			case SOUTHWEST: return CardinalDirection.NORTHEAST;
			case SOUTH: return CardinalDirection.NORTH;
			case SOUTHEAST: return CardinalDirection.NORTHWEST;
			default: return CardinalDirection.CENTER;
		}
	}

	public static CardinalDirection getSimpleCardinalDirection(float parDirection)
	{
		parDirection = MathUtilF.clampWithMod(parDirection + 45, 360);
		switch ((int) parDirection / 90)
		{
			case 0: return CardinalDirection.EAST;
			case 1: return CardinalDirection.NORTH;
			case 2: return CardinalDirection.WEST;
			case 3: return CardinalDirection.SOUTH;
			default: return CardinalDirection.CENTER;
		}
	}

	public static CardinalDirection getCardinalDirection(float parDirection)
	{
		parDirection = MathUtilF.clampWithMod(parDirection + 22.5F, 360);
		switch ((int) parDirection / 45)
		{
			case 0: return CardinalDirection.EAST;
			case 1: return CardinalDirection.NORTHEAST;
			case 2: return CardinalDirection.NORTH;
			case 3: return CardinalDirection.NORTHWEST;
			case 4: return CardinalDirection.WEST;
			case 5: return CardinalDirection.SOUTHWEST;
			case 6: return CardinalDirection.SOUTH;
			case 7: return CardinalDirection.SOUTHEAST;
			default: return CardinalDirection.CENTER;
		}
	}
}
