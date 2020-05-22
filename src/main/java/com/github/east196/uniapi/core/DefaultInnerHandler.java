package com.github.east196.uniapi.core;

public class DefaultInnerHandler implements InnerHandler<Request, Response> {

	@Override
	public Response handle(Request in) {
		return new Response(in.getCode(),in.toString()) ;
	}

	@Override
	public Class<Request> getInClass() {
		return Request.class;
	}

	@Override
	public Class<Response> getOutClass() {
		return Response.class;
	}
	

}
