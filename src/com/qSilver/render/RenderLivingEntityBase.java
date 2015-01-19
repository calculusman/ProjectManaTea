package com.qSilver.render;

import com.bStone.engine.render.drawable.render.Render;
import com.qSilver.living.LivingEntity;

public abstract class RenderLivingEntityBase extends Render
{
	@Override
	public void onRender(Object parObject)
	{
		if (parObject instanceof LivingEntity)
		{
			this.onRender((LivingEntity) parObject);
		}
	}

	public abstract void onRender(LivingEntity parLivingEntity);
}
