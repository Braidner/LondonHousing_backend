package com.london.housing.repository;

import com.london.housing.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author smith
 */
@Repository
public abstract class CommonRepository {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntity> T save(T entity) {
        return (T) openSession().save(entity);
    }

}
