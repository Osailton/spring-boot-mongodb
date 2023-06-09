package com.springmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmongo.domain.User;
import com.springmongo.dto.UserDTO;
import com.springmongo.respositories.UserRepository;
import com.springmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
	}

	public User insert(User user) {
		return repository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User user) {
		User dbUser = findById(user.getId());
		updateData(dbUser, user);
		return repository.save(dbUser);
	}

	private void updateData(User dbUser, User user) {
		dbUser.setName(user.getName());
		dbUser.setEmail(user.getEmail());
	}

	public User getUserFromDTO(UserDTO dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		return user;
	}

}
