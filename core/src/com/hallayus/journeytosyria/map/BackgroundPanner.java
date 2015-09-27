package com.hallayus.journeytosyria.map;

import com.badlogic.gdx.utils.Timer;
import com.hallayus.journeytosyria.utils.Mutable;

public class BackgroundPanner {
	private MapView view;
	private boolean moving;
	private Direction dir;
	
	final private Mutable<Float> speed;
	
	private enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT;
	}
	
	private float minX, minY, maxX, maxY;
	
	public BackgroundPanner(MapView view)
	{
		this.view = view;
		
		speed = new Mutable<Float>(50f);
		
		minX = view.camera.viewportWidth * view.camera.zoom * 0.5f;
		minY = view.camera.viewportHeight * view.camera.zoom * 0.5f;
		maxX = view.data.background.getWidth() - minX;
		maxY = view.data.background.getHeight() - minY;
		
		dir = Direction.UP;
	}
	
	public void start()
	{
		moving = true;
	}
	
	public void stop()
	{
		Timer.schedule(new Timer.Task() {
			
			@Override
			public void run() {
				speed.value *= 0.95f;
			}
		}, 0, 1f/24f, 24);
		
		Timer.schedule(new Timer.Task() {
			
			@Override
			public void run() {
				moving = false;				
			}
		}, 1.5f);
	}
	
	public void update(float delta)
	{
		if(moving)
		{
			float dist = delta * speed.value;
			
			if(view.camera.position.x > maxX - dist) //moving right
			{
				view.camera.position.add(-dist,0,0);
				dir = Direction.DOWN;
			}else if(view.camera.position.x  < minX + dist)
			{
				view.camera.position.add(dist,0,0);
				dir = Direction.UP;
			}else if(view.camera.position.y > maxY - dist)
			{
				view.camera.position.add(0,-dist,0);

				dir = Direction.RIGHT;
			}
			else if(view.camera.position.y < minY + dist)
			{
				view.camera.position.add(0,dist,0);
				dir = Direction.LEFT;	
			}
			
			switch(dir)
			{
			case UP: view.camera.position.add(0,dist,0); break;
			case DOWN: view.camera.position.add(0,-dist,0); break;
			case LEFT: view.camera.position.add(-dist,0,0); break;
			case RIGHT: view.camera.position.add(dist,0,0); break;
			}
		}
	}
}
