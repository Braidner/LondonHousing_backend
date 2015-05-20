package com.london.housing.repository;

import com.london.housing.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author smith
 */
@Repository
public abstract class CommonRepository implements Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session openSession() {
        return sessionFactory.openSession();
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntity> T save(T entity) {
        Long id = (Long) openSession().save(entity);
        return entity;
    }

}
