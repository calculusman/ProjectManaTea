package com.bStone.util.math;


public class Box extends VertexShape
{
	private float posX;
	private float posY;
	private float width;
	private float height;

	public Box(float parPosX, float parPosY, float parWidth, float parHeight)
	{
		this.posX = parPosX;
		this.posY = parPosY;
		this.width = parWidth;
		this.height = parHeight;
	}

	public Box setPosition(float parPosX, float parPosY)
	{
		this.posX = parPosX;
		this.posY = parPosY;
		return this;
	}

	public Box setDimension(float parWidth, float parHeight)
	{
		this.width = parWidth;
		this.height = parHeight;
		return this;
	}

	public Box expand(float parWidthOffset, float parHeightOffset)
	{
		return new Box(this.getPosX() - parWidthOffset / 2, this.getPosY() - parHeightOffset / 2, this.getWidth() + parWidthOffset, this.getHeight() + parHeightOffset);
	}

	public Box offset(float parOffsetX, float parOffsetY)
	{
		return new Box(this.getPosX() + parOffsetX, this.getPosY() + parOffsetY, this.getWidth(), this.getHeight());
	}

	public boolean withinBounds(Box parBox)
	{
		//TODO: Optimize this! And add a line version
		boolean flag = true;
		flag &= this.getPosX() < parBox.getPosX() + parBox.getWidth() && this.getPosX() > parBox.getPosX();
		flag &= this.getPosX() + this.getWidth() < parBox.getPosX() + parBox.getWidth() && this.getPosX() + this.getWidth() > parBox.getPosX();
		flag &= this.getPosY() < parBox.getPosY() + parBox.getHeight() && this.getPosY() > parBox.getPosY();
		flag &= this.getPosY() + this.getHeight() < parBox.getPosY() + parBox.getHeight() && this.getPosY() + this.getHeight() > parBox.getPosY();
		return flag;
	}

	public boolean intersects(float parPosX, float parPosY)
	{
		return parPosX > this.getPosX() && parPosX < this.getPosX() + this.getWidth() && parPosY > this.getPosY() && parPosY < this.getPosY() + this.getHeight();
	}

	public boolean intersects(Point parPoint)
	{
		return parPoint.getPosX() > this.getPosX() && parPoint.getPosX() < this.getPosX() + this.getWidth() && parPoint.getPosY() > this.getPosY() && parPoint.getPosY() < this.getPosY() + this.getHeight();
	}

	public boolean intersects(Line parLine)
	{
		float minX = parLine.getPosX1();
		float maxX = parLine.getPosX2();

		if (parLine.getPosX1() > parLine.getPosX2())
		{
			minX = parLine.getPosX2();
			maxX = parLine.getPosX1();
		}

		if (maxX > this.getPosX() + this.getWidth()) maxX = this.getPosX() + this.getWidth();

		if (minX < this.getPosX()) minX = this.getPosX();

		if (minX > maxX) return false;

		double minY = parLine.getPosY1();
		double maxY = parLine.getPosY2();

		double d = parLine.getPosX2() - parLine.getPosX1();

		if (Math.abs(d) > 0.0001F)
		{
			double d1 = (parLine.getPosY2() - parLine.getPosY1()) / d;
			double d2 = parLine.getPosY1() - d1 * parLine.getPosX1();
			minY = d1 * minX + d2;
			maxY = d1 * maxX + d2;
		}

		if (minY > maxY)
		{
			double d3 = maxY;
			maxY = minY;
			minY = d3;
		}

		if (maxY > this.getPosY() + this.getHeight()) maxY = this.getPosY() + this.getHeight();
		if (minY < this.getPosY()) minY = this.getPosY();
		if (minY > maxY) return false;

		return true;
	}

	public boolean intersects(Box parBox)
	{
		return this.getPosX() < parBox.getPosX() + parBox.getWidth() && this.getPosX() + this.getWidth() > parBox.getPosX() && this.getPosY() < parBox.getPosY() + parBox.getHeight() && this.getPosY() + this.getHeight() > parBox.getPosY();
	}

	public VertexPool findIntersectAtVertex(Line parLine)
	{
		Point p1 = new Point(this.getPosX(), this.getPosY());
		Point p2 = new Point(this.getPosX() + this.getWidth(), this.getPosY());
		Point p3 = new Point(this.getPosX(), this.getPosY() + this.getHeight());
		Point p4 = new Point(this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight());

		VertexPool p = new VertexPool();
		float m = (parLine.getPosY2() - parLine.getPosY1()) / (parLine.getPosX2() - parLine.getPosX1());
		float b = parLine.getPosY1() - m * parLine.getPosX1();

		if (Math.abs(p1.getPosY() - (m * p1.getPosX() + b)) < 0.0001F) p.add(p1);
		if (Math.abs(p2.getPosY() - (m * p2.getPosX() + b)) < 0.0001F) p.add(p2);
		if (Math.abs(p3.getPosY() - (m * p3.getPosX() + b)) < 0.0001F) p.add(p3);
		if (Math.abs(p4.getPosY() - (m * p4.getPosX() + b)) < 0.0001F) p.add(p4);

		return p;
	}

	public VertexPool findIntersect(Line parLine)
	{
		Line r1 = new Line(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY());
		Line r2 = new Line(this.getPosX() + this.getWidth(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight());
		Line r3 = new Line(this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), this.getPosX(), this.getPosY() + this.getHeight());
		Line r4 = new Line(this.getPosX(), this.getPosY() + this.getHeight(), this.getPosX(), this.getPosY());

		VertexPool p = new VertexPool();

		Point p1 = null;
		p1 = r1.findIntersect(parLine);
		if (p1 != null) p.add(p1);
		p1 = r2.findIntersect(parLine);
		if (p1 != null) p.add(p1);
		p1 = r3.findIntersect(parLine);
		if (p1 != null) p.add(p1);
		p1 = r4.findIntersect(parLine);
		if (p1 != null) p.add(p1);

		return p;
	}

	@Override
	public Point[] getVertexPoints()
	{
		return new Point[] {
				new Point(this.getPosX(), this.getPosY()),
				new Point(this.getPosX() + this.getWidth(), this.getPosY()),
				new Point(this.getPosX(), this.getPosY() + this.getHeight()),
				new Point(this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight())};
	}

	public float getPosX()
	{
		return this.posX;
	}

	public float getPosY()
	{
		return this.posY;
	}

	public float getWidth()
	{
		return this.width;
	}

	public float getHeight()
	{
		return this.height;
	}
}
