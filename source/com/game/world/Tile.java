package com.game.world;

public class Tile
{
	public static Tile[] tileList = new Tile[256];
	
	public boolean isSolid;
	public int resistance = 10000;
	
	public int getBlockOnBreak()
	{
		return 0;
	}
}
