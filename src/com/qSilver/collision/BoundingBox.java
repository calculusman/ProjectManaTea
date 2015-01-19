package com.qSilver.collision;

import java.util.ArrayList;
import java.util.List;

import com.bStone.util.IPositionable;
import com.bStone.util.math.Box;

public class BoundingBox extends Box
{
	protected final IPositionable parent;

	public BoundingBox(IPositionable parParent, float parOffsetX, float parOffsetY, float parWidth, float parHeight)
	{
		super(parOffsetX, parOffsetY, parWidth, parHeight);
		this.parent = parParent;
	}

	public BoundingBox(IPositionable parParent, float parWidth, float parHeight)
	{
		this(parParent, -parWidth / 2, -parHeight / 2, parWidth, parHeight);
	}

	public List<BoundingBox> getCollidedBoxes(IBoundingBoxPool parBoundingBoxPool)
	{
		ArrayList<BoundingBox> boundingBoxes = new ArrayList<BoundingBox>();
		List<BoundingBox> boundingBoxPool = parBoundingBoxPool.getBoundingBoxPool();

		for(BoundingBox boundingbox : boundingBoxPool)
		{
			if (this.intersects(boundingbox) && boundingbox != this)
			{
				boundingBoxes.add(boundingbox);
			}
		}

		return boundingBoxes;
	}

	public BoundingBox expand(float parWidthOffset, float parHeightOffset)
	{
		return new BoundingBox(this.parent, this.getPosX(), this.getPosY(), this.getWidth() + parWidthOffset, this.getHeight() + parHeightOffset);
	}

	public BoundingBox offset(float parOffsetX, float parOffsetY)
	{
		return new BoundingBox(this.parent, this.getPosX() + parOffsetX, this.getPosY() + parOffsetY, this.getWidth(), this.getHeight());
	}

	@Override
	public float getPosX()
	{
		return this.parent.getPosX() + super.getPosX();
	}

	@Override
	public float getPosY()
	{
		return this.parent.getPosY() + super.getPosY();
	}

	public IPositionable getParent()
	{
		return this.parent;
	}
}
