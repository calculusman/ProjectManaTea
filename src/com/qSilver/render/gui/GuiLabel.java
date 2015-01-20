package com.qSilver.render.gui;

import com.bStone.engine.render.drawable.render.Render;
import com.bStone.util.ColorUtil;
import com.qSilver.render.string.StringRenderer;

public class GuiLabel extends Gui
{
	protected String label;
	private int labelAlignment;

	public GuiLabel(float parPosX, float parPosY, float parDepth, String parLabel, int parAlignment)
	{
		super(parPosX, parPosY, parDepth, StringRenderer.getStringWidth(parLabel), StringRenderer.getStringHeight(parLabel));
		this.label = parLabel;
		this.labelAlignment = parAlignment;
	}

	public GuiLabel setLabel(String parLabel)
	{
		this.label = parLabel;
		return this;
	}

	public GuiLabel setAlignment(int parAlignment)
	{
		this.labelAlignment = parAlignment;
		return this;
	}

	@Override
	public void onUpdate()
	{
		Render.gl().glPushMatrix();
		{
			Render.gl().glTranslatef(this.posX, this.posY, 0F);
			Render.gl().glScalef(this.scale, this.scale, 1F);

			Render.gl().glColor3f(ColorUtil.getRed(this.color) / 255F, ColorUtil.getGreen(this.color) / 255F, ColorUtil.getBlue(this.color) / 255F);
			StringRenderer.renderString(this.label, 0, 0, 1F, this.labelAlignment);
		}
		Render.gl().glPopMatrix();
	}

	@Override
	public void onClick(int parButton) {}

	public String getLabel()
	{
		return this.label;
	}

	public int getAlignment()
	{
		return this.labelAlignment;
	}
}