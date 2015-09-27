package com.hallayus.journeytosyria.storyeditor;

import org.jgrapht.graph.DefaultEdge;

public class SceneLink extends DefaultEdge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final String optionText;
	public final int healthChange;
	public final int RecklessnessChange;
	
	public SceneLink(String optionText, int healthChange, int recklessnessChange)
	{
		this.optionText = optionText;
		this.healthChange = healthChange;
		this.RecklessnessChange = recklessnessChange;
	}
}
