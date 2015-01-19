package net.pong;

public class PaddleAuto extends Paddle
{
	public PaddleAuto(float parPosX, float parPosY)
	{
		super(parPosX, parPosY);
	}

	@Override
	public void onUpdate()
	{
		if (this.posY < this.getWorldObj().ping.getPosY())
		{
			this.setPosY(this.posY + 2);
		}
		
		if (this.posY > this.getWorldObj().ping.getPosY())
		{
			this.setPosY(this.posY - 2);
		}
	}
}
