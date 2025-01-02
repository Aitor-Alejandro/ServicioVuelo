package com.curso.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
/**
 * Clase lanzadera del microservicio de Avion
 * @author Aitor Alejandro Martinez Cedillo \ Viewnext
 * @version 1.0 2024-12-31
 */
@EntityScan(basePackages = "com.curso.model")
@EnableJpaRepositories(basePackages = "com.curso.repository")
@SpringBootApplication(scanBasePackages = {"com.curso.controller", "com.curso.service"})
public class ServicioAvionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioAvionApplication.class, args);
	}
	@Bean
	RestTemplate template() {
		return new RestTemplate();
	}
}
