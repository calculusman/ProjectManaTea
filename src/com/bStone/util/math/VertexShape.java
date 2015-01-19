package com.bStone.util.math;

import java.util.List;

public abstract class VertexShape
{
	public abstract Point[] getVertexPoints();

	public boolean isVertexPoint(Point parPoint)
	{
		for(Point vertex : this.getVertexPoints())
		{
			if (vertex.equals(parPoint))
			{
				return true;
			}
		}

		return false;
	}

	public static Point getPointClosestToPoint(List<Point> parPoints, Point parPoint)
	{
		if (parPoints.size() == 0) return null;

		Point p = parPoints.get(0);
		float f = MathUtilF.getDistanceSqu(parPoint.getPosX(), parPoint.getPosY(), p.getPosX(), p.getPosY());

		for(Point p1 : parPoints)
		{
			if (p1 != null)
			{
				float f1 = MathUtilF.getDistanceSqu(parPoint.getPosX(), parPoint.getPosY(), p1.getPosX(), p1.getPosY());
				if (f1 < f)
				{
					p = p1;
					f = f1;
				}
			}
		}

		return p;
	}
}
