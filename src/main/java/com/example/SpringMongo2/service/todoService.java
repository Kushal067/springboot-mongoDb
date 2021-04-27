package com.example.SpringMongo2.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.example.SpringMongo2.exception.TodoCollectionException;
import com.example.SpringMongo2.model.TodoDTO;

public interface todoService {
	
	public void createTodo(TodoDTO todo) throws TodoCollectionException,ConstraintViolationException;
	
	public List<TodoDTO> getAllTodos();
	
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException;
	
	public void updateTodo(String id,TodoDTO todo) throws TodoCollectionException;
	
	public void deleteTodo(String id) throws TodoCollectionException;
	
}
