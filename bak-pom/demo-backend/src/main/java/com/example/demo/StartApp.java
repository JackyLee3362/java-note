package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.extern.slf4j.Slf4j;

@ServletComponentScan(basePackages = "com.example.demo")
@SpringBootApplication
@Slf4j
public class StartApp {

	public static void main(String[] args) {
		SpringApplication.run(StartApp.class, args);
		log.info("服务启动成功");
	}

}
