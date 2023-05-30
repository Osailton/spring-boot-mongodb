package com.springmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springmongo.domain.User;
import com.springmongo.dto.UserDTO;
import com.springmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> list = service.findAll().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		UserDTO user = new UserDTO(service.findById(id));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO dto) {
		User user = service.getUserFromDTO(dto);
		service.insert(user);
		// Build a uri to send it back in the header
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		// "created" responses with a 201 code (for insert)
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO dto) {
		User user = service.getUserFromDTO(dto);
		user.setId(id);
		service.update(user);
		return ResponseEntity.noContent().build();
	}

}
