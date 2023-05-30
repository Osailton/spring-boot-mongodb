package com.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springmongo.domain.Post;
import com.springmongo.domain.User;
import com.springmongo.dto.AuthorDTO;
import com.springmongo.dto.CommentDTO;
import com.springmongo.respositories.PostRepository;
import com.springmongo.respositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Rogerinho do Ingá", "rogerinho@mail.com");
		User u2 = new User(null, "Maurílio", "maurilio@mail.com");
		User u3 = new User(null, "Julinho", "julinho@mail.com");
		User u4 = new User(null, "Renan", "renan@mail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		
		Post p1 = new Post(null, sdf.parse("30/05/2023"), "Chama o quadro", "Melhor post!", new AuthorDTO(u1));
		Post p2 = new Post(null, sdf.parse("29/05/2023"), "Salve", "Salve pra galera da Van!", new AuthorDTO(u1));
		
		CommentDTO c1 = new CommentDTO("Concordo", sdf.parse("30/05/2023"), new AuthorDTO(u4));
		CommentDTO c2 = new CommentDTO("Discordo", sdf.parse("31/05/2023"), new AuthorDTO(u2));
		p1.getComments().addAll(Arrays.asList(c1, c2));
		
		CommentDTO c3 = new CommentDTO("Responsa, na maior!", sdf.parse("29/05/2023"), new AuthorDTO(u3));
		p2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		u1.getPosts().addAll(Arrays.asList(p1, p2));
		
		userRepository.save(u1);
	}

}
