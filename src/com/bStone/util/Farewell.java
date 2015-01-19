package com.bStone.util;

import java.util.Random;

public class Farewell
{
	private static final Random RAND = new Random();

	public static String getGoodbye()
	{
		int i = Farewell.RAND.nextInt(Farewell.getMaxGoodbyes());
		switch(i)
		{
			case 0:
				return "Good Bye!";
			case 1:
				return "Happy Birthday!";
			case 2:
				return "I Love You!";
			case 3:
				return "Have an Awesome Day!";
			case 4:
				return "Au Revoir!";
			case 5:
				return "Adios!";
			case 6:
				return "See You in a Bit!";
			case 7:
				return "Hasta La Vista, Baby!";
			case 8:
				return "Have a Hug <3";
			case 9:
				return "Math.sqrt(-1) love you!";
			case 10:
				return "Click here to Start Fun.";
			case 11:
				return ":D";
			case 12:
				return ":3";
			case 13:
				return "Chiao!";
			case 14:
				return "C'ya la'ta ala'gata!";
			case 15:
				return "To Infinity . . . And Beyond!";
			case 16:
				return "Have some Cake. [Smile Here]";
			default:
				return "Good Bye!";
		}
	}

	public static int getMaxGoodbyes()
	{
		return 1 + 16;
	}
}
