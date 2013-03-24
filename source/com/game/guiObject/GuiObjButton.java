package com.game.guiObject;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.game.RenderEngine;

public class GuiObjButton extends GuiObject 
{
	private boolean enabled;
	private ButtonListener listener;
	private String text;
	private boolean mouseOver;
	private boolean mouseDown;
	private boolean prev;
	
	public GuiObjButton()
	{
		enabled = true;
	}
	
	public GuiObjButton(String text)
	{
		this();
		
		this.text = text;
	}
	
	public GuiObjButton(String text, int x, int y, int width, int height)
	{
		this(text);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setListener(ButtonListener listener)
	{
		this.listener = listener;
	}
	
	public ButtonListener getListener()
	{
		return listener;
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
		RenderEngine.bindTexture("gui.png");
		RenderEngine.resetColor();
		
		int yI = 0;
		
		if(mouseOver & mouseDown)
		{
			yI = 64;
		}
		else if(mouseOver)
		{
			yI = 32;
		}
		
		if(!enabled)
		{
			yI = 0;
		}
		
		RenderEngine.push();
		RenderEngine.drawTexture(x, y, width, height, 0, yI, 256, 32);
		RenderEngine.pop();
		
		RenderEngine.drawText(x + (width - RenderEngine.getTextLength(text)) / 2,
				y + (height - RenderEngine.getTextHeight(text)) / 2
				, text, Color.white);
		
		if(!enabled)
		{
			RenderEngine.push();
			RenderEngine.setGLColor(0, 0, 0, 0.5f);
			RenderEngine.fillTransparentRect(x, y, width, height);
			RenderEngine.pop();
		}
	}

	@Override
	public void tick() 
	{
		mouseOver = Mouse.getX() >= x & Mouse.getX() <= x + width &
		Display.getHeight() - Mouse.getY() >= y &
		Display.getHeight() - Mouse.getY() <= y + height;
		
		if(!Mouse.isButtonDown(0))
		{
			if(mouseOver & mouseDown)
			{
				if(listener != null & enabled)
				{
					listener.buttonPressed(this);
				}
			}
			
			mouseDown = false;
		}
		else if(prev == false & mouseOver)
		{
			mouseDown = true;
		}
		
		prev = Mouse.isButtonDown(0);
	}
}
