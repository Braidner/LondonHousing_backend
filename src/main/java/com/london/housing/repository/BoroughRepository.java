package com.london.housing.repository;

import com.london.housing.entity.Borough;
import com.london.housing.utils.LocationFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KuznetsovNE/ 20.05.2015.
 */
@Repository
public class BoroughRepository extends CommonRepository {

    public Borough saveBorough(Borough borough) {
        return save(borough);
    }

    @SuppressWarnings("unchecked")
    public List<Borough> findBorough(LocationFilter filter) {
        if (filter == null) return openSession().createQuery("from Borough").list();
        return openSession().createQuery("from Borough where name = :searchString").setProperties(filter).list();
    }
}
