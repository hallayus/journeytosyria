package com.hallayus.journeytosyria.episode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.hallayus.journeytosyria.map.MapViewData;
import com.hallayus.journeytosyria.utils.Pair;

public class ClipHttpLoader implements DataLoader {
	public static final String SERVER_URL = "http://localhost:1209";


	/* (non-Javadoc)
	 * @see com.hallayus.journeytosyria.episode.ClipGeneralLoader#loadClipPane(int, com.hallayus.journeytosyria.episode.ClipLoader.ClipLoaderListener)
	 */
	@Override
	public void loadClipPane(int epId, int clipId, final ClipLoaderListener listener){		
		HttpRequest httpRequest = new HttpRequest(Net.HttpMethods.GET);
		httpRequest.setUrl(SERVER_URL);
		httpRequest.setContent("id=" + epId);
		Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener(){

			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				String jsonString = httpResponse.getResultAsString();
				JsonValue val = new JsonReader().parse(jsonString);
				final ClipPaneData clipPaneData = jsonDecoder(val);
				
				HttpRequest httpRequest = new HttpRequest(Net.HttpMethods.GET);
				httpRequest.setUrl(clipPaneData.imgUrl);
				httpRequest.setContent(null);
				Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {
					
					@Override
					public void handleHttpResponse(HttpResponse httpResponse) {
						final byte[] rawImageBytes = httpResponse.getResult();
						Gdx.app.postRunnable(new Runnable() {
							public void run () {
								Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
								Texture texture = new Texture(pixmap);
								listener.received(clipPaneData,texture);
							}
						});
					}
					
					@Override
					public void failed(Throwable t) {
						listener.error();
					}
					
					@Override
					public void cancelled() {
						listener.error();
					}
				});
			}

			@Override
			public void failed(Throwable t) {
				listener.error();
			}

			@Override
			public void cancelled() {
				listener.error();
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
	public void loadMapView(MapLoaderListener map) {
		// TODO Auto-generated method stub
		
	}

	
	
}
