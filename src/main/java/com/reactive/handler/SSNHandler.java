package com.reactive.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.model.SSNDetails;
import com.reactive.repo.SSNRepository;

import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SSNHandler {
	private final SSNRepository ssnRepository;

	public SSNHandler(SSNRepository ssnRepository) {
		this.ssnRepository = ssnRepository;
	}
	public Mono<ServerResponse> listSSN(ServerRequest request) {
		Flux<SSNDetails> user = ssnRepository.fetchAll();
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(user, SSNDetails.class);
	}

	public Mono<ServerResponse> getSSNDetails(ServerRequest request) {
		int userId = Integer.valueOf(request.pathVariable("id"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<SSNDetails> userMono = ssnRepository.getSSNById(userId);
		return userMono.flatMap(user -> ServerResponse.ok()
				.contentType(APPLICATION_JSON)
				.body(BodyInserters.fromObject(user)))
				.switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> createSSN(ServerRequest request) {
		Mono<SSNDetails> user = request.bodyToMono(SSNDetails.class);
		UriBuilder builder = request.uriBuilder();
		return ServerResponse.created(builder.build()).build(ssnRepository.saveSSN(user));
	}
}
