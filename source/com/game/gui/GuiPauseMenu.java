package com.game.gui;

import com.game.Main;
import com.game.RenderEngine;
import com.game.guiObject.ButtonListener;
import com.game.guiObject.GuiObjButton;

public class GuiPauseMenu extends GuiContainer implements ButtonListener
{
	public GuiObjButton[] buttons;
	
	public GuiPauseMenu(GuiGame parent)
	{
		super(parent);
		initButtons();
	}
	
	public void initButtons()
	{
		buttons = new GuiObjButton[2];
		buttons[0] = new GuiObjButton("Back to game", 200, 450, 400, 50);
		buttons[0].setListener(this);
		buttons[1] = new GuiObjButton("Main Menu", 200, 520, 400, 50);
		buttons[1].setListener(this);
		
		add(buttons[0]);
		add(buttons[1]);
	}
	
	@Override
	public void tick()
	{
		tickObj();
	}

	@Override
	public void render()
	{
		parent.render();
		renderGrayBackground();
		renderObj();
	}
	
	protected void renderGrayBackground()
	{
		RenderEngine.setGLColor(0.6f, 0.6f, 0.6f, 0.6f);
		Main.renderEngine.fillRect(0, 0, 800, 640);
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
