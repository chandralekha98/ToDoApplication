package com.example.ToDoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ToDoApp.model.ToDo;
import com.example.ToDoApp.service.ToDoService;


@Controller
public class ToDoController {

	@Autowired
	private ToDoService todoServiceInterface;

	@GetMapping({"/", "viewToDoList"})
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list",todoServiceInterface.getAllToDoList());
		model.addAttribute("message", message);
		return "ViewToDoList";
	}
	@GetMapping("/updateToDoStatus/{id}")
	public String updateToDoStatus(@PathVariable long id, RedirectAttributes redirectAttributes) {
		if(todoServiceInterface.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Sucuess");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewToDoList";
	}
	@GetMapping("/addToDoItem")
	public String getToDoItem(Model model) {
		model.addAttribute("todo", new ToDo());
		return "AddToDoItem";
	}
	@PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		if(todoServiceInterface.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addToDoItem";
	}
	@GetMapping("/editToDoItem/{id}")
	public String editToDoItem(@PathVariable long id, Model model) {
		model.addAttribute("todo", todoServiceInterface.getToDoById(id));
		return "EditToDoItem";
	}
	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		if(todoServiceInterface.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editToDoItem/" + todo.getId();
	}
	
	@GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable long id, RedirectAttributes redirectAttributes) {
		if(todoServiceInterface.deleteToDoItem(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		return "redirect:/viewToDoList";
	}
	
}
