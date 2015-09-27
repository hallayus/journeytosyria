package com.hallayus.journeytosyria.storyeditor;

import org.jgrapht.graph.DirectedMultigraph;

public class SceneFlow {
	private DirectedMultigraph<SceneItem,SceneLink> graph;
	
	public SceneFlow()
	{
		graph = new DirectedMultigraph<SceneItem, SceneLink>(SceneLink.class);
	}
	
	public void connect(SceneItem from, SceneItem to, SceneLink link)
	{
		graph.addEdge(from, to, link);
	}
	
	public void remove(SceneLink link)
	{
		graph.removeEdge(link);
	}
	
}
