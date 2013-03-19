package com.game.entity;

import com.game.world.World;

public class EntityBullet extends Entity
{
	private int life = 50;
	
	public void tick(World w)
	{
		life--;
		if(life < 0)
		{
			kill();
		}
		
		move(w);
	}
	
	public void tryHitOver(World world)
	{
		if(isDead()){return;};
		
		int xMin = x / 32;
		int xMax = (x + xSize -1) / 32;
		int yCoord = (y - 1) / 32;
		
		for(; xMin <= xMax; xMin++)
		{
			if(tryHitBlock(world, xMin, yCoord))break;
		}
	}
	
	public void tryHitUnder(World world)
	{
		if(isDead()){return;};
		
		int xMin = x / 32;
		int xMax = (x + xSize -1) / 32;
		int yCoord = (y + ySize + 1) / 32;
		
		for(; xMin <= xMax; xMin++)
		{
			if(tryHitBlock(world, xMin, yCoord))break;
		}
	}
	
	public void tryHitLeft(World world)
	{
		if(isDead()){return;};
		
		int yMin = y / 32;
		int yMax = (y + ySize -1) / 32;
		int xCoord = (x - 1) / 32;
		
		for(; yMin <= yMax; yMin++)
		{
			if(tryHitBlock(world, xCoord, yMin))break;
		}
	}
	
	public void tryHitRight(World world)
	{
		if(isDead()){return;};
		
		int yMin = y / 32;
		int yMax = (y + ySize -1) / 32;
		int xCoord = (x + xSize + 1) / 32;
		
		for(; yMin <= yMax; yMin++)
		{
			if(tryHitBlock(world, xCoord, yMin))break;
		}
	}
	
	public boolean tryHitBlock(World world, int xBlock, int yBlock)
	{
		if(world.canBlockBreak(xBlock, yBlock))
		{
			onHit(world, xBlock, yBlock);
			kill();
			return true;
		}
		return false;
	}
	
	public void onHit(World world, int xBlock, int yBlock)
	{
		
	}
}
