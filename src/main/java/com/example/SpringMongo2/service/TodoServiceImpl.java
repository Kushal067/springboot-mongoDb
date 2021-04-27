package com.example.SpringMongo2.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SpringMongo2.exception.TodoCollectionException;
import com.example.SpringMongo2.model.TodoDTO;
import com.example.SpringMongo2.repository.TodoRepository;

@Service
public class TodoServiceImpl implements todoService {
	
	@Autowired
	private TodoRepository todoRepo;

	@Override
	public void createTodo(TodoDTO todo) throws TodoCollectionException,ConstraintViolationException   {
		Optional<TodoDTO> todoOptional= todoRepo.findByTodo(todo.getTodo());
		if(todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
		}
		else {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todo);
		}
	}

	@Override
	public List<TodoDTO> getAllTodos() {
		List<TodoDTO> todos= todoRepo.findAll();
		if(todos.size()>0) {
			return todos;
		}
		else{
			return new ArrayList<TodoDTO>();
		}
	}

	@Override
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
		Optional<TodoDTO> optionalTodo= todoRepo.findById(id);
		if(!optionalTodo.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		}
		else
		return optionalTodo.get();
	}

	@Override
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException {
		
		Optional<TodoDTO> todoOptional=todoRepo.findById(id);
		Optional<TodoDTO> todoWithSameName= todoRepo.findByTodo(todo.getTodo());
		if(todoOptional.isPresent()){
			if(todoWithSameName.isPresent()) {
				throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
			}
			else {
			TodoDTO todoToUpdate=todoOptional.get();
			todoToUpdate.setCompleted(todo.isCompleted());
			todoToUpdate.setTodo(todo.getTodo()!=null?todo.getTodo():todoToUpdate.getTodo());
			todoToUpdate.setDescription(todo.getDescription()!=null?todo.getDescription():todoToUpdate.getDescription());
			todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
			System.out.println(todoRepo.save(todoToUpdate).getId()); 
			//return new ResponseEntity<>(todoToSave,HttpStatus.OK);
			}
		}
		else {
		//	th new ResponseEntity<>("object not found with id:"+id, HttpStatus.NOT_FOUND);
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		}
	}

	@Override
	public void deleteTodo(String id) throws TodoCollectionException {
		Optional<TodoDTO> todoToDelete=todoRepo.findById(id);
		if(todoToDelete.isPresent()) {
			todoRepo.deleteById(id);
		}
		else
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		
	}
	
	
	
}
