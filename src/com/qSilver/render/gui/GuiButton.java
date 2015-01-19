package com.qSilver.render.gui;

import com.bStone.engine.render.drawable.render.Render;
import com.bStone.util.CardinalDirection;
import com.bStone.util.ColorUtil;
import com.qSilver.render.string.StringRenderer;

public class GuiButton extends Gui
{
	private String label;

	public GuiButton(float parPosX, float parPosY, float parDepth, float parWidth, float parHeight)
	{
		super(parPosX, parPosY, parDepth, parWidth, parHeight);
	}

	public GuiButton setLabel(String parLabel)
	{
		this.label = parLabel;
		return this;
	}

	@Override
	public void onUpdate()
	{
		Render.gl().glPushMatrix();
		{
			float r = ColorUtil.getRed(this.color) / 255F;
			float g = ColorUtil.getGreen(this.color) / 255F;
			float b = ColorUtil.getBlue(this.color) / 255F;

			Render.gl().glColor3f(r * 0.4F, g * 0.4F, b * 0.4F);
			Render.drawBoxFill(this.toBox().expand(8, 8));

			float i = this.toBox().intersects(this.getScreenObj().getViewPort().getMousePos()) ? 0.8F : 1F;
			Render.gl().glColor3f(r * i, g * i, b * i);
			Render.drawBoxFill(this.toBox());

			if (this.label != null)
			{
				Render.gl().glColor3f(1F, 1F, 1F);
				StringRenderer.renderString(this.label, this.getOrientationPosX(CardinalDirection.CENTER), this.getOrientationPosY(CardinalDirection.CENTER) - StringRenderer.getStringHeight(this.label) / 2, 1F, StringRenderer.CENTERED);
			}
		}
		Render.gl().glPopMatrix();
	}

	public String getLabel()
	{
		return this.label;
	}
}
