package net.pong;

import com.bStone.Start;
import com.bStone.engine.render.drawable.view.ViewPort;
import com.bStone.util.CardinalDirection;
import com.bStone.util.math.Point;
import com.qSilver.living.world.World;
import com.qSilver.opengl.ViewPortGL;
import com.qSilver.render.gui.GuiButton;
import com.qSilver.render.gui.GuiLabel;
import com.qSilver.render.gui.GuiScreen;
import com.qSilver.render.string.StringRenderer;

public class MenuScreen extends GuiScreen
{
	public MenuScreen(World parParent)
	{
		super(parParent);
	}

	@Override
	protected ViewPort setupViewPort()
	{
		return new ViewPortGL(Start.getApplication().getRenderEngine().getWindow().getCanvas(), 0, 0, 0F, 640, 480).setClients(this);
	}

	@Override
	public void initiate()
	{
		super.initiate();

		Point origin = this.getViewPort().getOrientationPoint(CardinalDirection.CENTER);

		GuiLabel title = new GuiLabel(origin.getPosX(), origin.getPosY() - 96, 0, "PONG", StringRenderer.CENTERED);
		title.setScale(4F).setColor(0xFFFFFF);

		GuiButton play = new GuiButton(origin.getPosX() - 64, origin.getPosY(), 0, 128, 32)
		{
			@Override
			public void onClick(int parButton)
			{
				Pong.INSTANCE.worldManager.setNextWorld(new GameWorld());
			}
		};
		play.setLabel("Play");

		GuiButton quit = new GuiButton(origin.getPosX() - 64, origin.getPosY() + 64, 0, 128, 32)
		{
			@Override
			public void onClick(int parButton)
			{
				Start.stop();
			}
		};
		quit.setLabel("Quit");

		this.instantiate(title);
		this.instantiate(play);
		this.instantiate(quit);
	}
}
