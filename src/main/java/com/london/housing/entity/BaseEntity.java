package com.london.housing.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author KuznetsovNE/ 20.05.2015.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
