package com.hallayus.journeytosyria.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Timer;
import com.hallayus.journeytosyria.CoreGame;
import com.hallayus.journeytosyria.episode.ClipPane;
import com.hallayus.journeytosyria.episode.ClipPaneData;
import com.hallayus.journeytosyria.episode.Episode;
import com.hallayus.journeytosyria.episode.DataLoader.ClipLoaderListener;

public class MapView implements Screen {

	private ShapeRenderer renderer;
	private SpriteBatch batch;
	
	public MapViewData data;
	public OrthographicCamera camera;
	
	final public CoreGame game;
	
	public Sprite spr;
	
	private Stage stage;

	private	final BackgroundPanner panner;
	
	public MapView(MapViewData d, final CoreGame game)
	{
		
		data = d;
		this.game = game;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	
		renderer = new ShapeRenderer();
		batch = new SpriteBatch();
		renderer.setAutoShapeType(true);

		camera.position.set(d.background.getWidth() / 2, d.background.getHeight() / 2, 10);
		camera.zoom = 0.4f;
		
		spr = new Sprite(data.background);
		

//	    style.fontColor = new Color(MColor.MWidgetColor.MENU_FONT.getTranslucentColor(0.6f));
//	    style.overFontColor = new Color(MColor.MWidgetColor.MENU_FONT.getTranslucentColor(0.3f));
		
		
		//main menu setup
		TextButtonStyle style = new TextButtonStyle();
	    style.font = game.titleFont;
	    final PlayButtonFadeColor col = new PlayButtonFadeColor();
	    style.fontColor = new Color(Color.BLACK);
	    style.fontColor.a = 0.5f;
	    style.overFontColor = col.set(Color.GRAY);
	    style.overFontColor.a = 0.5f;
	    
		stage = new Stage();
		//stage.setDebugAll(true);
		Gdx.input.setInputProcessor(stage);
		
	    final Table mainOptions = new Table();
	    mainOptions.setFillParent(true);
	    stage.addActor(mainOptions);

	    TextButton playButton = new TextButton("Play", style);
	    
	    mainOptions.add(playButton).center().row();
		
	    Gdx.input.setInputProcessor(stage);
	    
	    panner = new BackgroundPanner(this);
	    panner.start();
	    
		final MapView view = this;
	    playButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.input.setInputProcessor(new GestureDetector(new MapGestureListener(view)));
				panner.stop();
				col.fade(1f);
				Timer.schedule(new Timer.Task() {
					
					@Override
					public void run() {
						mainOptions.setVisible(false);
					}
				}, 1f);
			}
	    });
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);	
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		panner.update(delta);
		
		camera.update();
		batch.enableBlending();

		batch.setProjectionMatrix(camera.combined);
		renderer.setProjectionMatrix(camera.combined);	
		
		batch.begin();

		spr.draw(batch, 0.5f);
		//batch.draw(data.background, 0, 0);
		
		batch.end();
		
		renderer.begin();
		
		renderer.setColor(Color.GRAY);
		renderer.set(ShapeType.Filled);
		for(int i = 1; i < data.nodes.size; i++)
		{
			MapStoryNode n1 = data.nodes.get(i - 1);
			MapStoryNode n2 = data.nodes.get(i);
			renderer.rectLine(n1.x, n1.y, n2.x, n2.y, 3f);
		}
		
		for(MapStoryNode node : data.nodes)
		{
			node.render(renderer);
		}
		renderer.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
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
		renderer.dispose();
		batch.dispose();
		data.background.dispose();
	}

}
