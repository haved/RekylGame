package com.game.world;

public class Tile
{
	public static Tile[] tileList = new Tile[256];
	
	public static Tile air = new Tile(0);
	public static Tile stone = new TileRock(1);
	public static Tile rock1 = new TileBreakable(2, 1, (byte)0);
	public static Tile rock2 = new TileBreakable(3, 2, (byte)2);
	public static Tile rock3 = new TileBreakable(4, 3, (byte)3);
	
	protected boolean solid;
	protected int resistance = 100000;
	protected int sprite = -1;
	
	public Tile(int val)
	{
		tileList[val] = this;
	}
	
	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}
	
	public boolean isSolid()
	{
		return solid;
	}
	
	public void setResistance(int resistance)
	{
		this.resistance = resistance;
	}
	
	public int getResistance()
	{
		return resistance;
	}
		
	public byte getBlockOnBreak()
	{
		return 0;
	}

	public int getTexture()
	{
		return sprite;
	}
	
	public void setTexture(int sprite)
	{
		this.sprite = sprite;
	}
}
