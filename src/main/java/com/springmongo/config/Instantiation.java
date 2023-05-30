package com.springmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springmongo.domain.User;
import com.springmongo.respositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User u1 = new User(null, "Rogerinho do Ingá", "rogerinho@mail.com");
		User u2 = new User(null, "Maurílio", "maurilio@mail.com");
		User u3 = new User(null, "Julinho", "julinho@mail.com");
		User u4 = new User(null, "Renan", "renan@mail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
	}

}
