package com.game.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.game.RenderEngine;
import com.game.gun.Gun;
import com.game.gun.TestGun;
import com.game.world.World;

public class EntityPlayer extends Entity
{
	public Gun gun;
	
	private boolean stopped;
	public static int rotPointX = 20;
	public static int rotPointY = 40;
	private static boolean wasDown;
	private static byte renderAnim;
	private static int wantedSpeed = 800;
	
	public EntityPlayer()
	{
		xSpeed = wantedSpeed;
		y = 512 - 128;
		xSize = 32;
		ySize = 64;
		hasCollision = true;
		
		gun = new TestGun();
	}
	
	public void tick(World world)
	{
		anim();
		tryShoot(world);
		fixSpeed(world);
		
		gun.tick(world, this);
		
		move(world);
	}
	
	private void tryShoot(World world)
	{
		if(Mouse.isButtonDown(0))
		{
			if(!wasDown & gun.canShoot())
			{
				gun.fire(world, this, getRotation());
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
		ySpeed += 100;
		
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
			xSpeed -= 5;
			xSpeed = Math.max(xSpeed, wantedSpeed);
		}
		
		xSpeed = Math.min(xSpeed, wantedSpeed + 300);
		xSpeed = Math.max(xSpeed, 0);
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
		gun.render(this, getRotation());
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
