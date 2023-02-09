package com.example.demo.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;

	StudentService(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
	
	public Iterable<Student> getStudents(){
		return studentRepository.findAll();
				
	}
	
	public void saveStudent(Student student) {
		Optional<Student> optionalStudent = studentRepository.findByEmail(student.getEmail());
		if(optionalStudent.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
		studentRepository.save(student);
	}
	
	public Student getStudentById(Long Id) {
		Optional<Student> optionalStudent = studentRepository.findById(Id);
		
		if(!optionalStudent.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Student foundStudent = optionalStudent.get();
		return foundStudent;
	}
	
	public void deleteStudent(Long id) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		
		if(!optionalStudent.isPresent()) {
			//throw new IllegalStateException("Id does not exist");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		studentRepository.deleteById(id);
	}
	
	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student = studentRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		//Set new Name
		if(name!=null && name.length()>0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		//Set new Email
		if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> optionalStudent = studentRepository.findByEmail(email);
			if(optionalStudent.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			student.setEmail(email);
		}
		
	}

}
