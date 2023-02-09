package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

	//Or @Query("SELECT s FROM Student s WHERE s.email =?1")
	Optional<Student> findByEmail(String email);
}
