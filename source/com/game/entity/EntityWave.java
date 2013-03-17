package com.game.entity;

import com.game.RenderEngine;
import com.game.world.World;

public class EntityWave extends Entity
{
	private static int maxDistance = 150;
	private static int normalSpeed = 450;
	
	public EntityWave()
	{
		xSize = 80;
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
		if(world.player.x - (x + xSize) < maxDistance)
		{
			x = world.player.x - xSize - maxDistance;
		}
		move(world);
	}
	
	public void render()
	{
		RenderEngine.bindTexture("sprites.png");
		RenderEngine.drawTransparentTexture(x, y, xSize + 40, 512,
				208, 0, 48, 96);
		RenderEngine.setGLColor(0, 0, 0, 1);
		RenderEngine.fillRect(x-1000, 0, 1000, 512);
	}
	
	@Override
	public void onCollisionWithPlayer(World world, EntityPlayer player)
	{
		System.out.println("YOU LOST");
		world.loose();
	}
}
