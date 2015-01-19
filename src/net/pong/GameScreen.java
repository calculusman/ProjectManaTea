package net.pong;

import com.bStone.Start;
import com.bStone.engine.render.drawable.view.ViewPort;
import com.bStone.util.CardinalDirection;
import com.qSilver.opengl.ViewPortGL;
import com.qSilver.render.gui.GuiScreen;

public class GameScreen extends GuiScreen
{
	public GameScreen(GameWorld parParent)
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
		this.instantiate(new GuiReset(this.getViewPort().getOrientationPosX(CardinalDirection.NORTH) - 48, this.getViewPort().getOrientationPosY(CardinalDirection.NORTH) + 5, 1, 96, 32));
	}

	@Override
	public GameWorld getParent()
	{
		return (GameWorld) super.getParent();
	}
}
