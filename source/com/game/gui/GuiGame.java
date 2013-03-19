package com.game.gui;

import com.game.gun.Gun;
import com.game.gun.TestGun;
import com.game.world.World;

public class GuiGame extends GuiScreen
{
	public Gun gun;
	public Integer money;
	public World world;
	
	public GuiGame()
	{
		money = new Integer(0);
		startGame();
	}
	
	public void startGame()
	{
		gun = new TestGun();
		world = new World(this);
	}
	
	public void endGame()
	{
		System.out.println("The end");
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
