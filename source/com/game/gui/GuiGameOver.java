package com.game.gui;

import com.game.RenderEngine;

public class GuiGameOver extends GuiContainer
{
	public Integer money;
	
	public GuiGameOver(GuiGame game)
	{
		super(game);
	}
	
	public void tick()
	{
		tickObj();
	}
	
	public void render()
	{
		RenderEngine.push();
		RenderEngine.resetColor();
		RenderEngine.bindTexture("gui.png");
		RenderEngine.drawSquareWithTexture(0, 0, 100, 94, 100, 96, 100, 94);
		RenderEngine.pop();
		//renderObj();
	}
}
