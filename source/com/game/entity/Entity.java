package com.game.entity;

import org.lwjgl.util.Rectangle;

public class Entity
{	
	private boolean isDead;
	
	public int x;
	public int y;
	
	protected int xSpeed;
	protected int ySpeed;
	protected int xSlow;
	protected int ySlow;
	
	public Rectangle box;
	
	public Entity()
	{
		
	}
	
	public void kill()
	{
		isDead = true;
	}
	
	public boolean isDead()
	{
		return isDead;
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
			y++;
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
	
	public boolean blockUnder()
	{
		if(box == null)
		{
			return false;
		}
		
		int xMin = box.getX() / 32;
		int xMax = (box.getX() + box.getWidth() -1) / 32;
		int y = (box.getY() + 1) / 32;
		
		for(; xMin <= xMax; xMin++)
		{
			
		}
		
		return false;
	}
	
	protected Rectangle getNewCollisionBox(int width, int height)
	{
		return new Rectangle(x, y, width, height);
	}
}
