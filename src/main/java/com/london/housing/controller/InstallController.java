package com.london.housing.controller;

import com.london.housing.entity.Borough;
import com.london.housing.model.Location;
import com.london.housing.utils.PMF;
import com.london.housing.utils.JsonLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jdo.PersistenceManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author smith
 */
@Controller
@RequestMapping("/install")
public class InstallController {

    @Autowired
    private JsonLoader jsonLoader;

    @RequestMapping("initDB")
    public void initDB() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());
        Resource[] resources = resolver.getResources("/locations/*.json");

        List<Borough> boroughs = new ArrayList<>();

        PersistenceManager pm = PMF.get().getPersistenceManager();

        for (Resource resource : resources) {
            File file = resource.getFile();
            Location location = jsonLoader.loadLondonPolygon(file);
            boroughs.add(location.createBorough());
        }

        try {
            pm.makePersistentAll(boroughs);
        } finally {
            pm.close();
        }


        System.out.println("TEST");
    }


}
