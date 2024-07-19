package com.example.ToDoApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ToDoApp.model.ToDo;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long>{

}
