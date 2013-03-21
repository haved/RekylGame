package com.game.gun;

import com.game.entity.EntityPlayer;
import com.game.world.World;

public abstract class Gun
{
	public abstract boolean canShoot();
	
	public void tick(World world, EntityPlayer player)
	{
		
	}
	
	public void fire(World w, EntityPlayer player, int rotation)
	{
		
	}
	
	public void render(EntityPlayer player, int rotation)
	{
		
	}
	
	public int getXPos(EntityPlayer player)
	{
		return player.x + EntityPlayer.rotPointX;
	}
	
	public int getYPos(EntityPlayer player)
	{
		return player.y + EntityPlayer.rotPointY;
	}
	
	public float getXAxis(int rot)
	{
		return (float) Math.cos(Math.toRadians(rot));
	}
	
	public float getYAxis(int rot)
	{
		return (float) Math.sin(Math.toRadians(rot));
	}
	
	public int getHeatScaled(int scale)
	{
		return 0;
	}
}
