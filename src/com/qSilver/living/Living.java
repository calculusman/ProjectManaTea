package com.qSilver.living;

public abstract class Living
{
	private ILivingManager worldObj;
	protected boolean dead;

	public boolean onInit(ILivingManager parWorld)
	{
		this.setWorldObj(parWorld);
		return true;
	}

	public void onDestroy() {}

	public abstract void onUpdate();

	public boolean isDead()
	{
		return this.dead;
	}

	public void setDead()
	{
		this.dead = true;
	}

	public ILivingManager getWorldObj()
	{
		return this.worldObj;
	}

	public Living setWorldObj(ILivingManager parWorld)
	{
		this.worldObj = parWorld;
		return this;
	}

	@Override
	public String toString()
	{
		return this.worldObj.toString() + " : " + (this.isDead() ? "-DEAD : " : "") + super.toString();
	}
}
