package com.newren.controller;

import com.newren.model.ProcessResult;
import com.newren.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProcessController {
    private final ProcessService processService;
    private static final Logger log = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    /**
     * Endpoint que executa o processo 1
     * @param segundos duração simulada do processo
     * @return resultado do processo 1
     */
    @GetMapping("/processo1")
    public Flux<ProcessResult> executarProcessoUm(
            @RequestParam(name = "segundos", defaultValue = "3") int segundos) {
        log.info("Requisição recebida para processo 1 com duração de {} segundos", segundos);
        return Flux.merge(processService.executarProcessoUm(segundos));
    }

    /**
     * Endpoint que executa o processo 2
     * @param segundos duração simulada do processo
     * @return resultado do processo 2
     */
    @GetMapping("/processo2")
    public Flux<ProcessResult> executarProcesso2(
            @RequestParam(name = "segundos", defaultValue = "5") int segundos) {
        log.info("Requisição recebida para processo 2 com duração de {} segundos", segundos);
        return Flux.merge(processService.executarProcessoDois(segundos));
    }

    /**
     * Endpoint que executa os dois processos em paralelo e retorna ambos resultados
     * quando ambos completarem (demonstração)
     * @return resultado de ambos processos
     */
    @GetMapping("/executar-ambos")
    public Flux<ProcessResult> executarAmbosProcessos(
            @RequestParam(name = "segundos1", defaultValue = "3") int segundosProcesso1,
            @RequestParam(name = "segundos2", defaultValue = "5") int segundosProcesso2) {

        log.info("Requisição recebida para executar ambos processos");

        Mono<ProcessResult> processo1 = processService.executarProcessoUm(segundosProcesso1);
        Mono<ProcessResult> processo2 = processService.executarProcessoDois(segundosProcesso2);
        Mono<ProcessResult> processo3 = processService.executarProcessoUm(segundosProcesso1);
        Mono<ProcessResult> processo4 = processService.executarProcessoDois(segundosProcesso2);
        Mono<ProcessResult> processo5 = processService.executarProcessoUm(segundosProcesso1);
        Mono<ProcessResult> processo6 = processService.executarProcessoDois(segundosProcesso2);
        Mono<ProcessResult> processo7 = processService.executarProcessoUm(segundosProcesso1);
        Mono<ProcessResult> processo8 = processService.executarProcessoDois(segundosProcesso2);
        Mono<ProcessResult> processo9 = processService.executarProcessoUm(segundosProcesso1);
        Mono<ProcessResult> processo10 = processService.executarProcessoDois(segundosProcesso2);


        // O Flux.merge combina os resultados na ordem em que forem completados
        return Flux.merge(
                processo1,
                processo2,
                processo3,
                processo4,
                processo5,
                processo6,
                processo7,
                processo8,
                processo9,
                processo10);
    }
}
