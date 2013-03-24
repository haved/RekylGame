package com.game.guiObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
	
	public void load()
	{
		try
		{
			loadFile();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void save()
	{
		try
		{
			saveFile();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void loadFile() throws IOException
	{
		if(!file.exists())
		{
			file.getParentFile().mkdirs();
			file.createNewFile();
			
			resetFile();
		}
		
		try
		{
			openFile();
		}
		catch(IOException e)
		{
			System.err.println("unnable to open file:" + file.getAbsolutePath());
			e.printStackTrace();
			System.exit(0);
		}
		catch(NumberFormatException e)
		{
			System.err.println("File not readable:" + file.getAbsolutePath());
			System.err.println("Resetting highscore file");
			resetFile();
			openFile();
		}
	}
	
	private void saveFile() throws IOException
	{
		if(!file.exists())
		{
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		
		String[] lines = new String[10];
		
		for(int i = 0; i < lines.length; i++)
		{
			lines[i] = name[i] + ":" + val[i];
		}
		
		setFile(lines);
	}
	
	private void resetFile() throws IOException
	{
		setFile(new String[]{
				"HRK:1200",
				"PER:700",
				"PÅL:650",
				"ESP:590",
				"JAN:550",
				"HRK:400",
				"JON:320",
				"BRK:200",
				"OYS:140",
				"IDN:10"
				});
	}
	
	@SuppressWarnings("resource")
	private void openFile() throws NumberFormatException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line;
		int lineNumber = 0;
		while((line = reader.readLine()) != null)
		{
			if(lineNumber < 10)
			{
				System.out.println(line);
				name[lineNumber] = line.split(":")[0];
				val[lineNumber] = Integer.parseInt(line.split(":")[1]);
				lineNumber++;
			}
			else
			{
				break;
			}
		}
		
		if(lineNumber < 10)
		{
			throw new NumberFormatException("File was too short:" + file.getAbsolutePath());
		}
		
		reader.close();
	}
	
	private void setFile(String[] lines) throws IOException
	{
		PrintWriter out = new PrintWriter(file);
		
		for(String s:lines)
		{
			out.println(s);
		}
		
		out.flush();
		out.close();
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
