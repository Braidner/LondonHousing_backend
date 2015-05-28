package com.london.housing.service;

import com.london.housing.entity.Borough;
import com.london.housing.entity.Coordinate;
import com.london.housing.repository.BoroughRepository;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KuznetsovNE/ 21.05.2015.
 */
@Service
public class LocationService {

    @Autowired
    private BoroughRepository boroughRepository;

    public Borough findBorough(Coordinate coordinate) {
        List<Borough> boroughs = boroughRepository.findBorough(null);

        for (Borough borough : boroughs) {
            if (isBoroughIn(coordinate, borough.getCoordinates())) {
                return borough;
            }
        }
        return null;
    }

    private boolean isBoroughIn(Coordinate p, List<Coordinate> points) {
        float minX = points.get(0).getLatitude();
        float maxX = points.get(0).getLatitude();
        float minY = points.get(0).getLongitude();
        float maxY = points.get(0).getLongitude();

        for (Coordinate point : points) {
            minX = Math.min(point.getLatitude(), minX);
            maxX = Math.max(point.getLatitude(), maxX);
            minY = Math.min(point.getLongitude(), minY);
            maxY = Math.max(point.getLongitude(), maxY);
        }

        if (p.getLatitude() < minX || p.getLatitude() > maxX || p.getLongitude() < minY || p.getLongitude() > maxY) {
            return false;
        }

        final Coordinate[] polygon = points.toArray(new Coordinate[points.size()]);

        boolean inside = false;
        for (int i = 0, j = polygon.length - 1; i < polygon.length; j = i++) {
            if ((polygon[i].getLongitude() > p.getLongitude()) != (polygon[j].getLongitude() > p.getLongitude()) && p.getLatitude() <
                    (polygon[j].getLatitude() - polygon[i].getLatitude()) * (p.getLongitude() - polygon[i].getLongitude()) / (polygon[j].getLongitude() - polygon[i].getLongitude()) + polygon[i].getLatitude()) {
                inside = !inside;
            }
        }

        return inside;
    }

}
