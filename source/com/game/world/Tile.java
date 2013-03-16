package com.game.world;

public class Tile
{
	public static Tile[] tileList = new Tile[256];
	
	public static Tile air = new Tile(0);
	public static Tile rock = new TileRock(1);
	
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
		
	public int getBlockOnBreak()
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
