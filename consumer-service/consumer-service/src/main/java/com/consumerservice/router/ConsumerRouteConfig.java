package com.consumerservice.router;

import com.consumerservice.entity.ConsumerLog;
import com.consumerservice.repository.ConsumerLogRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ConsumerRouteConfig extends RouteBuilder {

    private final ConsumerLogRepository repository;

    public ConsumerRouteConfig(ConsumerLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void configure() throws Exception {

        /* Exception handling logic: retry 3 times on any exception with a 2-second delay */
        onException(Exception.class)
                .maximumRedeliveries(3)
                .redeliveryDelay(2000)
                .retryAttemptedLogLevel(LoggingLevel.WARN)
                .log(LoggingLevel.ERROR, "[Consumer] Retrying due to error: ${exception.message}")
                .handled(true);


        from("kafka:{{kafka.topic.post}}?brokers={{kafka.bootstrap.servers}}")
                .routeId("{{kafka.route.consume.id}}")
                .log("[Consumer] Received from Kafka: ${body}")
                .process(exchange -> {
                    String message = exchange.getIn().getBody(String.class);
                    ConsumerLog status = new ConsumerLog();
                    status.setMessage(message);
                    status.setReceivedAt(LocalDateTime.now());
                    status.setStatus("DELIVERED");
                    if (message.contains("fail")) {
                        throw new RuntimeException("Simulated error for testing retry logic");
                    }
                    repository.save(status);
                })
                .log("[Consumer] Saved message to DB with status DELIVERED");
    }
}
