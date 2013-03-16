package com.game.entity;

import org.lwjgl.util.Rectangle;

public class Entity
{
	protected boolean hasCollision;
	
	private boolean isDead;
	
	public int x;
	public int y;
	
	protected int xSpeed;
	protected int ySpeed;
	protected int xSlow;
	protected int ySlow;
	
	public Rectangle collisionBox;
	
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

	protected Rectangle getNewCollisionBox(int width, int height)
	{
		return new Rectangle(x, y, width, height);
	}
}
