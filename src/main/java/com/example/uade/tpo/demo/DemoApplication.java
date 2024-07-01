package com.example.uade.tpo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.uade.tpo.demo")
public class DemoApplication {
	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
