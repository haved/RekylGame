package com.game.world;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.game.RenderEngine;
import com.game.entity.Entity;
import com.game.entity.EntityPlayer;
import com.game.world.WorldMap.Collumn;

public class World
{
	public EntityPlayer player;
	private ArrayList<Entity> newEntities;
	private ArrayList<Entity> entities;
	public int xScroll;
	public WorldMap map;
	
	public World()
	{
		newEntities = new ArrayList<Entity>();
		entities = new ArrayList<Entity>();
		
		player = new EntityPlayer();
		entities.add(player);
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
		GL11.glTranslatef(xScroll, 0, 0);
		renderWorld();
		renderEntities();
		RenderEngine.pop();
	}
	
	private void renderBar()
	{
		RenderEngine.push();
		RenderEngine.setGLColor(0.2f, 0.2f, 0.2f, 1);
		RenderEngine.fillRect(0, 512, 800, 128);
		RenderEngine.pop();
	}
	
	public int getBlock(int x, int y)
	{
		if(map.getCollumn(x) != null)
		{
			return map.getCollumn(x).blocks[y];
		}
		return 0;
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
			e.tick();
		}
	}
	
	private void removeDeadEntities()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			
		}
	}
		
	private void renderWorld()
	{
		RenderEngine.setGLColor(1, 1, 1, 1);
		RenderEngine.fillRect(0, 0, 800, 640);
		
		RenderEngine.bindTexture("sprites.png");
	}
	
	private void renderEntities()
	{
		for(Entity e:entities)
		{
			e.render();
		}
	}

	private void loadWorld()
	{
		 map.removeCollumns(xScroll / 32);
		 if(map.hittingEdge((xScroll + 800) / 32))
		 {
			 map.addNewCollumns(new Collumn[10]);
		 }
	}
}
