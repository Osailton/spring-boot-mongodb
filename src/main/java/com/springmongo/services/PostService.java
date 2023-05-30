package com.springmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmongo.domain.Post;
import com.springmongo.respositories.PostRepository;
import com.springmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public List<Post> findAll() {
		return repository.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
	}

	public List<Post> findByTitle(String text) {
//		return repository.findByTitleContainingIgnoreCase(text);
		return repository.findByTitle(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + (24 * 60 * 60 * 1000) - 1);
		return repository.fullSearch(text, minDate, maxDate);
	}

}
