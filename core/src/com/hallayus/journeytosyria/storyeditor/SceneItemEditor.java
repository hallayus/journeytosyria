package com.hallayus.journeytosyria.storyeditor;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class SceneItemEditor implements Screen{
	
	private String title;
	private ScrollPane pane;
	
	private Table table;
	private Table container;
	
	
	public SceneItemEditor() {
		pane = new ScrollPane(null);

		this.table = new Table();
	    this.container = new Table();
	    pane = new ScrollPane(table);
	    container.add(pane).width(500f).height(500f);
	    container.row();
	    
	    
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {
	
	}

	@Override
	public void pause() {
	
	}

	@Override
	public void resume() {
	
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {		
	}
	
}
