package com.game.guiObject;

import com.game.RenderEngine;

public class GuiObjHighscoreList extends GuiObject
{
	private HighscoreData data;
	
	public GuiObjHighscoreList(HighscoreData data, int x, int y)
	{
		this.data = data;
		this.x = x;
		this.y = y;
		width = 12*32;
		height = 14*32;
	}
	
	@Override
	public void tick()
	{
		
	}

	@Override
	public void render() 
	{
		RenderEngine.push();
		RenderEngine.setGLColor(0, 0, 0, 1);
		RenderEngine.fillRect(x, y, width, height);
		RenderEngine.pop();
	}
}
