package com.reactive.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.reactive.repo.SSNRepository;
import com.reactive.repo.SSNRepositoryImpl;

public class SSNRouter {
	@Configuration
	public class UserRouter {
	    @Bean
	    public RouterFunction<ServerResponse> route() {
	        SSNRepository repository = new SSNRepositoryImpl();
	        SSNHandler userHandler = new SSNHandler(repository);
	        return RouterFunctions
	            .route(GET("/demo/{id}").and(accept(APPLICATION_JSON)), userHandler::getUser)
	            .andRoute(GET("/demo").and(accept(APPLICATION_JSON)), userHandler::listUser)
	            .andRoute(POST("/de mo/create").and(contentType(APPLICATION_JSON)), userHandler::createUser);
	        
	    }
	}
}
