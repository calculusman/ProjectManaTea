package com.bStone.util;

import java.awt.Color;
import java.util.Random;

public class ColorUtil
{
	/**Get Color from hex*/
	public static Color getColor(int parHex)
	{
		return new Color(getRed(parHex), getGreen(parHex), getBlue(parHex));
	}

	/**Get hex from RGB*/
	public static int getColorHex(int red, int green, int blue)
	{
		return ((red & 0x0FF) << 16) | ((green & 0x0FF) << 8) | (blue & 0x0FF);
	}

	/**Get red from hex*/
	public static int getRed(int parHex)
	{
		return (parHex >> 16) & 0xFF;
	}

	/**Get green from hex*/
	public static int getGreen(int parHex)
	{
		return (parHex >> 8) & 0xFF;
	}

	/**Get blue from hex*/
	public static int getBlue(int parHex)
	{
		return parHex & 0xFF;
	}

	/**Get alpha from hex*/
	public static int getAlpha(int parHex)
	{
		return (parHex >> 24) & 0xFF;
	}

	/**Get HSB from RGB*/
	public static int HSBtoRGB(float parHue, float parSaturation, float parBrightness)
	{
		return Color.HSBtoRGB(parHue, parSaturation, parBrightness);
	}

	/**Get random color*/
	public static Color getRandomColor(Random parRandom, float parSaturation, float parBrightness)
	{
		float hue = parRandom.nextFloat();
		return getColor(ColorUtil.HSBtoRGB(hue, parSaturation, parBrightness));
	}

	/**Get random color using Golden Ratio*/
	public static Color getRandomColorWithGoldenRatio(Random parRandom, float parSaturation, float parBrightness)
	{
		float goldenRatioConj = 0.618033988749895F;
		float hue = parRandom.nextFloat();
		hue += goldenRatioConj;
		hue %= 1;
		return getColor(ColorUtil.HSBtoRGB(hue, parSaturation, parBrightness));
	}
}
