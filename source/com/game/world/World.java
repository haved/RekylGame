package com.game.world;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import com.game.RenderEngine;
import com.game.entity.Entity;
import com.game.entity.EntityCoin;
import com.game.entity.EntityPlayer;
import com.game.entity.EntityWave;
import com.game.gui.GuiGame;
import com.game.world.WorldMap.Collumn;

public class World
{
	private GuiGame game;
	
	public EntityPlayer player;
	public EntityWave wave;
	private ArrayList<Entity> newEntities;
	private ArrayList<Entity> entities;
	public int xScroll;
	public WorldMap map;
	
	public World(GuiGame game)
	{
		this.game = game;
		
		newEntities = new ArrayList<Entity>();
		entities = new ArrayList<Entity>();
		
		player = new EntityPlayer();
		entities.add(player);
		wave = new EntityWave();
		entities.add(wave);
		
		map = new WorldMap(this);
	}
	
	public void tick()
	{
		addNewEntities();
		tickEntities();
		removeDeadEntities();
		xScroll = player.getScroll();
		loadWorld();
	}
	
	public void render()
	{
		renderWorldScreen();
		renderBar();
	}
	
	private void renderWorldScreen()
	{
		RenderEngine.push();
		GL11.glTranslatef(-xScroll, 0, 0);
		renderWorld();
		renderEntities();
		wave.customRender();
		RenderEngine.pop();
	}
	
	private void renderBar()
	{
		RenderEngine.push();
		RenderEngine.setGLColor(0.2f, 0.2f, 0.2f, 1);
		RenderEngine.fillRect(0, 512, 800, 128);
		
		RenderEngine.drawText(780 - RenderEngine.getTextLength("$" + game.money), 530,
				"$" + game.money, Color.yellow);
		
		RenderEngine.bindTexture("gui.png");
		RenderEngine.drawTexture(800 - 54, 600, player.gun.getHeatScaled(48), 8,
									200, 96, player.gun.getHeatScaled(48), 8);

		RenderEngine.pop();
	}
	
	public Tile getBlockClass(int x, int y)
	{
		return Tile.tileList[getBlock(x, y)];
	}
	
	public int getBlock(int x, int y)
	{
		if(map.getCollumn(x) != null && y < map.getCollumn(x).blocks.length & y >= 0)
		{
			return map.getCollumn(x).blocks[y];
		}
		return 0;
	}
	
	public void setBlock(byte block, int x, int y)
	{
		if(map.getCollumn(x) != null && y < map.getCollumn(x).blocks.length & y >= 0)
		{
			map.getCollumn(x).blocks[y] = block;
		}
	}
	
	public boolean hasBlockCollision(int x, int y)
	{
		return Tile.tileList[getBlock(x, y)] != null &&
				Tile.tileList[getBlock(x, y)].isSolid();
	}
	
	public boolean canBlockBreak(int x, int y)
	{
		return Tile.tileList[getBlock(x, y)] != null &&
				Tile.tileList[getBlock(x, y)].getResistance() < 100;
	}
	
	public ArrayList<Entity> getEntityList()
	{
		return entities;
	}
	
	public boolean containsEntity(Entity e)
	{
		return entities.contains(e);
	}
	
	public void addEntity(Entity e)
	{
		newEntities.add(e);
	}
	
	public void addMoney(int value)
	{
		game.money += value;
	}
	
	public void loose()
	{
		game.endGame();
	}
	
	private void addNewEntities()
	{
		for(Entity e:newEntities)
		{
			if(!entities.contains(e))
			{
				entities.add(e);
			}
		}
	}
	
	private void tickEntities()
	{
		for(Entity e:entities)
		{
			e.tick(this);
		}
	}
	
	public void testCollisionWithPlayer(Entity e)
	{
		Rectangle pBox = player.getCollisionBox();
		
		if(e instanceof EntityPlayer)
		{
			return;
		}
		else if(pBox.intersects(e.getCollisionBox()))
		{
			e.onCollisionWithPlayer(this, player);
		}
	}
	
	private void removeDeadEntities()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i).isDead())
			{
				entities.remove(i);
				i--;
			}
		}
	}
		
	private void renderWorld()
	{
		RenderEngine.resetColor();
		RenderEngine.bindTexture("background.png");
		RenderEngine.drawTexture(xScroll - xScroll % 800, 0, 800, 512, 0, 0, 256, 256);
		RenderEngine.drawTexture(xScroll + 800 - xScroll % 800, 0, 800, 512, 0, 0, 256, 256);
		
		RenderEngine.bindTexture("sprites.png");
		
		int sprite;
		
		for(int i = xScroll / 32; i < (xScroll + 800 / 32); i++)
		{
			Collumn col = map.getCollumn(i);
			for(int j = 0; col != null && j < col.blocks.length; j++)
			{				
				if((sprite = Tile.tileList[col.blocks[j]].getTexture()) < 0){continue;}
				
				RenderEngine.drawTransparentTexture(i * 32, j * 32, 32, 32,
						sprite * 16, 240, 16, 16);
			}
		}
	}
	
	private void renderEntities()
	{
		for(Entity e:entities)
		{
			e.render(this);
		}
	}

	private void loadWorld()
	{
		 if(map.hittingEdge((xScroll + 800) / 32))
		 {
			 Collumn[] col = new Collumn[20];
			 Entity[] entities = new Entity[12];
			 
			 for(int i = 0; i < 12; i++)
			 {
				 col[i] = new Collumn();
				 col[i].blocks[i] = (byte) (i % 5);
				 entities[i] = new EntityCoin(10);
				 entities[i].x = i * 32 + 32;
				 entities[i].y = i * 32;
			 }
			 map.addNewCollumns(xScroll / 32, col, entities); 
		 }
	}

}
