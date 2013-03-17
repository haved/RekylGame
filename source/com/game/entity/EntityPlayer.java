package com.game.entity;

import org.lwjgl.input.Keyboard;

import com.game.RenderEngine;
import com.game.world.World;

public class EntityPlayer extends Entity
{
	private static byte renderAnim;
	private static int wantedSpeed = 500;
	
	public EntityPlayer()
	{
		xSpeed = wantedSpeed;
		y = 512 - 128;
		xSize = 32;
		ySize = 64;
		hasCollision = true;
	}
	
	public void tick(World world)
	{
		anim();
		
		ySpeed += 100;
		fixSpeed(world);
		
		move(world);
	}
	
	private void fixSpeed(World world)
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			ySpeed -= 200;
		}
		
		if(xSpeed < wantedSpeed)
		{
			if(blockDown(world))
			{
				xSpeed += 75;
			}
			else
			{
				xSpeed += 25;
			}
		}
		
		if(xSpeed > wantedSpeed)
		{
			xSpeed =- 20;
		}
		
		xSpeed = Math.min(xSpeed, wantedSpeed + 200);
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
		RenderEngine.drawTransparentTexture(x, y, xSize, ySize, renderAnim < 5 ? 0 : 16, 0, 16, 32);
	}

	public int getScroll()
	{
		 return Math.max(0, x - 256);
	}
}
