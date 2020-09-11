package com.minutes.jpa.hibernate.demoJUnit;

import com.minutes.jpa.hibernate.demoJUnit.entity.Course;
import com.minutes.jpa.hibernate.demoJUnit.entity.Review;
import com.minutes.jpa.hibernate.demoJUnit.entity.Student;
import com.minutes.jpa.hibernate.demoJUnit.repository.CourseRepository;
import com.minutes.jpa.hibernate.demoJUnit.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoJUnitApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoJUnitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//		Course course = courseRepository.findById(1001l);
//		logger.info("Course 1001 ->{}",course);
		//courseRepository.save(new Course("Demo App - Microservices in 100 steps"));
        //courseRepository.save(new Course("Demo App - Java in 10 steps"));
        //courseRepository.playWithEntityManager();
        //studentRepository.saveStudentWithPassport();
        //courseRepository.addHardcodedReviewsForCourse();
        List<Review> reviews = new ArrayList<>();

        reviews.add(new Review("5", "Great Hands-on Stuff."));
        reviews.add(new Review("5", "Hatsoff."));

        courseRepository.addReviewsForCourse(10003L, reviews );
        //studentRepository.insertHardcodedStudentAndCourse();
       // studentRepository.insertStudentAndCourse(new Student("Jack"),
                //new Course("Microservices in 100 Steps"));
    }
}
