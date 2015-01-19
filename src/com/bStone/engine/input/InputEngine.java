package com.bStone.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.bStone.Start;
import com.bStone.application.Application;
import com.bStone.engine.Engine;
import com.bStone.engine.render.window.Window;

public class InputEngine extends Engine implements KeyListener, MouseListener, MouseWheelListener
{
	public static final int MOUSE_LEFT = 0;
	public static final int MOUSE_MIDDLE = 1;
	public static final int MOUSE_RIGHT = 2;

	private final Window window;

	private String keyCache = "";

	private boolean[] keyPressed = new boolean[256];
	private boolean[] keyReleased = new boolean[256];
	private int[] keyHolding = new int[256];
	private boolean keyPressing = false;
	private boolean keyReleasing = false;

	private boolean[] mousePressed = new boolean[3];
	private boolean[] mouseReleased = new boolean[3];
	private int[] mouseHolding = new int[3];
	private boolean mousePressing = false;
	private boolean mouseReleasing = false;

	private int wheelAmount;
	private boolean wheelIncreasing;
	private boolean wheelDecreasing;

	protected IKeyable[] clients;

	public InputEngine(Application parApplication, Window parWindow, Engine... parDependencies)
	{
		super(parApplication, parDependencies);
		this.window = parWindow;
		this.window.getCanvas().addKeyListener(this);
		this.window.getCanvas().addMouseListener(this);
		this.window.getCanvas().addMouseWheelListener(this);
	}

	public InputEngine setClients(IKeyable... parClients)
	{
		this.clients = parClients;
		return this;
	}

	@Override
	protected Thread setupThread()
	{
		return null;
	}

	@Override
	protected void initiate() {}

	@Override
	protected void terminate()
	{
		this.window.getCanvas().removeKeyListener(this);
		this.window.getCanvas().removeMouseListener(this);
		this.window.getCanvas().removeMouseWheelListener(this);
	}

	@Override
	public void update()
	{
		for(IKeyable keyable : this.clients)
		{
			keyable.onInputUpdate();
		}

		this.keyReleased = new boolean[256];
		this.keyReleasing = false;
		if(this.keyCache.length() > 1024)
		{
			this.clearKeyCache();
		}

		this.mouseReleased = new boolean[3];
		this.mouseReleasing = false;

		this.wheelDecreasing = false;
		this.wheelIncreasing = false;
	}

	public int getCurrentTime()
	{
		return (int) System.currentTimeMillis();
	}

	//**************** KEYBOARD HANDLING ****************//

	@Override
	public void keyPressed(KeyEvent parEvent)
	{
		int key = parEvent.getKeyCode();
		if(key >= 0 && key < 256)
		{
			this.setKeyPressed(key);
		}
	}

	@Override
	public void keyReleased(KeyEvent parEvent)
	{
		int key = parEvent.getKeyCode();
		if(key >= 0 && key < 256)
		{
			this.setKeyReleased(key);
		}
	}

	@Override
	public void keyTyped(KeyEvent parEvent)
	{
		this.keyCache += parEvent.getKeyChar();
	}

	public void setKeyPressed(int parKeyCode)
	{
		if (!this.isKeyPressed(parKeyCode))
		{
			Start.out("PRESSED " + KeyEvent.getKeyText(parKeyCode));
		}

		this.keyHolding[parKeyCode] = this.getCurrentTime();
		this.keyReleased[parKeyCode] = false;
		this.keyPressed[parKeyCode] = true;
		this.keyPressing = true;
		this.keyReleasing = false;
	}

	public void setKeyReleased(int parKeyCode)
	{
		if (!this.isKeyReleased(parKeyCode))
		{
			Start.out("RELEASED " + KeyEvent.getKeyText(parKeyCode));
		}

		this.keyHolding[parKeyCode] = 0;
		this.keyPressed[parKeyCode] = false;
		this.keyReleased[parKeyCode] = true;
		this.keyPressing = false;
		this.keyReleasing = true;
	}

	public void consumeKey(int parKeyCode)
	{
		this.keyHolding[parKeyCode] = 0;
		this.keyPressed[parKeyCode] = false;
		this.keyReleased[parKeyCode] = false;
		this.keyPressing = false;
		this.keyReleasing = false;
	}

	public boolean isKeyPressed(int parKeyCode)
	{
		return this.keyPressed[parKeyCode];
	}

	public boolean isKeyReleased(int parKeyCode)
	{
		return this.keyReleased[parKeyCode];
	}

	public int getKeyHoldingTime(int parKeyCode)
	{
		return this.keyHolding[parKeyCode];
	}

	public boolean isAnyKeyPressed()
	{
		return this.keyPressing;
	}

	public boolean isAnyKeyReleased()
	{
		return this.keyReleasing;
	}

	public void clearKeyCache()
	{
		this.keyCache = "";
	}

	//**************** MOUSE HANDLING ****************//

	@Override
	public void mousePressed(MouseEvent parEvent)
	{
		int mouse = parEvent.getButton() - 1;
		if(mouse >= 0 && mouse < 3)
		{
			this.setMousePressed(mouse);
		}
	}

	@Override
	public void mouseReleased(MouseEvent parEvent)
	{
		int mouse = parEvent.getButton() - 1;
		if(mouse >= 0 && mouse < 3)
		{
			this.setMouseReleased(mouse);
		}
	}

	@Override
	public void mouseClicked(MouseEvent parEvent) {}

	@Override
	public void mouseEntered(MouseEvent parEvent) {}

	@Override
	public void mouseExited(MouseEvent parEvent) {}

	public void setMousePressed(int parButton)
	{
		if (!this.isMousePressed(parButton))
		{
			Start.out("PRESSED " + (parButton == InputEngine.MOUSE_LEFT ? "Left Mouse" :
				parButton == InputEngine.MOUSE_RIGHT ? "Right Mouse" :
					parButton == InputEngine.MOUSE_MIDDLE ? "Middle Mouse" :
					"Unkown Mouse"));
		}

		this.mouseHolding[parButton] = this.getCurrentTime();
		this.mouseReleased[parButton] = false;
		this.mousePressed[parButton] = true;
		this.mousePressing = true;
		this.mouseReleasing = false;
	}

	public void setMouseReleased(int parButton)
	{
		if (!this.isMouseReleased(parButton))
		{
			Start.out("RELEASED " + (parButton == InputEngine.MOUSE_LEFT ? "Left Mouse" :
				parButton == InputEngine.MOUSE_RIGHT ? "Right Mouse" :
					parButton == InputEngine.MOUSE_MIDDLE ? "Middle Mouse" :
					"Unkown Mouse"));
		}

		this.mouseHolding[parButton] = 0;
		this.mousePressed[parButton] = false;
		this.mouseReleased[parButton] = true;
		this.mousePressing = false;
		this.mouseReleasing = true;
	}

	public void consumeMouse(int parButton)
	{
		this.mouseHolding[parButton] = 0;
		this.mousePressed[parButton] = false;
		this.mouseReleased[parButton] = false;
		this.mousePressing = false;
		this.mouseReleasing = false;
	}

	public boolean isMousePressed(int parButton)
	{
		return this.mousePressed[parButton];
	}

	public boolean isMouseReleased(int parButton)
	{
		return this.mouseReleased[parButton];
	}

	public int getMouseHoldingTime(int parButton)
	{
		return this.mouseHolding[parButton];
	}

	public boolean isAnyMousePressed()
	{
		return this.mousePressing;
	}

	public boolean isAnyMouseReleased()
	{
		return this.mouseReleasing;
	}

	//**************** MOUSE WHEEL HANDLING ****************//

	@Override
	public void mouseWheelMoved(MouseWheelEvent parEvent)
	{
		int wheel = parEvent.getWheelRotation();
		this.addScrollAmount(wheel);

		if (wheel > 0)
		{
			this.setScrollMoving(false);
		}

		if (wheel < 0)
		{
			this.setScrollMoving(true);
		}
	}

	public void setScrollMoving(boolean parIncreasing)
	{
		this.wheelIncreasing = parIncreasing;
		this.wheelDecreasing = !parIncreasing;
	}

	public void addScrollAmount(int parAmount)
	{
		if (parAmount != 0)
		{
			boolean flag = parAmount < 0;
			Start.out("SCROLLED " + (flag ? "Up" : "Down"));

		}
		this.wheelAmount += parAmount;
	}

	public boolean isScrollIncreasing()
	{
		return this.wheelIncreasing;
	}

	public boolean isScrollDecreasing()
	{
		return this.wheelDecreasing;
	}

	public int getScrollAmount()
	{
		return this.wheelAmount;
	}

	public void consumeWheel()
	{
		this.wheelAmount = 0;
		this.wheelIncreasing = false;
		this.wheelDecreasing = false;
	}
}
