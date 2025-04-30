package com.seasons.springyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.File; // << You forgot this import

@SpringBootApplication
public class SpringyouApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringyouApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> staticResourceRouter() {
        System.out.println("Spring Framework Version: " + SpringVersion.getVersion());
        return RouterFunctions.resources("/static/**", new FileSystemResource("/app/static/"));
    }
}