package com.reactive.repo;

import com.reactive.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SSNRepository{ 
	Mono<User> getUserById(int id);

	Flux<User> getAllUsers();

	Mono<Void> saveUser(Mono<User> user);
}
