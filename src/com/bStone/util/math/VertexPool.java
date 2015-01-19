package com.bStone.util.math;

import java.util.ArrayList;

public class VertexPool extends ArrayList<Point>
{
	private static final long serialVersionUID = 1L;

	public VertexPool exlcudeVertexPoints(VertexShape parVertexShape)
	{
		for(int i = this.size() - 1; i >= 0; i--)
		{
			if (parVertexShape.isVertexPoint(this.get(i)))
			{
				this.remove(i);
			}
		}

		return this;
	}
}
