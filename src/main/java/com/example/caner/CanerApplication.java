package com.example.caner;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CanerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanerApplication.class, args);
    }

    @Value("${myqueue}")
    String queue;

    @Bean
    Queue queue() {
        return new Queue(queue, false);
    }

    @Bean
    CommandLineRunner sender(Producer producer) {
        return args -> {
            producer.sendTo(queue, "Hello World");
        };
    }
}


