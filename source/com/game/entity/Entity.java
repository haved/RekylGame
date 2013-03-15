package com.game.entity;

import org.lwjgl.util.Rectangle;

public class Entity
{
	protected boolean hasCollision;
	
	protected int x;
	protected int y;
	
	protected int xSpeed;
	protected int ySpeed;
	protected int xSlow;
	protected int ySlow;
	
	public Rectangle collisionBox;
	
	public Entity()
	{
		
	}
	
	public void render()
	{
		
	}
	
	public void tick()
	{
		move();
	}
	
	public void move()
	{
		xSlow = xSlow + xSpeed;
		ySlow = ySlow + ySpeed;
		
		while(xSlow >= 100)
		{
			x++;
			xSlow -= 100;
		}
		
		while(ySlow >= 100)
		{
			y--;
			ySlow -= 100;
		}
		
		while(xSlow <= 100)
		{
			x--;
			xSlow += 100;
		}
		
		while(ySlow <= 100)
		{
			y--;
			ySlow += 100;
		}
	}
}
