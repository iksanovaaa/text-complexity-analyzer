package com.textcomplexityanalyzer.analyzer;

import gate.CorpusController;
import gate.Gate;
import gate.util.GateException;
import gate.util.GateRuntimeException;
import gate.util.persistence.PersistenceManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

@SpringBootApplication
public class AnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyzerApplication.class, args);
	}

    @Bean
    public CorpusController corpusController() throws GateException, URISyntaxException, IOException {
        Gate.init();

        var resource = Optional.ofNullable(getClass().getClassLoader().getResource("gate/corpus-pipeline.xml"))
                .orElseThrow(() -> new GateRuntimeException("GATE program not found"));

        return (CorpusController) PersistenceManager.loadObjectFromUrl(resource);
    }
}

