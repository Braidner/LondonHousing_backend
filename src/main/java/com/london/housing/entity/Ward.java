package com.london.housing.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author smith
 */
@Entity
@Table(name = "Ward")
public class Ward extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "borough_id")
    private Borough borough;

    public Borough getBorough() {
        return borough;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }
}
