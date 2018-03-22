package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@SpringBootApplication
public class DbCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbCheckApplication.class, args);

	}

	@Bean
	CommandLineRunner init(PersonRepository personRepository) {
		return (evt) -> Arrays
				.asList("jhoeller,dsyersdj,pwebrrrb,ogierkej,rwinchtttt,mfisherggg,mpollack,jlongssss".split(","))
				.forEach(p -> {
					Person person = personRepository.save(new Person(p, p + "@gmail.com", "LA"));
				});

	}
}
