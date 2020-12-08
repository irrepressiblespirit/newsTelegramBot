package com.skibnev.telegrambot.newstelegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.Collection;

@Service
public class ServiceRunner {

    private Collection<ServiceHelper> services;

    @Autowired
    public ServiceRunner(Collection<ServiceHelper> services) {
        this.services = services;
    }

    public Mono<String> run(String currentLocation) {
        return Flux.fromIterable(services)
                .parallel(services.size(), Integer.MAX_VALUE)
                .runOn(Schedulers.elastic())
                .flatMap(serviceHelper -> ParallelFlux.from(Flux.just(serviceHelper.getInfo(currentLocation))))
                .reduce((response, response2) -> {
                    StringBuilder allResponses = new StringBuilder();
                    allResponses.append(response);
                    allResponses.append(response2);
                    return allResponses.toString();
                });
    }
}
