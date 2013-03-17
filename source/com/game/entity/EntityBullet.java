package com.game.entity;

import com.game.world.World;

public class EntityBullet extends Entity
{
	private int life = 300;
	
	public void tick(World w)
	{
		life--;
		if(life < 0)
		{
			kill();
		}
	}
	
	public void onHit(int xBlock, int yBlock)
	{
		
	}
}
