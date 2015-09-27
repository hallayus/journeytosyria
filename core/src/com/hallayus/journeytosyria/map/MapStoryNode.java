package com.hallayus.journeytosyria.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class MapStoryNode {
	public float x;
	public float y;
	public int id;
	
	private long startTime;
	private Circle circle;
	
	public MapStoryNode()
	{
		startTime = TimeUtils.millis();
		circle = new Circle();
	}
	
	public void render(ShapeRenderer renderer)
	{
		long time = TimeUtils.millis();
		float sinVal = MathUtils.sin((float)(time - startTime) / 500) / 2 + 0.5f;
		
		renderer.set(ShapeType.Filled);
		renderer.setColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, sinVal);
		renderer.circle(x, y, 10f);
		
		renderer.setColor(Color.BLUE.r, Color.BLUE.g, Color.BLUE.b, sinVal);
		renderer.circle(x, y, 8f);		
	}
	
	public boolean hit(float x, float y)
	{
		circle.x = this.x;
		circle.y = this.y;
		circle.radius = 10f;
		return circle.contains(x,y);
	}
}
