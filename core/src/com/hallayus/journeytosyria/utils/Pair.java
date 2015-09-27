package com.hallayus.journeytosyria.utils;

public class Pair<U,V> {
	public U fst;
	public V snd;
	
	public Pair(U u, V v){
		this.fst = u; this.snd = v;
	}
	
	public boolean equals(Pair<U,V> pair){
		return fst.equals(pair.fst) && snd.equals(pair.snd);
	}
	
	public int hashCode()
	{
		return fst.hashCode() * snd.hashCode();
	}
	
	public String toString()
	{
		return "(" + fst.toString() + "," + snd.toString() + ")";
	}
}
