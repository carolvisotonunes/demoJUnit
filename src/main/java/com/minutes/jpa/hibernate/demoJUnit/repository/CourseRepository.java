package com.minutes.jpa.hibernate.demoJUnit.repository;

import com.minutes.jpa.hibernate.demoJUnit.entity.Course;
import com.minutes.jpa.hibernate.demoJUnit.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Course findById(Long id) {
        Course course = em.find(Course.class, id);
        logger.info("Course -> {}", course);
        return course;
    }


    public Course save(Course course) {

        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }

        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Web Services begginers");
        em.persist(course1);

        Course course2 = new Course("Angular JS in 100 steps");
        em.persist(course2);

        em.flush();

        course1.setName("Web Services begginers- updated");
        course2.setName("Angular JS in 100 steps - updated");

        //refresh- the obj will not be updated
//        em.refresh(course1);
//        em.refresh(course2);

        em.flush();

    }


    public void addHardcodedReviewsForCourse() {
        //get the course 10003
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        //add 2 reviews to it
        Review review1 = new Review("5", "Great Hands-on Stuff.");
        Review review2 = new Review("5", "Hatsoff.");

        //setting the relationship
        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        //save it to the database
        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviews());
        for(Review review:reviews)
        {
            //setting the relationship
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }



}
