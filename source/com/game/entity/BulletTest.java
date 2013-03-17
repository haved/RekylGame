package com.game.entity;

import com.game.RenderEngine;
import com.game.world.World;

public class BulletTest extends EntityBullet
{
	public BulletTest(int x, int y, int xSpeed, int ySpeed)
	{
		this.x = x - 2;
		this.y = y - 2;
		
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		
		if(xSpeed == 0){xSpeed++;}
		if(ySpeed == 0){ySpeed++;}
		
		xSize = 4;
		ySize = 4;
		
		hasCollision = true;
	}
	
	public void tick(World w)
	{
		super.tick(w);
		if(xSpeed == 0)
		{
			kill();
		}
		if(ySpeed == 0)
		{
			kill();
		}
	}
	
	public void render()
	{
		RenderEngine.setGLColor(0, 0, 0, 1);
		RenderEngine.fillRect(x, y, xSize, ySize);
	}
}
