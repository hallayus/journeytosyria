package com.hallayus.journeytosyria.utils;

import com.badlogic.gdx.utils.Array;


public class Maybe<X> implements IMaybe<X> {
	public static class Function<X>
	{
		public void just(X x){}
		public void nothing(){}
	}
	
	private X val;
	private boolean nothing = true;
	
	public Maybe(X x) //value constructor
	{
		val = x;
		nothing = false;
	}
	
	public Maybe() //nothing constructor
	{
		
	}
	
	public static <T> IMaybe<T> factory(T t)
	{
		if(t != null)
		{
			return new Maybe<T>(t);
		}
		else
		{
			return new Maybe<T>();
		}
	}
	
	@Override
	public void apply(Function<X> f)
	{
		if(nothing)
		{
			f.nothing();
		}
		else
		{
			f.just(val);
		}
	}
	
	@Override
	public IMaybe<X> set(X x)
	{
		val = x;
		nothing = false;
		return this;
	}
	
	@Override
	public IMaybe<X> set()
	{
		val = null;
		nothing = true;
		return this;
	}
	
	@Override
	public boolean nothing(){return nothing;}
	
	@Override
	public String toString(){
		if(nothing)
		{
			return "Nothing";
		}
		else
		{
			return val.toString();
		}
	}
	
	//LIFT METHODS
	
	@Override
	public <V> IMaybe<V> Lift(Converter<X,V> converter)
	{
		if(nothing)
		{
			return new Maybe<V>();
		}
		else
		{
			return new Maybe<V>(converter.from(val));
		}
	}
	
	public static <T> IMaybe<T> flatten(IMaybe<IMaybe<T>> m)
	{
		final IMaybe<T> t = new Maybe<T>();
		
		m.apply(new Maybe.Function<IMaybe<T>>(){
			@Override
			public void just(IMaybe<T> x) {
				x.apply(new Maybe.Function<T>(){

					@Override
					public void just(T x2) {
						t.set(x2);
					}
					
				});
			}
		});
		
		return t;
	}
	
	public static <T> Array<T> flatten(Array<IMaybe<T>> mts)
	{
		final Array<T> ts = new Array<T>();
		
		for(IMaybe<T> mt : mts)
		{
			mt.apply(new Maybe.Function<T>(){
				@Override
				public void just(T x) {
					ts.add(x);
				}
			});
		}
		return ts;
	}
	
}
