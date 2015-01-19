package net.pong;

import com.bStone.Start;
import com.bStone.engine.render.drawable.view.ViewPort;
import com.qSilver.living.world.World;
import com.qSilver.opengl.ViewPortGL;
import com.qSilver.render.gui.GuiScreen;

public class MenuWorld extends World
{
	public MenuWorld()
	{
		super("PongMenu");
	}

	@Override
	protected GuiScreen[] setupGuiScreen()
	{
		return new GuiScreen[] {new MenuScreen(this)};
	}

	@Override
	protected ViewPort setupViewPort()
	{
		return new ViewPortGL(Start.getApplication().getRenderEngine().getWindow().getCanvas(), 0, 0, 0F, 640, 480).setClients(this);
	}
}
