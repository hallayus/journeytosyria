package com.hallayus.journeytosyria.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hallayus.journeytosyria.CoreGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.width = 640;
		config.height = 640;
		config.x = -1;
		config.y = -1;
		new LwjglApplication(new CoreGame(new DesktopFont()), config);
	}
}
