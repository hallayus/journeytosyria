package com.hallayus.journeytosyria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public abstract class Platform {
	public abstract BitmapFont getNormalFont();
	public abstract BitmapFont getTitleFont();
	public abstract BitmapFont getOptionButtonFont();
	
	protected BitmapFont getFont(int i)
	{
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DejaVuSans.ttf"));
	    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	    parameter.size = i;
	    BitmapFont font = generator.generateFont(parameter);
	    generator.dispose();
	    
	    return font;
	}
	
}
