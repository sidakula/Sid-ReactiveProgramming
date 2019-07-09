package com.reactive.repo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.reactive.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SSNRepositoryImpl implements SSNRepository{
	Map<Integer, User> userMap = new ConcurrentHashMap<Integer, User>(); 
    public SSNRepositoryImpl(){
        // adding entries to Map
        userMap.put(1, new User(1, "Robert", "Ludlum", "rl@rl.com"));
        userMap.put(2, new User(2, "John", "Grisham", "jg@jg.com"));
        userMap.put(3, new User(3, "James", "Patterson", "jp@jp.com"));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(userMap.get(id));
    }

    @Override
    public Flux<User> getAllUsers() {
        // get as stream
        return Flux.fromStream(userMap.values().stream());
    }

    @Override
    public Mono<Void> saveUser(Mono<User> user) {    
        Mono<User> userMono = user.doOnNext(value->{
            userMap.put((userMap.keySet().size() +1), value);            
        } );
        return userMono.then();
    } 
}
