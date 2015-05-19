package com.london.housing.repository;

import com.google.appengine.api.datastore.*;
import com.london.housing.entity.Borough;
import com.london.housing.entity.Coordinate;
import com.london.housing.utils.PMF;
import org.springframework.stereotype.Repository;

import javax.jdo.PersistenceManager;
import java.util.List;

/**
 * @author smith
 */
@Repository
public class CommonRepository {

    protected PersistenceManager pm;

    public CommonRepository() {
        pm = PMF.get().getPersistenceManager();
    }

    public void deleteAll(DatastoreService service, Class clazz) {
        Query query = new Query(clazz.getSimpleName());
        List<Entity> entities = service.prepare(query).asList(FetchOptions.Builder.withDefaults());
        for (Entity entity : entities) {
            pm.currentTransaction().begin();
            try {
                System.out.println(entity.getKey());
                Key key = KeyFactory.createKey(clazz.getSimpleName(), entity.getKey().getName());
                Object borough = pm.getObjectById(clazz, key);
                pm.deletePersistent(borough);
                pm.currentTransaction().commit();
            } finally {
                if (pm.currentTransaction().isActive()) {
                    pm.currentTransaction().rollback();
                }
            }
        }
    }

    public List<Entity> findAll(DatastoreService service, String clazzName) {
        Query query = new Query(clazzName);
        return service.prepare(query).asList(FetchOptions.Builder.withDefaults());
    }

    public void saveEntity(Object entity) {
        pm.currentTransaction().begin();
        try {
            pm.makePersistent(entity);
            pm.currentTransaction().commit();
        } finally {
            if (pm.currentTransaction().isActive()) {
                pm.currentTransaction().rollback();
            }
        }
    }

    public void addCoordinateToBorough(Borough borough, Coordinate coordinate) {
        pm.currentTransaction().begin();
        try {
            Borough object = pm.getObjectById(Borough.class, borough.getCode());
            object.getCoordinates().add(coordinate);
            pm.currentTransaction().commit();
        } finally {
            if (pm.currentTransaction().isActive()) {
                pm.currentTransaction().rollback();
            }
        }
    }

    public void addCoordinatesToBorough(Borough borough, List<Coordinate> coordinates) {
        pm.currentTransaction().begin();
        try {
            Borough object = pm.getObjectById(Borough.class, borough.getCode());
            object.getCoordinates().addAll(coordinates);
            pm.currentTransaction().commit();
        } finally {
            if (pm.currentTransaction().isActive()) {
                pm.currentTransaction().rollback();
            }
        }
    }

}
