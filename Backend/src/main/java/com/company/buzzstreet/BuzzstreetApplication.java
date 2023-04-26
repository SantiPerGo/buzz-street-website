package com.company.buzzstreet;

import com.company.buzzstreet.entity.User;
import com.company.buzzstreet.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.util.Arrays;

@SpringBootApplication
public class BuzzstreetApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuzzstreetApplication.class, args);
	}

	@Value("classpath:data/users.json")
	Resource usersJsonFile;

	@Bean
	public CommandLineRunner demo(UserRepository userRepository){
		return (args) -> {
			ObjectMapper mapper = new ObjectMapper();
			userRepository.saveAll(Arrays.asList
					(mapper.readValue(usersJsonFile.getFile(), User[].class)));

			for(User product: userRepository.findAll())
				System.out.println(product.toString());
		};
	}
}
