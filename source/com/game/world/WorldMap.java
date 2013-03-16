package com.game.world;

public class WorldMap
{
	public static class Collumn
	{
		public int[] blocks;
		
		public Collumn()
		{
			blocks = new int[14];
		}
		
		public Collumn(int[] blocks)
		{
			this.blocks = blocks;
		}
	}
	
	private Collumn[] list;
	private int scroll;
	private int newscroll;
	
	public WorldMap()
	{
		list = new Collumn[40];
	}
	
	public Collumn getCollumn(int x)
	{
		x = x - scroll;
		
		if(x >= 0 & x < list.length)
		{
			return list[x];
		}
		return null;
	}

	public void removeCollumns(int cap)
	{
		cap = cap - scroll;
		for(int i = 0; i < cap; i++)
		{
			list[i] = null;
			newscroll++;
		}
	}
	
	public void addNewCollumns(Collumn[] newCollums)
	{
		Collumn[] newList = new Collumn[newCollums.length + list.length - (newscroll - scroll)];
		
		for(int i = 0; i < newList.length; i++)
		{
			if(i <  newList.length - newCollums.length)
			{
				newList[i] = list[i + newscroll];
			}
			else
			{
				newList[i] = newCollums[newList.length - newCollums.length + i];
			}
		}
		
		list = newList;
		
		scroll = newscroll;
	}
}
