package com.reactive.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.repo.SSNRepository;
import com.reactive.repo.SSNRepositoryImpl;

import reactor.core.publisher.Flux;

@Controller
public class SSNController {
	SSNRepository repository = new SSNRepositoryImpl();
    SSNHandler service = new SSNHandler(repository);

	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/flights")
	@ResponseBody
	Flux<ServerResponse> flights(){
		return Flux.from(service.listSSN(null));

	}
}
