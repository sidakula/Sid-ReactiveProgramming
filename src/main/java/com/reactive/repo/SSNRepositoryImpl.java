package com.reactive.repo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.reactive.model.Address;
import com.reactive.model.SSNDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SSNRepositoryImpl implements SSNRepository{
	Map<Integer, SSNDetails> ssnMap = new ConcurrentHashMap<Integer, SSNDetails>();
    Address address = new Address();
    public SSNRepositoryImpl(){

        ssnMap.put(1, new SSNDetails(1, "Robert", "Ludlum", "rl@rl.com", address));
        ssnMap.put(2, new SSNDetails(2, "John", "Grisham", "jg@jg.com", address));
        ssnMap.put(3, new SSNDetails(3, "James", "Patterson", "jp@jp.com", address));
    }

    @Override
    public Mono<SSNDetails> getSSNById(int id) {
        return Mono.justOrEmpty(ssnMap.get(id));
    }

    @Override
    public Flux<SSNDetails> fetchAll() {
        return Flux.fromStream(ssnMap.values().stream());
    }

    @Override
    public Mono<Void> saveSSN(Mono<SSNDetails> user) {    
        Mono<SSNDetails> userMono = user.doOnNext(value -> {
            ssnMap.put((ssnMap.keySet().size() +1), value);            
        } );
        return userMono.then();
    } 
}
