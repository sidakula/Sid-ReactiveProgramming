package com.reactive.handler;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.reactive.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClient {
	private org.springframework.web.reactive.function.client.WebClient client = org.springframework.web.reactive.function.client.WebClient.create("http://localhost:8080");
    // For getting all users
    private Mono<ClientResponse> result = client.get()
            .uri("/demo")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange();
    
    // Getting user by ID
    private Mono<User> singleUser = client.get()
            .uri("/demo/1")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .flatMap(res -> res.bodyToMono(User.class));
    

    public List<User> getResult() {
            Flux<User> userList = result.flatMapMany(res -> res.bodyToFlux(User.class));
            return userList.collectList().block();
    }
}
