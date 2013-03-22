package com.game.gui;

import com.game.RenderEngine;
import com.game.guiObject.GuiObjTextField;

public class GuiGameOver extends GuiContainer
{
	public Integer money;
	
	public GuiObjTextField field;
	
	public GuiGameOver(GuiGame game)
	{
		super(game);
		money = game.money;
		initObjects();
	}
	
	private void initObjects()
	{
		field = new GuiObjTextField(100, 100, 200, 50);
		add(field);
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
		RenderEngine.drawSquareWithTexture(0, 0, 800, 640, 100, 96, 100, 94);
		RenderEngine.pop();
		renderObj();
	}
}
