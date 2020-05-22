package com.github.east196.uniapi.core;
public interface InnerHandler<In,Out> {
	
	Out handle(In in);

	Class<In> getInClass();
	
	Class<Out> getOutClass();
	
}