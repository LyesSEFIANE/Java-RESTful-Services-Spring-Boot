package com.restfulservice.application.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restfulservice.application.entity.Post;
import com.restfulservice.application.entity.User;
import com.restfulservice.application.exception.UserNotFoundException;
import com.restfulservice.application.service.PostRepository;
import com.restfulservice.application.service.UserRepository;

@RestController
public class UserJPAResourceController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/users")
	public List<Resource<User>> retrieveAllUsers() {
		List<User> users = userRepository.findAll();
		List<Resource<User>> resources = new ArrayList<>();
		for(User user : users) {
			Resource<User> resource = new Resource<User>(user);
			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retieveUser(resource.getContent().getId()));
			resource.add(linkTo.withSelfRel());
			resources.add(resource);
		}
		return resources;
	}

	@GetMapping("/users/{id}")
	public Resource<User> retieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("ID : " + id + " Not Found");
		}
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retieveAllPosts(@PathVariable int id) {
		Resource<User> user = retieveUser(id);
		return user.getContent().getPosts();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> CreateUser(@PathVariable int id, @RequestBody Post post) {
		Resource<User> user = retieveUser(id);
		post.setUser(user.getContent());
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	
	
	

}
