package com.game.world;

public class WorldMap
{
	public static class Collumn
	{
		public byte[] blocks;
		
		public Collumn()
		{
			blocks = new byte[14];
		}
		
		public Collumn(byte[] blocks)
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
		for(; newscroll < cap; newscroll++)
		{
			list[newscroll] = null;
		}
	}
	
	public boolean hittingEdge(int x)
	{
		x = x - scroll;
		
		return list.length - 2 < x;
	}
	
	public void addNewCollumns(Collumn[] newCollumns)
	{
		Collumn[] newList = new Collumn[newCollumns.length + list.length - (newscroll - scroll)];
		
		for(int i = 0; i < newList.length; i++)
		{
			if(i < list.length - (newscroll - scroll))
			{
				newList[i] = list[i + (newscroll - scroll)];
			}
			else
			{
				newList[i] = newCollumns[i - (list.length - (newscroll - scroll))];
			}
		}
		
		list = newList;
		scroll = newscroll;
	}
}
