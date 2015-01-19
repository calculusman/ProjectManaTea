package com.qSilver.render;

import com.bStone.engine.render.drawable.render.Render;
import com.bStone.util.ColorUtil;
import com.qSilver.living.LivingEntity;

public class RenderLivingEntity extends RenderLivingEntityBase
{
	@Override
	public void onRender(LivingEntity parLivingEntity)
	{
		gl().glPushMatrix();
		{
			gl().glTranslatef(parLivingEntity.getPosX(), parLivingEntity.getPosY(), 0F);
			gl().glRotatef(parLivingEntity.getPosRot(), 0F, 0F, 1F);

			gl().glTranslatef(-parLivingEntity.getWidth() / 2F, -parLivingEntity.getHeight() / 2F, 0F);
			int color = parLivingEntity.hashCode();
			gl().glColor3f(ColorUtil.getRed(color) / 255F, ColorUtil.getGreen(color) / 255F, ColorUtil.getBlue(color) / 255F);

			Render.drawBoxFill(parLivingEntity.getWidth(), parLivingEntity.getHeight());
		}
		gl().glPopMatrix();
	}
}
