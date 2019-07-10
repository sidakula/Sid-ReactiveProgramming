package com.reactive.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.repo.SSNRepository;
import com.reactive.repo.SSNRepositoryImpl;

@Configuration
public class SSNRouter {
	    @Bean
	    public RouterFunction<ServerResponse> route() {
	        SSNRepository repository = new SSNRepositoryImpl();
	        SSNHandler ssnHandler = new SSNHandler(repository);
	        return RouterFunctions
	            .route(GET("/reactive/{id}").and(accept(APPLICATION_JSON)), ssnHandler::getSSNDetails)
	            .andRoute(GET("/reactive").and(accept(APPLICATION_JSON)), ssnHandler::listSSN)
	            .andRoute(POST("/reactive/create").and(contentType(APPLICATION_JSON)), ssnHandler::createSSN);
	        
	    }
}
