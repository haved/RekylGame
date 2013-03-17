package com.game.gun;

import org.lwjgl.opengl.GL11;

import com.game.RenderEngine;
import com.game.entity.EntityPlayer;
import com.game.world.World;

public class TestGun extends Gun
{
	@Override
	public void fire(World w, EntityPlayer player, int rotation)
	{
		
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
