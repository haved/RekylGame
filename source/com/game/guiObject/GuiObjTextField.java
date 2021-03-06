package com.game.guiObject;

import org.newdawn.slick.Color;

import com.game.RenderEngine;

public class GuiObjTextField extends GuiObject
{
	private boolean enabled;
	private String text;
	
	public GuiObjTextField(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	@Override 
	public void tick()
	{
		
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	@Override
	public void render()
	{
		RenderEngine.push();
		
		if(enabled)
		{
			RenderEngine.resetColor();
		}
		else
		{
			RenderEngine.setGLColor(1, 1, 1, 0.5f);
		}
		
		RenderEngine.bindTexture("gui.png");
		RenderEngine.drawTexture(x, y, width, height, 200, 104, 48, 16);
		if(text != null)
		{
			RenderEngine.drawText(x + 4, y + 4, text, Color.white);
		}
		RenderEngine.pop();
	}
}
