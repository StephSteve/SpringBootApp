package com.peerlender.profile;

import com.peerlender.profile.domain.model.User;
import com.peerlender.profile.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User(1, "Anne", "Apple", 27, "Software Developer"));
		userRepository.save(new User(2, "Peter", "Orange", 21, "QA"));
		userRepository.save(new User(3, "Mary", "Grape", 35, "AWS Architect"));
	}
}
