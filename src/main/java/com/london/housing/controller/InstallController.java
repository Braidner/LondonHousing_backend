package com.london.housing.controller;

import com.google.appengine.api.datastore.Entity;
import com.london.housing.entity.Borough;
import com.london.housing.model.Location;
import com.london.housing.service.BoroughService;
import com.london.housing.utils.JsonLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private BoroughService boroughService;

    @RequestMapping("initDB")
    public @ResponseBody String initDB() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());
        Resource[] resources = resolver.getResources("/locations/*.json");

        List<Borough> boroughs = new ArrayList<>();

        for (Resource resource : resources) {
            File file = resource.getFile();
            Location location = jsonLoader.loadLondonPolygon(file);
            Borough borough = location.createBorough();
            boroughs.add(borough);
        }

        boroughService.saveBoroughs(boroughs);
        System.out.println(boroughs.size());
        return "Database initialized";
    }

    @RequestMapping("loadDB")
    public @ResponseBody List<Entity> loadDB() {

        return null;
    }

    @RequestMapping("eraseDB")
    public @ResponseBody String eraseDB() {

        return "Database clear";
    }

}
