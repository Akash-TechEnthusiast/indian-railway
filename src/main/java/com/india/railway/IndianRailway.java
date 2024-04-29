package com.india.railway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.india.railway.repository")
public class  IndianRailway{
	public static void main(String[] args) {
		
		SpringApplication.run(IndianRailway.class, args);
	}
}
