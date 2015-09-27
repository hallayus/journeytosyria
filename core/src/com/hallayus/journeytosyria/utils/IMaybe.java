package com.hallayus.journeytosyria.utils;

public interface IMaybe<X> {

	public void apply(Maybe.Function<X> f);

	public IMaybe<X> set(X x);

	public IMaybe<X> set();

	public boolean nothing();

	public <V> IMaybe<V> Lift(Converter<X, V> converter);
}