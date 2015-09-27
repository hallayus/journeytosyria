package com.hallayus.journeytosyria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen implements Screen {

	private CoreGame game;

	private Stage stage;
	
	public MenuScreen(CoreGame game)
	{
		this.game = game;
		
		Skin skin = new Skin();
	    
	    Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.GRAY);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
	    
	    TextButtonStyle style = new TextButtonStyle();
	    style.font = game.font;
//	    style.fontColor = new Color(MColor.MWidgetColor.MENU_FONT.getTranslucentColor(0.6f));
//	    style.overFontColor = new Color(MColor.MWidgetColor.MENU_FONT.getTranslucentColor(0.3f));
		
		
		//main menu setup
		stage = new Stage();

	    Table mainOptions = new Table();
	    mainOptions.setFillParent(true);
	    stage.addActor(mainOptions);
	    
	    TextButton createButton = new TextButton("Create Story", style);
	    TextButton editButton = new TextButton("Edit Story", style);
	    TextButton viewButton = new TextButton("View Story", style);
	    TextButton exitButton = new TextButton("Exit", style);
	    
	    mainOptions.add(createButton).left().expandX().row();
	    mainOptions.add(viewButton).left().expandX().row();
	    mainOptions.add(editButton).left().expandX().row();
	    mainOptions.add(exitButton).left();
	    mainOptions.left().top().pad(10f);
	    
	    createButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			}
	    });
	    
	    viewButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			}
	    });
	    
	    exitButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {	
				Gdx.app.exit();
			}
	    });
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);	
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
