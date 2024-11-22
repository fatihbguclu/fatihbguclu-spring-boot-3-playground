package com.ft.jpa;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return runner -> {
            createStudents(studentRepository);
            getAllStudents(studentRepository);
        };
    }

    private void createStudents(StudentRepository studentRepository) {
        Student student = new Student(null, "John", "Doe", "john@luv2code.com");
        Student student1 = new Student(null, "Mary", "Public", "mary@luv2code.com");
        Student student2 = new Student(null, "Bonita", "Applebum", "bonita@luv2code.com");
        System.out.println("saving students");
        studentRepository.saveStudent(student);
        studentRepository.saveStudent(student1);
        studentRepository.saveStudent(student2);
    }


    private void getAllStudents(StudentRepository studentRepository) {
        studentRepository.findAllStudent().forEach(student -> System.out.println(student.toString()));
    }

}
