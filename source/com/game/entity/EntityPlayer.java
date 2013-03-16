package com.game.entity;

import com.game.RenderEngine;
import com.game.world.World;

public class EntityPlayer extends Entity
{
	private byte renderAnim;
	
	public EntityPlayer()
	{
		xSpeed = 500;
		y = 512 - 128;
	}
	
	public void tick(World world)
	{
		anim();
		
		box = getNewCollisionBox(32, 64);
		ySpeed += 100;
		
		move(world);
	}
	
	private void anim()
	{
		renderAnim++;
		if(renderAnim >= 10)
		{
			renderAnim = 0;
		}
	}
	
	public void render()
	{
		RenderEngine.bindTexture("sprites.png");
		RenderEngine.drawTransparentTexture(x, y, 32, 64, renderAnim < 5 ? 0 : 16, 0, 16, 32);
	}

	public int getScroll()
	{
		 return Math.max(0, x - 256);
	}
}
