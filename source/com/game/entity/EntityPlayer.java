package com.game.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.game.RenderEngine;
import com.game.world.World;

public class EntityPlayer extends Entity
{
	private static boolean wasDown;
	
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
		tryShoot(world);
		ySpeed += 100;
		fixSpeed(world);
		
		System.out.println(xSpeed);
		
		move(world);
	}
	
	private void tryShoot(World world)
	{
		if(Mouse.isButtonDown(0))
		{
			if(!wasDown)
			{
				world.fireGun(this, getRotation());
			}
			wasDown = true;
		}
		else
		{
			wasDown = false;
		}
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
			
			xSpeed = Math.min(xSpeed, wantedSpeed);
		}
		
		if(xSpeed > wantedSpeed)
		{
			xSpeed =- 5;
			//xSpeed = Math.max(xSpeed, wantedSpeed);
		}
		
		xSpeed = Math.min(xSpeed, wantedSpeed + 800);
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
		if(y + ySize < 0)
		{
			RenderEngine.drawTransparentTexture(x, 0, 32, 32, renderAnim < 5 ? 0 : 16, 0, 16, 16);
			RenderEngine.drawText(x + (32 - RenderEngine.getTextLength("" + -(y + ySize))) / 2, 30,
					"" + -(y + ySize), Color.black);
		}
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
