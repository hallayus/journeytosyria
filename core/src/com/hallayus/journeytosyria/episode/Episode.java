package com.hallayus.journeytosyria.episode;

import com.badlogic.gdx.graphics.Texture;
import com.hallayus.journeytosyria.CoreGame;
import com.hallayus.journeytosyria.episode.DataLoader.ClipLoaderListener;

public class Episode {
	
	public int id;
	public ClipPane pane;
	final private DataLoader loader;
	
	private CoreGame game;
	
	private Episode(CoreGame game, ClipPaneData data, Texture texture, int epId)
	{
		this.loader = game.loader;
		this.game = game;
		id = epId;
	}
	
	public static ClipPane factory(CoreGame game, ClipPaneData data, Texture texture, int epId)
	{
		Episode ep = new Episode(game,data,texture, epId);
		ClipPane pane = new ClipPane(game,ep,texture,data.description,data.options);
		ep.pane = pane;
		return pane;
	}
	
	public void next(int id)
	{
		final Episode e = this;
		
		if(id == -1)
		{
			return;
		}
		
		game.loader.loadClipPane(this.id, id, new ClipLoaderListener() {
			
			@Override
			public void received(ClipPaneData data, Texture text) {
				ClipPane pane = Episode.factory(game, data, text, e.id);
				game.setScreen(pane);
			}
			
			@Override
			public void error() {
			
			}
		});
	}
	
	
}
