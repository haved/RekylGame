package com.game.guiObject;

import java.io.File;
import java.io.IOException;

public class HighscoreData
{
	private File file;
	private String[] name;
	private int[] val;
	
	public HighscoreData(String s)
	{
		file = new File(s);
		name = new String[10];
		val = new int[10];
	}
	
	public void loadFile()
	{
		if(!file.exists())
		{
			try
			{
				file.mkdirs();
				file.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	
	public String getNameAt(int i)
	{
		return name[i];
	}
	
	public int getValueAt(int i)
	{
		return val[i];
	}
}
