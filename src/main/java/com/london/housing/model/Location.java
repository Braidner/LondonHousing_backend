package com.london.housing.model;

import com.google.gson.annotations.SerializedName;
import com.london.housing.entity.Borough;
import com.london.housing.entity.Coordinate;

import java.util.List;

/**
 * Created by smith / 01.05.2015.
 */
public class Location {

    @SerializedName("type")
    private String type;

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("properties")
    private LocationProperties properties;

    public Borough createBorough() {
        Borough borough = new Borough();
        borough.setName(this.getProperties().getName());
        borough.setCode(this.getProperties().getCode());
        List<List<List<Float>>> coordinates = this.getGeometry().getCoordinates();

        for (List<List<Float>> coordinate : coordinates) {
            for (List<Float> points : coordinate) {
                Coordinate coord = new Coordinate(points.get(1), points.get(0));
                coord.setBorough(borough);
                borough.getCoordinates().add(coord);
            }
        }

        return borough;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public LocationProperties getProperties() {
        return properties;
    }

    public void setProperties(LocationProperties properties) {
        this.properties = properties;
    }
}
