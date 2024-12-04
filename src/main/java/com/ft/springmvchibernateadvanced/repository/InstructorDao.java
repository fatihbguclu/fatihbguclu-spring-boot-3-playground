package com.ft.springmvchibernateadvanced.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.ft.springmvchibernateadvanced.model.Instructor;

@Repository
@RequiredArgsConstructor
public class InstructorDao {

    private final EntityManager entityManager;

    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Transactional
    public void deleteInstructorById(int id) {
        Instructor tempInstructor = entityManager.find(Instructor.class, id);
        entityManager.remove(tempInstructor);
    }
}
