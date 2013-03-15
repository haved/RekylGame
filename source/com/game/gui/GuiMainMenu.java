package com.game.gui;

import com.game.guiObject.ButtonListener;
import com.game.guiObject.GuiObjButton;

public class GuiMainMenu extends GuiContainer implements ButtonListener
{
	private GuiObjButton[] buttons;
	
	public GuiMainMenu()
	{
		initButtons();
	}
	
	private void initButtons()
	{
		buttons = new GuiObjButton[1];
		
		buttons[0] = new GuiObjButton("Start Game", 250, 500, 300, 40);
		buttons[0].setListener(this);
		add(buttons[0]);
	}
	
	public void buttonPressed(GuiObjButton source)
	{
		if(source == buttons[0])
		{
			startGame();
		}
	}
	
	private void startGame()
	{
		canvas.setGuiScreen(GuiGame.createGame());
	}
}
