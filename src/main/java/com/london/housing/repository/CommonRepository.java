package com.london.housing.repository;

import com.london.housing.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author smith
 */
@Repository
@Transactional
public abstract class CommonRepository implements Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    protected  <T extends BaseEntity> T save(T entity) {
        Long id = (Long) openSession().save(entity);
        return entity;
    }

}
