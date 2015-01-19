package net.pong;

import java.util.List;

import com.bStone.util.math.Box;
import com.bStone.util.math.MathUtilF;
import com.qSilver.collision.BoundingBox;
import com.qSilver.living.LivingEntity;

public class Ping extends LivingEntity
{
	private float velocityX;
	private float velocityY;

	public Ping(float parPosX, float parPosY, float parPosRot)
	{
		super(parPosX, parPosY, parPosRot);
		this.velocityX = 4;
		this.velocityY = 4;
	}

	@Override
	protected BoundingBox setupBoundingBox()
	{
		return new BoundingBox(this, 8, 8);
	}

	@Override
	public void onUpdate()
	{
		this.setPosX(this.posX + MathUtilF.getVectorX(this.velocityX, this.posRot));
		this.setPosY(this.posY + MathUtilF.getVectorY(this.velocityY, this.posRot));
	}

	@Override
	public void onPositionUpdate()
	{
		Box viewBox = this.getWorldObj().getViewPort().getViewBox();
		if (this.posX < viewBox.getPosX())
		{
			this.velocityX *= -1;
			this.posX -= this.getPosDX();
			this.getWorldObj().scoreB++;
			return;
		}
		else if (this.posX > viewBox.getPosX() + viewBox.getWidth())
		{
			this.velocityX *= -1;
			this.posX -= this.getPosDX();
			this.getWorldObj().scoreA++;
			return;
		}
		else if (this.posY < viewBox.getPosY() || this.posY > viewBox.getPosY() + viewBox.getHeight())
		{
			this.velocityY *= -1;
			this.posY -= this.getPosDY();
			return;
		}

		List<BoundingBox> boxes = this.getBoundingBox().getCollidedBoxes(this.getWorldObj());
		for(BoundingBox box : boxes)
		{
			this.velocityX *= -1;
			this.posX -= this.getPosDX();
			box.toString();
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
