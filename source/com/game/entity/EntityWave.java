package com.game.entity;

import com.game.RenderEngine;
import com.game.world.World;

public class EntityWave extends Entity
{
	private static int maxDistance = 300;
	private static int normalSpeed = 750;
	
	private boolean hasWon;
	
	public EntityWave()
	{
		xSize = 480;
		ySize = 512;
		x = -maxDistance - xSize;
	}
	
	public void tick(World world)
	{
		if(hasWon)
		{
			xSpeed += 50;
			move(world);
			return;
		}
		
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
		//xSpeed = 0; //Debug
		move(world);
		testCollisionWithPlayer(world, this);
		System.out.println(world.player.x - x);
		if(hasWon & world.player.x - x < 50)
		{
			world.loose();
		}
	}
	
	public void render()
	{
		//It has a custom render
	}
	
	public void customRender()
	{ 
		RenderEngine.bindTexture("sprites.png");
		RenderEngine.drawTransparentTexture(x + 400, y, 180, 512,
				208, 0, 48, 96);
		RenderEngine.setGLColor(0, 0, 0, 1);
		RenderEngine.fillRect(x-1000, 0, 1400, 512);
	}
	
	@Override
	public void onCollisionWithPlayer(World world, EntityPlayer player)
	{	
		hasWon = true;
		player.stop();
	}
}
