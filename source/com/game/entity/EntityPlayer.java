package com.game.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.game.RenderEngine;
import com.game.world.World;

public class EntityPlayer extends Entity
{
	public static int rotPointX = 20;
	public static int rotPointY = 40;
	
	private boolean stopped;
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
		
		if(Mouse.isButtonDown(1))
		{
			world.fireGun(this, getRotation());
		}
		
		ySpeed += 100;
		fixSpeed(world);
		
		move(world);
	}
	
	public void stop()
	{
		stopped = true;
		xSpeed = 0;
	}
	
	private void fixSpeed(World world)
	{
		if(stopped){xSpeed = 0; return;}
		
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
	
	public void render(World w)
	{
		RenderEngine.resetColor();
		RenderEngine.bindTexture("sprites.png");
		RenderEngine.drawTransparentTexture(x, y, xSize, ySize, renderAnim < 5 ? 0 : 16, 0, 16, 32);
		w.renderGun(this, getRotation());
	}

	public int getScroll()
	{
		 return Math.max(0, x - 256);
	}

	public int getRotation()
	{
		int posX = x - getScroll() + rotPointX;
		int posY = 640 - (y + rotPointY);
		
		float xAxe = Mouse.getX() - posX;
		int yAxe = -(Mouse.getY() - posY);
		
		int svar = (int) Math.toDegrees(Math.atan(yAxe / xAxe));
		
		if(xAxe < 0)
		{
			svar = svar - 180;
		}
		
		return svar;
	}
}
