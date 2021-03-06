package com.london.housing.utils;

import com.london.housing.entity.Coordinate;

/**
 * @author KuznetsovNE/ 21.05.2015.
 */
public class LocationFilter {
    private Coordinate coordinate;
    private String searchString;
    private Long id;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
