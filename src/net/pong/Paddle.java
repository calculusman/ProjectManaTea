package net.pong;

import com.qSilver.collision.BoundingBox;
import com.qSilver.living.LivingEntity;

public class Paddle extends LivingEntity
{
	public Paddle(float parPosX, float parPosY)
	{
		super(parPosX, parPosY, 0F);
	}

	@Override
	protected BoundingBox setupBoundingBox()
	{
		return new BoundingBox(this, 16, 96);
	}

	@Override
	public void onUpdate() {}

	@Override
	public void onPositionUpdate()
	{
		if (!this.getBoundingBox().withinBounds(this.getWorldObj().getViewPort().getViewBox()))
		{
			this.posX -= this.getPosDX();
			this.posY -= this.getPosDY();
		}
	}

	@Override
	public void onRotationUpdate() {}
	
	@Override
	public GameWorld getWorldObj()
	{
		return (GameWorld) super.getWorldObj();
	}
}
