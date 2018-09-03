package com.restfulservice.application.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Lyes SEFIANE
 *
 */
@ApiModel(description="Post Description")
@Entity
public class Post {
	
	@Id
	@GeneratedValue
	@ApiModelProperty(notes="Post Identifier, Generated Value", required=true)
	private Integer id;
	
	@NotNull
	@Size(message="The Size Of Post Should Be 128 characters", max=128)
	@ApiModelProperty(notes="Post Description, Should Be 128 characters", required=true, allowEmptyValue=false)
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
}
