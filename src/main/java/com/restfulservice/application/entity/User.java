package com.restfulservice.application.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Lyes SEFIANE
 *
 */
@ApiModel(description="User Description")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	@ApiModelProperty(notes="User Identifier, Generated Value", required=true)
	private Integer id;
	
	@NotNull
	@ApiModelProperty(notes="User Name", required=true, allowEmptyValue=false)
	private String name;
	
	@PastOrPresent
	@NotNull
	@ApiModelProperty(notes="User Date Of Birth, Should Be In The Past")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthDay;
	
	@ApiModelProperty(notes="User Posts")
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
