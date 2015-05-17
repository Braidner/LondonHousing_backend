package com.london.housing.repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.london.housing.utils.PMF;
import org.springframework.stereotype.Repository;

import javax.jdo.PersistenceManager;
import java.util.List;

/**
 * @author smith
 */
@Repository
public class CommonRepository {

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
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(entity);
        } finally {
            pm.close();
        }
    }

}
