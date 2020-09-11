package com.minutes.jpa.hibernate.demoJUnit.repository;

import com.minutes.jpa.hibernate.demoJUnit.entity.Course;
import com.minutes.jpa.hibernate.demoJUnit.entity.Review;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CourseRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em;

    @Test
    public void findById_basic() {
        Course course = repository.findById(1001l);
        logger.debug(course.getName());
        assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @DirtiesContext
    @Ignore
    public void deleteById_basic() {
        repository.deleteById(1002L);
        assertNull(repository.findById(1002L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        // get a course
        Course course = repository.findById(1001L);
        assertEquals("JPA in 50 steps", course.getName());

        // update details
        course.setName("JPA in 50 steps - Updated");
        repository.save(course);

        // check the value
        Course course1 = repository.findById(1001L);
        assertEquals("JPA in 50 steps - Updated", course1.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager() {
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = repository.findById(10001L);
        logger.info("{}",course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview() {
        Review review = em.find(Review.class, 50001L);
        logger.info("{}",review.getCourse());
    }

}