package com.game.world;

import java.util.HashMap;

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
	
	private HashMap<Integer, Collumn> list;
	
	public WorldMap()
	{
		list = new HashMap<Integer, Collumn>();
	}
	
	public Collumn getCollumn(int x)
	{
		if(list.containsKey(x))
		{
			return list.get(x);
		}
		else
		{
			return new Collumn();
		}
	}
}
