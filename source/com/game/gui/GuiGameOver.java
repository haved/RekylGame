package com.game.gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.game.RenderEngine;
import com.game.guiObject.ButtonListener;
import com.game.guiObject.GuiObjButton;
import com.game.guiObject.GuiObjHighscoreList;
import com.game.guiObject.GuiObjTextField;
import com.game.guiObject.HighscoreData;

public class GuiGameOver extends GuiContainer implements ButtonListener
{
	private static TrueTypeFont moneyFont;
	
	public Integer money;
	
	public HighscoreData data;
	public GuiObjHighscoreList list;
	public GuiObjTextField field;
	public GuiObjButton[] buttons;
	
	public GuiGameOver(GuiGame game)
	{
		money = game.money;
		moneyFont = RenderEngine.getCustomFont(new Font("Dialog", Font.PLAIN, 50), true);
		data = new HighscoreData(
		"C:/Users/haavard/AppData/Roaming/haved/Rekyl/highscore.txt");
		initButtons();
		initObjects();
		loadData();
	}
	
	private void initButtons()
	{
		buttons = new GuiObjButton[3];
		
		buttons[0] = new GuiObjButton("Main Menu", 32, 17*32, 10 *32, 2*32);
		buttons[0].setListener(this);
		buttons[1] = new GuiObjButton("Retry", 14*32, 17*32, 10*32, 2*32);
		buttons[1].setListener(this);
		buttons[2] = new GuiObjButton("Submit", 14*32, 12*32, 10*32, 3*32);
		buttons[2].setListener(this);
		
		add(buttons[0]);
		add(buttons[1]);
		add(buttons[2]);
	}
	
	private void initObjects()
	{
		field = new GuiObjTextField(14*32, 8*32, 10*32, 3*32);
		field.setText("ABC");
		add(field);
		list = new GuiObjHighscoreList(data, 7, 55);
		add(list);
	}
	
	private void loadData()
	{
		data.load();
		list.setHighlight(data.getListSpot(money));
		enableSubmission(data.getListSpot(money) < 10);
	}
	
	private void enableSubmission(boolean val)
	{
		buttons[0].setEnabled(!val);
		buttons[1].setEnabled(!val);
		buttons[2].setEnabled(val);
		field.setEnabled(val);
	}
	
	public void tick()
	{
		tickObj();
	}
	
	public void render()
	{
		renderBG();
		renderBoxes();
		renderObj();
		renderText();
	}
	
	private void renderBG()
	{
		RenderEngine.push();
		RenderEngine.resetColor();
		RenderEngine.bindTexture("gui.png");
		RenderEngine.drawTexture(0, 0, 800, 640, 100, 96, 100, 94);
		RenderEngine.pop();
	}
	
	private void renderBoxes()
	{
		RenderEngine.push();
		RenderEngine.drawTexture(0, 0, 400, 16*32, 100, 96, 100, 94);
		RenderEngine.drawTexture(400, 0, 400, 16*32, 100, 96, 100, 94);
		RenderEngine.drawTexture(0, 16*32, 800, 4*32, 100, 96, 100, 94);
		RenderEngine.pop();
	}
	
	private void renderText()
	{
		RenderEngine.push();
		RenderEngine.resetColor();
		RenderEngine.bindTexture("gui.png");
		RenderEngine.drawTransparentTexture(32*14, 16, 4*32, 32, 200, 120, 56, 16);//Score
		RenderEngine.drawTransparentTexture(32*14, 6*32, 4*32, 32, 200, 136, 56, 16);//Name
		RenderEngine.drawTransparentTexture(32*4, 16, 4*32, 32, 200, 152, 56, 16);//Top10
		RenderEngine.pop();
		
		RenderEngine.push();
		RenderEngine.setCustomFont(moneyFont);
		RenderEngine.drawCustomText(32*14, 62, "$" + money.toString(), Color.yellow);
		RenderEngine.pop();
	}
	
	@Override
	public void buttonPressed(GuiObjButton source)
	{
		if(source == buttons[0])
		{
			canvas.setGuiScreen(new GuiMainMenu());
		}
		else if(source == buttons[1])
		{
			canvas.setGuiScreen(new GuiGame());
		}
		else if(source == buttons[2])
		{
			data.addEntry(field.getText(), money);
			data.save();
			list.setHighlight(10);
			enableSubmission(false);
		}
	}
}
