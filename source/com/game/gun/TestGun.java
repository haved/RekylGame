package com.game.gun;

import org.lwjgl.opengl.GL11;

import com.game.RenderEngine;
import com.game.entity.BulletTest;
import com.game.entity.EntityPlayer;
import com.game.world.World;

public class TestGun extends Gun
{
	public static int speed = 4000;
	private static int barrel= 32;
	private int heat = 0;
	private int flash;
	
	@Override
	public void fire(World w, EntityPlayer player, int rot)
	{
		heat += 30;
		flash = 2;
		
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
		return (int) (getXPos(player) + getTipX(rot) + (player.xSpeed / 50));
	}
	
	public int getRealYPos(EntityPlayer player, int rot)
	{
		return (int) (getYPos(player) + getTipY(rot) + (player.ySpeed / 50));
	}
	
	public int getTipX(int rot)
	{
		return (int) (getXAxis(rot) * barrel);
	}
	
	public int getTipY(int rot)
	{
		return (int) (getYAxis(rot) * barrel);
	}
	
	@Override
	public void render(EntityPlayer player, int rot)
	{
		RenderEngine.push();
		RenderEngine.setGLColor(0, 0, 0, 1);
		GL11.glTranslatef(getXPos(player), getYPos(player), 0);
		GL11.glRotatef(rot, 0, 0, 1);
		
		RenderEngine.resetColor();
		
		RenderEngine.bindTexture("sprites.png");
		RenderEngine.drawTransparentTexture(-3*2, -6*2, 16*2, 8*2,
											32, 0, 16, 8);
		if(flash > 0)
		{
			RenderEngine.drawTransparentTexture(barrel - 3, -8 + -8, 32, 16,
			32, 8, 16, 8); //The last -8 is because the barrel is higher than the rotation
		}
		
		RenderEngine.pop();
	}

	
	@Override
	public void tick(World world, EntityPlayer player)
	{
		if(flash > 0)
		{
			flash--;
		}
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
