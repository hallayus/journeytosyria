package com.hallayus.journeytosyria.episode;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.hallayus.journeytosyria.CoreGame;
import com.hallayus.journeytosyria.utils.Pair;

public class ClipPane implements Screen{

	private ScrollPane pane;
	
	private Table table;
	private Table container;
	
	private Stage stage;
	
	public ClipPane(CoreGame game, final Episode ep, Texture image, String description, Array<Pair<String,Integer>> options)
	{
//		OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//	    camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 0);
//	    camera.update();
		
		this.stage = new Stage();
		
	    this.container = new Table();
		container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		container.pad(20f);
	    container.setFillParent(true);

		this.table = new Table();
		//table.setFillParent(true);
	    
	    //table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	   // table.center();
	   
	    pane = new ScrollPane(table);
	    pane.setFlickScroll(true);
	    pane.layout();
	   // pane.setFillParent(true);

	    //container.row();
		
	    
	   // table.center();
	    
	    stage.addActor(container);
	    Gdx.input.setInputProcessor(stage);
	    	    

	    
	    Label.LabelStyle labelStyle = new LabelStyle(game.normalFont, Color.BLACK);
	    
	    Image img = new Image(image);
	    //img.scaleBy();
	    img.setScaling(Scaling.fit);
//	    img.setScale((Gdx.graphics.getWidth() - 40f) / img.getWidth());
	    
	    //container.add(img).row();
	    container.add(pane).fill().expand().row();

	    Label text = new Label(description,labelStyle);
	    text.setWrap(true);
	    	    
	    Gdx.app.log("ClipPane","" + image.getHeight());
	    
	    table.add(img).width(Gdx.graphics.getWidth() - 40f).height(img.getHeight()).fill().expand().row();
		table.add(text).width(Gdx.graphics.getWidth() - 40f).padTop(3f).padBottom(50f).row();
		
		//System.out.println("debug");
		
		Skin skin = new Skin();
		
	    Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(new Color(0.7f, 0.7f, 0.7f, 0.7f));
		pixmap.fill();
		skin.add("option button", new Texture(pixmap));
		
	    TextButtonStyle style = new TextButtonStyle();
	    style.font = game.optionButtonFont;
	    style.fontColor = Color.BLACK;
	    style.checkedOverFontColor = Color.GRAY;
	    style.overFontColor = Color.GRAY;
	    style.up = skin.getDrawable("option button");
		
		for(Pair<String,Integer> pair : options)
		{
			String choice = pair.fst;
			final Integer id = pair.snd;
			
			
			TextButton choiceButton = new TextButton(choice, style);
			choiceButton.getLabel().setWrap(true);
			choiceButton.align(Align.center);
			table.add(choiceButton).width(Gdx.graphics.getWidth() - 40f).padBottom(3f).padTop(3f).row();
			
			choiceButton.addListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					ep.next(id);
				}
			});
		}
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);	
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
