package br.com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@EnableMongoRepositories
@SpringBootApplication
public class MbtcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbtcApplication.class, args);
	}
}
