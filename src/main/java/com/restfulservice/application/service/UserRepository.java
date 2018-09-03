/**
 * 
 */
package com.restfulservice.application.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfulservice.application.entity.User;

/**
 * @author Lyes SEFIANE
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
