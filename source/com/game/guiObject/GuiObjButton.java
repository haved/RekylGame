package com.game.guiObject;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.game.Main;
import com.game.RenderEngine;

public class GuiObjButton extends GuiObject 
{
	private ButtonListener listener;
	private String text;
	private boolean mouseOver;
	private boolean mouseDown;
	private boolean prev;
	
	public GuiObjButton()
	{
		
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
	
	@Override
	public void render()
	{
		Main.renderEngine.bindTexture("gui.png");
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
		
		RenderEngine.push();
		Main.renderEngine.drawTexture(x, y, width, height, 0, yI, 256, 32);
		RenderEngine.pop();
		
		Main.renderEngine.drawText(x + (width - Main.renderEngine.getTextLength(text)) / 2,
				y + (height - Main.renderEngine.getTextHeight(text)) / 2
				, text, Color.white);
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
				if(listener != null)
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
