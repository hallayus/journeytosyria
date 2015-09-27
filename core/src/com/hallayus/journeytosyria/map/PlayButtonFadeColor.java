package com.hallayus.journeytosyria.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;

public class PlayButtonFadeColor extends Color{
	
	public void fade(float time)
	{
		final float acopy = a;
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				setAlpha(a - acopy/24f);
			}
		}, 0, (time/24f), 24);
	}
	
	public void setAlpha(float val)
	{
		a = val;
	}
	
}
