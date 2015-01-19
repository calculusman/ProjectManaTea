package com.bStone.engine.render.window;

import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.bStone.Start;
import com.bStone.engine.render.IRenderable;

public abstract class Window
{
	protected boolean fullscreen;

	private final JFrame frame;

	protected List<IRenderable> listeners;

	public Window(JFrame parFrame)
	{
		this.frame = parFrame;
		this.listeners = new ArrayList<IRenderable>();
	}

	public Window addListener(IRenderable parListener)
	{
		this.listeners.add(parListener);
		return this;
	}

	public void open()
	{
		this.createWindow();
	}

	public void close()
	{
		this.frame.removeAll();
		this.frame.dispose();
	}

	public abstract void update();

	protected void createWindow()
	{
		Start.out("...Creating window...");
		this.getCanvas().setFocusable(true);
		this.getCanvas().setIgnoreRepaint(true);

		this.frame.getContentPane().add(this.getCanvas());
		this.frame.pack();

		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent parEvent)
			{
				Start.stop();
			}
		});

		this.frame.setLocationRelativeTo(null);
		this.frame.setIgnoreRepaint(true);
		this.frame.setResizable(true);

		this.frame.setVisible(true);
		this.frame.requestFocus();
	}

	public void setFullScreen(boolean parFullScreen)
	{
		if (parFullScreen == this.fullscreen) return;

		this.fullscreen = parFullScreen;
		GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		Start.out("Initiating " + (this.fullscreen ? "fullscreen" : "window") + " mode...");
		this.frame.dispose();
		this.frame.setUndecorated(this.fullscreen);
		this.frame.setVisible(true);
		graphicsDevice.setFullScreenWindow(this.fullscreen ? this.frame : null);
		this.getCanvas().requestFocus();
	}

	public boolean isFullScreen()
	{
		return this.fullscreen;
	}

	public Window setSize(int parWidth, int parHeight)
	{
		this.getCanvas().setSize(parWidth, parHeight);
		this.frame.setSize(this.getCanvas().getPreferredSize());
		return this;
	}

	public Window setTitle(String parTitle)
	{
		this.frame.setTitle(parTitle);
		return this;
	}

	public int getWidth()
	{
		return this.getCanvas().getWidth();
	}

	public int getHeight()
	{
		return this.getCanvas().getHeight();
	}

	public final JFrame toFrame()
	{
		return this.frame;
	}

	public final List<IRenderable> getListeners()
	{
		return this.listeners;
	}

	public abstract Component getCanvas();
}
