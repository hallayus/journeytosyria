package com.hallayus.journeytosyria.android;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.firebase.client.Firebase;
import com.hallayus.journeytosyria.Platform;

import android.content.Context;

public class AndroidPlatform extends Platform{
	private Context con;
	
	public AndroidPlatform(Context context)
	{
		con = context;
		Firebase.setAndroidContext(context);
	}
	
	@Override
	public BitmapFont getNormalFont() {
		return getFont(56);
	}

	@Override
	public BitmapFont getTitleFont() {
		return getFont(256);
	}

	@Override
	public BitmapFont getOptionButtonFont() {
		return getFont(80);
	}

}
