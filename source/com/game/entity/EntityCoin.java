package com.game.entity;

import com.game.RenderEngine;
import com.game.world.World;

public class EntityCoin extends Entity
{
	private int animation;
	private int life = 700;
	private int value = 10;
	
	public EntityCoin(int value)
	{
		this.value = value;
		xSize = 16;
		ySize = 16;
	}
	
	public void tick(World w)
	{
		System.out.println(x+":"+y);
		
		life--;
		animation++;
		
		if(life < 0)
		{
			kill();
		}
		
		if(animation >= 4)
		{
			animation = 0;
		}
	}
	
	@Override
	public void onCollisioWithPlayer(World world, EntityPlayer player)
	{
		world.addMoney(value);
		kill();
	}
	
	public void render()
	{
		RenderEngine.bindTexture("sprites.png");
		RenderEngine.drawTransparentTexture(x, y, xSize, ySize, animation<2 ? 0:8, 32, 8, 8);
		System.out.println("render");
	}
}
