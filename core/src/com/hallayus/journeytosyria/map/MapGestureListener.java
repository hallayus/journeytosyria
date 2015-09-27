package com.hallayus.journeytosyria.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.hallayus.journeytosyria.episode.ClipPane;
import com.hallayus.journeytosyria.episode.ClipPaneData;
import com.hallayus.journeytosyria.episode.DataLoader.ClipLoaderListener;
import com.hallayus.journeytosyria.episode.Episode;

import javafx.scene.chart.PieChart.Data;

public class MapGestureListener implements GestureListener{

	private MapView view;
	private static float minZoom = 0.3f, maxZoom = 2.5f;
	
	private float minX, minY, maxX, maxY;
	
	private Vector3 touchPos;
	
	public MapGestureListener(MapView v) {
		view = v;
		minX = v.camera.viewportWidth * v.camera.zoom * 0.5f;
		minY = v.camera.viewportHeight * v.camera.zoom * 0.5f;
		maxX = v.data.background.getWidth() - minX;
		maxY = v.data.background.getHeight() - minY;
		
		touchPos = new Vector3(0,0,0);
	}
	
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		touchPos.x = x;
		touchPos.y = y;
		touchPos.z = 0;
		
		view.camera.unproject(touchPos);
		
		
		for(final MapStoryNode node : view.data.nodes)
		{
		
			
			if(node.hit(touchPos.x, touchPos.y))
			{
				view.game.loader.loadClipPane(node.id, 1, new ClipLoaderListener() {
					
					@Override
					public void received(ClipPaneData data, Texture text) {
						ClipPane firstPane = Episode.factory(view.game, data, text, node.id);
						view.game.setScreen(firstPane);
					}
					
					@Override
					public void error() {
					
					}
				});
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
//		if(rect.contains(view.camera.position.x, view.camera.position.y))
//		{
		
		if(deltaX < 0 && view.camera.position.x - deltaX < maxX)
		{
			view.camera.position.add(-deltaX,0,0);
		}
		
		if(deltaX > 0 && view.camera.position.x - deltaX > minX)
		{
			view.camera.position.add(-deltaX,0,0);
		}
		
		if(deltaY > 0 && view.camera.position.y + deltaY < maxY)
		{
			view.camera.position.add(0,deltaY,0);
		}
		
		if(deltaY < 0 && view.camera.position.y + deltaY > minY)
		{
			view.camera.position.add(0,deltaY,0);
		}
		
			//view.camera.position.add(-deltaX,deltaY,0);
//		}
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		
		//check for zoom in
//		if(view.camera.zoom < maxZoom && distance > initialDistance)
//		{
//			view.camera.zoom *= 1.1f;
//		}
//		//check for zoom out
//		if(view.camera.zoom > minZoom && distance < initialDistance)
//		{
//			view.camera.zoom *= 0.9f;
//		}
		
		
		
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
