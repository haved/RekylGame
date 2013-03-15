package com.game.gui;

import static org.lwjgl.opengl.GL11.*;

import com.game.Main;
import com.game.guiObject.GuiObjButton;

public class GuiPauseMenu extends GuiContainer
{
	public GuiObjButton[] buttons;
	
	public GuiPauseMenu(GuiGame parent)
	{
		super(parent);
	}
	
	public void initButtons()
	{
		buttons = new GuiObjButton[2];
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
		glColor4f(0.6f,  0.6f,  0.6f, 0.6f);
		Main.renderEngine.fillRect(0, 0, 800, 640);
	}
}
