package com.gisa.gisaassociados;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication(scanBasePackages = "com.gisa")
public class GisaAssociadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GisaAssociadosApplication.class, args);
	}

}
