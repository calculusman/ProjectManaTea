package com.qSilver.opengl;

import com.bStone.application.Application;
import com.bStone.engine.render.RenderEngine;
import com.bStone.engine.render.window.Window;

public class RenderEngineGL extends RenderEngine
{
	public RenderEngineGL(Application parApplication)
	{
		super(parApplication);
	}

	@Override
	protected Window setupWindow()
	{
		Window window = new WindowGL(this);
		window.setSize(640, 480);
		return window;
	}
}
