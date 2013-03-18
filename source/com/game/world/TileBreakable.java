package com.game.world;

public class TileBreakable extends Tile
{
	public byte output;
	
	public TileBreakable(int val, int texture, byte output)
	{
		super(val);
		this.output = output;
		this.sprite = texture;
		this.resistance = 1;
		this.solid = true;
	}
	
	@Override
	public byte getBlockOnBreak()
	{
		return output;
	}
}
