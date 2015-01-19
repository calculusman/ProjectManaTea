package com.bStone.util.math;

public class Line extends VertexShape implements Comparable<Line>
{
	private float posX1;
	private float posY1;
	private float posX2;
	private float posY2;

	public Line(float parPosX1, float parPosY1, float parPosX2, float parPosY2)
	{
		this.posX1 = parPosX1;
		this.posY1 = parPosY1;
		this.posX2 = parPosX2;
		this.posY2 = parPosY2;
	}

	public boolean intersects(Point parPoint)
	{
		float x = Math.abs((this.posX2 - this.posX1) / (parPoint.getPosX() - this.posX1));
		float y = Math.abs((this.posY2 - this.posY1) / (parPoint.getPosY() - this.posY1));
		if (Math.abs(y - x) < 0.0001F)
		{
			float minX = this.posX1;
			float maxX = this.posX2;
			if (this.posX1 > this.posX2)
			{
				minX = this.posX2;
				maxX = this.posX1;
			}

			if (parPoint.getPosX() >= maxX && parPoint.getPosX() <= minX) return false;

			float minY = this.posY1;
			float maxY = this.posY2;
			if (this.posY1 > this.posY2)
			{
				minY = this.posY2;
				maxY = this.posY1;
			}

			if (parPoint.getPosY() >= maxY && parPoint.getPosY() <= minY) return false;

			return true;
		}

		return false;
	}

	public Point findIntersect(Line parLine)
	{
		Point p1 = new Point(this.getPosX2() - this.getPosX1(), this.getPosY2() - this.getPosY1());
		Point p2 = new Point(parLine.getPosX2() - parLine.getPosX1(), parLine.getPosY2() - parLine.getPosY1());

		float f = p1.getPosX() * p2.getPosY() - p1.getPosY() * p2.getPosX();
		if (Math.abs(f) < 0.0001F)
		{
			return null;
		}

		Point p3 = new Point(parLine.getPosX1() - this.getPosX1(), parLine.getPosY1() - this.getPosY1());
		float t = (p3.getPosX() * p2.getPosY() - p3.getPosY() * p2.getPosX()) / f;
		if (t < 0 || t > 1)
		{
			return null;
		}

		float u = (p3.getPosX() * p1.getPosY() - p3.getPosY() * p1.getPosX()) / f;
		if (u < 0 || u > 1)
		{
			return null;
		}

		return new Point(this.getPosX1() + t * p1.getPosX(), this.getPosY1() + t * p1.getPosY());
	}

	@Override
	public Point[] getVertexPoints()
	{
		return new Point[] {
				new Point(this.getPosX1(), this.getPosY1()),
				new Point(this.getPosX2(), this.getPosY2())};
	}

	public float getPosX1()
	{
		return this.posX1;
	}

	public float getPosY1()
	{
		return this.posY1;
	}

	public float getPosX2()
	{
		return this.posX2;
	}

	public float getPosY2()
	{
		return this.posY2;
	}

	public float getLength()
	{
		return MathUtilF.getDistance(this.posX1, this.posY1, this.posX2, this.posY2);
	}

	/**Returns -1, 0, 1, reflecting difference in length; i.e. this.length < parLine.length ? -1*/
	@Override
	public int compareTo(Line parLine)
	{
		float f = MathUtilF.getDistanceSqu(this.posX1, this.posY1, this.posX2, this.posY2) - MathUtilF.getDistanceSqu(parLine.posX1, parLine.posY1, parLine.posX2, parLine.posY2);
		return f < 0 ? -1 : f > 0 ? 1 : 0;
	}
}
