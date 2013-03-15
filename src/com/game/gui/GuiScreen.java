package com.game.gui;

import com.game.MainCanvas;

public abstract class GuiScreen
{
	protected static MainCanvas canvas;
	public GuiScreen parent;
	
	public GuiScreen()
	{
		
	}
	
	public GuiScreen(GuiScreen parent)
	{
		this();
		this.parent = parent;
	}
	
	public abstract void tick();
	
	public abstract void render();

	public void onOpen()
	{
		
	}
	
	public void onClose()
	{
		
	}
	
	public void exit()
	{
		if(parent != null)
		{
			canvas.setGuiScreen(parent);
		}
	}
	
	public static void setCanvas(MainCanvas canvas)
	{
		GuiScreen.canvas = canvas;
	}
}
