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
            findStudentsByLastName(studentRepository, "Public");
            updateStudent(studentRepository);
            deleteStudent(studentRepository);
            deleteAllStudents(studentRepository);
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

    private void findStudentsByLastName(StudentRepository studentRepository, String  lastName) {
        List<Student> theStudents = studentRepository.findByLastName(lastName);
        System.out.println("Students whose last names is : " + lastName);
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void updateStudent(StudentRepository studentRepository) {
        System.out.println("Getting student with id: " + 1);
        Student myStudent = studentRepository.findById(1);

        System.out.println("Updating student ...");
        myStudent.setFirstName("John");

        studentRepository.update(myStudent);
        System.out.println("Updated student: " + myStudent);
    }

    private void deleteAllStudents(StudentRepository studentRepository) {
        System.out.println("Deleting all students");
        System.out.println("Deleted row count: " + studentRepository.deleteAll());
    }

    private void deleteStudent(StudentRepository studentRepository) {
        System.out.println("Deleting student id: " + 3);
        studentRepository.delete(3);
    }
}
