package com.london.housing.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author smith
 */
@Entity(name = "Borough")
@Table(name = "Borough")
public class Borough extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "map_id")
    private String mapId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "borough")
    private List<Ward> wards;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "borough_id", referencedColumnName = "id")
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
