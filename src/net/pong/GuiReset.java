package net.pong;

import com.qSilver.render.gui.GuiButton;

public class GuiReset extends GuiButton
{
	public GuiReset(float parPosX, float parPosY, float parDepth, float parWidth, float parHeight)
	{
		super(parPosX, parPosY, parDepth, parWidth, parHeight);
		this.setLabel("Reset");
	}

	@Override
	public void onClick(int parButton)
	{
		this.getScreenObj().getParent().resetScore();
	}

	@Override
	public GameScreen getScreenObj()
	{
		return (GameScreen) super.getScreenObj();
	}
}
