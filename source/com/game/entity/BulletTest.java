package com.game.entity;

import com.game.RenderEngine;

public class BulletTest extends EntityBullet
{	
	public BulletTest(int xSpeed, int ySpeed)
	{
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		
		xSize = 8;
		ySize = 8;
	}
	
	public void render()
	{
		RenderEngine.setGLColor(0, 0, 0, 1);
		RenderEngine.fillRect(x, y, xSize, ySize);
	}
}
