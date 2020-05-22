package com.github.east196.uniapi.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.boon.Boon;

import com.github.east196.uniapi.core.InnerHandler;
import com.github.east196.uniapi.core.InnerHandlers;

public class MinaServerHandler<In, Out> extends IoHandlerAdapter {
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("Now Client:" + session);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String jsonMessage = (String) message;
		System.out.println(jsonMessage);
		InnerHandler<In, Out> innerHandler = InnerHandlers.getHandler();
		System.out.println(innerHandler);
		In in = Boon.fromJson(jsonMessage, innerHandler.getInClass());
		System.out.println(innerHandler);
		Out out = innerHandler.handle(in);
		session.write(out + new Date().toString());
		System.out.println("Message written...");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("IDLE " + session.getIdleCount(status));
	}
}