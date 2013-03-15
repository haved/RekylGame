package com.game;

import org.lwjgl.input.Keyboard;

import com.game.gui.GuiMainMenu;
import com.game.gui.GuiScreen;

import static org.lwjgl.opengl.GL11.*;

public class MainCanvas
{
	private boolean pressed; //Used to prevent spamming of ESC
	public GuiScreen current;
	
	public MainCanvas()
	{
		GuiScreen.setCanvas(this);
		setGuiScreen(new GuiMainMenu());
	}
	
	public void setGuiScreen(GuiScreen g)
	{
		if(current != null)
		{
			current.onClose();
		}
		
		current = g;
		
		if(current != null)
		{
			current.onOpen();
		}
	}
	
	public void tick()
	{
		if(current != null)
		{
			tryClose();
			current.tick();
		}
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		if(current != null)
		{
			current.render();
		}
	}

	private void tryClose()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			if(pressed == false)
			{
				current.exit();
				pressed = true;
			}
		}
		else
		{
			pressed = false;
		}
	}
}
