package com.ft.springmvchibernateadvanced.repository;

import com.ft.springmvchibernateadvanced.model.Course;
import com.ft.springmvchibernateadvanced.model.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.ft.springmvchibernateadvanced.model.Instructor;

import java.util.List;

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

    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
        // remove the associated object reference break bidirectional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);
        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        return query.getResultList();
    }

    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " +
                "JOIN FETCH i.courses " + "JOIN FETCH i.instructorDetail " +
                "where i.id = :data", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Transactional
    public void deleteCourseById(int id) {
        Course tempCourse = entityManager.find(Course.class, id);
        entityManager.remove(tempCourse);
    }

    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " + "JOIN FETCH c.reviews " + "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        return query.getSingleResult();
    }
}
