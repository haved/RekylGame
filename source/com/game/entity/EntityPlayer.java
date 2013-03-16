package com.game.entity;

import com.game.RenderEngine;

public class EntityPlayer extends Entity
{
	public EntityPlayer()
	{
		xSpeed = 500;
		ySpeed = 0;
	}
	
	public void tick()
	{
		ySpeed += 100;
		
		collisionBox = getNewCollisionBox(32, 64);
		if(collisionBox.getY() + collisionBox.getHeight() >= 512)
		{
			ySpeed = (int)(-ySpeed / 1.05f);
		}
		
		if(collisionBox.getX() + collisionBox.getWidth() > 800 |
			collisionBox.getX() < 0)
		{
			xSpeed = -xSpeed;
		}
		
		move();
	}
	
	public void render()
	{
		RenderEngine.bindTexture("sprites.png");
		RenderEngine.drawTransparentTexture(x, y, 32, 64, 0, 0, 16, 32);
	}

	public int getScroll()
	{
		 return Math.min(0, -x + 256);
	}
}
