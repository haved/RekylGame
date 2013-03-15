package com.game.entity;

import com.game.Main;

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
		if(collisionBox.getY() + collisionBox.getHeight() >= 640)
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
		Main.renderEngine.bindTexture("sprites.png");
		Main.renderEngine.drawTransparentTexture(x, y, 32, 64, 0, 0, 16, 32);
	}
}
