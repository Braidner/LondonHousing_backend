package com.london.housing.controller;

import com.google.appengine.api.datastore.*;
import com.london.housing.entity.Borough;
import com.london.housing.entity.Coordinate;
import com.london.housing.model.Location;
import com.london.housing.repository.CommonRepository;
import com.london.housing.utils.JsonLoader;
import com.london.housing.utils.PMF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jdo.PersistenceManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author smith
 */
@RestController
@RequestMapping("/install")
public class InstallController {

    @Autowired
    private JsonLoader jsonLoader;

    @Autowired
    private CommonRepository commonRepository;

    @RequestMapping("initDB")
    public @ResponseBody String initDB() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());
        Resource[] resources = resolver.getResources("/locations/*.json");

        for (Resource resource : resources) {
            File file = resource.getFile();
            Location location = jsonLoader.loadLondonPolygon(file);
            Borough borough = location.createBorough();
            commonRepository.saveEntity(borough);
        }

        return "Database initialized";
    }

    @RequestMapping("loadDB")
    public @ResponseBody List<Entity> loadDB() {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        List<Entity> boroughs = commonRepository.findAll(datastoreService, Borough.class.getSimpleName());
        List<Entity> coordinates = commonRepository.findAll(datastoreService, Coordinate.class.getSimpleName());
        return boroughs;
    }

    @RequestMapping("eraseDB")
    public @ResponseBody String eraseDB() {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        commonRepository.deleteAll(datastoreService, Borough.class.getSimpleName());
        commonRepository.deleteAll(datastoreService, Coordinate.class.getSimpleName());
        return "Database clear";
    }

}
