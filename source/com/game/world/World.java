package com.game.world;

import java.util.ArrayList;

import com.game.Main;
import com.game.RenderEngine;
import com.game.entity.Entity;
import com.game.entity.EntityPlayer;

public class World
{
	public EntityPlayer player;
	private ArrayList<Entity> newEntities;
	private ArrayList<Entity> entities;
	
	public World()
	{
		newEntities = new ArrayList<Entity>();
		entities = new ArrayList<Entity>();
		
		player = new EntityPlayer();
		entities.add(player);
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
	
	public void tick()
	{
		addNewEntities();
		tickEntities();
		removeDeadEntities();
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
	
	public void render()
	{
		renderWorld();
		renderEntities();
	}
	
	private void renderWorld()
	{
		RenderEngine.setGLColor(1, 1, 1, 1);
		Main.renderEngine.fillRect(0, 0, 800, 640);
	}
	
	private void renderEntities()
	{
		for(Entity e:entities)
		{
			e.render();
		}
	}
}
