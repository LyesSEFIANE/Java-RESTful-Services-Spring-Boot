package com.restfulservice.application.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Descption of User")
@Entity
public class User {
	
	@ApiModelProperty(notes="Identifier Of User", required=true, allowEmptyValue=false)
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	private String name;
	private Date birthDay;
	// User 1..N Posts
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	public User() {
		
	}
	
	public User(Integer id, String name, Date birthDay) {
		super();
		this.id = id;
		this.name = name;
		this.birthDay = birthDay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getName()=" + getName() + ", getBirthDay()=" + getBirthDay() + "]";
	}
}
