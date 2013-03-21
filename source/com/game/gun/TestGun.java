package com.game.gun;

import org.lwjgl.opengl.GL11;

import com.game.RenderEngine;
import com.game.entity.BulletTest;
import com.game.entity.EntityPlayer;
import com.game.world.World;

public class TestGun extends Gun
{
	public int speed = 4000;
	private int heat = 0;
	
	@Override
	public void fire(World w, EntityPlayer player, int rot)
	{
		heat += 30;
		
//		w.addEntity(new BulletTest(getRealXPos(player, rot), getRealYPos(player, rot),
//				player.xSpeed + getSpeedX(rot + 2),
//				player.ySpeed + getSpeedY(rot + 2)));
		w.addEntity(new BulletTest(getRealXPos(player, rot), getRealYPos(player, rot),
				player.xSpeed + getSpeedX(rot),
				player.ySpeed + getSpeedY(rot)));
//		w.addEntity(new BulletTest(getRealXPos(player, rot), getRealYPos(player, rot),
//				player.xSpeed + getSpeedX(rot - 2),
//				player.ySpeed + getSpeedY(rot - 2)));
		
		player.xSpeed -= getXAxis(rot) * 500;
		player.ySpeed -= getYAxis(rot) * 1000;
	}
	
	public int getSpeedX(int rot)
	{
		return (int) (getXAxis(rot) * speed);
	}
	
	public int getSpeedY(int rot)
	{
		return (int) (getYAxis(rot) * speed);
	}
	
	public int getRealXPos(EntityPlayer player, int rot)
	{
		return (int) (getXPos(player) + (getXAxis(rot) * 18) + (player.xSpeed / 100));
	}
	
	public int getRealYPos(EntityPlayer player, int rot)
	{
		return (int) (getYPos(player) + (getYAxis(rot) * 18) + (player.ySpeed / 100));
	}
	
	@Override
	public void render(EntityPlayer player, int rotation)
	{
		RenderEngine.push();
		RenderEngine.setGLColor(0, 0, 0, 1);
		GL11.glTranslatef(getXPos(player), getYPos(player), 0);
		GL11.glRotatef(rotation, 0, 0, 1);
		RenderEngine.fillRect(-2, -3, 20, 6);
		RenderEngine.pop();
	}

	
	@Override
	public void tick(World world, EntityPlayer player)
	{
		heat -= 2;
		heat = Math.max(heat, 0);
	}
	
	@Override
	public boolean canShoot()
	{
		return heat <= 120;
	}
	
	public int getHeatScaled(int scale)
	{
		return heat * scale / 150;
	}
}
