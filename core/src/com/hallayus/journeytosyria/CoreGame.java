package com.hallayus.journeytosyria;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.hallayus.journeytosyria.episode.DataLoader;
import com.hallayus.journeytosyria.episode.FileDataLoader;
import com.hallayus.journeytosyria.episode.DataLoader.MapLoaderListener;
import com.hallayus.journeytosyria.map.MapView;
import com.hallayus.journeytosyria.map.MapViewData;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class CoreGame extends Game {

	//fonts
	private Platform platformFont;
	
	public BitmapFont titleFont;
	public BitmapFont normalFont;
	public BitmapFont optionButtonFont;
	
	public DataLoader loader;
	
	public CoreGame(Platform platformFont){
		this.platformFont = platformFont;
		
	}
	
	@Override
	public void create() {
		//font handling
	    titleFont = platformFont.getTitleFont();
	    normalFont = platformFont.getNormalFont();
	    optionButtonFont = platformFont.getOptionButtonFont();
	    
	    loader = new FileDataLoader();
	    
		Gdx.gl.glEnable(GL20.GL_BLEND);

	    final CoreGame game = this;
		game.loader.loadMapView(new MapLoaderListener() {
			
			@Override
			public void received(MapViewData data) {
				MapView view = new MapView(data,game);
				game.setScreen(view);
			}
			
			@Override
			public void error() {}
		});
	}
	
	@Override
	public void dispose() {
		titleFont.dispose();
	}	
	

	@Override
	public void render() {
		super.render();
	}

}
