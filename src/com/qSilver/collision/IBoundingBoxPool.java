package com.qSilver.collision;

import java.util.List;

import com.bStone.util.IPositionable;

public interface IBoundingBoxPool
{
	public BoundingBox addBoxToPool(BoundingBox parBoundingBox);
	public BoundingBox removeBoxFromPool(BoundingBox parBoundingBox);
	public BoundingBox getBoxFromPool(IPositionable parParent);

	public List<BoundingBox> getBoundingBoxPool();
}
