package com.london.housing.entity;

import com.google.appengine.api.datastore.Key;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author smith
 */
@Entity
@Table(name = "Ward")
public class Ward extends BaseEntity {

    private Key key;

    @ManyToOne
    private Borough borough;

    public Borough getBorough() {
        return borough;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
