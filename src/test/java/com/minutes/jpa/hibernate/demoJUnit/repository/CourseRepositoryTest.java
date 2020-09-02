package com.minutes.jpa.hibernate.demoJUnit.repository;

import com.minutes.jpa.hibernate.demoJUnit.DemoJUnitApplication;
import com.minutes.jpa.hibernate.demoJUnit.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = DemoJUnitApplication.class)
class CourseRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Test
    public void findById_basic() {
        Course course = repository.findById(1001l);
        logger.debug(course.getName());
        assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        repository.deleteById(1002L);
        assertNull(repository.findById(1002L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        // get a course
        Course course = repository.findById(1001L);
        assertEquals("JPA in 50 Steps", course.getName());

        // update details
        course.setName("JPA in 50 Steps - Updated");
        repository.save(course);

        // check the value
        Course course1 = repository.findById(1001L);
        assertEquals("JPA in 50 Steps - Updated", course1.getName());
    }
}