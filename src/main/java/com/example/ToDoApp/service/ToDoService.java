package com.example.ToDoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDoApp.model.ToDo;
import com.example.ToDoApp.repo.ToDoRepo;

@Service
public class ToDoService {
	@Autowired
	ToDoRepo repo;
	
	public List<ToDo> getAllToDoList(){
		return repo.findAll();
	}
	

	public ToDo getToDoById(long id){
		return repo.findById(id).get();
	}
	
	
	public boolean updateStatus(long id){
		ToDo todo=getToDoById(id);
		todo.setStatus("Completed");
		return saveOrUpdateToDoItem(todo);
	}
	
	
	public boolean saveOrUpdateToDoItem(ToDo todo){
		ToDo checkUpdatedOrNo= repo.save(todo);
		if(getToDoById(checkUpdatedOrNo.getId())!=null)
			return true;
		return false;
	}

	public boolean deleteToDoItem(long id){
		repo.deleteById(id);
		if(repo.findById(id).isEmpty()) {
			return true;
		}
			
		return false;
	}
}
