package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
	
	private final StudentService studentService;

	public StudentController(final StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping
	public Iterable<Student> getStudents(){
		return studentService.getStudents();
				
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void createStudent(@RequestBody Student student){
		studentService.saveStudent(student);
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteStudent(@PathVariable("id") Long id) {
		studentService.deleteStudent(id);
	}
	
	@PutMapping(path="/{id}")
	public void updateStudent(@PathVariable("id") Long id, 
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String email) {
		studentService.updateStudent(id, name, email);
	}
	
}
