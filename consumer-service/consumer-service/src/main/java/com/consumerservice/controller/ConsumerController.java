package com.consumerservice.controller;

import com.consumerservice.entity.ConsumerLog;
import com.consumerservice.repository.ConsumerLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    private final ConsumerLogRepository repository;

    public ConsumerController(ConsumerLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/logs")
    public List<ConsumerLog> getLogs() {
        return repository.findAll();
    }

}

