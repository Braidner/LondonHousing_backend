package com.london.housing.service;

import com.london.housing.entity.Borough;
import com.london.housing.repository.BoroughRepository;
import com.london.housing.utils.LocationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author KuznetsovNE/ 20.05.2015.
 */
@Service
public class BoroughService implements Serializable {

    @Autowired
    private BoroughRepository boroughRepository;

    public void saveBoroughs(List<Borough> boroughs) {
        for (Borough borough : boroughs) {
            boroughRepository.saveBorough(borough);
            System.out.println("Borough: " + borough.getName() + " - " + borough.getCoordinates().size());
        }
    }


    public Borough findBoroughById(Long id) {
        LocationFilter filter = new LocationFilter();
        filter.setId(id);
        List<Borough> borough = boroughRepository.findBorough(filter);
        return borough.get(0);
    }
}
