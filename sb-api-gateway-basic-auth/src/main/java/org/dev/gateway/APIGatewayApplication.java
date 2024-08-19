package org.dev.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class APIGatewayApplication {

    private static final Logger logger = LoggerFactory.getLogger(APIGatewayApplication.class);

    public static void main(String[] args) {
        logger.info("Application entry point: main method in Application class");
        SpringApplication.run(APIGatewayApplication.class, args);
    }
}