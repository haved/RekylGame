package com.game.gui;

import com.game.world.World;

public class GuiGame extends GuiScreen
{
	public int money;
	public World world;
	
	public GuiGame()
	{
		startGame();
	}
	
	public void startGame()
	{
		world = new World(this);
	}
	
	public void endGame()
	{
		
	}
	
	@Override
	public void render()
	{
		world.render();
	}

	@Override
	public void tick()
	{
		world.tick();
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
