package com.example.SpringMongo2.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringMongo2.exception.TodoCollectionException;
import com.example.SpringMongo2.model.Test;
import com.example.SpringMongo2.model.TodoDTO;
import com.example.SpringMongo2.repository.TodoRepository;
import com.example.SpringMongo2.service.todoService;

@RestController()
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired
	private todoService todoService;
	
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos(){
		List<TodoDTO> todos= todoService.getAllTodos();
		//Test test=new Test("ok ","Tested");
			return new ResponseEntity<>(todos, todos.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
		try {
			//todo.setCreatedAt(new Date(System.currentTimeMillis()));
			//todoRepo.save(todo);
			todoService.createTodo(todo);
			return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
		}
		catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch(TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
//		TodoDTO t1=new TodoDTO();
//		t1.setId(id);
		try {
			return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodoDTO todo){
		Optional<TodoDTO> todoOptional=todoRepo.findById(id);
		try {
			todoService.updateTodo(id, todo);
			return new ResponseEntity<>("object updated succesfull", HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id){
		//Optional<TodoDTO> todoOptional=todoRepo.findById(id);
		
		try {
			todoService.deleteTodo(id);
			return new ResponseEntity<>("todo with id+"+id+" is deleted", HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
