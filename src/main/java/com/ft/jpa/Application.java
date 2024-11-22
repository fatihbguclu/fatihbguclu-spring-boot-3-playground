package com.ft.jpa;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return runner -> {
            createStudents(studentRepository);
        };
    }

    private void createStudents(StudentRepository studentRepository) {
        System.out.println("Creating 3 student objects ...");
        Student tempStudent1 = new Student(null, "John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student(null, "Mary", "Public", "mary@luv2code.com");
        Student tempStudent3 = new Student(null, "Bonita", "Applebum", "bonita@luv2code.com");
        System.out.println("Saving the students ...");
        studentRepository.saveStudent(tempStudent1);
        studentRepository.saveStudent(tempStudent2);
        studentRepository.saveStudent(tempStudent3);
    }

}
