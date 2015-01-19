package com.qSilver.living;

import com.qSilver.collision.BoundingBox;
import com.qSilver.collision.IBoundingBoxPool;
import com.qSilver.living.world.World;

public abstract class LivingEntity extends LivingEntityBase
{
	private BoundingBox boundingBox;

	public LivingEntity(float parPosX, float parPosY, float parPosRot)
	{
		super(parPosX, parPosY, parPosRot);
		this.boundingBox = this.setupBoundingBox();
	}

	protected abstract BoundingBox setupBoundingBox();

	public boolean onInit(World parWorld)
	{
		return this.onInit((ILivingManager)parWorld);
	}

	@Override
	public boolean onInit(ILivingManager parWorld)
	{
		boolean flag = super.onInit(parWorld);
		if (parWorld instanceof IBoundingBoxPool && this.boundingBox != null)
		{
			((IBoundingBoxPool) parWorld).addBoxToPool(this.boundingBox);
		}

		return flag;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();

		if (this.getWorldObj() instanceof IBoundingBoxPool && this.boundingBox != null)
		{
			((IBoundingBoxPool) this.getWorldObj()).removeBoxFromPool(this.boundingBox);
		}
	}

	@Override
	public abstract void onUpdate();

	@Override
	public abstract void onPositionUpdate();

	@Override
	public abstract void onRotationUpdate();

	@Override
	public World getWorldObj()
	{
		return (World) super.getWorldObj();
	}

	public BoundingBox getBoundingBox()
	{
		return this.boundingBox;
	}

	public float getWidth()
	{
		return this.boundingBox != null ? this.boundingBox.getWidth() : 0;
	}

	public float getHeight()
	{
		return this.boundingBox != null ? this.boundingBox.getHeight() : 0;
	}
}
