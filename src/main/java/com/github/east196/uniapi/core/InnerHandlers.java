package com.github.east196.uniapi.core;

public class InnerHandlers {
	
	@SuppressWarnings("unchecked")
	public static <In,Out> InnerHandler<In,Out> getHandler(){
		InnerHandler<In, Out> handler=null;
		handler=(InnerHandler<In, Out>) new DefaultInnerHandler();
		return handler;
	}

}
