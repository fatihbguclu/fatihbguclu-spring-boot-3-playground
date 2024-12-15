package com.ft.springmvchibernateadvanced;

import com.ft.springmvchibernateadvanced.model.Course;
import com.ft.springmvchibernateadvanced.model.Instructor;
import com.ft.springmvchibernateadvanced.model.InstructorDetail;
import com.ft.springmvchibernateadvanced.model.Review;
import com.ft.springmvchibernateadvanced.repository.InstructorDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private final InstructorDao instructorDao;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*
    @Override
    public void run(String... args) throws Exception {
        // One To One Example
        createInstructor();
        findInstructor();
        deleteInstructor();
        findInstructorDetail();
        deleteInstructorDetail();
    }*/

    @Override
    public void run(String... args) throws Exception {
        // One To Many Example
        createCourseAndReviews();
        retrieveCourseAndReviews();
        deleteCourseAndReviews();
    }

    private void createInstructor() {
        Instructor tempInstructor = new Instructor();
        tempInstructor.setFirstName("asd qwe");
        tempInstructor.setLastName("qwerty");
        tempInstructor.setEmail("asd@asd");

        InstructorDetail tempInstructorDetail = new InstructorDetail();
        tempInstructorDetail.setYoutubeChannel("http://www.luv2code.com/youtube");
        tempInstructorDetail.setHobby("Guitar");

        tempInstructor.setInstructorDetail(tempInstructorDetail);
        // save the instructor
        // NOTE: this will ALSO save the details object because of CascadeType.ALL
        System.out.println("Saving instructor: " + tempInstructor);
        instructorDao.save(tempInstructor);
    }

    private void findInstructor() {
        System.out.println("Finding instructor id: " + 1);
        Instructor tempInstructor = instructorDao.findInstructorById(1);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

    }

    private void deleteInstructor() {
        System.out.println("Deleting instructor id: " + 1);
        instructorDao.deleteInstructorById(1);
        System.out.println("Done!");
    }

    private void findInstructorDetail() {
        InstructorDetail tempInstructorDetail = instructorDao.findInstructorDetailById(1);
        System.out.println("tempInstructorDetail: " + tempInstructorDetail);
        System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
    }

    private void deleteInstructorDetail() {
        System.out.println("Deleting instructor detail id: " + 1);
        instructorDao.deleteInstructorDetailById(1);
        System.out.println("Done!");
    }

    private void deleteCourseAndReviews() {
        System.out.println("Deleting course id: " + 1);
        instructorDao.deleteCourseById(1);
        System.out.println("Done!");
    }

    private void retrieveCourseAndReviews() {
        Course tempCourse = instructorDao.findCourseAndReviewsByCourseId(1);
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());
    }

    private void createCourseAndReviews() {
        Course tempCourse = new Course(null, "Pacman - How To Score One Million Points", null, null);
        tempCourse.addReview(new Review(null, "Great course ... loved it!"));
        tempCourse.addReview(new Review(null, "Cool course, job well done."));
        tempCourse.addReview(new Review(null, "What a dumb course, you are an idiot!"));
        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());
        instructorDao.save(tempCourse);
        System.out.println("Done!");
    }
}
