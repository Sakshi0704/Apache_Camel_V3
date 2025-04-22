package com.producerservice.controller;


import com.producerservice.entity.ProducerLog;
import com.producerservice.repository.ProducerLogRepository;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producer")
public class PostController {

    private final ProducerTemplate producerTemplate;
    private final ProducerLogRepository repository;

    @Value("${kafka.route.produce}")
    private String producerRoute;

    public PostController(ProducerTemplate producerTemplate, ProducerLogRepository repository) {
        this.producerTemplate = producerTemplate;
        this.repository = repository;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendToKafka(@RequestBody String message) {
        producerTemplate.sendBody(producerRoute, message);
        return ResponseEntity.ok("Message sent to Kafka!");
    }

    @GetMapping("/logs")
    public List<ProducerLog> getLogs() {
        return repository.findAll();
    }

}
