package com.ft.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class StudentRepository {

    private final EntityManager entityManager;

    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    public List<Student> findAllStudent() {
        TypedQuery<Student> allStudent = entityManager.createQuery("FROM Student", Student.class);
        return allStudent.getResultList();
    }

}
