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
		
		if(this.xSpeed == 0){this.xSpeed++;}
		if(this.ySpeed == 0){this.ySpeed++;}
		
		xSize = 4;
		ySize = 4;
		
		hasCollision = true;
	}
	
	public void tick(World world)
	{
		super.tick(world);
		if(xSpeed == 0)
		{
			tryHitLeft(world);
			tryHitRight(world);
			kill();
		}
		if(ySpeed == 0)
		{
			tryHitOver(world);
			tryHitUnder(world);
			kill();
		}
	}
	
	public void onHit(World w, int x, int y)
	{
		w.setBlock(w.getBlockClass(x, y).getBlockOnBreak(), x, y);
	}
	
	public void render()
	{
		RenderEngine.setGLColor(0, 0, 0, 1);
		RenderEngine.fillRect(x, y, xSize, ySize);
	}
}
