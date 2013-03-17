package com.game.entity;

import com.game.RenderEngine;

public class BulletTest extends EntityBullet
{	
	public BulletTest(int x, int y, float xSpeed, float ySpeed)
	{
		this.x = x - 2;
		this.y = y - 2;
		
		this.xSpeed = (int)xSpeed;
		this.ySpeed = (int)ySpeed;
		
		xSize = 4;
		ySize = 4;
	}
	
	public void render()
	{
		RenderEngine.setGLColor(0, 0, 0, 1);
		RenderEngine.fillRect(x, y, xSize, ySize);
	}
}