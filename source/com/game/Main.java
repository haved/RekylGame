package com.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Main
{
	public static Main instance;
	public static RenderEngine renderEngine;
	
	public MainCanvas canvas;
	
	public void start()
	{
		initGLDisplay(800, 640);
		setup();
		gameLoop();
		destroy();
	}
	
	private void initGLDisplay(int width, int height)
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
		
		glShadeModel(GL_SMOOTH);       
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);                   
		 
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);               
		glClearDepth(1);
		 
		glViewport(0,0,width,height);
		glMatrixMode(GL_MODELVIEW);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 640, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	private void setup()
	{
		canvas = new MainCanvas();
		renderEngine = new RenderEngine();
	}
	
	private void gameLoop()
	{
		while(!Display.isCloseRequested())
		{
			canvas.tick();
			canvas.render();
			Display.update();
			Display.sync(30);
		}
	}
	
	private void destroy()
	{
		Mouse.destroy();
		Keyboard.destroy();
		Display.destroy();
	}
	
	public static void start(String [] args)
	{
		instance = new Main();
		instance.start();
	}
}
