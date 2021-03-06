package com.restfulservice.application.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restfulservice.application.entity.User;

/**
 * 
 * @author Lyes SEFIANE
 * 
 *         Database Alternative
 *
 */
@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 4;

	static {
		users.add(new User(1, "ice", new Date()));
		users.add(new User(2, "Adam", new Date()));
		users.add(new User(3, "Eve", new Date()));
		users.add(new User(4, "Jack", new Date()));
	}

	/**
	 * Find All Users
	 * 
	 * @return List<User>
	 */
	public List<User> findAll() {
		return users;

	}

	/**
	 * Save User
	 * 
	 * @param user
	 * @return user
	 */
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	/**
	 * Find User
	 * 
	 * @param id : User Identifier
	 * @return user
	 */
	public User findOne(int id) {
		return users.stream()
				.filter(user -> user.getId() == id)
				.findFirst()
				.orElse(null);
	}

	/**
	 * Delete User
	 * 
	 * @param id : User Identifier
	 * @return user
	 */
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
