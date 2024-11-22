package com.ft.jpa;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class StudentRepository {

    private final EntityManager entityManager;

    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

}
