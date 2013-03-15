package com.game.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.game.Main;
import com.game.entity.Entity;

public class GuiGame extends GuiScreen
{
	public ArrayList<Entity> entities;
	
	public GuiGame()
	{
		entities = new ArrayList<Entity>();
	}
	
	@Override
	public void render()
	{
		GL11.glColor3f(1, 1, 1);
		Main.renderEngine.fillRect(0, 0, 800, 640);
	}

	@Override
	public void tick()
	{
		
	}
	
	@Override
	public void exit()
	{
		canvas.setGuiScreen(new GuiPauseMenu(this));
	}
	
	public static GuiGame createGame()
	{
		return new GuiGame();
	}
}
