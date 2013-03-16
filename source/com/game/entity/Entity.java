package com.game.entity;

import org.lwjgl.util.Rectangle;

import com.game.world.World;

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
	
	public void tick(World world)
	{
		move(world);
	}
	
	public void move(World world)
	{
		xSlow = xSlow + xSpeed;
		ySlow = ySlow + ySpeed;
		
		while(xSlow >= 100)
		{
			if(blockRight(world))
			{
				xSpeed = Math.min(0, xSpeed);
				xSlow = 0;
				break;
			}
			else
			{
				x++;
				xSlow -= 100;
			}
		}
		
		while(ySlow >= 100)
		{
			if(blockDown(world))
			{
				ySpeed = Math.min(0, ySpeed);
				ySlow = 0;
				break;
			}
			else
			{
				y++;
				ySlow -= 100;
			}
		}
		
		while(xSlow <= -100)
		{
			if(blockLeft(world))
			{
				xSpeed = Math.max(0, xSpeed);
				xSlow = 0;
				break;
			}
			else
			{
				x--;
				xSlow += 100;
			}
		}
		
		while(ySlow <= -100)
		{
			if(blockUp(world))
			{
				ySpeed = Math.max(0, ySpeed);
				ySlow = 0;
				break;
			}
			else
			{
				y--;
				ySlow += 100;
			}
		}
	}
	
	public boolean blockUp(World world)
	{
		if(box == null)
		{
			return false;
		}
		
		int xMin = box.getX() / 32;
		int xMax = (box.getX() + box.getWidth() -1) / 32;
		int y = (box.getY() - 1) / 32;
		
		for(; xMin <= xMax; xMin++)
		{
			if(world.hasBlockCollision(xMin, y))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean blockDown(World world)
	{
		if(box == null)
		{
			return false;
		}
		
		if(box.getY() + box.getHeight() >= 512)
		{
			y = 512 - box.getHeight();
			return true;
		}
		
//		int xMin = box.getX() / 32;
//		int xMax = (box.getX() + box.getWidth() -1) / 32;
//		int y = (box.getY() + box.getHeight() + 1) / 32;
//		
//		for(; xMin <= xMax; xMin++)
//		{
//			if(world.hasBlockCollision(xMin, y))
//			{
//				return true;
//			}
//		}
		
		return false;
	}
	
	public boolean blockLeft(World world)
	{
		if(box == null)
		{
			return false;
		}
		
		int yMin = box.getY() / 32;
		int yMax = (box.getY() + box.getHeight() -1) / 32;
		int x = (box.getX() - 1) / 32;
		
		for(; yMin <= yMax; yMin++)
		{
			if(world.hasBlockCollision(yMin, x))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean blockRight(World world)
	{
		if(box == null)
		{
			return false;
		}
		
		int yMin = box.getY() / 32;
		int yMax = (box.getY() + box.getHeight() -1) / 32;
		int x = (box.getX() + box.getWidth() + 1) / 32;
		
		for(; yMin <= yMax; yMin++)
		{
			if(world.hasBlockCollision(yMin, x))
			{
				return true;
			}
		}
		
		return false;
	}
	
	protected Rectangle getNewCollisionBox(int width, int height)
	{
		return new Rectangle(x, y, width, height);
	}
}
