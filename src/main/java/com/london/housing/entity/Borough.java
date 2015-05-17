package com.london.housing.entity;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author smith
 */
@PersistenceCapable
public class Borough {
    @Persistent
    private String name;

    @PrimaryKey
    private String code;

    @Persistent
    private String mapId;

    @Persistent(mappedBy = "ward")
    private List<Ward> wards;

    @Persistent(mappedBy = "borough", loadFetchGroup = "true")
    private List<Coordinate> coordinates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

    public List<Coordinate> getCoordinates() {
        if (coordinates == null) coordinates = new ArrayList<>();
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
