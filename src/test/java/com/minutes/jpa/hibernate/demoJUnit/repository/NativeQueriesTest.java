package com.minutes.jpa.hibernate.demoJUnit.repository;

import com.minutes.jpa.hibernate.demoJUnit.DemoJUnitApplication;
import com.minutes.jpa.hibernate.demoJUnit.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@SpringBootTest(classes = DemoJUnitApplication.class)
public class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void native_queries_basic() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE  -> {}", resultList);
        //SELECT * FROM COURSE  -> [Course[Web Services in 100 Steps], Course[JPA in 50 Steps - Updated], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]]
    }

    @Test
    public void native_queries_with_parameter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE  where id = ? -> {}", resultList);
        //[Course[JPA in 50 Steps - Updated]]
    }

    @Test
    public void native_queries_with_named_parameter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE  where id = :id -> {}", resultList);
        //[Course[JPA in 50 Steps - Updated]]
    }

    @Test
    @Transactional
    public void native_queries_to_update() {
        Query query = em.createNativeQuery("Update COURSE set last_updated_date=sysdate()");
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("noOfRowsUpdated  -> {}", noOfRowsUpdated);
        //SELECT * FROM COURSE  -> [Course[Web Services in 100 Steps], Course[JPA in 50 Steps - Updated], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]]
    }


}