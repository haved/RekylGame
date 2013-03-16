package com.game.debug;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.game.RenderEngine;

import static org.lwjgl.opengl.GL11.*;

public class Debug
{
	public static Debug instance;
	
	public void start()
	{
		createGLDisplay(800, 640);
		RenderEngine.init();
		gameLoop();
		destroy();
	}
	
	public void createGLDisplay(int width, int height)
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setVSyncEnabled(true);
			Display.setResizable(false);
			
			Display.create();
			Keyboard.create();
			Mouse.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);       
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);                   
		 
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);               
		glClearDepth(1);                                      
		 
//		glEnable(GL_BLEND);
//		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		 
		glViewport(0,0,width,height);
		glMatrixMode(GL_MODELVIEW);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, 0, height, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void gameLoop()
	{
		do
		{
			tick();
			render();
			Display.update();
			Display.sync(24);
			
		}
		while(!Display.isCloseRequested());
	}
	
	public void tick()
	{
		
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glColor3f(1, 1, 1);
		
		RenderEngine.fillRect(0, 0, 400, 640);
	}
	
	public void destroy()
	{
		Keyboard.destroy();
		Mouse.destroy();
		Display.destroy();
	}
	
	public static void main(String[] args)
	{
		instance = new Debug();
		instance.start();
	}
}
