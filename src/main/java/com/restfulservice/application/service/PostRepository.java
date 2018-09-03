/**
 * 
 */
package com.restfulservice.application.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restfulservice.application.entity.Post;

/**
 * @author Lyes SEFIANE
 *
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

}
