package com.reactive.repo;

import com.reactive.model.SSNDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SSNRepository{ 
	Mono<SSNDetails> getSSNById(int id);

	Flux<SSNDetails> fetchAll();

	Mono<Void> saveSSN(Mono<SSNDetails> user);
}
