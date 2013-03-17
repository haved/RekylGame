package com.game.gui;

import com.game.world.World;

public class GuiGame extends GuiScreen
{
	public Integer money;
	public World world;
	
	public GuiGame()
	{
		money = new Integer(0);
		startGame();
	}
	
	public void startGame()
	{
		world = new World(this);
	}
	
	public void endGame()
	{
		canvas.setGuiScreen(new GuiGameOver(this));
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
