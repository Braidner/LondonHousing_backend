package com.london.housing.entity;

import javax.persistence.*;

/**
 * @author smith
 */
@Entity
@Table(name = "Coordinate")
public class Coordinate extends BaseEntity {

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @ManyToOne
    @JoinColumn(name = "borough_id")
    private Borough borough;

    public Coordinate() {
    }

    public Coordinate(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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
