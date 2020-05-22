package com.github.east196.uniapi.core;

public class StringInnerHandler implements InnerHandler<String, String> {

	@Override
	public String handle(String in) {
		return in;
	}

	@Override
	public Class<String> getInClass() {
		return String.class;
	}

	@Override
	public Class<String> getOutClass() {
		return String.class;
	}
	

}
