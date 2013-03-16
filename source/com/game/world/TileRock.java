package com.game.world;

public class TileRock extends Tile
{
	public TileRock(int val)
	{
		super(val);
		setSolid(true);
		setResistance(100000);
		setTexture(0);
	}
}
