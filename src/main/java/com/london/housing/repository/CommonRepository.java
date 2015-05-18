package com.london.housing.repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
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

    private PersistenceManager pm;

    public CommonRepository() {
        pm = PMF.get().getPersistenceManager();
    }

    public void deleteAll(DatastoreService service, String clazzName) {
        Query query = new Query(clazzName);
        List<Entity> entities = service.prepare(query).asList(FetchOptions.Builder.withDefaults());
        for (Entity entity : entities) {
            service.delete(entity.getKey());
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
        PersistenceManager pm = PMF.get().getPersistenceManager();
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

}
