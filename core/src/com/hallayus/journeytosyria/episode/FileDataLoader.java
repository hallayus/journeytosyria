package com.hallayus.journeytosyria.episode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.hallayus.journeytosyria.map.MapStoryNode;
import com.hallayus.journeytosyria.map.MapViewData;
import com.hallayus.journeytosyria.utils.Pair;

public class FileDataLoader implements DataLoader{
	private static String INDEX_FILE = "index.json";
	private static final String MAP_IMG_LOC = "map.png";

	
	@Override
	public void loadClipPane(int epId, int clipId, final ClipLoaderListener listener) {
		FileHandle handle = Gdx.files.internal(INDEX_FILE);
		JsonValue index = new JsonReader().parse(handle);
		
		String epPath = index.get("" + epId).get("path").asString();
		
		FileHandle epHandle = Gdx.files.internal(epPath);
		JsonValue val = new JsonReader().parse(epHandle).get(clipId + "");
		final ClipPaneData clipPaneData = jsonDecoder(val);
		
		FileHandle imageHandle = Gdx.files.internal(clipPaneData.imgUrl);
		
		final byte[] rawImageBytes = imageHandle.readBytes();
		Gdx.app.postRunnable(new Runnable() {
			public void run () {
				Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
				Texture texture = new Texture(pixmap);
				listener.received(clipPaneData,texture);
				Gdx.app.log("loader","loading clip with img: " + clipPaneData.imgUrl + " and description: " + clipPaneData.description);
			}
		});
	}

	private ClipPaneData jsonDecoder(JsonValue val)
	{
		ClipPaneData data = new ClipPaneData();
		data.imgUrl = val.get(IMG_URL).asString();
		data.description = val.get(DESCRIPTION).asString();
		data.options = new Array<Pair<String,Integer>>();
		
		JsonValue optionList = val.get(OPTIONS);
		for(JsonValue option : optionList)
		{
			String optionText = option.get(OPTIONTEXT).asString();
			int optionId = option.get(OPTIONID).asInt();
			data.options.add(new Pair<String,Integer>(optionText,optionId));
		}
		return data;
	}

	@Override
	public void loadMapView(final MapLoaderListener listener) {
		FileHandle handle = Gdx.files.internal(INDEX_FILE);
		JsonValue index = new JsonReader().parse(handle);
		
		Array<MapStoryNode> nodes = new Array<MapStoryNode>();
		for(int i = 1; i <= index.size; i++)
		{
			JsonValue clip = index.get("" + i);
			MapStoryNode node = new MapStoryNode();
			
			//System.out.println(i + " " + clip.toString());
			
			node.id = i;
			node.x = clip.get("x").asFloat();
			node.y = clip.get("y").asFloat();
			nodes.add(node);
		}
		
		final MapViewData data = new MapViewData();
		data.nodes = nodes;
		
		
		FileHandle imgHandle = Gdx.files.internal(MAP_IMG_LOC);
		final byte[] rawImageBytes = imgHandle.readBytes();
		Gdx.app.postRunnable(new Runnable() {
			public void run () {
				Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
				data.background = new Texture(pixmap);
				listener.received(data);
			}
		});
		
		
	}
}
