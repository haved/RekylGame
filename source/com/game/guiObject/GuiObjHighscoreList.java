package com.game.guiObject;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.game.RenderEngine;

public class GuiObjHighscoreList extends GuiObject
{
	private static TrueTypeFont textFont;
	private int highlight;
	private HighscoreData data;
	
	public GuiObjHighscoreList(HighscoreData data, int x, int y)
	{
		this.data = data;
		this.x = x;
		this.y = y;
		width = 386;
		height = 450;
		
		highlight = 10;
		
		if(textFont == null)
		{
			textFont = RenderEngine.getCustomFont(new Font("Dialog", Font.PLAIN, 30),  true);
		}
	}
	
	public void setHighlight(int highlight)
	{
		this.highlight = highlight;
	}
	
	@Override
	public void tick()
	{
		
	}

	@Override
	public void render() 
	{
		RenderEngine.push();
		RenderEngine.setTranslation(x, y);
		for(int i = 0; i < 10; i++)
		{
			if(i%2 == 0){RenderEngine.setGLColor(0.6f, 0.6f, 0.6f, 1);}
			else{RenderEngine.setGLColor(0.4f, 0.4f, 0.4f, 1);}
			
			RenderEngine.fillRect(0, i*(height/10), width, height/10);
			
			if(i == highlight){continue;}
			
			int j = i;
			if(i > highlight){j--;}
			
			RenderEngine.setCustomFont(textFont);
			RenderEngine.drawCustomText(2, i*(height/10) + 2, data.getNameAt(j), Color.white);
			
			RenderEngine.drawCustomText(width-2-RenderEngine.getCustomTextLength("$"+data.getValueAt(j)),
					i*(height/10) + 2, "$"+data.getValueAt(j), Color.yellow);
		}
		RenderEngine.pop();
	}
}
