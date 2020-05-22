package com.github.east196.uniapi.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.boon.Boon;

import com.github.east196.uniapi.core.Request;

public class LogicClient {
	
	  private static final String HOSTNAME = "localhost";
	
	  private static final int PORT = 10056;
	
	  private static final long CONNECT_TIMEOUT = 30*1000L; // 30 seconds

	
	public static void main(String[] args) throws Throwable {
	    NioSocketConnector connector = new NioSocketConnector();
	    connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
	    connector.getFilterChain().addLast("logger", new LoggingFilter());
	    connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
	    connector.setHandler(new MinaClientHandler());
	    IoSession session;

	    for (;;) {
	        try {
	            ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
	            future.awaitUninterruptibly();
	            session = future.getSession();
	            for (int i = 0; i <10; i++) {
	            	 Request request = new Request();
	            	 request.setCode(i+"");
					session.write(Boon.toJson(request));
				}
	           
	            break;
	        } catch (RuntimeIoException e) {
	            e.printStackTrace();
	            Thread.sleep(5000);
	        }
	    }

	    // wait until the summation is done
	    session.getCloseFuture().awaitUninterruptibly();
	    connector.dispose();
	}
}
