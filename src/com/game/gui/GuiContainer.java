package com.game.gui;

import java.util.ArrayList;

import com.game.guiObject.GuiObject;

public class GuiContainer extends GuiScreen
{
	private ArrayList<GuiObject> list;
	
	public GuiContainer()
	{
		init();
	}
	public GuiContainer(GuiScreen parent)
	{
		super(parent);
		init();
	}
	
	private void init()
	{
		list = new ArrayList<GuiObject>();
	}
	
	
	public void tick()
	{
		tickObj();
	}
	
	public void render()
	{
		renderObj();
	}
	
	public void tickObj()
	{
		for(GuiObject o:list)
		{
			o.tick();
		}
	}
	
	public void renderObj()
	{
		for(GuiObject o:list)
		{
			o.render();
		}
	}
	
	public void add(GuiObject obj)
	{
		list.add(obj);
	}
	
	public void remove(GuiObject obj)
	{
		list.remove(obj);
	}
	
	public void removeAll()
	{
		list.clear();
	}
}
	
