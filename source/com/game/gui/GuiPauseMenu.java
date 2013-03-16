package com.game.gui;

import com.game.Main;
import com.game.RenderEngine;
import com.game.guiObject.ButtonListener;
import com.game.guiObject.GuiObjButton;

public class GuiPauseMenu extends GuiContainer implements ButtonListener
{
	public int size = 50;
	public GuiObjButton[] buttons;
	
	public GuiPauseMenu(GuiGame parent)
	{
		super(parent);
		initButtons();
	}
	
	public void initButtons()
	{
		buttons = new GuiObjButton[2];
		buttons[0] = new GuiObjButton("Back to game", 300, 450, 200, 50);
		buttons[0].setListener(this);
		buttons[1] = new GuiObjButton("Main Menu", 300, 520, 200, 50);
		buttons[1].setListener(this);
		
		add(buttons[0]);
		add(buttons[1]);
	}
	
	@Override
	public void tick()
	{
		size++;
		if(size >= 100)
		{
			size = 0;
		}
		tickObj();
	}

	@Override
	public void render()
	{
		parent.render();
		renderGrayBackground();
		renderCustomText();
		renderObj();
	}
	
	private void renderCustomText()
	{
		Main.renderEngine.bindTexture("gui.png");
		Main.renderEngine.drawTexture(272, 300, 256, 280, 0, 96, 100, 94);
	}
	
	protected void renderGrayBackground()
	{
		RenderEngine.setGLColor(0.6f, 0.6f, 0.6f, 0.6f);
		Main.renderEngine.fillTransparentRect(0, 0, 800, 640);
	}

	public void buttonPressed(GuiObjButton source)
	{
		if(source == buttons[0])
		{
			exit();
		}
		else if(source == buttons[1])
		{
			canvas.setGuiScreen(new GuiMainMenu());
		}
	}
}
