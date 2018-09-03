package com.restfulservice.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restfulservice.application.entity.Post;

/**
 * @author Lyes SEFIANE
 *
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

}
