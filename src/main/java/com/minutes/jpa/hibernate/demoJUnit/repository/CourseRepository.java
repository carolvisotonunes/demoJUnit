package com.minutes.jpa.hibernate.demoJUnit.repository;

import com.minutes.jpa.hibernate.demoJUnit.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


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



}
