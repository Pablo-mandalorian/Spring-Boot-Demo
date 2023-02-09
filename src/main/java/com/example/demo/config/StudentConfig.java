package com.example.demo.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args->{
			Student pablo = new Student(
					"Pablo",
					"pablo@gmail.com",
					LocalDate.of(2000, Month.JANUARY, 22)
					);
			
			Student robin = new Student(
					"Robin",
					"robin@gmail.com",
					LocalDate.of(2000, Month.JANUARY, 22)
					);
			
			studentRepository.saveAll(List.of(pablo, robin));
		};
	}

}
