package com.github.east196.uniapi.mvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.east196.uniapi.core.InnerHandler;
import com.github.east196.uniapi.core.InnerHandlers;
import com.github.east196.uniapi.core.Response;
import com.github.east196.uniapi.core.Request;

@RestController
@SpringBootApplication
public class MainController<In,Out>{
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/mini")
	public Response mini(@RequestBody Request request){
		InnerHandler<In, Out> innerHandler = InnerHandlers.getHandler();
		return (Response) innerHandler.handle((In) request);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MainController.class, args);
	}

}