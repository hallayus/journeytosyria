package com.hallayus.journeytosyria.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.hallayus.journeytosyria.Platform;

public class DesktopFont extends Platform {


	@Override
	public BitmapFont getNormalFont() {
		return getFont(12);
	}

	@Override
	public BitmapFont getTitleFont() {
		return getFont(144);
	}

	@Override
	public BitmapFont getOptionButtonFont() {
		return getFont(14);
	}

}
