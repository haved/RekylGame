package com.game.entity;

import com.game.RenderEngine;
import com.game.world.World;

public class EntityWave extends Entity
{
	private static int maxDistance = 300;
	private static int normalSpeed = 450;
	
	public EntityWave()
	{
		xSize = 280;
		ySize = 512;
		x = -maxDistance - xSize;
	}
	
	public void tick(World world)
	{
		if(xSpeed > normalSpeed)
		{
			xSpeed = Math.max(normalSpeed, xSpeed - 20);
		}
		if(xSpeed < normalSpeed)
		{
			xSpeed = Math.min(normalSpeed, xSpeed + 20);
		}
		if(world.player.x - (x + xSize) > maxDistance)
		{
			xSpeed += 100;
		}
		move(world);
	}
	
	public void render()
	{
		//It has a custom render
	}
	
	public void customRender()
	{
		RenderEngine.bindTexture("sprites.png");
		RenderEngine.drawTransparentTexture(x + 200, y, 180, 512,
				208, 0, 48, 96);
		RenderEngine.setGLColor(0, 0, 0, 1);
		RenderEngine.fillRect(x-1000, 0, 1200, 512);
	}
	
	@Override
	public void onCollisionWithPlayer(World world, EntityPlayer player)
	{
		player.stop();
	}
}
