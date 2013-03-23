package com.game.gui;

import com.game.RenderEngine;
import com.game.guiObject.ButtonListener;
import com.game.guiObject.GuiObjButton;
import com.game.guiObject.GuiObjTextField;

public class GuiGameOver extends GuiContainer implements ButtonListener
{
	public Integer money;
	
	public GuiObjTextField field;
	public GuiObjButton[] buttons;
	
	public GuiGameOver(GuiGame game)
	{
		money = game.money;
		initButtons();
		initObjects();
	}
	
	private void initButtons()
	{
		buttons = new GuiObjButton[3];
		
		buttons[0] = new GuiObjButton("Main Menu", 32, 17*32, 11*32, 2*32);
		buttons[0].setListener(this);
		buttons[1] = new GuiObjButton("Retry", 13*32, 17*32, 11*32, 2*32);
		buttons[1].setListener(this);
		buttons[2] = new GuiObjButton("Submit", 32, 13*32, 11*32, 3*32);
		buttons[2].setListener(this);
		
		add(buttons[0]);
		add(buttons[1]);
		//add(buttons[2]);
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
		renderBG();
		renderObj();
		renderText();
	}
	
	private void renderBG()
	{
		RenderEngine.push();
		RenderEngine.resetColor();
		RenderEngine.bindTexture("gui.png");
		RenderEngine.drawTexture(0, 0, 800, 640, 100, 96, 100, 94);
		RenderEngine.pop();
	}
	
	private void renderText()
	{
		RenderEngine.push();
		
		RenderEngine.pop();
	}
	
	@Override
	public void buttonPressed(GuiObjButton source)
	{
		if(source == buttons[0])
		{
			canvas.setGuiScreen(new GuiMainMenu());
		}
		
		if(source == buttons[1])
		{
			canvas.setGuiScreen(new GuiGame());
		}
	}
}
