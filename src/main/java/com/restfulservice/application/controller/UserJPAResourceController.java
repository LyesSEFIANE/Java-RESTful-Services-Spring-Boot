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
import com.restfulservice.application.repository.PostRepository;
import com.restfulservice.application.repository.UserRepository;
import com.restfulservice.application.service.UserDaoService;

/**
 * 
 * @author Lyes SEFIANE
 *
 */
@RestController
public class UserJPAResourceController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private UserDaoService userDaoService;

	/**
	 * Retrieve All Users 
	 * 
	 * "localhost:8080/users"
	 * 
	 * @return List<Resource<User>>
	 */
	@GetMapping("/users")
	public List<Resource<User>> retrieveAllUsers() {
		List<User> users = userRepository.findAll();
		List<Resource<User>> resources = new ArrayList<>();
		for (User user : users) {
			Resource<User> resource = new Resource<User>(user);
			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass())
					.retrieve(resource.getContent().getId()));
			resource.add(linkTo.withSelfRel());
			resources.add(resource);
		}
		return resources;
	}

	/**
	 * Retrieve User Information 
	 * 
	 * "localhost:8080/users/100"
	 * 
	 * @param id : identifier of user
	 * @return Resource<User>
	 */
	@GetMapping("/users/{id}")
	public Resource<User> retrieve(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("ID : " + id + " not found");
		}
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	/**
	 * Create User 
	 * 
	 * "localhot:8080/users" 
	 * 
	 * Body : 
	 * 
	 * {"Name": "E", "BirthDay":"2018-09-03T18:50:27.178+0000"}
	 * 
	 * @param user
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/users")
	public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Delete User
	 * 
	 * "localhost:8080/users/101"
	 * 
	 * @param id
	 */
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	/**
	 * Retrieve All Posts For Current User ID
	 * 
	 * "localhost:8080/users/100/posts"
	 * 
	 * @param id : User Identifier
	 * @return List<Post>
	 */
	@GetMapping("/users/{id}/posts")
	public List<Post> retieveAllPosts(@PathVariable int id) {
		Resource<User> user = retrieve(id);
		return user.getContent().getPosts();
	}
	
	/**
	 * Create Post For Current User ID
	 * 
	 * "localhost:8080/users/103/posts"
	 * 
	 * Body :
	 * 
	 * {"Description": "Third Post"}
	 * 
	 * @param id   : User Identifier
	 * @param post : User Post
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> CreatePost(@PathVariable int id, @RequestBody Post post) {
		Resource<User> user = retrieve(id);
		post.setUser(user.getContent());
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	
	
	

}
