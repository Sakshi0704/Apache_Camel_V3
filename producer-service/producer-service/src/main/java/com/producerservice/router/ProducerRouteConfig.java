package com.producerservice.router;


import com.producerservice.entity.ProducerLog;
import com.producerservice.repository.ProducerLogRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProducerRouteConfig extends RouteBuilder {

    private final ProducerLogRepository repository;

    public ProducerRouteConfig(ProducerLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void configure() {
        from("{{kafka.route.produce}}")
                .routeId("{{kafka.route.produce.id}}")
                .log("[Producer] Sending to Kafka -> ${body}")
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    ProducerLog log = new ProducerLog();
                    log.setMessage(body);
                    log.setCreatedAt(LocalDateTime.now());
                    repository.save(log);
                })
                .to("kafka:{{kafka.topic.post}}?brokers={{kafka.bootstrap.servers}}")
                .log("[Producer] Message logged and sent to Kafka");
    }
}

