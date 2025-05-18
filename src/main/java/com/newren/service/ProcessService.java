package com.newren.service;

import com.newren.model.ProcessResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Slf4j
public class ProcessService {

    private static final Logger log = LoggerFactory.getLogger(ProcessService.class);

    /**
     * Executa o processo 1 de forma não bloqueante
     * @param segundos tempo para simular processamento
     * @return resultado do processo
     */
    public Mono<ProcessResult> executarProcessoUm(int segundos) {
        String processId = "[ Processo 1 ] - " + UUID.randomUUID().toString().substring(0, 8);
        log.info("Iniciando processo 1: {}", processId);

        long startTime = System.currentTimeMillis();

        return Mono.delay(Duration.ofMillis(segundos))
                .map(
                        i -> {
                            long executionTime = System.currentTimeMillis() - startTime;
                            String response = "[Processo-1-"+i+"] concluído: "+processId+" em "+executionTime+" segundos";
                            log.info(response);
                            return new ProcessResult(
                                    processId,
                                    response,
                                    executionTime
                            );
                        });
    }

    /**
     * Executa o processo 2 de forma não bloqueante
     * @param segundos tempo para simular processamento
     * @return resultado do processo
     */
    public Mono<ProcessResult> executarProcessoDois(int segundos) {
        String processId = "Processo-2-" + UUID.randomUUID().toString().substring(0, 8);
        log.info("Iniciando processo 2: {}", processId);

        long startTime = System.currentTimeMillis();

        return Mono.delay(Duration.ofMillis(segundos))
                .map(i -> {
                    long executionTime = System.currentTimeMillis() - startTime;
                    String response = "[Processo-2-"+i+"] concluído: "+processId+" em "+executionTime+" segundos";
                    log.info(response);
                    return new ProcessResult(
                            processId,
                            response,
                            executionTime
                    );
                });
    }


}
