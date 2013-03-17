package com.game.gun;

import org.lwjgl.opengl.GL11;

import com.game.RenderEngine;
import com.game.entity.EntityPlayer;

public class TestGun extends Gun
{
	@Override
	public void render(EntityPlayer player, int rotation)
	{
		RenderEngine.push();
		RenderEngine.setGLColor(0, 0, 0, 1);
		GL11.glTranslatef(player.x + 15, player.y + 40, 0);
		//GL11.glRotatef(rotation, 0, 0, 1);
		RenderEngine.fillRect(-2, -3, 20, 6);
		RenderEngine.pop();
	}
}
