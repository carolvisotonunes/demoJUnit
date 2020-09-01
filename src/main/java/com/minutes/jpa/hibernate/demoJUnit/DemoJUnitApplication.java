package com.minutes.jpa.hibernate.demoJUnit;

import com.minutes.jpa.hibernate.demoJUnit.entity.Course;
import com.minutes.jpa.hibernate.demoJUnit.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJUnitApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoJUnitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		Course course = courseRepository.findById(1001l);
		logger.info("Course 1001 -> {}", course);
    }
}
