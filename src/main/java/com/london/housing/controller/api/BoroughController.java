package com.london.housing.controller.api;

import com.london.housing.entity.Borough;
import com.london.housing.entity.Coordinate;
import com.london.housing.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KuznetsovNE/ 21.05.2015.
 */
@RestController
@RequestMapping("api/borough")
public class BoroughController {

    @Autowired
    private LocationService locationService;

    @RequestMapping("find")
    public Borough findBorough(@PathVariable Float latitude, @PathVariable Float longitude) {
        return locationService.findBorough(new Coordinate(latitude, longitude));
    }
}
