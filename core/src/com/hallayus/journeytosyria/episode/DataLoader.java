package com.hallayus.journeytosyria.episode;

import com.badlogic.gdx.graphics.Texture;
import com.hallayus.journeytosyria.map.MapViewData;

public interface DataLoader {

	public static final String IMG_URL = "imgUrl";
	public static final String DESCRIPTION = "description";
	public static final String OPTIONS = "options";
	public static final String OPTIONTEXT = "optionText";
	public static final String OPTIONID = "optionId";
	
	public void loadClipPane(int epId, int clipId, ClipLoaderListener listener);
	public void loadMapView(MapLoaderListener map);
	
	public interface ClipLoaderListener{
		public void received(ClipPaneData data, Texture text);
		public void error();
	}
	
	public interface MapLoaderListener{
		public void received(MapViewData data);
		public void error();
	}
}