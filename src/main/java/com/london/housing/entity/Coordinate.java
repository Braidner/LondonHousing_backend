package com.london.housing.entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author smith
 */
@PersistenceCapable
public class Coordinate {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private Float latitude;

    @Persistent
    private Float longitude;

    @Persistent
    @JsonIgnore
    private Borough borough;

    public Coordinate() {
    }

    public Coordinate(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Borough getBorough() {
        return borough;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }
}
