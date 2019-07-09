package com.reactive.handler;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import com.reactive.model.User;
import com.reactive.repo.SSNRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SSNHandler {
	private final SSNRepository ssnRepository;

	public SSNHandler(SSNRepository ssnRepository) {
		this.ssnRepository = ssnRepository;
	}
	public Mono<ServerResponse> listUser(ServerRequest request) {
		Flux<User> user = ssnRepository.getAllUsers();
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(user, User.class);
	}

	public Mono<ServerResponse> getUser(ServerRequest request) {
		int userId = Integer.valueOf(request.pathVariable("id"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<User> userMono = ssnRepository.getUserById(userId);
		return userMono.flatMap(user -> ServerResponse.ok()
				.contentType(APPLICATION_JSON)
				.body(BodyInserters.fromObject(user)))
				.switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> createUser(ServerRequest request) {
		System.out.println("in create user");
		Mono<User> user = request.bodyToMono(User.class);
		return ServerResponse.ok().build(ssnRepository.saveUser(user));
	}
}
