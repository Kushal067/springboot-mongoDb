package com.example.SpringMongo2.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todos")			
public class TodoDTO {
	
	@Id
	private String id;
	
	@NotNull(message = "todo cannot be null")
	private String todo;

	private Test testCheck;
	
	@NotNull(message = "description cannot be null")
	private String description;
	
	@NotNull(message = "completed cannot be null")
	private boolean completed;
	private Date createdAt;
	private Date updatedAt;
	
	public Test getTestCheck() {
		return testCheck;
	}
	public void setTestCheck(Test testCheck) {
		this.testCheck = testCheck;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
