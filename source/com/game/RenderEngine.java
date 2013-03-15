package com.game;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class RenderEngine
{
	private HashMap<String, Texture> list;
	private float currentSize;
	
	private TrueTypeFont font;
	private TrueTypeFont customFont;
	
	public RenderEngine()
	{
		list = new HashMap<String, Texture>();
		initFont();
	}
	
	private void initFont()
	{
		font = new TrueTypeFont(new Font("Times New Roman", Font.PLAIN, 20), true);
	}
	
	public void loadTexture(String location)
	{
		if(location.endsWith("png"))
		{
			loadTexture("png", location);
		}
		if(location.endsWith("jpg"))
		{
			loadTexture("jpg", location);
		}
	}
	
	public void loadTexture(String type, String location)
	{
		if(list.containsKey(location))
		{
			return;
		}
		
		try
		{
			list.put(location, loadTextureToApp(type, location));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private Texture loadTextureToApp(String type, String location) throws Exception
	{
		return TextureLoader.getTexture(type, new FileInputStream(
				new File("bin/res/" + location)));
	}
	
	public boolean bindTexture(String s)
	{
		if(!list.containsKey(s))
		{
			loadTexture(s);
		}
		
		if(list.containsKey(s))
		{
			bindTexture(getTexture(s));
			return true;
		}
		
		return false;
	}
	
	private void bindTexture(Texture tex)
	{
		currentSize = tex.getImageWidth();
		tex.bind();
	}
	
	public Texture getTexture(String s)
	{
		return list.get(s);
	}
	
	
	public void drawTexture(int x, int y, int width, int height,
			int x2, int y2, int width2, int height2)
	{
		glPushMatrix();
		
		glEnable(GL_TEXTURE_2D);
		
		glTranslatef(x, y, 0);
		
		float tX = x2 / currentSize;
		float tY = y2 / currentSize;
		float tW = width2 / currentSize;
		float tH = height2 / currentSize;
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(tX, tY);
			glVertex2f(0, 0);
			glTexCoord2f(tX + tW, tY);
			glVertex2f(width, 0);
			glTexCoord2f(tX + tW, tY + tH);
			glVertex2f(width, height);
			glTexCoord2f(tX, tY + tH);
			glVertex2f(0, height);
		}
		glEnd();
		
		glDisable(GL_TEXTURE_2D);
		
		glPopMatrix();
	}
	
	public void fillRect(int x, int y, int width, int height)
	{
		glPushMatrix();
		
		glTranslatef(x, y, 0);
		
		glBegin(GL_QUADS);
		{
			glVertex2f(0, 0);
			glVertex2f(width, 0);
			glVertex2f(width, height);
			glVertex2f(0, height);
		}
		glEnd();
		glPopMatrix();
	}
	
	public void setCustomFont(Font f, boolean antiAlias)
	{
		customFont = new TrueTypeFont(f, antiAlias);
	}
	
	public void drawText(int x, int y, String text, Color c)
	{
		drawFont(font, x, y, text, c);
	}
	
	public void drawCustomText(int x, int y, String text, Color c)
	{
		if(customFont != null)
		{
			drawFont(customFont, x, y, text, c);
		}
		else
		{
			System.err.println("No custom font");
		}
	}
	
	private void drawFont(TrueTypeFont font, int x, int y, String text, Color c)
	{
		glEnable(GL_BLEND);
		font.drawString(x, y, text, c);
		glDisable(GL_BLEND);
	}
	
	public int getTextLength(String text)
	{
		return font.getWidth(text);
	}
	
	public int getTextHeight(String text)
	{
		return font.getHeight(text);
	}
	
	public int getCustomTextLength(String text)
	{
		return customFont.getWidth(text);
	}
	
	public int getCustomTextHeight(String text)
	{
		return customFont.getHeight(text);
	}

	public static void setGLColor(float red, float green, float blue, float alpha)
	{
		glColor4f(red, green, blue, alpha);
	}

	public static void resetColor()
	{
		setGLColor(1, 1, 1, 1);
	}
}
