package com.game.gun;

import org.lwjgl.opengl.GL11;

import com.game.RenderEngine;
import com.game.entity.BulletTest;
import com.game.entity.EntityPlayer;
import com.game.world.World;

public class TestGun extends Gun
{
	public int speed = 1000;
	
	@Override
	public void fire(World w, EntityPlayer player, int rot)
	{
		w.addEntity(new BulletTest(getXPos(player), getYPos(player),
				player.xSpeed + getSpeedX(rot + 2),
				player.ySpeed + getSpeedY(rot + 2)));
		w.addEntity(new BulletTest(getXPos(player), getYPos(player),
				player.xSpeed + getSpeedX(rot),
				player.ySpeed + getSpeedY(rot)));
		w.addEntity(new BulletTest(getXPos(player), getYPos(player),
				player.xSpeed + getSpeedX(rot - 2),
				player.ySpeed + getSpeedY(rot - 2)));
		
		player.xSpeed -= getXAxis(rot) * 100;
		player.ySpeed -= getYAxis(rot) * 200;
	}
	
	public int getSpeedX(int rot)
	{
		return (int) getXAxis(rot) * speed;
	}
	
	public int getSpeedY(int rot)
	{
		return (int) getYAxis(rot) * speed;
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
}
