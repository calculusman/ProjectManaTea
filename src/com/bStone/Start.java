package com.bStone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.bStone.application.Application;
import com.bStone.application.ApplicationState;
import com.bStone.util.Farewell;
import com.bStone.util.collection.SmallMap;

/**Starts, runs, and stops {@link Application}.
 * <br>
 * <br>To Begin:
 * <br> - Add an application by <code>Start.add(Application)</code>
 * <br> - Call <code>Start.start()</code> to initialize.
 * <br>
 * <br>Happy Coding! :D*/
public class Start
{
	public static boolean DEBUG_MODE = false;

	/**Map of all running applications to their unique id*/
	private static final SmallMap<String, Application> application = new SmallMap<String, Application>();

	/**Add application to start queue*/
	public static void add(Application parApplication)
	{
		Start.application.put(parApplication.toString(), parApplication);
	}

	/**Start the game engine and applications*/
	public static void start()
	{
		//Initiating Application
		Start.printTitle("Author", "Andrew Kuo");

		if (Start.application.size() == 0)
		{
			Start.exception("No Applications to start! Call Start.add(Application)!");
			return;
		}

		for(String id : Start.application.keySet())
		{
			Start.application.get(id).start();
		}

		//Running Application
		while(!Start.application.isEmpty())
		{
			ArrayList<String> dumplist = new ArrayList<String>();
			for(String id : Start.application.keySet())
			{
				Application app = Start.application.get(id);
				if (app.getState() == ApplicationState.DEAD)
				{
					Start.out("Removing " + id + " application...");
					dumplist.add(id);
					continue;
				}

				Start.application.get(id).update();
			}

			for(String id : dumplist)
			{
				Start.out("...Removed " + id + " application");
				Start.application.remove(id);
			}
		}

		//Terminating Application
		Start.out(Farewell.getGoodbye());
		Start.exit();
	}

	/**Stop the applications and game engine*/
	public static void stop()
	{
		System.out.println();
		Start.printTitle("Credit", "Familia y Amigos");
		
		for(String id : Start.application.keySet())
		{
			Start.application.get(id).stop();
		}
	}

	/**Get running application by id*/
	public static Application getApplication(String parID)
	{
		return Start.application.get(parID);
	}

	/**Get running application by current thread*/
	public static Application getApplication()
	{
		Thread thread = Thread.currentThread();
		for(String id : Start.application.keySet())
		{
			Application app = Start.application.get(id);
			if (app.hasThread(thread))
			{
				return app;
			}
		}

		return null;
	}

	/*************************** DEBUG *******************************/

	private static boolean closing = false;

	private static String repOut;
	private static int reps;

	/**Throws exception with passed-in error*/
	public static void exception(String parException)
	{
		try
		{
			throw new Exception(parException);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**Prints stack trace to console.*/
	public static void stackTrace()
	{
		System.out.println();
		System.out.print("STACK_TRACE:");

		int i = 0;
		for(StackTraceElement e : Thread.currentThread().getStackTrace())
		{
			if (i++ < 3) continue;

			System.out.println();
			System.out.print(Start.indent(1, e.toString()));
		}
	}

	/**Prints string directly to console*/
	protected static void print(String parString)
	{
		parString = "[" + Start.time() + "]" + "[" + Start.currentThread().getName() + "] " + parString;

		System.out.println();
		System.out.print(parString);
	}

	protected static void printWith(String parString)
	{
		System.out.print(parString);
	}

	public static void out(Object parObject)
	{
		Start.out(parObject.toString(), parObject.getClass().getSimpleName());
	}

	public static void out(String parString, String... parTags)
	{
		String string = "";
		for(String tag : parTags)
		{
			string += "[" + tag + "]";
		}

		string += " " + parString;
		Start.out(string);
	}

	/**Prints string to console.
	 * <br>
	 * <br>Replaces <code>System.out.println("")</code>*/
	public static void out(String parString)
	{
		if (parString.equals("Initiating..."))
		{
			Start.print("");
			Start.print(parString);
			Start.print("");
			return;
		}
		else if (parString.equals("Closing..."))
		{
			closing = true;
			Start.print("");
			Start.print(parString);
			Start.print("");
			return;
		}

		int i = 0;
		if (parString.startsWith("-"))
		{
			for(i = 0; parString.charAt(i) == '-'; i++) {}
			parString = parString.substring(i);
		}

		if (closing)
		{
			i++;
		}

		if (parString.startsWith("PRESSED"))
		{
			//return;
		}

		if (parString.startsWith("RELEASED"))
		{
			return;
		}

		if (parString.startsWith("SCROLLED"))
		{
			//return;
		}

		if (parString.equals(Start.repOut))
		{
			Start.reps++;
			if (Start.reps == 1)
			{
				Start.printWith(" ");
			}
			if (Start.reps % 10 == 0)
			{
				Start.printWith("*");
			}
			else
			{
				Start.printWith("|");
			}

			return;
		}
		else
		{
			Start.repOut = parString;
			Start.reps = 0;
		}

		if (parString.startsWith("ERROR"))
		{
			if (parString.endsWith(">"))
			{
				System.err.print(indent(i, parString));
			}
			else
			{
				System.err.println(indent(i, parString));
			}
		}
		else
		{
			if (parString.endsWith(">"))
			{
				System.out.print(indent(i, parString));
			}
			else
			{
				Start.print(indent(i, parString));
			}
		}
	}

	private static String indent(int parIndents, String parString)
	{
		for(; parIndents > 0; parIndents--)
		{
			parString = "   " + parString;
		}

		return parString;
	}

	public static void printTitle(String parHead, String parBody)
	{
		int i = 24 - parBody.length() - 1;
		if (i < 24)
		{
			for(int j = i / 2; j >= 0; j--)
			{
				parBody = " " + parBody;
			}
		}

		int j = 24 - parHead.length() - 1;
		if (j < 24)
		{
			parHead = "*" + parHead + "*";
			for(int k = j / 2; k > 0; k--)
			{
				parHead = "-" + parHead + "-";
			}
		}
		
		System.out.println("\n////////////////////////\n");
		System.out.println(parHead);
		System.out.println(parBody);
		System.out.println("------------------------");
		System.out.println("\n////////////////////////");
	}

	public static String time()
	{
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		return format.format(Calendar.getInstance().getTime());
	}

	public static Thread currentThread()
	{
		return Thread.currentThread();
	}

	public static void sleep(long parMilliseconds)
	{
		try
		{
			Thread.sleep(parMilliseconds);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void exit()
	{
		System.exit(0);
	}
}
